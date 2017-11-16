package com.checc.service;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import java.util.List;

import com.checc.domain.SysUserRoleDO;

 /**
 * 系统用户角色 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysUserRoleService extends GeneralService<SysUserRoleDO, SysUserRoleDO> {

	void insertBatch(List<SysUserRoleDO> list) throws CommonServiceException;
	
	List<SysUserRoleDO> selectByUserIds(List<Long> userIds);
	
}
