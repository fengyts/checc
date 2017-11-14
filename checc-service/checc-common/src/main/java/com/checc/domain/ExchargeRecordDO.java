package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 商品兑换记录
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class ExchargeRecordDO extends BaseDO {

/** 主键 */
private Long id;

/** 专题id */
private Long topicId;

/** 商品id */
private Long itemId;

/** 用户id */
private Long userId;

/** 商品兑换数量，默认1 */
private Integer count;

/** 兑换金额，即商品兑换单价，冗余字段 */
private Integer exchargeAmount;

/** 兑换时间 */
private Date createTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 专题id
* @param topicId
*/
	public void setTopicId(Long topicId) {
	this.topicId = topicId;
}
/**
* 设置 商品id
* @param itemId
*/
	public void setItemId(Long itemId) {
	this.itemId = itemId;
}
/**
* 设置 用户id
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 商品兑换数量，默认1
* @param count
*/
	public void setCount(Integer count) {
	this.count = count;
}
/**
* 设置 兑换金额，即商品兑换单价，冗余字段
* @param exchargeAmount
*/
	public void setExchargeAmount(Integer exchargeAmount) {
	this.exchargeAmount = exchargeAmount;
}
/**
* 设置 兑换时间
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
* 获取 专题id
* @return topicId
*/
	public Long getTopicId() {
	return topicId;
}
/**
* 获取 商品id
* @return itemId
*/
	public Long getItemId() {
	return itemId;
}
/**
* 获取 用户id
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 商品兑换数量，默认1
* @return count
*/
	public Integer getCount() {
	return count;
}
/**
* 获取 兑换金额，即商品兑换单价，冗余字段
* @return exchargeAmount
*/
	public Integer getExchargeAmount() {
	return exchargeAmount;
}
/**
* 获取 兑换时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}

}