package com.checc.controller;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checc.ao.item.TopicItemAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.domain.TopicItemDO;
import com.checc.dto.enums.TopicStatusEnum;
import com.checc.dto.enums.TopicTypeEnum;

import ng.bayue.common.Page;

@Controller
@RequestMapping({"/topicItem"})
public class TopicItemController {
	
	@Autowired
	private TopicItemAO topicItemAO;
	
	
	@RequestMapping({"/list"})
	public String list(Model model, TopicItemDO topicItemDO, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<TopicItemDO> page = topicItemAO.queryPageList(topicItemDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("topicStatus", TopicStatusEnum.values());
		model.addAttribute("topicTypes", TopicTypeEnum.values());

		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "topic/list";
	}

}
