package com.checc.dao;

import com.checc.domain.UserCurrencyDO;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralDAO;

/**
 * 用户西币 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface UserCurrencyDAO extends GeneralDAO<UserCurrencyDO> {

	/**
	 * <pre>
	 * 冻结用户西币
	 * </pre>
	 *
	 * @param userId
	 * @param currency
	 * @return
	 * @throws CommonServiceException
	 */
	public int freezeCurrency(Long userId, Integer currency) throws CommonDAOException;

	/**
	 * <pre>
	 * 增加用户西币值
	 * </pre>
	 *
	 * @param userId
	 * @param currency
	 * @return
	 * @throws CommonServiceException
	 */
	public int increaseTotalCurrency(Long userId, Integer currency) throws CommonDAOException;
	
	/**
	 * <pre>
	 * 兑换商品扣减西币
	 * </pre>
	 *
	 * @param userId
	 * @param currency
	 * @return
	 * @throws CommonServiceException
	 */
	public int reduceExchangeCurrency(Long userId, Integer currency) throws CommonDAOException;
	
	
	
}
