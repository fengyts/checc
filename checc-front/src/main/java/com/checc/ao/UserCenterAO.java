package com.checc.ao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.CheccUserDO;
import com.checc.domain.UserCurrencyDO;
import com.checc.service.AuctionRecordService;
import com.checc.service.TopicItemService;
import com.checc.service.UserCurrencyService;
import com.checc.vo.front.UserCenterVO;

import ng.bayue.util.StringUtils;

@Service
public class UserCenterAO {

	private Logger logger = LoggerFactory.getLogger(UserCenterAO.class);

	@Autowired
	private UserCurrencyService userCurrencyService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private TopicItemService topicItemService;

	public void userCurrencyInfo(Model model, CheccUserDO userDO) {
		Long userId = userDO.getId();
		UserCenterVO vo = new UserCenterVO();
		UserCurrencyDO uc = userCurrencyService.selectById(userId);
		if (null != uc) {
			vo.setTotalCurrency(uc.getTotalCurrency());
			vo.setRefund(uc.getRefund());
			vo.setUserId(userId);
			vo.setMobile(StringUtils.securityMobile(userDO.getMobile()));
		}
		
		AuctionRecordDO ar = new AuctionRecordDO();
		ar.setUserId(userDO.getId());
		List<AuctionRecordDO> list = auctionRecordService.selectDynamic(ar);
		vo.setCurrencyTradeNum(list.size());

		model.addAttribute("userDetailVO", vo);
	}

}
