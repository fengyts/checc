package com.checc.service;

import com.checc.dto.AuctionActionDTO;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.exception.CommonServiceException;

/**
 * <pre>
 * 	竞拍或兑换接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: AuctionActionService.java, v 0.1 2017年12月19日 下午1:50:22 lenovopc Exp $
 */
public interface AuctionActionService {
	/**
	 * <pre>
	 * 竞拍
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public CommonResultMessage auctionAction(AuctionActionDTO dto) throws CommonServiceException;
	
	
	/**
	 * <pre>
	 * 兑换
	 * </pre>
	 *
	 * @return
	 * @throws CommonServiceException
	 */
	public CommonResultMessage exchangeAction(AuctionActionDTO dto) throws CommonServiceException;
	
	
	
}
