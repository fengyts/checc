package com.checc.dao;

import java.util.Map;

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
	 * 根据用户id获取用户西币信息
	 * </pre>
	 *
	 * @param userId
	 * @return
	 */
	UserCurrencyDO selectByUserId(Long userId) throws CommonDAOException;

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
	
	/**
	 * <pre>
	 * 竞拍结束回退用户西币
	 * </pre>
	 *
	 *@param refundList回退列表
	 *
	 * @return
	 */
	public int refundCurrency(Map<String, Object> refundList) throws CommonDAOException;
	
	
}
