package com.checc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.ao.TopicItemAO;

@Controller
@RequestMapping(value = { "/topicItem" })
public class TopicItemController {

	@Autowired
	private TopicItemAO topicItemAO;

	@RequestMapping(value = "/itemDetails/{detailType}/{tpId}")
	public String topicItemDetails(Model model, @PathVariable String detailType,
			@PathVariable Long tpId) {

		return "/business/topicItemDetail";
	}

}
