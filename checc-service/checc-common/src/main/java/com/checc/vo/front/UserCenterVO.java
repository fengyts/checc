package com.checc.vo.front;

import java.util.List;

import com.checc.vo.BaseVO;

public class UserCenterVO extends BaseVO {

	private static final long serialVersionUID = 8502984456288306544L;

	private Long userId;
	private String mobile;
	/** 用户总的西币 */
	private Integer totalCurrency;
	/** 退回的西币 */
	private Integer refund;

	/** 西币交易记录数量 */
	private Integer currencyTradeNum;

	/** 我参与的竞拍商品列表 */
	private List<?> myAuctionList;

	/** 我参与的兑换商品列表 */
	private List<?> myExchangeList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getTotalCurrency() {
		return totalCurrency;
	}

	public void setTotalCurrency(Integer totalCurrency) {
		this.totalCurrency = totalCurrency;
	}

	public Integer getRefund() {
		return refund;
	}

	public void setRefund(Integer refund) {
		this.refund = refund;
	}

	public Integer getCurrencyTradeNum() {
		return currencyTradeNum;
	}

	public void setCurrencyTradeNum(Integer currencyTradeNum) {
		this.currencyTradeNum = currencyTradeNum;
	}

	public List<?> getMyAuctionList() {
		return myAuctionList;
	}

	public void setMyAuctionList(List<?> myAuctionList) {
		this.myAuctionList = myAuctionList;
	}

	public List<?> getMyExchangeList() {
		return myExchangeList;
	}

	public void setMyExchangeList(List<?> myExchangeList) {
		this.myExchangeList = myExchangeList;
	}

}
