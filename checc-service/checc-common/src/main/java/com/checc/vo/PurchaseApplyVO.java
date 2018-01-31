package com.checc.vo;

import java.util.Date;

public class PurchaseApplyVO extends BaseVO {

	private static final long serialVersionUID = -8643912105345155523L;

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
	/** 拍得数量 */
	private Integer auctNum;

	/** 购车申请单id */
	private Long purchaseId;
	/** 拍得时间(最后出价时间) */
	private Date auctTime;
	/** 申请购车 时间 */
	private Date applyTime;
	/** 购车状态 */
	private String purchaseStatus;
	/** 购车状态备注 */
	private String remark;

	/** 竞拍参与的人数 */
	private Integer countAuctionNum;
	/** 竞拍得主用户id */
	private Long userId;
	/** 竞拍得主用户手机号 */
	private String mobile;

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

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCountAuctionNum() {
		return countAuctionNum;
	}

	public void setCountAuctionNum(Integer countAuctionNum) {
		this.countAuctionNum = countAuctionNum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
