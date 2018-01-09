package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 充值记录
 * 
 * @author fengyts Thu Nov 16 14:54:40 CST 2017
 */

public class DepositRecordDO extends BaseDO {

	private static final long serialVersionUID = -1310542289715192694L;

	/** 主键 */
	private Long id;

	/** 用户id */
	private Long userId;

	/** 充值类型：01-卡密充值，02-其他方式 */
	private String depositType;

	/** 充值卡号 */
	private String cardNo;

	/** 充值金额,即充值卡的额度，冗余字段 */
	private Integer amount;

	/** 充值时间 */
	private Date createTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 用户id
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 充值类型：01-卡密充值，02-其他方式
	 * 
	 * @param depositType
	 */
	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	/**
	 * 设置 充值卡号
	 * 
	 * @param cardNo
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 设置 充值金额,即充值卡的额度，冗余字段
	 * 
	 * @param amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * 设置 充值时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * 获取 用户id
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 充值类型：01-卡密充值，02-其他方式
	 * 
	 * @return depositType
	 */
	public String getDepositType() {
		return depositType;
	}

	/**
	 * 获取 充值卡号
	 * 
	 * @return cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 获取 充值金额,即充值卡的额度，冗余字段
	 * 
	 * @return amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * 获取 充值时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

}