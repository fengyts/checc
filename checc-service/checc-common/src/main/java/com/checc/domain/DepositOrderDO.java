package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 充值订单
* @author fengyts Tue Dec 26 15:53:53 CST 2017
*/

public class DepositOrderDO extends BaseDO {

/** 主键 */
private Long id;

/** 用户id */
private Long userId;

/** 充值金额(西币个数) */
private Integer depositAmount;

/** 充值方式：01-卡密；02-支付宝；03-微信；04-其他方式 */
private String depositType;

/** 折扣,默认无折扣为1,如95折则为0.95 */
private Double discount;

/** 订单状态:01-待支付；02-支付成功；03:-支付失败；04-支付超时 */
private String orderStatus;

/** 下单时间 */
private Date createTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 用户id
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 充值金额(西币个数)
* @param depositAmount
*/
	public void setDepositAmount(Integer depositAmount) {
	this.depositAmount = depositAmount;
}
/**
* 设置 充值方式：01-卡密；02-支付宝；03-微信；04-其他方式
* @param depositType
*/
	public void setDepositType(String depositType) {
	this.depositType = depositType;
}
/**
* 设置 折扣,默认无折扣为1,如95折则为0.95
* @param discount
*/
	public void setDiscount(Double discount) {
	this.discount = discount;
}
/**
* 设置 订单状态:01-待支付；02-支付成功；03:-支付失败；04-支付超时
* @param orderStatus
*/
	public void setOrderStatus(String orderStatus) {
	this.orderStatus = orderStatus;
}
/**
* 设置 下单时间
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
* 获取 用户id
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 充值金额(西币个数)
* @return depositAmount
*/
	public Integer getDepositAmount() {
	return depositAmount;
}
/**
* 获取 充值方式：01-卡密；02-支付宝；03-微信；04-其他方式
* @return depositType
*/
	public String getDepositType() {
	return depositType;
}
/**
* 获取 折扣,默认无折扣为1,如95折则为0.95
* @return discount
*/
	public Double getDiscount() {
	return discount;
}
/**
* 获取 订单状态:01-待支付；02-支付成功；03:-支付失败；04-支付超时
* @return orderStatus
*/
	public String getOrderStatus() {
	return orderStatus;
}
/**
* 获取 下单时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}

}