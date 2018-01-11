package com.checc.vo;

import java.util.Date;

public class CheccUserVO extends BaseVO {

	private static final long serialVersionUID = 7770118770192766247L;

	private Long userId;

	private String mobile;

	private String nickname;

	/** 西币余额 */
	private Integer totalCurrency;

	/** 冻结西币(正在参与竞拍的) */
	private Integer freeze;

	private Date modifyTime;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getTotalCurrency() {
		return totalCurrency;
	}

	public void setTotalCurrency(Integer totalCurrency) {
		this.totalCurrency = totalCurrency;
	}

	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
