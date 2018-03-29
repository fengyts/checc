package com.checc.dto;

import ng.bayue.common.BaseDO;

/**
 * <pre>
 * (竞拍/兑换)商品(购买/发货)状态
 * </pre>
 *
 * @author lenovopc
 * @version $Id: PurchaseExchangeStatusDTO.java, v 0.1 2018年3月29日 下午4:18:01 lenovopc Exp $
 */
public class PurchaseExchangeStatusDTO extends BaseDO {

	private static final long serialVersionUID = 7381538220945229088L;

	private String itemTitle;
	private String mobile;
	
	/** 竞拍成功商品购买状态 */
	private String purchaseApplyStatus;
	
	/** 兑换商品发货状态 */
	private String shipmentsStatus;
	/** 运单号 */
	private String waybillNo;
	/** 物流公司id */
	private Long expressId;
	/** 物流公司名称 */
	private String companyName;
	/** 物流公司编码（非简码） */
	private String companyNo;

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

	public String getShipmentsStatus() {
		return shipmentsStatus;
	}

	public void setShipmentsStatus(String shipmentsStatus) {
		this.shipmentsStatus = shipmentsStatus;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public Long getExpressId() {
		return expressId;
	}

	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

}
