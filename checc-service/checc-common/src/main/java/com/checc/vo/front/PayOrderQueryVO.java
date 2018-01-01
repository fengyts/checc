package com.checc.vo.front;

import java.util.Date;

import com.checc.vo.BaseVO;

public class PayOrderQueryVO extends BaseVO {

	private static final long serialVersionUID = -7503858381007498401L;

	/** 支付订单编号 */
	private String dpOrderNo;
	/** 支付类型 */
	private String depositType;
	private String depositTypeDesc;
	/** 订单金额，西币值（个） */
	private Integer depositAmt;
	/** 实际支付人名币（折扣值 * 充值的西币个数） */
	private Double realCNY;

	private Date depositTime;

	/** 支付状态：SUCCESS-充值成功；FAILURE-充值失败 */
	private String depositStatus;

	public String getDpOrderNo() {
		return dpOrderNo;
	}

	public void setDpOrderNo(String dpOrderNo) {
		this.dpOrderNo = dpOrderNo;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getDepositTypeDesc() {
		return depositTypeDesc;
	}

	public void setDepositTypeDesc(String depositTypeDesc) {
		this.depositTypeDesc = depositTypeDesc;
	}

	public Integer getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(Integer depositAmt) {
		this.depositAmt = depositAmt;
	}

	public Double getRealCNY() {
		return realCNY;
	}

	public void setRealCNY(Double realCNY) {
		this.realCNY = realCNY;
	}

	public Date getDepositTime() {
		return depositTime;
	}

	public void setDepositTime(Date depositTime) {
		this.depositTime = depositTime;
	}

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}

}
