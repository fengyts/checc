package com.checc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.CheccUserDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.domain.UserCurrencyDO;
import com.checc.dto.AuctionActionDTO;
import com.checc.enums.TopicStatusEnum;
import com.checc.service.AuctionActionService;
import com.checc.service.AuctionRecordService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;

import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.RedisCacheService;

@Service
public class AuctionActionServiceImpl implements AuctionActionService {
	
	private static Logger logger = LoggerFactory.getLogger(AuctionActionServiceImpl.class);

	/** 竞拍用户锁 */
	private static String LOCK_AUCTION_KEY = "AUCTION_";
	/** 兑换用户锁 */
	private static String LOCK_EXCHANGE_KEY = "EXCHANGE_";

	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;

	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private UserCurrencyService userCurrencyService;

	@Override
	public CommonResultMessage auctionAction(AuctionActionDTO dto) throws CommonServiceException {
		CheccUserDO userDO = dto.getCheccUserDO();
		Long userId = userDO.getId();
		String auctKeyLock = LOCK_AUCTION_KEY + userId;
		try {
			Long tpId = dto.getTpId();
			TopicItemDO topicItemDO = topicItemService.selectById(tpId);
			if (null == topicItemDO) {
				return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
						CommonResultCode.SystemError.REQ_ERROR.desc);
			}
			// 校验竞拍次数和总西比值
			Integer auctionTimes = dto.getAuctionTimes();
			int dbAuctionMaxTimes = topicItemDO.getAuctionMaxTimes();
			if (dbAuctionMaxTimes < auctionTimes) {
				return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
						CommonResultCode.SystemError.REQ_ERROR.desc);
			}
			Integer totalCurrency = dto.getTotalCurrency();
			int plusTotalCur = auctionTimes * topicItemDO.getAuctionCurrency();
			if (plusTotalCur != totalCurrency) {
				return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
						CommonResultCode.SystemError.REQ_ERROR.desc);
			}

			Long topicId = topicItemDO.getTopicId();
			TopicDO topicDO = topicService.selectById(topicId);
			// 校验专题状态
			String topicStatus = getTopicStatus(topicDO.getStartTime(), topicDO.getEndTime());
			if (!TopicStatusEnum.InProgress.getCode().equals(topicStatus)) {
				return new CommonResultMessage(CommonResultCode.BusinessError.PROMOTION_HAS_END.code,
						CommonResultCode.BusinessError.PROMOTION_HAS_END.desc);
			}

			// 校验用户西币是否足够
			UserCurrencyDO userCurrencyDO = userCurrencyService.selectById(userDO.getId());
			if(null == userCurrencyDO){
				return new CommonResultMessage(CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
						CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
			}
			int userUseable = userCurrencyDO.getTotalCurrency() - userCurrencyDO.getFreeze();
			if (plusTotalCur > userUseable) {
				return new CommonResultMessage(CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
						CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
			}

			AuctionRecordDO auctRecord = new AuctionRecordDO();
			auctRecord.setTopicItemId(tpId);
			auctRecord.setBidTime(new Date());
			auctRecord.setBidNum(auctionTimes);
			auctRecord.setTotalCurrency(totalCurrency);

			auctRecord.setUserId(userDO.getId());
			auctRecord.setMobile(userDO.getMobile());
			auctRecord.setNickname(userDO.getNickname());

			// 扣减用户西币
			if (redisCacheService.lock(auctKeyLock)) {
				// 锁用户，每个用户同一时间只能请求一次
				userCurrencyService.freezeCurrency(userId, plusTotalCur);
				auctionRecordService.insert(auctRecord);
			} else {
				return new CommonResultMessage(CommonResultCode.BusinessError.ONCE_EVERY_TIME.code,
						CommonResultCode.BusinessError.ONCE_EVERY_TIME.desc);
			}

		} catch (Exception e) {
			return new CommonResultMessage(CommonResultCode.SystemError.BUSINESS_PROCESS_ERROR.code,
					CommonResultCode.SystemError.BUSINESS_PROCESS_ERROR.desc);
		} finally {
			try {
				redisCacheService.unLock(auctKeyLock);
			} catch (Exception e) {
				logger.error("", e);
			}
		}

		return CommonResultMessage.success();
	}

	private String getTopicStatus(Date startTime, Date endTime) {
		if (null == startTime || null == endTime) {
			return TopicStatusEnum.End.getCode();
		}
		Date now = new Date();
		if (startTime.after(now)) {
			return TopicStatusEnum.NotStarted.getCode();
		}
		if (endTime.before(now)) {
			return TopicStatusEnum.End.getCode();
		}
		return TopicStatusEnum.InProgress.getCode();
	}

}
