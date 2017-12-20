package com.checc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.AuctionAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.dto.AuctionActionDTO;
import com.checc.vo.front.ItemAuctionVO;

import constants.CommonPathConstant;

/**
 * <pre>
 * 核心竞拍或兑换业务控制层
 * </pre>
 *
 * @author fengyts
 * @version $Id: AuctionActionController.java, v 0.1 2017年12月16日 下午2:29:46
 *          fengyts Exp $
 */
@Controller
@RequestMapping(value = { "/auction" })
public class AuctionActionController {

	@Autowired
	private AuctionAO auctionAO;

	@RequestMapping("/auctionAction/{tpId}")
	public String auctionPage(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable Long tpId) {
		if (null == tpId || tpId < 0l) {
			return CommonPathConstant.PATH_ERROR_UNSAFE_REQ;
		}
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(UserConstants.USER_SESSION_KEY);
		CommonResultMessage crm = auctionAO.auctionDetails(model, tpId, userDO.getId());
		if (CommonResultMessage.Failure == crm.getResult()) {
			return CommonPathConstant.PATH_ERROR_UNSAFE_REQ;
		}
		ItemAuctionVO vo = (ItemAuctionVO) crm.getData();
		model.addAttribute("auctionVO", vo);
		return "/business/auction_action";
	}

	@RequestMapping("/exchangeAction/{tpId}")
	public String exchangePage(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable Long tpId) {
		if (null == tpId || tpId < 0l) {
			return CommonPathConstant.PATH_ERROR_UNSAFE_REQ;
		}
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(UserConstants.USER_SESSION_KEY);
		CommonResultMessage crm = auctionAO.exchangeDetails(model, tpId, userDO.getId());
		if (CommonResultMessage.Failure == crm.getResult()) {
			return CommonPathConstant.PATH_ERROR_UNSAFE_REQ;
		}
		ItemAuctionVO vo = (ItemAuctionVO) crm.getData();
		model.addAttribute("auctionVO", vo);
		return "/business/exchange_action";
	}

	@RequestMapping(value = "/auctionAct", method = { RequestMethod.POST })
	@ResponseBody
	public CommonResultMessage auctAct(HttpServletRequest request, AuctionActionDTO dto) {
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(UserConstants.USER_SESSION_KEY);
		if(null == userDO){
			return new CommonResultMessage(CommonResultCode.SystemError.UN_LOGIN.code, CommonResultCode.SystemError.UN_LOGIN.desc);
		}
		dto.setCheccUserDO(userDO);
		return auctionAO.auctionAct(dto);
	}

	@RequestMapping(value = "/exchangeAct", method = { RequestMethod.POST })
	@ResponseBody
	public CommonResultMessage exchangeAct(HttpServletRequest request, AuctionActionDTO dto) {
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(UserConstants.USER_SESSION_KEY);
		if(null == userDO){
			return new CommonResultMessage(CommonResultCode.SystemError.UN_LOGIN.code, CommonResultCode.SystemError.UN_LOGIN.desc);
		}
		dto.setCheccUserDO(userDO);
		return auctionAO.exchangeAct(dto);
	}

}
