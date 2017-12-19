package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 竞拍记录
* @author fengyts Tue Dec 19 13:15:09 CST 2017
*/

public class AuctionRecordDO extends BaseDO {

/** 主键 */
private Long id;

/** 专题商品表topic_item主键id */
private Long topicItemId;

/** 用户id */
private Long userId;

/** 用户昵称，冗余字段 */
private String nickname;

/** 用户手机号，冗余字段 */
private String mobile;

/** 出价次数 */
private Integer bidNum;

/** 本次出价消耗总西币(出价次数*单次出价西币) */
private Integer totalCurrency;

/** 出价时间 */
private Date bidTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 专题商品表topic_item主键id
* @param topicItemId
*/
	public void setTopicItemId(Long topicItemId) {
	this.topicItemId = topicItemId;
}
/**
* 设置 用户id
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 用户昵称，冗余字段
* @param nickname
*/
	public void setNickname(String nickname) {
	this.nickname = nickname;
}
/**
* 设置 用户手机号，冗余字段
* @param mobile
*/
	public void setMobile(String mobile) {
	this.mobile = mobile;
}
/**
* 设置 出价次数
* @param bidNum
*/
	public void setBidNum(Integer bidNum) {
	this.bidNum = bidNum;
}
/**
* 设置 本次出价消耗总西币(出价次数*单次出价西币)
* @param totalCurrency
*/
	public void setTotalCurrency(Integer totalCurrency) {
	this.totalCurrency = totalCurrency;
}
/**
* 设置 出价时间
* @param bidTime
*/
	public void setBidTime(Date bidTime) {
	this.bidTime = bidTime;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 专题商品表topic_item主键id
* @return topicItemId
*/
	public Long getTopicItemId() {
	return topicItemId;
}
/**
* 获取 用户id
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 用户昵称，冗余字段
* @return nickname
*/
	public String getNickname() {
	return nickname;
}
/**
* 获取 用户手机号，冗余字段
* @return mobile
*/
	public String getMobile() {
	return mobile;
}
/**
* 获取 出价次数
* @return bidNum
*/
	public Integer getBidNum() {
	return bidNum;
}
/**
* 获取 本次出价消耗总西币(出价次数*单次出价西币)
* @return totalCurrency
*/
	public Integer getTotalCurrency() {
	return totalCurrency;
}
/**
* 获取 出价时间
* @return bidTime
*/
	public Date getBidTime() {
	return bidTime;
}

}