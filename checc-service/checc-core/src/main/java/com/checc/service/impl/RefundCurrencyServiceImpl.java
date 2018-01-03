package com.checc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.service.RefundCurrencyService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;

@Service
public class RefundCurrencyServiceImpl implements RefundCurrencyService {

	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicItemService topicItemService;

	@Override
	@Transactional
	public int refundCurrency() throws Exception {
		List<TopicDO> listTopics = topicService.selectTopicNotRefund();
		if (CollectionUtils.isEmpty(listTopics)) {
			return 0;
		}
		List<Long> topicIds = new ArrayList<Long>();
		for (TopicDO topic : listTopics) {
			topicIds.add(topic.getId());
		}
		List<TopicItemDO> tpItems = topicItemService.selectByTopicIds(topicIds);

		List<Long> tpIds = new ArrayList<Long>();

		for (TopicItemDO ti : tpItems) {
			tpIds.add(ti.getId());
		}

		return 0;
	}

}
