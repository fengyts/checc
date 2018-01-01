package com.checc.dto;

public class DepositInsertDTO extends BaseDTO {

	private static final long serialVersionUID = 7078096673500912719L;

	/** 充值订单id */
	private Long depositId;

	private Long userId;
	private Integer depositAmount;
	private String depositType;
	private Long discountId;
	private Double discount;

	public Long getDepositId() {
		return depositId;
	}

	public void setDepositId(Long depositId) {
		this.depositId = depositId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
