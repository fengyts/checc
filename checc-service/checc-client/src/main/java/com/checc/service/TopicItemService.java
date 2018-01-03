package com.checc.service;

import java.util.List;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.TopicItemDO;

/**
 * 专题商品 Service
 * 
 * @author fengyts 2017-11-16 14:54:40
 */
public interface TopicItemService extends GeneralService<TopicItemDO, TopicItemDO> {

	/**
	 * <pre>
	 * 扣减兑换商品剩余数量
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public int reduceExchangeResidue(Long id) throws CommonServiceException;

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
