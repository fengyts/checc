package com.checc.service;

import java.util.List;
import java.util.Map;

import com.checc.domain.SysRoleMenuDO;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.exception.ServiceException;
import ng.bayue.service.common.GeneralService;

 /**
 * 系统角色菜单 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysRoleMenuService extends GeneralService<SysRoleMenuDO, SysRoleMenuDO> {


	/**
	 * 根据ID删除 角色菜单关系
	 * @param roleId
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	int deleteByRoleId(Long roleId) throws CommonServiceException;

	/**
	 * 根据ID查询 一个 角色菜单关系
	 * @param roleId
	 * @return SysMenuRoleDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysRoleMenuDO> selectByRoleId(Long roleId) throws CommonServiceException;
	
	
	void insertBatch(Map<String,Object> map) throws CommonServiceException;
}
