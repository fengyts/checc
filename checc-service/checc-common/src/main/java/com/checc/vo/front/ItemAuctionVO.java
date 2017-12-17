package com.checc.vo.front;

import com.checc.vo.BaseVO;

public class ItemAuctionVO extends BaseVO {

	private static final long serialVersionUID = 6683566891261774660L;

	/** topicItem 主键ID */
	private Long id;
	/** 商品名称 */
	private String itemTitle;

	/** 竞拍商品单次出价西币 */
	private Integer auctionCurrency;
	/** 竞拍商品单次出价最大次数 */
	private Integer auctionMaxTimes;

	/** 用户可用西币 */
	private Integer useableCurrency;

	/** 兑换商品价格 */
	private Double exchangeAmount;
	/** 兑换商品每人限兑次数 */
	private Integer exchangeLimitNum;

	/** 专题状态 */
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Integer getAuctionCurrency() {
		return auctionCurrency;
	}

	public void setAuctionCurrency(Integer auctionCurrency) {
		this.auctionCurrency = auctionCurrency;
	}

	public Integer getAuctionMaxTimes() {
		return auctionMaxTimes;
	}

	public void setAuctionMaxTimes(Integer auctionMaxTimes) {
		this.auctionMaxTimes = auctionMaxTimes;
	}

	public Integer getUseableCurrency() {
		return useableCurrency;
	}

	public void setUseableCurrency(Integer useableCurrency) {
		this.useableCurrency = useableCurrency;
	}

	public Double getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public Integer getExchangeLimitNum() {
		return exchangeLimitNum;
	}

	public void setExchangeLimitNum(Integer exchangeLimitNum) {
		this.exchangeLimitNum = exchangeLimitNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
