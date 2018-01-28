package com.checc.service;

import ng.bayue.service.common.GeneralService;

import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.vo.ExchangeOrderStatusVO;

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
	
}
