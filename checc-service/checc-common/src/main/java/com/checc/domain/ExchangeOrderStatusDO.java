package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 兑换商品发货状态
 * 
 * @author fengyts Fri Mar 30 16:21:55 CST 2018
 */

public class ExchangeOrderStatusDO extends BaseDO {

	private static final long serialVersionUID = 750292460381984462L;

	/** 主键 */
	private Long id;

	/** 对应兑换记录表id */
	private Long recordId;

	/** 兑换商品发货状态，01-待发货；02-已发货；03-已签收 */
	private String shipmentsStatus;

	/** 发货时间 */
	private Date shipmentsTime;

	/** 运单号 */
	private String waybillNo;

	/** 快递公司id，对应express_info表主键id */
	private Long expressId;

	/** 备注 */
	private String remark;

	/** 更新发货人 */
	private Long modifyUserId;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 对应兑换记录表id
	 * 
	 * @param recordId
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	/**
	 * 设置 兑换商品发货状态，01-待发货；02-已发货；03-已签收
	 * 
	 * @param shipmentsStatus
	 */
	public void setShipmentsStatus(String shipmentsStatus) {
		this.shipmentsStatus = shipmentsStatus;
	}

	/**
	 * 设置 发货时间
	 * 
	 * @param shipmentsTime
	 */
	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}

	/**
	 * 设置 运单号
	 * 
	 * @param waybillNo
	 */
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	/**
	 * 设置 快递公司id，对应express_info表主键id
	 * 
	 * @param expressId
	 */
	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	/**
	 * 设置 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 设置 更新发货人
	 * 
	 * @param modifyUserId
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	 * 获取 对应兑换记录表id
	 * 
	 * @return recordId
	 */
	public Long getRecordId() {
		return recordId;
	}

	/**
	 * 获取 兑换商品发货状态，01-待发货；02-已发货；03-已签收
	 * 
	 * @return shipmentsStatus
	 */
	public String getShipmentsStatus() {
		return shipmentsStatus;
	}

	/**
	 * 获取 发货时间
	 * 
	 * @return shipmentsTime
	 */
	public Date getShipmentsTime() {
		return shipmentsTime;
	}

	/**
	 * 获取 运单号
	 * 
	 * @return waybillNo
	 */
	public String getWaybillNo() {
		return waybillNo;
	}

	/**
	 * 获取 快递公司id，对应express_info表主键id
	 * 
	 * @return expressId
	 */
	public Long getExpressId() {
		return expressId;
	}

	/**
	 * 获取 备注
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 获取 更新发货人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

}