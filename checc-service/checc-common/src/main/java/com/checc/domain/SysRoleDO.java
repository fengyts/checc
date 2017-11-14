package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 系统用户角色
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class SysRoleDO extends BaseDO {

/** 主键 */
private Long id;

/** 角色名称 */
private String roleName;

/** 角色代码 */
private String roleCode;

/** 创建人 */
private Long createUserId;

/** 创建时间 */
private Date createTime;

/** 修改人 */
private Long modifyUserId;

/** 修改时间 */
private Date modifyTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 角色名称
* @param roleName
*/
	public void setRoleName(String roleName) {
	this.roleName = roleName;
}
/**
* 设置 角色代码
* @param roleCode
*/
	public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}
/**
* 设置 创建人
* @param createUserId
*/
	public void setCreateUserId(Long createUserId) {
	this.createUserId = createUserId;
}
/**
* 设置 创建时间
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 设置 修改人
* @param modifyUserId
*/
	public void setModifyUserId(Long modifyUserId) {
	this.modifyUserId = modifyUserId;
}
/**
* 设置 修改时间
* @param modifyTime
*/
	public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 角色名称
* @return roleName
*/
	public String getRoleName() {
	return roleName;
}
/**
* 获取 角色代码
* @return roleCode
*/
	public String getRoleCode() {
	return roleCode;
}
/**
* 获取 创建人
* @return createUserId
*/
	public Long getCreateUserId() {
	return createUserId;
}
/**
* 获取 创建时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}
/**
* 获取 修改人
* @return modifyUserId
*/
	public Long getModifyUserId() {
	return modifyUserId;
}
/**
* 获取 修改时间
* @return modifyTime
*/
	public Date getModifyTime() {
	return modifyTime;
}

}