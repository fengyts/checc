package com.checc.vo;

import java.util.List;

import com.checc.domain.SysMenuDO;
import com.checc.domain.SysRoleDO;
import com.checc.domain.SysUserDO;

public class SysUserVO extends SysUserDO {

	private static final long serialVersionUID = 4165829077032805591L;

	List<SysRoleDO> sysRoles;

	List<SysMenuDO> sysMenus;

	public List<SysRoleDO> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<SysRoleDO> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public List<SysMenuDO> getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(List<SysMenuDO> sysMenus) {
		this.sysMenus = sysMenus;
	}

}
