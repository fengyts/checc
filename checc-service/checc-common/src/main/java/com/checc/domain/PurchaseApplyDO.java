package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 竞拍商品购买状态
 * 
 * @author fengyts Sun Jan 28 13:52:44 CST 2018
 */

public class PurchaseApplyDO extends BaseDO {

	private static final long serialVersionUID = -3518638583944879517L;

	/** 主键 */
	private Long id;

	/** 竞拍专题商品id */
	private Long topicItemId;

	/** 竞拍商品购买状态，01-待申请；02-待购车；03-已购车；04-放弃购车 */
	private String purchaseStatus;

	/** 申请购车&发货 时间 */
	private Date applyTime;

	/** 备注 */
	private String remark;

	/** 修改时间 */
	private Date modifyTime;

	/** 修改人 */
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
	 * 设置 竞拍专题商品id
	 * 
	 * @param topicItemId
	 */
	public void setTopicItemId(Long topicItemId) {
		this.topicItemId = topicItemId;
	}

	/**
	 * 设置 竞拍商品购买状态，01-待申请；02-待购车；03-已购车；04-放弃购车
	 * 
	 * @param purchaseStatus
	 */
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * 设置 申请购车&发货 时间
	 * 
	 * @param applyTime
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 设置 修改人
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
	 * 获取 竞拍专题商品id
	 * 
	 * @return topicItemId
	 */
	public Long getTopicItemId() {
		return topicItemId;
	}

	/**
	 * 获取 竞拍商品购买状态，01-待申请；02-待购车；03-已购车；04-放弃购车
	 * 
	 * @return purchaseStatus
	 */
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * 获取 申请购车&发货 时间
	 * 
	 * @return applyTime
	 */
	public Date getApplyTime() {
		return applyTime;
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
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 获取 修改人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

}