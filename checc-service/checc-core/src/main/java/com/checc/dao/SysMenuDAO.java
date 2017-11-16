package com.checc.dao;

import java.util.List;

import com.checc.domain.SysMenuDO;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.service.common.GeneralDAO;

/**
 * 系统菜单 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysMenuDAO extends GeneralDAO<SysMenuDO> {
	/**
	 * 
	 * <pre>
	 * 根据parentid找到所有菜单
	 * </pre>
	 *
	 * @param sysMenuList
	 * @return
	 * @throws CommonDAOException
	 */
	List<SysMenuDO> findListByParentIds(List<SysMenuDO> sysMenuList) throws CommonDAOException;

	/**
	 * 
	 * <pre>
	 * 根据id找到菜单集合
	 * </pre>
	 *
	 * @param ids
	 * @return
	 * @throws CommonDAOException
	 */
	List<SysMenuDO> findListByIds(List<Long> ids) throws CommonDAOException;

	/**
	 * 
	 * <pre>
	 * 得到父菜单
	 * </pre>
	 *
	 * @return
	 */
	List<SysMenuDO> findParentMenu() throws CommonDAOException;

	List<SysMenuDO> selectByIds(List<Long> ids);

	List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO) throws CommonDAOException;
}
