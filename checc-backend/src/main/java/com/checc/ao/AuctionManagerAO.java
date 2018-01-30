package com.checc.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.service.ExchangeOrderStatusService;
import com.checc.service.PurchaseApplyService;
import com.checc.vo.front.ExchangeOrderStatusVO;
import com.checc.vo.front.PurchaseDetailVO;

import ng.bayue.common.Page;

@Service
public class AuctionManagerAO {

	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private ExchangeOrderStatusService exchangeOrderStatusService;

	public Page<PurchaseDetailVO> queryAuctionPageList(Integer pageNo, Integer pageSize) {
		Page<PurchaseDetailVO> pageResult = null;
		return pageResult;
	}

	public Page<ExchangeOrderStatusVO> queryExchangePageList(Integer pageNo, Integer pageSize) {
		Page<ExchangeOrderStatusVO> pageResult = null;
		return pageResult;
	}

}
