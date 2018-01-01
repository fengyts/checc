package com.checc.service;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.DepositOrderDO;

/**
 * 充值订单 Service
 * 
 * @author fengyts 2017-12-26 15:53:53
 */
public interface DepositOrderService extends GeneralService<DepositOrderDO, DepositOrderDO> {

	/**
	 * <pre>
	 * 根据唯一订单编号查询订单信息
	 * </pre>
	 *
	 * @param orderNo
	 * @return
	 * @throws CommonServiceException
	 */
	DepositOrderDO selectByOrderNo(String orderNo) throws CommonServiceException;

	/**
	 * <pre>
	 * 生成订单编号
	 * </pre>
	 *
	 * @param dpOrder
	 * @return
	 */
	CommonResultMessage productPayOrder(DepositOrderDO dpOrder) throws CommonServiceException;

}
