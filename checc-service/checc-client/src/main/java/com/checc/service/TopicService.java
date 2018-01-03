package com.checc.service;

import java.util.List;
import java.util.Map;

import ng.bayue.service.common.GeneralService;

import com.checc.domain.TopicDO;
import com.checc.enums.TopicStatusEnum;

 /**
 * 专题 Service
 * @author fengyts 2017-11-16 14:54:39
 */
public interface TopicService extends GeneralService<TopicDO, TopicDO> {

	/**
	 * <pre>
	 * 获取所有商品,根据专题起始时间获取
	 * </pre>
	 *
	 * @param topicType 专题类型
	 * @param type:竞拍专题必传参数,01-本期；02-下期预告
	 * @return
	 */
//	@Deprecated
//	List<TopicDO> selectAllDynamic(String type, String topicType);
	
	/**
	 * <pre>
	 * 获取专题数据，只获取未开始或者进行中的
	 * </pre>
	 *
	 * @param topicType
	 * @param status
	 * @return
	 */
	List<TopicDO> selectTopicByProgress(String topicType, TopicStatusEnum status);
	
	/**
	 * <pre>
	 * 获取已经结束但是还未退还西币的专题,现默认获取当天晚上8点前结束的
	 * </pre>
	 *
	 * @return
	 */
	List<TopicDO> selectTopicNotRefund();

}
