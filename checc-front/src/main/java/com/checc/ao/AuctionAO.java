package com.checc.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.domain.UserCurrencyDO;
import com.checc.dto.AuctionActionDTO;
import com.checc.service.AuctionActionService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;
import com.checc.vo.front.ItemAuctionVO;

import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.TokenModel;
import ng.bayue.constants.TokenTypeConstant;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.TokenService;
import ng.bayue.util.StringUtils;
import ng.bayue.util.crypto.AESUtils;

@Service
public class AuctionAO {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private UserCurrencyService userCurrencyService;
	@Autowired
	private AuctionActionService auctionActionService;

	public CommonResultMessage auctionDetails(Model model, Long tpId, Long userId) {
		if (null == tpId || tpId < 0l) {
			return CommonResultMessage.failure(CommonMessages.ReqException);
		}
		TopicItemDO itemDO = topicItemService.selectById(tpId);
		if (null == itemDO) {
			return CommonResultMessage.failure(CommonMessages.ReqException);
		}
		Long topicId = itemDO.getTopicId();
		TopicDO topicDO = topicService.selectById(topicId);

		ItemAuctionVO vo = new ItemAuctionVO();
		vo.setId(itemDO.getId());
		vo.setAuctionCurrency(itemDO.getAuctionCurrency());
		vo.setAuctionMaxTimes(itemDO.getAuctionMaxTimes());
		vo.setExchangeAmount(itemDO.getExchangeAmount());
		vo.setItemTitle(itemDO.getItemTitle());
		vo.setStatus(AuctionCommonAO.getTopicStatus(topicDO.getStartTime(), topicDO.getEndTime()));
		vo.setExchangeLimitNum(itemDO.getExchangeLimitNum());
		
		int userUseable = 0;
		UserCurrencyDO currencyDO = userCurrencyService.selectById(userId);
		if (null != currencyDO) {
			userUseable = currencyDO.getTotalCurrency() - currencyDO.getFreeze();
		}
		vo.setUseableCurrency(userUseable);

		// 创建token
		TokenModel tokenModel = new TokenModel(userId.toString(),
				TokenTypeConstant.BusinessTokenTypeEnum.AUCTION_AUCTION.getCodeType());
		tokenService.create(tokenModel);
		AESUtils aes = new AESUtils();
		model.addAttribute("auctact_tk_key", aes.encrypt(tokenModel.getToken()));

		return new CommonResultMessage(vo);
	}

	public CommonResultMessage exchangeDetails(Model model, Long tpId, Long userId) {
		if (null == tpId || tpId < 0l) {
			return CommonResultMessage.failure(CommonMessages.ReqException);
		}
		TopicItemDO itemDO = topicItemService.selectById(tpId);
		if (null == itemDO) {
			return CommonResultMessage.failure(CommonMessages.ReqException);
		}
		Long topicId = itemDO.getTopicId();
		TopicDO topicDO = topicService.selectById(topicId);

		ItemAuctionVO vo = new ItemAuctionVO();
		vo.setId(itemDO.getId());
		vo.setAuctionCurrency(itemDO.getAuctionCurrency());
		vo.setAuctionMaxTimes(itemDO.getAuctionMaxTimes());
		vo.setExchangeAmount(itemDO.getExchangeAmount());
		vo.setItemTitle(itemDO.getItemTitle());
		vo.setStatus(AuctionCommonAO.getTopicStatus(topicDO.getStartTime(), topicDO.getEndTime()));
		vo.setExchangeLimitNum(itemDO.getExchangeLimitNum());
		
		int userUseable = 0;
		UserCurrencyDO currencyDO = userCurrencyService.selectById(userId);
		if (null != currencyDO) {
			userUseable = currencyDO.getRefund();
		}
		vo.setUseableCurrency(userUseable);

		// 创建token
		TokenModel tokenModel = new TokenModel(userId.toString(),
				TokenTypeConstant.BusinessTokenTypeEnum.AUCTION_EXCHANGE.getCodeType());
		tokenService.create(tokenModel);
		AESUtils aes = new AESUtils();
		model.addAttribute("auctact_tk_key", aes.encrypt(tokenModel.getToken()));

		return new CommonResultMessage(vo);
	}

	public CommonResultMessage auctionAct(AuctionActionDTO dto) {
		Long tpId = dto.getTpId();
		Integer auctionTimes = dto.getAuctionTimes();
		Integer totalCurrency = dto.getTotalCurrency();
		if (null == tpId || null == auctionTimes || null == totalCurrency || tpId < 0 || auctionTimes < 1
				|| totalCurrency < 1) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}
		String auctionTk = dto.getAuctactTK();
		CommonResultMessage crm = validateTk(auctionTk,
				TokenTypeConstant.BusinessTokenTypeEnum.AUCTION_AUCTION.getCodeType(), dto.getCheccUserDO().getId());
		if (CommonResultMessage.Success != crm.getResult()) {
			return crm;
		}

		crm = auctionActionService.auctionAction(dto);
		return crm;
	}

	public CommonResultMessage exchangeAct(AuctionActionDTO dto) {
		Long tpId = dto.getTpId();
		Integer totalCurrency = dto.getTotalCurrency();
		if (null == tpId || null == totalCurrency || tpId < 0 || totalCurrency < 1) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}
		String auctionTk = dto.getAuctactTK();
		CommonResultMessage crm = validateTk(auctionTk,
				TokenTypeConstant.BusinessTokenTypeEnum.AUCTION_EXCHANGE.getCodeType(), dto.getCheccUserDO().getId());
		if (CommonResultMessage.Success != crm.getResult()) {
			return crm;
		}
		
		try {
			crm = auctionActionService.exchangeAction(dto);
		} catch (Exception e) {
			crm = CommonResultMessage.failure(e.getMessage());
		}
		return crm;
	}

	private CommonResultMessage validateTk(String auctionTk, String tkType, Long userId) {
		if (StringUtils.isBlank(auctionTk)) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}

		// 校验token
		AESUtils aes = new AESUtils();
		auctionTk = aes.decrypt(auctionTk);
		TokenModel tokenModel = new TokenModel(userId.toString(), tkType);
		tokenModel.setToken(auctionTk);
		RedisModelStatusEnum tokenStatus = tokenService.check(tokenModel);
		if (RedisModelStatusEnum.CORRECT != tokenStatus) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}

		return CommonResultMessage.success();
	}

	/**
	 * <pre>
	 * 获取用户可用西币
	 * </pre>
	 *
	 * @param userId
	 * @return
	 */
//	private int getUseableCurrency(Long userId) {
//		if (null == userId) {
//			return 0;
//		}
//		UserCurrencyDO currencyDO = userCurrencyService.selectById(userId);
//		if (null == currencyDO) {
//			return 0;
//		}
//		int userUseable = currencyDO.getTotalCurrency() - currencyDO.getFreeze();
//
//		return userUseable;
//
//	}

}
