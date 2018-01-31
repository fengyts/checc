package com.checc.dto;

import ng.bayue.common.BaseDO;

public class PurchaseExchangeStatusDTO extends BaseDO {

	private static final long serialVersionUID = 7381538220945229088L;

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
