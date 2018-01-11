package com.checc.service;

import java.util.List;

import com.checc.domain.CheccUserDO;
import com.checc.vo.CheccUserVO;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

/**
 * 车西西用户 Service
 * 
 * @author fengyts 2017-11-16 14:54:40
 */
public interface CheccUserService extends GeneralService<CheccUserDO, CheccUserDO> {

	int register(CheccUserDO checcUserDO) throws CommonServiceException;

	CheccUserDO login(String mobile, String password);

	/**
	 * <pre>
	 * 找回密码
	 * </pre>
	 *
	 * @param mobile
	 * @param password
	 * @return
	 */
	int recoveredPwd(String mobile, String password) throws CommonServiceException;

	CheccUserDO selectByMobile(String mobile);

	/**
	 * <pre>
	 * 根据用户id或者用户手机号    获取所有虚拟账号列表, 查询单条记录时, 参数userId 和 mobile 二者任传一个即可
	 * </pre>
	 *
	 * @param userId
	 * @param mobile
	 * @return
	 */
	List<CheccUserVO> selectAllVMAccount(Long userId, String mobile);

	/**
	 * <pre>
	 * 修改所有虚拟账号密码
	 * </pre>
	 *
	 * @param password
	 * @return
	 */
	int recoveredVmAccountPwd(String password) throws CommonServiceException;

}
