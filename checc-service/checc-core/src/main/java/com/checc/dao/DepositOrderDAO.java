package com.checc.dao;

import com.checc.domain.DepositOrderDO;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.service.common.GeneralDAO;

/**
 * 充值订单 DAO
 *
 * @author fengyts 2017-12-26 15:53:53
 */
public interface DepositOrderDAO extends GeneralDAO<DepositOrderDO> {
	
	/**
	 * <pre>
	 * 根据唯一订单编号查询订单信息
	 * </pre>
	 *
	 * @param orderNo
	 * @return
	 * @throws CommonServiceException
	 */
	DepositOrderDO selectByOrderNo(String orderNo) throws CommonDAOException;

}
