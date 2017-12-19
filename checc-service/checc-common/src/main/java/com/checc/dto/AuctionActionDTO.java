package com.checc.dto;

import com.checc.domain.CheccUserDO;

public class AuctionActionDTO extends BaseDTO {

	private static final long serialVersionUID = 9064572015627777256L;

	private CheccUserDO checcUserDO;

	/** token key */
	private String auctactTK;
	/** topicItem id */
	private Long tpId;
	/** 竞拍出价次数 */
	private Integer auctionTimes;
	/** 话费的总西比值 */
	private Integer totalCurrency;

	public CheccUserDO getCheccUserDO() {
		return checcUserDO;
	}

	public void setCheccUserDO(CheccUserDO checcUserDO) {
		this.checcUserDO = checcUserDO;
	}

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
