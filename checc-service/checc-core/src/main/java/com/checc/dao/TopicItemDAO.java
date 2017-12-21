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
	 * 扣减兑换商品剩余数量
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public int reduceExchangeResidue(Long id) throws CommonDAOException;
	
	public List<TopicItemDO> selectByIds(List<Long> ids);

}
