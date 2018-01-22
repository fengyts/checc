package com.checc.service;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import java.util.Map;

import com.checc.domain.UserCurrencyDO;

 /**
 * 用户西币 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface UserCurrencyService extends GeneralService<UserCurrencyDO, UserCurrencyDO> {
	
	/**
	 * <pre>
	 * 根据用户id查询用户西币信息
	 * </pre>
	 *
	 * @param userId
	 * @return
	 */
	UserCurrencyDO selectByUserId(Long userId);

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
	 * 用户西币充值
	 * 先校验用户是否存在：存在则直接更新用户西币值，否则创建新纪录
	 * 详见<link>DepositService.deposit()</link>
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
	
	/**
	 * <pre>
	 * 竞拍结束回退用户西币
	 * </pre>
	 *
	 *@param refundMap回退列表
	 *
	 * @return
	 */
	public int refundCurrency(Map<String, Integer> refundMap);
	
	/**
	 * <pre>
	 * 竞拍成功的用户西币直接扣减掉，不回退
	 * </pre>
	 *
	 * @param auctSuccessMap
	 * @return
	 */
	public int reduceAuctionSuccess(Map<String, Integer> auctSuccessMap);

}
