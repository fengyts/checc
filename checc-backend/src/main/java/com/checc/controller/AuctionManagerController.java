package com.checc.controller;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checc.ao.AuctionManagerAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.enums.PurchaseStatusEnum;
import com.checc.enums.TopicProgressEnum;
import com.checc.vo.PurchaseApplyVO;

import ng.bayue.common.Page;

@Controller
@RequestMapping("/auctionManager")
public class AuctionManagerController {

	@Autowired
	private AuctionManagerAO auctionManagerAO;

	@RequestMapping("/auctionList")
	public String auctionList(Model model, PurchaseExchangeStatusDTO dto,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<PurchaseApplyVO> page = auctionManagerAO.queryAuctionPageList(dto, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("paramDto", dto);
		model.addAttribute("topicProgress", TopicProgressEnum.values());
		model.addAttribute("purchaseStatus", PurchaseStatusEnum.values());
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "auctionManager/auction_list";
	}

	@RequestMapping("/exchangeList")
	public String exchangeList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<?> page = null;
		model.addAttribute("page", page);
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "auctionManager/exchange_list";
	}

}
