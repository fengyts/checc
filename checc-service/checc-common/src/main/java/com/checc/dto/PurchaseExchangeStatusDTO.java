package com.checc.dto;

public class PurchaseExchangeStatusDTO extends BaseDTO {

	private static final long serialVersionUID = -4555989604513461316L;

	private String itemTitle;
	private String mobile;
	private String purchaseApplyStatus;
	private String exchangeOrderStatus;

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPurchaseApplyStatus() {
		return purchaseApplyStatus;
	}

	public void setPurchaseApplyStatus(String purchaseApplyStatus) {
		this.purchaseApplyStatus = purchaseApplyStatus;
	}

	public String getExchangeOrderStatus() {
		return exchangeOrderStatus;
	}

	public void setExchangeOrderStatus(String exchangeOrderStatus) {
		this.exchangeOrderStatus = exchangeOrderStatus;
	}

}
