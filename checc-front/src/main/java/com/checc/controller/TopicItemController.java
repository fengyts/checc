package com.checc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.ao.TopicItemAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.vo.front.TopicItemDetailVO;

import constants.CommonPathConstant;
import ng.bayue.util.StringUtils;

@Controller
@RequestMapping(value = { "/topicItem" })
public class TopicItemController {

	@Autowired
	private TopicItemAO topicItemAO;

	// @InitBinder
	// public void binder(WebDataBinder binder){
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	// }

	/**
	 *
	 * @param model
	 * @param detailType
	 * @param tpId
	 *            topicItem 主键id
	 * @return
	 */
	@RequestMapping(value = "/itemDetails/{detailType}/{tpId}")
	public String topicItemDetails(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable String detailType, @PathVariable Long tpId, Long reqTime) {
		if (StringUtils.isBlank(detailType) || tpId == null) {
			return CommonPathConstant.PATH_ERROR_404;
		}
		
		HttpSession session = request.getSession();
		CheccUserDO userDO = (CheccUserDO) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if(null != userDO){
			model.addAttribute("checcUser", userDO);
		}

		TopicItemDetailVO detailVO = topicItemAO.topicItemDetails(tpId);
		model.addAttribute("detailVO", detailVO);
		if (null != reqTime && reqTime > 0l) {
			model.addAttribute("reqTime", reqTime);
		}
		long endTime = detailVO.getEndTime().getTime();
		long countDownTime = endTime - System.currentTimeMillis();
		model.addAttribute("countDownTime", countDownTime);

		return "/business/topic_item_detail";
	}

	@Deprecated
	@RequestMapping(value = "/auctionAction/{auctionType}/{tpId}")
	public String auctionActionPage(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable String auctionType, @PathVariable Long tpId) {
		if (StringUtils.isBlank(auctionType) || tpId == null) {
			return CommonPathConstant.PATH_ERROR_404;
		}
		return "/business/auction_action";
	}

}
