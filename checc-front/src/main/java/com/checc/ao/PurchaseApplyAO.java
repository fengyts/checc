package com.checc.ao;

import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.AuctionRecordDO;
import com.checc.service.AuctionRecordService;
import com.checc.service.PurchaseApplyService;

@Service
public class PurchaseApplyAO {
	
	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	
	public CommonResultMessage purchaseApply(Long userId, Long tiId){
		
		return purchaseApplyService.successAuctPurchaseApply(userId, tiId);
	}

}
