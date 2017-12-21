package com.checc.service;

import java.util.List;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.TopicItemDO;

 /**
 * 专题商品 Service
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
	
	public List<TopicItemDO> selectByIds(List<Long> ids);
	
}
