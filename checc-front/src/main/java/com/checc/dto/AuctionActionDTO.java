package com.checc.dto;

public class AuctionActionDTO extends BaseDTO {

	private static final long serialVersionUID = 9064572015627777256L;

	/** token key */
	private String auctactTK;
	/** topicItem id */
	private Long tpId;
	/** 竞拍出价次数 */
	private Integer auctionTimes;
	/** 话费的总西比值 */
	private Integer totalCurrency;

	public String getAuctactTK() {
		return auctactTK;
	}

	public void setAuctactTK(String auctactTK) {
		this.auctactTK = auctactTK;
	}

	public Long getTpId() {
		return tpId;
	}

	public void setTpId(Long tpId) {
		this.tpId = tpId;
	}

	public Integer getAuctionTimes() {
		return auctionTimes;
	}

	public void setAuctionTimes(Integer auctionTimes) {
		this.auctionTimes = auctionTimes;
	}

	public Integer getTotalCurrency() {
		return totalCurrency;
	}

	public void setTotalCurrency(Integer totalCurrency) {
		this.totalCurrency = totalCurrency;
	}

}
