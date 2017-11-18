package com.checc.service;

import java.util.List;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.exception.ServiceException;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.SysMenuDO;

/**
 * 系统菜单 Service
 * 
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysMenuService extends GeneralService<SysMenuDO, SysMenuDO> {

	/**
	 * 
	 * <pre>
	 * 根据parentid得到菜单集合
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	List<SysMenuDO> findListByParentIds(List<SysMenuDO> list);

	/**
	 * 
	 * <pre>
	 * 根据id得到菜单集合
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	List<SysMenuDO> findListByIds(List<Long> list);

	/**
	 * 
	 * <pre>
	 * 得到父菜单
	 * </pre>
	 *
	 * @return
	 */
	List<SysMenuDO> findParentMenu();

	List<SysMenuDO> selectByIds(List<Long> ids);

	List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO) throws CommonServiceException;
	
	/**
	 * 插入  菜单
	 * @param sysMenuDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-30 19:15:56
	 */
	SysMenuDO save(SysMenuDO sysMenuDO) throws CommonServiceException;
	
}
