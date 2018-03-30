package com.checc.controller;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.AuctionManagerAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.enums.PurchaseStatusEnum;
import com.checc.enums.ShipmentsStatusEnum;
import com.checc.enums.TopicProgressEnum;
import com.checc.util.Messages;
import com.checc.util.ResultMessage;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.PurchaseApplyVO;
import com.checc.vo.front.ExchangeOrderStatusVO;

import ng.bayue.common.Page;
import ng.bayue.util.StringUtils;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author lenovopc
 * @version $Id: AuctionManagerController.java, v 0.1 2018年3月30日 上午9:39:31 lenovopc Exp $
 */
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

	/**
	 * <pre>
	 * 修改购车状态
	 * </pre>
	 *
	 * @param type
	 * @param purchaseId
	 * @param remark
	 * @param purchaseStatus
	 * @return
	 */
	@RequestMapping("/savePurchaseInfo")
	@ResponseBody
	public ResultMessage savePurchaseStatusInfo(String type, Long purchaseId, String remark, String purchaseStatus) {
		if (StringUtils.isBlank(type) || null == purchaseId) {
			return ResultMessage.failure(Messages.ParameterError);
		}

		return auctionManagerAO.savePurchaseInfo(type, purchaseId, remark, purchaseStatus);
	}

	@RequestMapping("/exchangeList")
	public String exchangeList(Model model, PurchaseExchangeStatusDTO dto,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<ExchangeOrderVO> page = auctionManagerAO.queryExchangePageList(dto, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("paramDto", dto);
		model.addAttribute("shipmentsOrderStatus", ShipmentsStatusEnum.values());
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "auctionManager/exchange_list";
	}
	
	/**
	 * <pre>
	 * 更新发货状态
	 * </pre>
	 *
	 * @param type
	 * @param exchangeOrderId
	 * @param remark
	 * @param shipmentsStatus
	 * @return
	 */
//	@RequestMapping("/saveShipmentsInfo")
//	@ResponseBody
//	public ResultMessage saveShipmentsInfo(String type, Long exchangeOrderId, String remark, String shipmentsStatus) {
//		if (StringUtils.isBlank(type) || null == exchangeOrderId) {
//			return ResultMessage.failure(Messages.ParameterError);
//		}
//
//		return auctionManagerAO.saveShipmentsInfo(type, exchangeOrderId, remark, shipmentsStatus);
//	}
	@RequestMapping("/saveShipmentsInfo")
	@ResponseBody
	public ResultMessage saveShipmentsInfo(String type, ExchangeOrderStatusDO eoDO) {

		return auctionManagerAO.saveShipmentsInfo(type, eoDO);
	}
	
	@RequestMapping("/confirmShipments")
	public String confirmShipmentsPage(Model model, Long recordId, String iframeName){
		model.addAttribute("listIframeName", iframeName);
		ExchangeOrderStatusVO vo = auctionManagerAO.selectExchangeOrderDetails(recordId);
		model.addAttribute("vo", vo);
		return BackendConstant.BACKEND_VIEW_PATH + "auctionManager/confirm_shipments";
	}

}
