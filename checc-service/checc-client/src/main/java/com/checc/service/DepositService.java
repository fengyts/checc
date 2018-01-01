package com.checc.service;

import ng.bayue.exception.CommonServiceException;

import com.checc.dto.DepositInsertDTO;

public interface DepositService {

	/**
	 * <pre>
	 * 用户充值，更新用户西币，生成充值记录
	 * 先校验用户是否存在：存在则直接更新用户西币值，否则创建新纪录
	 * </pre>
	 *
	 * @param dto
	 * @return
	 * @throws CommonServiceException
	 */
	int deposit(DepositInsertDTO dto) throws CommonServiceException;

}
