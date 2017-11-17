package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 后台系统用户
* @author fengyts Fri Nov 17 16:08:38 CST 2017
*/

public class SysUserDO extends BaseDO {

/** 主键 */
private Long id;

/** 用户名称 */
private String userName;

/** 用户登录名 */
private String loginName;

/** E_mail */
private String email;

/** 密码 */
private String password;

/** 登录盐 */
private String salt;

/** 手机号 */
private String mobile;

/** 用户状态：0-无效；1-有效 */
private Boolean status;

/** 创建时间 */
private Date createTime;

/** 创建人 */
private Long createUserId;

/** 修改时间 */
private Date modifyTime;

/** 修改人 */
private Long modifyUserId;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 用户名称
* @param userName
*/
	public void setUserName(String userName) {
	this.userName = userName;
}
/**
* 设置 用户登录名
* @param loginName
*/
	public void setLoginName(String loginName) {
	this.loginName = loginName;
}
/**
* 设置 E_mail
* @param email
*/
	public void setEmail(String email) {
	this.email = email;
}
/**
* 设置 密码
* @param password
*/
	public void setPassword(String password) {
	this.password = password;
}
/**
* 设置 登录盐
* @param salt
*/
	public void setSalt(String salt) {
	this.salt = salt;
}
/**
* 设置 手机号
* @param mobile
*/
	public void setMobile(String mobile) {
	this.mobile = mobile;
}
/**
* 设置 用户状态：0-无效；1-有效
* @param status
*/
	public void setStatus(Boolean status) {
	this.status = status;
}
/**
* 设置 创建时间
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 设置 创建人
* @param createUserId
*/
	public void setCreateUserId(Long createUserId) {
	this.createUserId = createUserId;
}
/**
* 设置 修改时间
* @param modifyTime
*/
	public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
}
/**
* 设置 修改人
* @param modifyUserId
*/
	public void setModifyUserId(Long modifyUserId) {
	this.modifyUserId = modifyUserId;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 用户名称
* @return userName
*/
	public String getUserName() {
	return userName;
}
/**
* 获取 用户登录名
* @return loginName
*/
	public String getLoginName() {
	return loginName;
}
/**
* 获取 E_mail
* @return email
*/
	public String getEmail() {
	return email;
}
/**
* 获取 密码
* @return password
*/
	public String getPassword() {
	return password;
}
/**
* 获取 登录盐
* @return salt
*/
	public String getSalt() {
	return salt;
}
/**
* 获取 手机号
* @return mobile
*/
	public String getMobile() {
	return mobile;
}
/**
* 获取 用户状态：0-无效；1-有效
* @return status
*/
	public Boolean getStatus() {
	return status;
}
/**
* 获取 创建时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}
/**
* 获取 创建人
* @return createUserId
*/
	public Long getCreateUserId() {
	return createUserId;
}
/**
* 获取 修改时间
* @return modifyTime
*/
	public Date getModifyTime() {
	return modifyTime;
}
/**
* 获取 修改人
* @return modifyUserId
*/
	public Long getModifyUserId() {
	return modifyUserId;
}

}