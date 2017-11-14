package com.checc.domain;


import java.util.Date;

import ng.bayue.common.BaseDO;

/**
* 竞拍记录
* @author fengyts Tue Nov 14 10:46:30 CST 2017
*/

public class AuctionRecordDO extends BaseDO {

/** 主键 */
private Long id;

/** 专题id */
private Long topicId;

/** 商品id */
private Long itemId;

/** 用户id */
private Long userId;

/** 用户出价 */
private Integer bid;

/** 出价时间 */
private Date bidTime;

/** 商品当前竞拍价 */
private Double itemPrice;

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
* 设置 用户出价
* @param bid
*/
	public void setBid(Integer bid) {
	this.bid = bid;
}
/**
* 设置 出价时间
* @param bidTime
*/
	public void setBidTime(Date bidTime) {
	this.bidTime = bidTime;
}
/**
* 设置 商品当前竞拍价
* @param itemPrice
*/
	public void setItemPrice(Double itemPrice) {
	this.itemPrice = itemPrice;
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
* 获取 用户出价
* @return bid
*/
	public Integer getBid() {
	return bid;
}
/**
* 获取 出价时间
* @return bidTime
*/
	public Date getBidTime() {
	return bidTime;
}
/**
* 获取 商品当前竞拍价
* @return itemPrice
*/
	public Double getItemPrice() {
	return itemPrice;
}

}