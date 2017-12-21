package com.checc.vo;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 竞拍详情页-->出价记录列表数据模型
 * </pre>
 *
 * @author fengyts
 * @version $Id: AuctionListVO.java, v 0.1 2017年12月21日 下午9:33:25 fengyts Exp $
 */
public class AuctionListVO extends BaseVO {

	/**  */
	private static final long serialVersionUID = 2922001297115301240L;
	private Long tpId;
	private String itemTitle;
	private Boolean isOnlyMe;
	private List<ListTableVO> auctionList;

	public class ListTableVO {
		/** 出价时间 */
		private Date bidTime;
		/** 出价人 */
		private String bidder;
		/** 出价次数 */
		private Integer bidNum;
		/** 出价西币 */
		private Integer totalCurrency;
		/** 当前价 */
		private Double currenctAuctPrice;
		/** 是否领先 */
		private Boolean isAhead;

		public Date getBidTime() {
			return bidTime;
		}

		public void setBidTime(Date bidTime) {
			this.bidTime = bidTime;
		}

		public String getBidder() {
			return bidder;
		}

		public void setBidder(String bidder) {
			this.bidder = bidder;
		}

		public Integer getBidNum() {
			return bidNum;
		}

		public void setBidNum(Integer bidNum) {
			this.bidNum = bidNum;
		}

		public Integer getTotalCurrency() {
			return totalCurrency;
		}

		public void setTotalCurrency(Integer totalCurrency) {
			this.totalCurrency = totalCurrency;
		}

		public Double getCurrenctAuctPrice() {
			return currenctAuctPrice;
		}

		public void setCurrenctAuctPrice(Double currenctAuctPrice) {
			this.currenctAuctPrice = currenctAuctPrice;
		}

		public Boolean getIsAhead() {
			return isAhead;
		}

		public void setIsAhead(Boolean isAhead) {
			this.isAhead = isAhead;
		}

	}

	public Long getTpId() {
		return tpId;
	}

	public void setTpId(Long tpId) {
		this.tpId = tpId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Boolean getIsOnlyMe() {
		return isOnlyMe;
	}

	public void setIsOnlyMe(Boolean isOnlyMe) {
		this.isOnlyMe = isOnlyMe;
	}

	public List<ListTableVO> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(List<ListTableVO> auctionList) {
		this.auctionList = auctionList;
	}

}
