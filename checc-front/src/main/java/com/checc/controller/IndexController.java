package com.checc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.ao.TopicItemAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.enums.TopicTypeEnum;
import com.checc.vo.front.TopicItemVO;

@Controller
@RequestMapping({"/"})
public class IndexController {
	
	@Autowired
	private TopicItemAO topicItemAO;
	
	@RequestMapping({"/index", "/"})
	public String index(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		CheccUserDO userDO = (CheccUserDO) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if(null != userDO){
			model.addAttribute("checcUser", userDO);
		}
		List<TopicItemVO> auctionList = topicItemAO.listAuction("01", TopicTypeEnum.TOPIC_AUCTION.getCode());
		model.addAttribute("auctionList", auctionList);
		
		if (CollectionUtils.isNotEmpty(auctionList)){
			TopicItemVO vo = auctionList.get(0);
			model.addAttribute("topicStartTime", vo.getStartTime());
			model.addAttribute("topicEndTime", vo.getEndTime());
		}
		
		List<TopicItemVO> exchangeList = topicItemAO.listExchange();
		model.addAttribute("exchangeList", exchangeList);
		
		return "index/index";
	}
	

	@RequestMapping({"/index/helper"})
	public String helperCenter(){
		return "/index/helper_center";
	}

}
