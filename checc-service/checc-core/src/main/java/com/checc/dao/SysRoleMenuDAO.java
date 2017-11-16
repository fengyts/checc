package com.checc.dao;

import java.util.List;
import java.util.Map;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.service.common.GeneralDAO;

import com.checc.domain.SysRoleMenuDO;

 /**
 * 系统角色菜单 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysRoleMenuDAO extends GeneralDAO<SysRoleMenuDO> {

	/**
	 * 根据ID删除 角色菜单关系
	 * @param roleId
	 * @return 删除行数
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer deleteByRoleId(Long roleId) throws CommonDAOException;

	/**
	 * 动态更新 角色菜单关系部分属性，包括全部
	 * @param sysMenuRoleDO
	 * @return 更新行数
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	Integer updateDynamic(SysRoleMenuDO sysMenuRoleDO) throws CommonDAOException;
	
	/**
	 * 根据ID查询 一个 角色菜单关系
	 * @param id
	 * @return SysMenuRoleDO
	 * @throws CommonDAOException
	 * @author longhaisheng 2016-10-25 13:31:48
	 */
	List<SysRoleMenuDO> selectByRoleId(Long roleId) throws CommonDAOException;
	
	void insertBatch(Map<String,Object> map) throws CommonDAOException;
}
