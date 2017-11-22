package com.checc.ao.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.TopicItemDO;
import com.checc.service.TopicItemService;

import ng.bayue.common.Page;

@Service
public class TopicItemAO {

	@Autowired
	private TopicItemService topicItemService;
	
	public Page<TopicItemDO> queryPageList(TopicItemDO topicItemDO, Integer pageNo, Integer pageSize){
		Page<TopicItemDO> page = topicItemService.queryPageListDynamicAndStartPageSize(topicItemDO, pageNo, pageSize);
		return page;
	}
		
	
}
