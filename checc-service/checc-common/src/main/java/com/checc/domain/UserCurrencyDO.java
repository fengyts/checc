package com.checc.domain;

import ng.bayue.common.BaseDO;

/**
* 用户西币
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class UserCurrencyDO extends BaseDO {

/** 主键 */
private Long userId;

/** 总的西币 */
private Long totalCurrency;

/** 冻结西币(正在参与竞拍的) */
private Long freeze;

/** 退还金额 */
private Long refund;

/**
* 设置 主键
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 总的西币
* @param totalCurrency
*/
	public void setTotalCurrency(Long totalCurrency) {
	this.totalCurrency = totalCurrency;
}
/**
* 设置 冻结西币(正在参与竞拍的)
* @param freeze
*/
	public void setFreeze(Long freeze) {
	this.freeze = freeze;
}
/**
* 设置 退还金额
* @param refund
*/
	public void setRefund(Long refund) {
	this.refund = refund;
}
/**
* 获取 主键
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 总的西币
* @return totalCurrency
*/
	public Long getTotalCurrency() {
	return totalCurrency;
}
/**
* 获取 冻结西币(正在参与竞拍的)
* @return freeze
*/
	public Long getFreeze() {
	return freeze;
}
/**
* 获取 退还金额
* @return refund
*/
	public Long getRefund() {
	return refund;
}

}