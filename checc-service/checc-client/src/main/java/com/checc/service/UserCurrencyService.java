package com.checc.service;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;
import com.checc.domain.UserCurrencyDO;

 /**
 * 用户西币 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface UserCurrencyService extends GeneralService<UserCurrencyDO, UserCurrencyDO> {

	/**
	 * <pre>
	 * 竞拍冻结用户西币
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public int freezeCurrency(Long userId, Integer currency) throws CommonServiceException;
	
	/**
	 * <pre>
	 * 增加用户西币值
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public int increaseTotalCurrency(Long userId, Integer currency) throws CommonServiceException;
	
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
	public int reduceExchangeCurrency(Long userId, Integer currency) throws CommonServiceException;

}
