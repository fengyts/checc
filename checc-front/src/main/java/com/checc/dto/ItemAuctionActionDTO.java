package com.checc.dto;

public class ItemAuctionActionDTO extends BaseDTO{

	private static final long serialVersionUID = 9064572015627777256L;
	
	private Long tpId;
	/** 竞拍次数 */
	private Integer auctionTimes;
	/** 话费的总西比值 */
	private Integer totalCurrency;

}
