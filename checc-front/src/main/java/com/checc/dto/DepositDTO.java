package com.checc.dto;

public class DepositDTO extends BaseDTO {

	private static final long serialVersionUID = 685290075928559279L;

	/** token */
	private String depositTk;
	/** 配置项id：-1-非法请求；0-用户自定义金额；大于0：选用系统配置的选项 */
	private Long discountId;
	/** 充值金额,这里只允许冲整数值,不能是小数 */
	private Integer depositAmt;
	private Double discount;

	public String getDepositTk() {
		return depositTk;
	}

	public void setDepositTk(String depositTk) {
		this.depositTk = depositTk;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public Integer getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(Integer depositAmt) {
		this.depositAmt = depositAmt;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
