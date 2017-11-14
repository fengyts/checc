package com.checc.domain;

import ng.bayue.common.BaseDO;

/**
* 系统用户角色
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class SysUserRoleDO extends BaseDO {

/** 主键 */
private Long userId;

/** 主键 */
private Long roleId;

/**
* 设置 主键
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 主键
* @param roleId
*/
	public void setRoleId(Long roleId) {
	this.roleId = roleId;
}
/**
* 获取 主键
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 主键
* @return roleId
*/
	public Long getRoleId() {
	return roleId;
}

}