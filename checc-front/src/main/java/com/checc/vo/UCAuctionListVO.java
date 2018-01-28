package com.checc.vo;

/**
 * <pre>
 * 个人中心-->我的竞拍、我的兑换列表数据模型
 * </pre>
 *
 * @author fengyts
 * @version $Id: UCAuctionListVO.java, v 0.1 2017年12月21日 下午9:34:24 fengyts Exp $
 */
/**
 * <pre>
 * 
 * </pre>
 *
 * @author fengyts
 * @version $Id: UCAuctionListVO.java, v 0.1 2018年1月28日 下午5:10:06 fengyts Exp $
 */
public class UCAuctionListVO extends BaseVO {

	private static final long serialVersionUID = -6805631115875665165L;
	
	/** 记录id */
	private Long recordId;
	/** topicItem主键id */
	private Long tpId;
	/** 商品id */
	private Long itemId;
	/** 商品名称 */
	private String itemTitle;
	/** 图片地址 */
	private String picture;
	/** 商品进度状态 */
	private String itemStatus;
	/** 收货状态(是否已提车) */
	private String deliveryStatus;
	/** 当前价 */
	private Double currenctAuctPrice;
	/** 是否本人拍得 */
	private Boolean isWinner;

	
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getTpId() {
		return tpId;
	}

	public void setTpId(Long tpId) {
		this.tpId = tpId;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Double getCurrenctAuctPrice() {
		return currenctAuctPrice;
	}

	public void setCurrenctAuctPrice(Double currenctAuctPrice) {
		this.currenctAuctPrice = currenctAuctPrice;
	}

	public Boolean getIsWinner() {
		return isWinner;
	}

	public void setIsWinner(Boolean isWinner) {
		this.isWinner = isWinner;
	}

}
