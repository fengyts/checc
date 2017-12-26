package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 充值选项配置
* @author fengyts Tue Dec 26 15:53:53 CST 2017
*/

public class DepositConfigDO extends BaseDO {

/** 主键 */
private Long id;

/** 充值金额(西币个数) */
private Integer depositAmount;

/** 折扣,默认无折扣为1,如95折则为0.95 */
private Double discount;

/** 有效性状态:1-有效;0-无效 */
private Boolean status;

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
* 设置 充值金额(西币个数)
* @param depositAmount
*/
	public void setDepositAmount(Integer depositAmount) {
	this.depositAmount = depositAmount;
}
/**
* 设置 折扣,默认无折扣为1,如95折则为0.95
* @param discount
*/
	public void setDiscount(Double discount) {
	this.discount = discount;
}
/**
* 设置 有效性状态:1-有效;0-无效
* @param status
*/
	public void setStatus(Boolean status) {
	this.status = status;
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
* 获取 充值金额(西币个数)
* @return depositAmount
*/
	public Integer getDepositAmount() {
	return depositAmount;
}
/**
* 获取 折扣,默认无折扣为1,如95折则为0.95
* @return discount
*/
	public Double getDiscount() {
	return discount;
}
/**
* 获取 有效性状态:1-有效;0-无效
* @return status
*/
	public Boolean getStatus() {
	return status;
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