package com.checc.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 车西西用户
 * 
 * @author fengyts Mon Nov 27 12:40:43 CST 2017
 */

public class CheccUserDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = -6482119385569726761L;

	/** 主键 */
	private Long id;

	/** 昵称 */
	private String nickname;

	/** 手机号 */
	private String mobile;

	/** 密码 */
	private String password;

	/** 登录盐 */
	private String salt;

	/** 注册时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/** 最后一次登录时间 */
	private Date lastLoginTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 昵称
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 设置 手机号
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 设置 密码
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置 登录盐
	 * 
	 * @param salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 设置 注册时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 设置 最后一次登录时间
	 * 
	 * @param lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取 主键
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 获取 昵称
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 获取 手机号
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 获取 密码
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获取 登录盐
	 * 
	 * @return salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 获取 注册时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 获取 最后一次登录时间
	 * 
	 * @return lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

}