package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 用户身份信息
 * 
 * @author fengyts Thu Jan 25 22:01:44 CST 2018
 */

public class CheccUserInfoDO extends BaseDO {

	private static final long serialVersionUID = -5508083690308747016L;

	/** 主键 */
	private Long userId;

	/** 用户手机号 */
	private String mobile;

	/** 用户真实姓名 */
	private String realName;

	/** 用户身份证号 */
	private String identityNo;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 用户手机号
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 设置 用户真实姓名
	 * 
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 设置 用户身份证号
	 * 
	 * @param identityNo
	 */
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	/**
	 * 设置 创建时间
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
	 * 获取 主键
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 用户手机号
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 获取 用户真实姓名
	 * 
	 * @return realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 获取 用户身份证号
	 * 
	 * @return identityNo
	 */
	public String getIdentityNo() {
		return identityNo;
	}

	/**
	 * 获取 创建时间
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

}