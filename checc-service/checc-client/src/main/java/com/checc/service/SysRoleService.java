package com.checc.service;

import java.util.List;

import com.checc.domain.SysRoleDO;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

 /**
 * 系统用户角色 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysRoleService extends GeneralService<SysRoleDO, SysRoleDO> {
	
	/**
	 * <pre>
	 * 插入用户角色的同时插入角色菜单关联
	 * </pre>
	 *
	 * @param sysRoleDO
	 * @param menuIds 关联该角色菜单id列表
	 * @throws ServiceException
	 */
	void insertSysRoleAndRoleMenuRelation(SysRoleDO sysRoleDO,List<Long> menuIds) throws CommonServiceException;
	
	void updateSysRoleAndRoleMenuRelation(SysRoleDO sysRoleDO,Long roleId,List<Long> menuIds) throws CommonServiceException;
	
	List<SysRoleDO> selectByIds(List<Long> ids);

}
