package com.checc.vo.front;

import java.util.Date;

import com.checc.vo.BaseVO;

/**
 * <pre>
 * 用户中心西币交易记录数据模型
 * </pre>
 *
 * @author lenovopc
 * @version $Id: CurrencyRecordVO.java, v 0.1 2018年1月2日 下午5:09:43 lenovopc Exp $
 */
public class CurrencyRecordVO extends BaseVO {

	private static final long serialVersionUID = -5423907802897545804L;

	/** 交易记录id */
	private Long id;
	/** topicItem id */
	private Long tpId;
	/** 交易时间 */
	private Date bidTime;
	/** 交易类型 */
	private String recordType;
	private String itemTitle;
	/** 出价、兑换次数 */
	private Integer auctNum;
	/** 交易西币值(兑换,竞拍,回退) */
	private Integer totalCurrency;
	/** 充值西比值 */
	private Integer depositCurrency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTpId() {
		return tpId;
	}

	public void setTpId(Long tpId) {
		this.tpId = tpId;
	}

	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Integer getAuctNum() {
		return auctNum;
	}

	public void setAuctNum(Integer auctNum) {
		this.auctNum = auctNum;
	}

	public Integer getTotalCurrency() {
		return totalCurrency;
	}

	public void setTotalCurrency(Integer totalCurrency) {
		this.totalCurrency = totalCurrency;
	}

	public Integer getDepositCurrency() {
		return depositCurrency;
	}

	public void setDepositCurrency(Integer depositCurrency) {
		this.depositCurrency = depositCurrency;
	}

}
