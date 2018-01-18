package com.checc.dao;

import java.util.List;

import com.checc.domain.TopicItemDO;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.service.common.GeneralDAO;

 /**
 * 专题商品 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface TopicItemDAO extends GeneralDAO<TopicItemDO> {
	
	/**
	 * <pre>
	 * 根据商品id更新专题商品冗余字段
	 * </pre>
	 *
	 * @param topicItemDO
	 * @return
	 */
	public int updateItemRedundance(TopicItemDO topicItemDO);
	
	/**
	 * <pre>
	 * 扣减兑换商品剩余数量
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public int reduceExchangeResidue(Long id) throws CommonDAOException;
	
	/**
	 * <pre>
	 * 根据主键id列表获取专题商品信息
	 * </pre>
	 *
	 * @param ids
	 * @return
	 */
	public List<TopicItemDO> selectByIds(List<Long> ids);
	
	/**
	 * <pre>
	 * 根据专题id列表获取专题商品信息
	 * </pre>
	 *
	 * @param topicIds
	 * @return
	 */
	public List<TopicItemDO> selectByTopicIds(List<Long> topicIds);

}
