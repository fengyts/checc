package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 用户西币
 * 
 * @author fengyts Sun Dec 31 22:49:32 CST 2017
 */

public class UserCurrencyDO extends BaseDO {

	/** 主键 */
	private Long id;

	/** 对应c_user用户表id */
	private Long userId;

	/** 总的西币 */
	private Integer totalCurrency;

	/** 冻结西币(正在参与竞拍的) */
	private Integer freeze;

	/** 退还金额，兑换商品只能使用用此金额 */
	private Integer refund;

	/** 第一次充值时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 对应c_user用户表id
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 总的西币
	 * 
	 * @param totalCurrency
	 */
	public void setTotalCurrency(Integer totalCurrency) {
		this.totalCurrency = totalCurrency;
	}

	/**
	 * 设置 冻结西币(正在参与竞拍的)
	 * 
	 * @param freeze
	 */
	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	/**
	 * 设置 退还金额，兑换商品只能使用用此金额
	 * 
	 * @param refund
	 */
	public void setRefund(Integer refund) {
		this.refund = refund;
	}

	/**
	 * 设置 第一次充值时间
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
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 获取 对应c_user用户表id
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 总的西币
	 * 
	 * @return totalCurrency
	 */
	public Integer getTotalCurrency() {
		return totalCurrency;
	}

	/**
	 * 获取 冻结西币(正在参与竞拍的)
	 * 
	 * @return freeze
	 */
	public Integer getFreeze() {
		return freeze;
	}

	/**
	 * 获取 退还金额，兑换商品只能使用用此金额
	 * 
	 * @return refund
	 */
	public Integer getRefund() {
		return refund;
	}

	/**
	 * 获取 第一次充值时间
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