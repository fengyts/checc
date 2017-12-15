package com.checc.dto;

import java.io.Serializable;
import java.util.Date;

public class TopicItemDTO implements Serializable {

	private static final long serialVersionUID = -4998297441365163831L;

	/** topicItem 主键ID */
	private Long id;
	private Long topicId;
	/** 专题类型 */
	private String topicType;
	/** 专题开始时间 */
	private Date startTime;
	/** 专题结束时间 */
	private Date endTime;
	/** 专题状态 */
	private String status;

	private Long itemId;
	private String itemTitle;
	/** 商品兑换总数量 */
	private Integer inventory;
	/** 商品兑换价格 */
	private Double exchangeAmount;
	/** 商品兑换剩余数量 */
	private Integer residue;
	/** 兑换商品限兑次数 */
	private Integer exchangeLimitNum;
	/** 商品状态 */
	private Boolean itemStatus;

	/** 竞拍商品单次出价 */
	private Integer auctionCurrency;
	/** 竞拍商品单次最大限次 */
	private Integer auctionMaxTimes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Double getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public Integer getResidue() {
		return residue;
	}

	public void setResidue(Integer residue) {
		this.residue = residue;
	}

	public Boolean getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Boolean itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Integer getExchangeLimitNum() {
		return exchangeLimitNum;
	}

	public void setExchangeLimitNum(Integer exchangeLimitNum) {
		this.exchangeLimitNum = exchangeLimitNum;
	}

	public Integer getAuctionCurrency() {
		return auctionCurrency;
	}

	public void setAuctionCurrency(Integer auctionCurrency) {
		this.auctionCurrency = auctionCurrency;
	}

	public Integer getAuctionMaxTimes() {
		return auctionMaxTimes;
	}

	public void setAuctionMaxTimes(Integer auctionMaxTimes) {
		this.auctionMaxTimes = auctionMaxTimes;
	}

}
