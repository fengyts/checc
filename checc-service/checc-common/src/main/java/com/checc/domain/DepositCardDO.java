package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 充值卡
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class DepositCardDO extends BaseDO {

/** 主键 */
private String cardNo;

/** 金额 */
private Integer amount;

/** 卡使用有效期 */
private Date expireTime;

/** 是否已经卖出：0-未卖出；1-已卖出 */
private Boolean isSelled;

/** 是否已经使用：0-未使用；1-已使用 */
private Boolean isUsed;

/** 卡创建时间 */
private Date createTime;

/**
* 设置 主键
* @param cardNo
*/
	public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}
/**
* 设置 金额
* @param amount
*/
	public void setAmount(Integer amount) {
	this.amount = amount;
}
/**
* 设置 卡使用有效期
* @param expireTime
*/
	public void setExpireTime(Date expireTime) {
	this.expireTime = expireTime;
}
/**
* 设置 是否已经卖出：0-未卖出；1-已卖出
* @param isSelled
*/
	public void setIsSelled(Boolean isSelled) {
	this.isSelled = isSelled;
}
/**
* 设置 是否已经使用：0-未使用；1-已使用
* @param isUsed
*/
	public void setIsUsed(Boolean isUsed) {
	this.isUsed = isUsed;
}
/**
* 设置 卡创建时间
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 获取 主键
* @return cardNo
*/
	public String getCardNo() {
	return cardNo;
}
/**
* 获取 金额
* @return amount
*/
	public Integer getAmount() {
	return amount;
}
/**
* 获取 卡使用有效期
* @return expireTime
*/
	public Date getExpireTime() {
	return expireTime;
}
/**
* 获取 是否已经卖出：0-未卖出；1-已卖出
* @return isSelled
*/
	public Boolean getIsSelled() {
	return isSelled;
}
/**
* 获取 是否已经使用：0-未使用；1-已使用
* @return isUsed
*/
	public Boolean getIsUsed() {
	return isUsed;
}
/**
* 获取 卡创建时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}

}