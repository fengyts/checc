package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 竞拍|兑换|充值记录
 * 
 * @author fengyts Wed Dec 20 09:51:37 CST 2017
 */

public class AuctionRecordDO extends BaseDO {

	/** 主键 */
	private Long id;

	/** 专题商品表topic_item主键id */
	private Long topicItemId;

	/** 本记录类型：01-竞拍；02-兑换；03-充值；04-竞拍退回 */
	private String recordType;

	/** 充值类型：01-卡密；02-支付宝；03-微信；04-其他方式 */
	private String depositType;

	/** 用户id */
	private Long userId;

	/** 用户昵称，冗余字段 */
	private String nickname;

	/** 用户手机号，冗余字段 */
	private String mobile;

	/** 出价次数 */
	private Integer bidNum;

	/** 兑换件数 */
	private Integer exchangeCount;

	/** 本次出价消耗总西币(出价次数*单次出价西币);兑换消耗总西币(兑换单价*兑换件数) */
	private Integer totalCurrency;

	/** 当前竞拍价(为本商品往次出价[包含本次]总和) */
	private Integer currentAuctPrice;

	/** 充值卡卡号,冗余字段 */
	private String cardNo;

	/** 充值金额 */
	private Integer depositAmount;

	/** 交易时间 */
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
	 * 设置 专题商品表topic_item主键id
	 * 
	 * @param topicItemId
	 */
	public void setTopicItemId(Long topicItemId) {
		this.topicItemId = topicItemId;
	}

	/**
	 * 设置 本记录类型：01-竞拍；02-兑换；03-充值；04-竞拍退回
	 * 
	 * @param recordType
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * 设置 充值类型：01-卡密；02-支付宝；03-微信；04-其他方式
	 * 
	 * @param depositType
	 */
	public void setDepositType(String depositType) {
		this.depositType = depositType;
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
	 * 设置 用户昵称，冗余字段
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 设置 用户手机号，冗余字段
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 设置 出价次数
	 * 
	 * @param bidNum
	 */
	public void setBidNum(Integer bidNum) {
		this.bidNum = bidNum;
	}

	/**
	 * 设置 兑换件数
	 * 
	 * @param exchangeCount
	 */
	public void setExchangeCount(Integer exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	/**
	 * 设置 本次出价消耗总西币(出价次数*单次出价西币);兑换消耗总西币(兑换单价*兑换件数)
	 * 
	 * @param totalCurrency
	 */
	public void setTotalCurrency(Integer totalCurrency) {
		this.totalCurrency = totalCurrency;
	}

	/**
	 * 设置 当前竞拍价(为本商品往次出价[包含本次]总和)
	 * 
	 * @param currentAuctPrice
	 */
	public void setCurrentAuctPrice(Integer currentAuctPrice) {
		this.currentAuctPrice = currentAuctPrice;
	}

	/**
	 * 设置 充值卡卡号,冗余字段
	 * 
	 * @param cardNo
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 设置 充值金额
	 * 
	 * @param depositAmount
	 */
	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * 设置 交易时间
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
	 * 获取 专题商品表topic_item主键id
	 * 
	 * @return topicItemId
	 */
	public Long getTopicItemId() {
		return topicItemId;
	}

	/**
	 * 获取 本记录类型：01-竞拍；02-兑换；03-充值；04-竞拍退回
	 * 
	 * @return recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * 获取 充值类型：01-卡密；02-支付宝；03-微信；04-其他方式
	 * 
	 * @return depositType
	 */
	public String getDepositType() {
		return depositType;
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
	 * 获取 用户昵称，冗余字段
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 获取 用户手机号，冗余字段
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 获取 出价次数
	 * 
	 * @return bidNum
	 */
	public Integer getBidNum() {
		return bidNum;
	}

	/**
	 * 获取 兑换件数
	 * 
	 * @return exchangeCount
	 */
	public Integer getExchangeCount() {
		return exchangeCount;
	}

	/**
	 * 获取 本次出价消耗总西币(出价次数*单次出价西币);兑换消耗总西币(兑换单价*兑换件数)
	 * 
	 * @return totalCurrency
	 */
	public Integer getTotalCurrency() {
		return totalCurrency;
	}

	/**
	 * 获取 当前竞拍价(为本商品往次出价[包含本次]总和)
	 * 
	 * @return currentAuctPrice
	 */
	public Integer getCurrentAuctPrice() {
		return currentAuctPrice;
	}

	/**
	 * 获取 充值卡卡号,冗余字段
	 * 
	 * @return cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 获取 充值金额
	 * 
	 * @return depositAmount
	 */
	public Integer getDepositAmount() {
		return depositAmount;
	}

	/**
	 * 获取 交易时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

}