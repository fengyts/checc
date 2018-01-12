package com.checc.vo.front;

import java.util.Date;

import com.checc.vo.BaseVO;

public class TopicItemVO extends BaseVO {

	private static final long serialVersionUID = 6084541220722196553L;

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
	/** 兑换商品是否已经兑光,true-已经兑光；false-未兑光 */
	private Boolean hasExchangeOut;

	/** 商品图片地址 */
	private String picture;

	/** 市场价 */
	private Double marketPrice;
	/** 竞拍底价 */
	private Double floorPrice;
	/** 当前竞拍价 */
	private Double currentAuctionPrice;
	/** 当前出价人 */
	private String mobile;

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

	public Double getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(Double floorPrice) {
		this.floorPrice = floorPrice;
	}

	public Double getCurrentAuctionPrice() {
		return currentAuctionPrice;
	}

	public void setCurrentAuctionPrice(Double currentAuctionPrice) {
		this.currentAuctionPrice = currentAuctionPrice;
	}

	public Boolean getHasExchangeOut() {
		return hasExchangeOut;
	}

	public void setHasExchangeOut(Boolean hasExchangeOut) {
		this.hasExchangeOut = hasExchangeOut;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
