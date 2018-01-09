package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 充值订单
 * 
 * @author fengyts Sat Dec 30 21:56:51 CST 2017
 */

public class DepositOrderDO extends BaseDO {

	private static final long serialVersionUID = 7326038048171146870L;

	/** 主键 */
	private Long id;

	/** 订单编号，全局唯一 */
	private String orderNo;

	/** 用户id */
	private Long userId;

	/** 充值方式：01-卡密；02-支付宝；03-微信；04-其他方式 */
	private String depositType;

	/** 折扣项id；0-用户自定义金额；大于0：使用系统配置项 */
	private Long discountId;

	/** 充值金额(西币个数) */
	private Integer depositAmount;

	/** 折扣,范围区间:(0,1],如95折则为0.95,默认无折扣为1 */
	private Double discount;

	/** 订单状态:01-待支付；02-支付成功；03:-支付失败；04-支付超时 */
	private String orderStatus;

	/**
	 * 交易状态,查询订单时返回：SUCCESS-支付成功;REFUND-转入退款;NOTPAY-未支付;CLOSED-已关闭;REVOKED-已撤销（
	 * 刷卡支付）;USERPAYING-用户支付中;PAYERROR-支付失败(其他原因，如银行返回失败)
	 */
	private String tradeState;

	/** 交易状态描述，查询订单或支付结果通知时返回 */
	private String tradeStateDesc;

	/** 微信支付订单号，查询订单或支付结果通知时返回 */
	private String transactionId;

	/** 付款银行代码，查询订单或支付结果通知时返回 */
	private String bankType;

	/** 支付错误代码，查询订单或支付结果通知时返回 */
	private String errCode;

	/** 支付错误代码描述，查询订单或支付结果通知时返回 */
	private String errCodeDes;

	/** 订单支付完成时间，格式为yyyyMMddHHmmss，查询订单或支付结果通知时返回 */
	private String timeEnd;

	/** 下单时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 订单编号，全局唯一
	 * 
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 设置 用户id
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 充值方式：01-卡密；02-支付宝；03-微信；04-其他方式
	 * 
	 * @param depositType
	 */
	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	/**
	 * 设置 折扣项id；0-用户自定义金额；大于0：使用系统配置项
	 * 
	 * @param discountId
	 */
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	/**
	 * 设置 充值金额(西币个数)
	 * 
	 * @param depositAmount
	 */
	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

	/**
	 * 设置 折扣,范围区间:(0,1],如95折则为0.95,默认无折扣为1
	 * 
	 * @param discount
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * 设置 订单状态:01-待支付；02-支付成功；03:-支付失败；04-支付超时
	 * 
	 * @param orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 设置
	 * 交易状态,查询订单时返回：SUCCESS-支付成功;REFUND-转入退款;NOTPAY-未支付;CLOSED-已关闭;REVOKED-已撤销
	 * （刷卡支付）;USERPAYING-用户支付中;PAYERROR-支付失败(其他原因，如银行返回失败)
	 * 
	 * @param tradeState
	 */
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	/**
	 * 设置 交易状态描述，查询订单或支付结果通知时返回
	 * 
	 * @param tradeStateDesc
	 */
	public void setTradeStateDesc(String tradeStateDesc) {
		this.tradeStateDesc = tradeStateDesc;
	}

	/**
	 * 设置 微信支付订单号，查询订单或支付结果通知时返回
	 * 
	 * @param transactionId
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * 设置 付款银行代码，查询订单或支付结果通知时返回
	 * 
	 * @param bankType
	 */
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	/**
	 * 设置 支付错误代码，查询订单或支付结果通知时返回
	 * 
	 * @param errCode
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * 设置 支付错误代码描述，查询订单或支付结果通知时返回
	 * 
	 * @param errCodeDes
	 */
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	/**
	 * 设置 订单支付完成时间，格式为yyyyMMddHHmmss，查询订单或支付结果通知时返回
	 * 
	 * @param timeEnd
	 */
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	/**
	 * 设置 下单时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取 主键
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 获取 订单编号，全局唯一
	 * 
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 获取 用户id
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 充值方式：01-卡密；02-支付宝；03-微信；04-其他方式
	 * 
	 * @return depositType
	 */
	public String getDepositType() {
		return depositType;
	}

	/**
	 * 获取 折扣项id；0-用户自定义金额；大于0：使用系统配置项
	 * 
	 * @return discountId
	 */
	public Long getDiscountId() {
		return discountId;
	}

	/**
	 * 获取 充值金额(西币个数)
	 * 
	 * @return depositAmount
	 */
	public Integer getDepositAmount() {
		return depositAmount;
	}

	/**
	 * 获取 折扣,范围区间:(0,1],如95折则为0.95,默认无折扣为1
	 * 
	 * @return discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * 获取 订单状态:01-待支付；02-支付成功；03:-支付失败；04-支付超时
	 * 
	 * @return orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 获取
	 * 交易状态,查询订单时返回：SUCCESS-支付成功;REFUND-转入退款;NOTPAY-未支付;CLOSED-已关闭;REVOKED-已撤销
	 * （刷卡支付）;USERPAYING-用户支付中;PAYERROR-支付失败(其他原因，如银行返回失败)
	 * 
	 * @return tradeState
	 */
	public String getTradeState() {
		return tradeState;
	}

	/**
	 * 获取 交易状态描述，查询订单或支付结果通知时返回
	 * 
	 * @return tradeStateDesc
	 */
	public String getTradeStateDesc() {
		return tradeStateDesc;
	}

	/**
	 * 获取 微信支付订单号，查询订单或支付结果通知时返回
	 * 
	 * @return transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * 获取 付款银行代码，查询订单或支付结果通知时返回
	 * 
	 * @return bankType
	 */
	public String getBankType() {
		return bankType;
	}

	/**
	 * 获取 支付错误代码，查询订单或支付结果通知时返回
	 * 
	 * @return errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * 获取 支付错误代码描述，查询订单或支付结果通知时返回
	 * 
	 * @return errCodeDes
	 */
	public String getErrCodeDes() {
		return errCodeDes;
	}

	/**
	 * 获取 订单支付完成时间，格式为yyyyMMddHHmmss，查询订单或支付结果通知时返回
	 * 
	 * @return timeEnd
	 */
	public String getTimeEnd() {
		return timeEnd;
	}

	/**
	 * 获取 下单时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}