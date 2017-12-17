package com.checc.ao;

import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.TokenModel;
import ng.bayue.constants.TokenTypeConstant;
import ng.bayue.service.TokenService;
import ng.bayue.util.crypto.AESUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.vo.front.ItemAuctionVO;

@Service
public class AuctionAO {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private TopicService topicService;

	public CommonResultMessage auctionDetails(Model model, Long tpId) {
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
		
		vo.setUseableCurrency(1000);

		// 创建token
		TokenModel tokenModel = new TokenModel();
		tokenModel.setTokenType(TokenTypeConstant.BusinessTokenTypeEnum.AUCTION_AUCTION
				.getCodeType());
		tokenService.create(tokenModel);
		String keyOrigin = tokenModel.getKey();
		AESUtils aes = new AESUtils();
		model.addAttribute("auctact_tk_key", aes.encrypt(keyOrigin));
		
		return new CommonResultMessage(vo);
	}
	
	public CommonResultMessage exchangeDetails(Model model, Long tpId) {
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
		
		vo.setUseableCurrency(1000);

		// 创建token
		TokenModel tokenModel = new TokenModel();
		tokenModel.setTokenType(TokenTypeConstant.BusinessTokenTypeEnum.AUCTION_EXCHANGE
				.getCodeType());
		tokenService.create(tokenModel);
		String keyOrigin = tokenModel.getKey();
		AESUtils aes = new AESUtils();
		model.addAttribute("auctact_tk_key", aes.encrypt(keyOrigin));
		
		return new CommonResultMessage(vo);
	}

}
