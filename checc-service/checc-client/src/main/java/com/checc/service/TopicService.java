package com.checc.service;

import java.util.List;

import ng.bayue.service.common.GeneralService;

import com.checc.domain.TopicDO;

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
	List<TopicDO> selectAllDynamic(String type, String topicType);

}
