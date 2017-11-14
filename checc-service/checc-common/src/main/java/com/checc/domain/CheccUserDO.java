package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 车西西用户
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class CheccUserDO extends BaseDO {

/** 主键 */
private Long id;

/** 昵称 */
private String nickname;

/** 手机号 */
private String mobile;

/** 密码 */
private String password;

/** 创建时间 */
private Date createTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 昵称
* @param nickname
*/
	public void setNickname(String nickname) {
	this.nickname = nickname;
}
/**
* 设置 手机号
* @param mobile
*/
	public void setMobile(String mobile) {
	this.mobile = mobile;
}
/**
* 设置 密码
* @param password
*/
	public void setPassword(String password) {
	this.password = password;
}
/**
* 设置 创建时间
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 昵称
* @return nickname
*/
	public String getNickname() {
	return nickname;
}
/**
* 获取 手机号
* @return mobile
*/
	public String getMobile() {
	return mobile;
}
/**
* 获取 密码
* @return password
*/
	public String getPassword() {
	return password;
}
/**
* 获取 创建时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}

}