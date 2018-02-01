package com.checc.dao;

import java.util.List;

import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.front.ExchangeOrderStatusVO;

import ng.bayue.service.common.GeneralDAO;

/**
 * 兑换商品发货状态 DAO
 *
 * @author fengyts 2018-01-28 13:52:42
 */
public interface ExchangeOrderStatusDAO extends GeneralDAO<ExchangeOrderStatusDO> {
	
	/**
	 * <pre>
	 * 获取兑换商品详情以及发货状态
	 * </pre>
	 *
	 * @param recordId
	 * @return
	 */
	public ExchangeOrderStatusVO selectExchangeOrderDetails(Long recordId);
	
	Integer countExchange(PurchaseExchangeStatusDTO dto);
	
	/**
	 * <pre>
	 * 后台兑换管理分页查询列表
	 * </pre>
	 *
	 * @param dto
	 * @return
	 */
	List<ExchangeOrderVO> selectExchangeListBackend(PurchaseExchangeStatusDTO dto);
	
	
}
