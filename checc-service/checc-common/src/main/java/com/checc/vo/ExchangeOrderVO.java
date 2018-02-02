package com.checc.vo;

import java.util.Date;

public class ExchangeOrderVO extends BaseVO {

	private static final long serialVersionUID = 937001729418902875L;

	/** 兑换记录id */
	private Long recordId;

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
	/** 兑换花费的西币 */
	private Double exchangeAmount;
	/** 兑换数量 */
	private Integer auctNum;
	/** 兑换时间 */
	private Date auctTime;
	/** 兑换人手机号 */
	private String mobile;

	/** 兑换订单id */
	private Long exchangeOrderId;
	/** 发货状态 */
	private String shipmentsStatus;
	/** 发货时间 */
	private Date shipmentsTime;
	/** 发货备注 */
	private String remark;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
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

	public Double getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getShipmentsStatus() {
		return shipmentsStatus;
	}

	public void setShipmentsStatus(String shipmentsStatus) {
		this.shipmentsStatus = shipmentsStatus;
	}

	public Long getExchangeOrderId() {
		return exchangeOrderId;
	}

	public void setExchangeOrderId(Long exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getShipmentsTime() {
		return shipmentsTime;
	}

	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}

}
