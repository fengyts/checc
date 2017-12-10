package com.checc.service;

import com.checc.domain.CheccUserDO;

import ng.bayue.service.common.GeneralService;

 /**
 * 车西西用户 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface CheccUserService extends GeneralService<CheccUserDO, CheccUserDO> {
	
	int register(CheccUserDO checcUserDO);
	
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
	int recoveredPwd(String mobile, String password);
	
	CheccUserDO selectByMobile(String mobile);

}
