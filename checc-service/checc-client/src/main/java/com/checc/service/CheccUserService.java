package com.checc.service;

import com.checc.domain.CheccUserDO;

import ng.bayue.service.common.GeneralService;

 /**
 * 车西西用户 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface CheccUserService extends GeneralService<CheccUserDO, CheccUserDO> {
	
	int register(CheccUserDO checcUserDO);
	
	int login(String mobile, String password);

}
