package com.checc.service;

import ng.bayue.common.Page;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.front.ExchangeOrderStatusVO;

 /**
 * 兑换商品发货状态 Service
 * @author fengyts 2018-01-28 13:52:42
 */
public interface ExchangeOrderStatusService extends GeneralService<ExchangeOrderStatusDO, ExchangeOrderStatusDO> {

	/**
	 * <pre>
	 * 获取兑换商品详情以及发货状态
	 * </pre>
	 *
	 * @param recordId
	 * @return
	 */
	public ExchangeOrderStatusVO selectExchangeOrderDetails(Long recordId);
	
	/**
	 * <pre>
	 * 后台兑换管理分页查询
	 * </pre>
	 *
	 * @return
	 */
	public Page<ExchangeOrderVO> queryPageExchangeBackend(PurchaseExchangeStatusDTO dto, Integer pageNo, Integer pageSize);
	
}
