package com.checc.service;

import java.util.List;

import com.checc.domain.TopicDO;
import com.checc.enums.TopicStatusEnum;
import com.checc.vo.front.TopicItemVO;

import ng.bayue.common.Page;
import ng.bayue.service.common.GeneralService;

 /**
 * 专题 Service
 * @author fengyts 2017-11-16 14:54:39
 */
public interface TopicService extends GeneralService<TopicDO, TopicDO> {

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
	
	/**
	 * <pre>
	 * 获取往期最近一期的专题
	 * </pre>
	 *
	 * @param topicType
	 * @return
	 */
	TopicDO selectPreviousOne(String topicType);
	
	/**
	 * <pre>
	 * 获取当前最新一期的专题
	 * </pre>
	 *
	 * @param topicType
	 * @return
	 */
	TopicDO selectLatest(String topicType);
	
	/**
	 * <pre>
	 * 获取往期竞拍商品
	 * </pre>
	 *
	 * @return
	 */
	Page<TopicItemVO> queryPreviousAuctions(Integer pageNo, Integer pageSize);
	
	/**
	 * <pre>
	 * 获取往期竞拍成功数量(成交价>=底价的才算成功,否则算流拍)
	 * </pre>
	 *
	 * @return
	 */
	Long totalPreviousNum();

}
