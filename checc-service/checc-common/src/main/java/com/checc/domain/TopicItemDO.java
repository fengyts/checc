package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 专题商品
 * 
 * @author fengyts Wed Jan 03 09:07:15 CST 2018
 */

public class TopicItemDO extends BaseDO {

	private static final long serialVersionUID = -373457883455360008L;

	/** 主键 */
	private Long id;

	/** 专题id */
	private Long topicId;

	/** 商品id */
	private Long itemId;

	/** 商品名称,冗余字段 */
	private String itemTitle;

	/** 商品市场价，冗余字段 */
	private Double marketPrice;

	/** 商品在专题起始时间，该时间必须在专题时间范围内 */
	private Date startTime;

	/** 商品在专题结束时间，该时间必须在专题时间范围内 */
	private Date endTime;

	/** 是否和专题状态一致：1-一致；0-不一致 */
	private Boolean isTopicStatus;

	/** 竞拍底价 */
	private Double floorPrice;

	/** 竞拍商品单次出价西币 */
	private Integer auctionCurrency;

	/** 竞拍商品单次出价最大次数 */
	private Integer auctionMaxTimes;

	/** 兑换商品在本专题的库存数量 */
	private Integer inventory;

	/** 兑换商品价格 */
	private Double exchangeAmount;

	/** 兑换剩余数量 */
	private Integer residue;

	/** 兑换商品每人限兑次数 */
	private Integer exchangeLimitNum;

	/** 商品在该专题排序值 */
	private Integer sort;

	/** 专题商品状态：1-有效；0-无效 */
	private Boolean status;

	/** 专题商品创建人 */
	private Long createUserId;

	/** 专题商品添加时间 */
	private Date createTime;

	/** 修改人 */
	private Long modifyUserId;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 专题id
	 * 
	 * @param topicId
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**
	 * 设置 商品id
	 * 
	 * @param itemId
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 设置 商品名称,冗余字段
	 * 
	 * @param itemTitle
	 */
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	/**
	 * 设置 商品市场价，冗余字段
	 * 
	 * @param marketPrice
	 */
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * 设置 商品在专题起始时间，该时间必须在专题时间范围内
	 * 
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 设置 商品在专题结束时间，该时间必须在专题时间范围内
	 * 
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 设置 是否和专题状态一致：1-一致；0-不一致
	 * 
	 * @param isTopicStatus
	 */
	public void setIsTopicStatus(Boolean isTopicStatus) {
		this.isTopicStatus = isTopicStatus;
	}

	/**
	 * 设置 竞拍底价
	 * 
	 * @param floorPrice
	 */
	public void setFloorPrice(Double floorPrice) {
		this.floorPrice = floorPrice;
	}

	/**
	 * 设置 竞拍商品单次出价西币
	 * 
	 * @param auctionCurrency
	 */
	public void setAuctionCurrency(Integer auctionCurrency) {
		this.auctionCurrency = auctionCurrency;
	}

	/**
	 * 设置 竞拍商品单次出价最大次数
	 * 
	 * @param auctionMaxTimes
	 */
	public void setAuctionMaxTimes(Integer auctionMaxTimes) {
		this.auctionMaxTimes = auctionMaxTimes;
	}

	/**
	 * 设置 兑换商品在本专题的库存数量
	 * 
	 * @param inventory
	 */
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	/**
	 * 设置 兑换商品价格
	 * 
	 * @param exchangeAmount
	 */
	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	/**
	 * 设置 兑换剩余数量
	 * 
	 * @param residue
	 */
	public void setResidue(Integer residue) {
		this.residue = residue;
	}

	/**
	 * 设置 兑换商品每人限兑次数
	 * 
	 * @param exchangeLimitNum
	 */
	public void setExchangeLimitNum(Integer exchangeLimitNum) {
		this.exchangeLimitNum = exchangeLimitNum;
	}

	/**
	 * 设置 商品在该专题排序值
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 专题商品状态：1-有效；0-无效
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * 设置 专题商品创建人
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 设置 专题商品添加时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
	 * 获取 专题id
	 * 
	 * @return topicId
	 */
	public Long getTopicId() {
		return topicId;
	}

	/**
	 * 获取 商品id
	 * 
	 * @return itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * 获取 商品名称,冗余字段
	 * 
	 * @return itemTitle
	 */
	public String getItemTitle() {
		return itemTitle;
	}

	/**
	 * 获取 商品市场价，冗余字段
	 * 
	 * @return marketPrice
	 */
	public Double getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 获取 商品在专题起始时间，该时间必须在专题时间范围内
	 * 
	 * @return startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 获取 商品在专题结束时间，该时间必须在专题时间范围内
	 * 
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 获取 是否和专题状态一致：1-一致；0-不一致
	 * 
	 * @return isTopicStatus
	 */
	public Boolean getIsTopicStatus() {
		return isTopicStatus;
	}

	/**
	 * 获取 竞拍底价
	 * 
	 * @return floorPrice
	 */
	public Double getFloorPrice() {
		return floorPrice;
	}

	/**
	 * 获取 竞拍商品单次出价西币
	 * 
	 * @return auctionCurrency
	 */
	public Integer getAuctionCurrency() {
		return auctionCurrency;
	}

	/**
	 * 获取 竞拍商品单次出价最大次数
	 * 
	 * @return auctionMaxTimes
	 */
	public Integer getAuctionMaxTimes() {
		return auctionMaxTimes;
	}

	/**
	 * 获取 兑换商品在本专题的库存数量
	 * 
	 * @return inventory
	 */
	public Integer getInventory() {
		return inventory;
	}

	/**
	 * 获取 兑换商品价格
	 * 
	 * @return exchangeAmount
	 */
	public Double getExchangeAmount() {
		return exchangeAmount;
	}

	/**
	 * 获取 兑换剩余数量
	 * 
	 * @return residue
	 */
	public Integer getResidue() {
		return residue;
	}

	/**
	 * 获取 兑换商品每人限兑次数
	 * 
	 * @return exchangeLimitNum
	 */
	public Integer getExchangeLimitNum() {
		return exchangeLimitNum;
	}

	/**
	 * 获取 商品在该专题排序值
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 专题商品状态：1-有效；0-无效
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * 获取 专题商品创建人
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 获取 专题商品添加时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 修改人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}