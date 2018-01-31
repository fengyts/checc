package com.checc.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.service.ExchangeOrderStatusService;
import com.checc.service.PurchaseApplyService;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.PurchaseApplyVO;

import ng.bayue.common.Page;

@Service
public class AuctionManagerAO {

	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private ExchangeOrderStatusService exchangeOrderStatusService;

	public Page<PurchaseApplyVO> queryAuctionPageList(PurchaseExchangeStatusDTO dto, Integer pageNo, Integer pageSize) {
		Page<PurchaseApplyVO> pageResult = purchaseApplyService.queryPageBackend(dto, pageNo, pageSize);
		return pageResult;
	}

	public Page<ExchangeOrderVO> queryExchangePageList(Integer pageNo, Integer pageSize) {
		Page<ExchangeOrderVO> pageResult = null;
		return pageResult;
	}

}
