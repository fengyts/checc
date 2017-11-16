package com.checc.ao;

import java.util.List;


import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.SysRoleMenuDO;
import com.checc.service.SysRoleMenuService;

@Service
public class SysMenuRoleAO {

	private static final Logger logger = LoggerFactory.getLogger(SysMenuRoleAO.class);

	@Autowired
	private SysRoleMenuService sysMenuRoleService;

	public String selectMenuIdsByRoleId(Long roleId) {
		List<SysRoleMenuDO> list = sysMenuRoleService.selectByRoleId(roleId);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		String menuIds = "";
		for (SysRoleMenuDO smr : list) {
			menuIds += smr.getMenuId() + ",";
		}
		return menuIds.substring(0, menuIds.length() - 1);
	}
	

}
