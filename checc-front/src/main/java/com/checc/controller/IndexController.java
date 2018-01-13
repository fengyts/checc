package com.checc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checc.ao.TopicItemAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.vo.front.TopicItemVO;

@Controller
@RequestMapping({ "/" })
public class IndexController {

	@Autowired
	private TopicItemAO topicItemAO;

	@RequestMapping({ "/index", "/" })
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		CheccUserDO userDO = (CheccUserDO) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if (null != userDO) {
			model.addAttribute("checcUser", userDO);
		}

		// 获取竞拍商品列表
		List<TopicItemVO> auctionList = topicItemAO.listAuction();
		model.addAttribute("auctionList", auctionList);

		if (CollectionUtils.isNotEmpty(auctionList)) {
			TopicItemVO vo = auctionList.get(0);
			model.addAttribute("topicStartTime", vo.getStartTime());
			model.addAttribute("topicEndTime", vo.getEndTime());
		}

		// 获取4个往期竞拍商品
		List<TopicItemVO> previousList = topicItemAO.listPrevious();
		model.addAttribute("previousList", previousList);
		model.addAttribute("totalPreviousNum", topicItemAO.totalPreviousNum());

		// 获取兑换商品列表
		List<TopicItemVO> exchangeList = topicItemAO.listExchange();
		model.addAttribute("exchangeList", exchangeList);

		return "index/index";
	}

	@RequestMapping({ "/index/helper" })
	public String helperCenter() {
		return "/index/helper_center";
	}

	@RequestMapping({ "/index/previous" })
	public String previouTopic(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		Page<TopicItemVO> page = topicItemAO.queryPrevioucAuctions(pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("totalAuctNum", page.getTotalCount());
		return "/business/topic_previous";
	}

}
