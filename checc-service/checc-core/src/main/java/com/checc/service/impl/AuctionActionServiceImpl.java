package com.checc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.CheccUserDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.domain.UserCurrencyDO;
import com.checc.dto.AuctionActionDTO;
import com.checc.enums.AuctionRecordTypeEnum;
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
	private static final String LOCK_AUCTION_KEY = "AUCTION_ACT_";
	/** 兑换用户锁 */
	private static final String LOCK_EXCHANGE_KEY = "EXCHANGE_ACT_";

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
	@Transactional
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
			if (plusTotalCur != totalCurrency) { // 校验前端输入和后台是否匹配
				return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
						CommonResultCode.SystemError.REQ_ERROR.desc);
			}

			Long topicId = topicItemDO.getTopicId();
			TopicDO topicDO = topicService.selectById(topicId);
			// 校验专题状态
			String topicStatus = getTopicStatus(topicDO.getStartTime(), topicDO.getEndTime());
			if (!TopicStatusEnum.InProgress.getCode().equals(topicStatus)) {
				return new CommonResultMessage(
						CommonResultCode.BusinessError.PROMOTION_HAS_END.code,
						CommonResultCode.BusinessError.PROMOTION_HAS_END.desc);
			}

			// 校验用户西币是否足够
			UserCurrencyDO userCurrencyDO = userCurrencyService.selectByUserId(userDO.getId());
			if (null == userCurrencyDO) {
				return new CommonResultMessage(
						CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
						CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
			}
			int userUseable = userCurrencyDO.getTotalCurrency() - userCurrencyDO.getFreeze();
			if (plusTotalCur > userUseable) {
				return new CommonResultMessage(
						CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
						CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
			}

			AuctionRecordDO auctRecord = new AuctionRecordDO();
			auctRecord.setRecordType(AuctionRecordTypeEnum.AUCTION.code);
			auctRecord.setTopicItemId(tpId);
			auctRecord.setCreateTime(new Date());
			auctRecord.setBidNum(auctionTimes);
			auctRecord.setTotalCurrency(totalCurrency);

			auctRecord.setUserId(userDO.getId());
			auctRecord.setMobile(userDO.getMobile());
			auctRecord.setNickname(userDO.getNickname());

			// 扣减用户西币
			// 锁用户，每个用户同一时间只能请求一次
			long res = 0l;
			if (redisCacheService.lock(auctKeyLock)) {
				res = userCurrencyService.freezeCurrency(userId, plusTotalCur);
				if (res <= 0l) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚事务
					return new CommonResultMessage(
							CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.code,
							CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
				}

				// 获取最新出价
				AuctionRecordDO latest = auctionRecordService.selectLatestAuction(tpId);
				int currentAuctPrice = plusTotalCur;
				if (null != latest) {
					currentAuctPrice += latest.getCurrentAuctPrice();
				} 
				/*else { // 不是最新出价，即第一个人出价，需要加上底价
					currentAuctPrice += topicItemDO.getFloorPrice();
				}*/
				auctRecord.setCurrentAuctPrice(currentAuctPrice);
				res = auctionRecordService.insert(auctRecord);
				if (res <= 0l) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚事务
					return new CommonResultMessage(
							CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.code,
							CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
				}
			} else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚事务
				return new CommonResultMessage(CommonResultCode.BusinessError.ONCE_EVERY_TIME.code,
						CommonResultCode.BusinessError.ONCE_EVERY_TIME.desc);
			}

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动回滚事务
			return new CommonResultMessage(
					CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.code,
					CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
		} finally {
			try {
				redisCacheService.unLock(auctKeyLock);
			} catch (Exception e) {
				logger.error("{redis}释放用户竞拍锁异常,{redis}release user auction lock exception：{}", e);
			}
		}

		return CommonResultMessage.success();
	}

	@Override
	@Transactional
	public CommonResultMessage exchangeAction(AuctionActionDTO dto) throws CommonServiceException {
		CheccUserDO userDO = dto.getCheccUserDO();
		Long userId = userDO.getId();
		String auctKeyLock = LOCK_EXCHANGE_KEY + userId;
		try {
			// 锁用户，每个用户同一时间只能请求一次
			if (redisCacheService.lock(auctKeyLock)) {
				Long tpId = dto.getTpId();

				// 校验专题状态 和 兑换商品库存
				// synchronized (this) {
				TopicItemDO topicItemDO = topicItemService.selectById(tpId);
				if (null == topicItemDO) {
					return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
							CommonResultCode.SystemError.REQ_ERROR.desc);
				}

				Long topicId = topicItemDO.getTopicId();
				TopicDO topicDO = topicService.selectById(topicId);
				// 校验专题状态
				String topicStatus = getTopicStatus(topicDO.getStartTime(), topicDO.getEndTime());
				if (!TopicStatusEnum.InProgress.getCode().equals(topicStatus)) {
					return new CommonResultMessage(
							CommonResultCode.BusinessError.PROMOTION_HAS_END.code,
							CommonResultCode.BusinessError.PROMOTION_HAS_END.desc);
				}

				// // 校验兑换库存
				// int residue = topicItemDO.getResidue();
				// if (residue <= 0) {
				// return new
				// CommonResultMessage(CommonResultCode.BusinessError.EXCHANGE_COUNT_NOT_ENOUGH.code,
				// CommonResultCode.BusinessError.EXCHANGE_COUNT_NOT_ENOUGH.desc);
				// }

				// }

				// 校验用户西币是否足够
				UserCurrencyDO userCurrencyDO = userCurrencyService.selectByUserId(userDO.getId());
				if (null == userCurrencyDO) {
					return new CommonResultMessage(
							CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
							CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
				}

				int totalCurrency = dto.getTotalCurrency(); // 兑换金额
				int userUseable = userCurrencyDO.getRefund(); // 兑换商品仅能使用退回的西币兑换
				if (totalCurrency > userUseable) {
					return new CommonResultMessage(
							CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
							CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
				}

				AuctionRecordDO auctRecord = new AuctionRecordDO();
				auctRecord.setRecordType(AuctionRecordTypeEnum.EXCHANGE.code);
				auctRecord.setTopicItemId(tpId);
				auctRecord.setCreateTime(new Date());
				auctRecord.setTotalCurrency(totalCurrency);

				auctRecord.setUserId(userDO.getId());
				auctRecord.setMobile(userDO.getMobile());
				auctRecord.setNickname(userDO.getNickname());

				auctRecord.setExchangeCount(1); // 默认仅能兑换1次

				// 校验用户是否已经兑换
				if (auctionRecordService.isExchanged(userId, tpId)) {
					return new CommonResultMessage(
							CommonResultCode.BusinessError.HAS_EXCHANGED.code,
							CommonResultCode.BusinessError.HAS_EXCHANGED.desc);
				}

				long res = 0l;
				// 扣减兑换商品剩余数量
				res = topicItemService.reduceExchangeResidue(tpId);
				if (res <= 0l) { // 扣减剩余数量失败,数量不足
					throw new CommonServiceException(
							CommonResultCode.BusinessError.EXCHANGE_COUNT_NOT_ENOUGH.desc);
					// // 手动回滚事务
					// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					// return new
					// CommonResultMessage(CommonResultCode.BusinessError.EXCHANGE_COUNT_NOT_ENOUGH.code,
					// CommonResultCode.BusinessError.EXCHANGE_COUNT_NOT_ENOUGH.desc);
				}
				// 扣减用户西币
				res = userCurrencyService.reduceExchangeCurrency(userId, totalCurrency);
				if (res > 0l) { // 扣减用户西币成功
					res = auctionRecordService.insert(auctRecord);
					if (res <= 0l) {
						// // 手动回滚事务
						// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						// return new
						// CommonResultMessage(CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.code,
						// CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
						throw new CommonServiceException(
								CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
					}
				} else { // 返回西币不足
					throw new CommonServiceException(
							CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
					// // 手动回滚事务
					// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					// return new
					// CommonResultMessage(CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.code,
					// CommonResultCode.BusinessError.USEABLE_CURRENCY_NOT_ENOUGH.desc);
				}
			} else {
				return new CommonResultMessage(CommonResultCode.BusinessError.ONCE_EVERY_TIME.code,
						CommonResultCode.BusinessError.ONCE_EVERY_TIME.desc);
			}

		} catch (CommonServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new CommonServiceException(
					CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
			// // 手动回滚事务
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// return new
			// CommonResultMessage(CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.code,
			// CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
		} finally {
			try {
				redisCacheService.unLock(auctKeyLock);
			} catch (Exception e) {
				logger.error("{redis}释放用户兑换锁异常,{redis}release user exchange lock exception：{}", e);
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
