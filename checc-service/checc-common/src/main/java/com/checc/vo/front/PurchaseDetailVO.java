package com.checc.vo.front;

import java.util.Date;

import com.checc.vo.BaseVO;

public class PurchaseDetailVO extends BaseVO {

	private static final long serialVersionUID = 4980215099821930430L;

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
	private Long tiId;
	/** 专题商品名称 */
	private String itemTitle;
	/** 市场价 */
	private Double marketPrice;
	/** 竞拍底价 */
	private Double floorPrice;
	/** 最终成交价 */
	private Double finalAuctionPrice;
	/** 拍得数量*/
	private Integer auctNum;
	/** 拍得时间(最后出价时间) */
	private Date auctTime;
	/** 申请购车/兑换商品发货 时间 */
	private Date applyTime;
	/** 购车状态 */
	private String purchaseStatus;

	
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

	public Long getTiId() {
		return tiId;
	}

	public void setTiId(Long tiId) {
		this.tiId = tiId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
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

	public Double getFinalAuctionPrice() {
		return finalAuctionPrice;
	}

	public void setFinalAuctionPrice(Double finalAuctionPrice) {
		this.finalAuctionPrice = finalAuctionPrice;
	}

	public Integer getAuctNum() {
		return auctNum;
	}

	public void setAuctNum(Integer auctNum) {
		this.auctNum = auctNum;
	}

	public Date getAuctTime() {
		return auctTime;
	}

	public void setAuctTime(Date auctTime) {
		this.auctTime = auctTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	
}
