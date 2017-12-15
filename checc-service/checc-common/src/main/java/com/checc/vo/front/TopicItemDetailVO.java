package com.checc.vo.front;

import java.util.Date;

import com.checc.vo.BaseVO;

public class TopicItemDetailVO extends BaseVO {

	private static final long serialVersionUID = -3171606217018855718L;

	/** topicItem 主键ID */
	private Long id;

	/** 专题主键id */
	private Long topicId;
	/** 专题类型 */
	private String topicType;
	/** 专题开始时间 */
	private Date startTime;
	/** 专题结束时间 */
	private Date endTime;
	/** 专题状态 */
	private String status;

	/** 专题商品id */
	private Long itemId;
	private String itemTitle;
	/** 商品兑换总数量 */
	private Integer inventory;
	/** 商品兑换价格 */
	private Double exchangeAmount;
	/** 商品兑换剩余数量 */
	private Integer residue;
	/** 商品状态,竞拍、兑换是否结束 */
	private String itemStatus;
	/** 兑换商品是否已经兑光 */
	private Boolean hasExchangeOut;

	/** 商品图片地址 */
	private String picture;

	/** 市场价 */
	private Double marketPrice;
	/** 当前竞拍价 */
	private Double currentAuctionPrice;

	/** 结束倒计时 */
	private Long timeCountDown;

	/** 商品描述 */
	private String description;

	/** 竞拍商品单次出价西币 */
	private Integer auctionCurrency;
	/** 兑换商品限兑次数 */
	private Integer exchangeLimitNum;

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

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Boolean getHasExchangeOut() {
		return hasExchangeOut;
	}

	public void setHasExchangeOut(Boolean hasExchangeOut) {
		this.hasExchangeOut = hasExchangeOut;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getCurrentAuctionPrice() {
		return currentAuctionPrice;
	}

	public void setCurrentAuctionPrice(Double currentAuctionPrice) {
		this.currentAuctionPrice = currentAuctionPrice;
	}

	public Long getTimeCountDown() {
		return timeCountDown;
	}

	public void setTimeCountDown(Long timeCountDown) {
		this.timeCountDown = timeCountDown;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAuctionCurrency() {
		return auctionCurrency;
	}

	public void setAuctionCurrency(Integer auctionCurrency) {
		this.auctionCurrency = auctionCurrency;
	}

	public Integer getExchangeLimitNum() {
		return exchangeLimitNum;
	}

	public void setExchangeLimitNum(Integer exchangeLimitNum) {
		this.exchangeLimitNum = exchangeLimitNum;
	}

}
