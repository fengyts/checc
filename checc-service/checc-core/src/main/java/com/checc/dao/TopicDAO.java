package com.checc.dao;

import java.util.List;
import java.util.Map;

import ng.bayue.service.common.GeneralDAO;

import com.checc.domain.TopicDO;
import com.checc.vo.front.TopicItemVO;

 /**
 * 专题 DAO
 *
 * @author fengyts 2017-11-16 14:54:39
 */
public interface TopicDAO extends GeneralDAO<TopicDO> {
	
	List<TopicDO> selectAllDynamic(Map<String, Object> params);
	
	/**
	 * <pre>
	 * 根据专题进度查询专题数据
	 * </pre>
	 *
	 * @param params
	 * @return
	 */
	List<TopicDO> selectTopicByProgress(Map<String, Object> params);
	
	/**
	 * <pre>
	 * 获取已经结束但是还未退还西币的专题
	 * </pre>
	 *
	 * @return
	 */
	List<TopicDO> selectTopicNotRefund(Map<String, Object> params);
	
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
	 * 获取往期竞拍商品数量
	 * </pre>
	 *
	 * @param params
	 * @return
	 */
	Long countPreviousAuctions(Map<String, Object> params);
	
	/**
	 * <pre>
	 * 获取往期竞拍商品列表
	 * </pre>
	 *
	 * @param params
	 * @return
	 */
	List<TopicItemVO> queryPreviousAuctions(Map<String, Object> params);
	
	/**
	 * <pre>
	 * 获取往期竞拍成功数量(成交价>=底价的才算成功,否则算流拍)
	 * </pre>
	 *
	 * @return
	 */
	Long totalPreviousNum();
	
}
