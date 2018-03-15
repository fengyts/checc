package com.checc.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 专题商品竞拍当前领先者的信息
 * </pre>
 *
 * @author lenovopc
 * @version $Id: LeadEdgeDTO.java, v 0.1 2018年3月15日 上午11:29:41 lenovopc Exp $
 */
public class LeadEdgeDTO implements Serializable {

	private static final long serialVersionUID = -5859030801808393645L;

	private Long userId;
	private String mobile;
	/** 领先者当前出价总次数 */
	private Integer currentAuctTotalCount;
	/** 当前竞拍价格 */
	private Double currentAuctPrice;
	/** 当前出价时间 */
	private Date currentAuctTime;

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

	public Integer getCurrentAuctTotalCount() {
		return currentAuctTotalCount;
	}

	public void setCurrentAuctTotalCount(Integer currentAuctTotalCount) {
		this.currentAuctTotalCount = currentAuctTotalCount;
	}

	public Double getCurrentAuctPrice() {
		return currentAuctPrice;
	}

	public void setCurrentAuctPrice(Double currentAuctPrice) {
		this.currentAuctPrice = currentAuctPrice;
	}

	public Date getCurrentAuctTime() {
		return currentAuctTime;
	}

	public void setCurrentAuctTime(Date currentAuctTime) {
		this.currentAuctTime = currentAuctTime;
	}

}
