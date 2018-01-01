package com.checc.wechatpay.common;

import org.apache.commons.lang3.StringUtils;

public class ReturnWechatData {

	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE = "FAIL";

	/** 默认返回信息 */
	public static final String DEFAULT_RETURN_MSG = "OK";

	/** 返回状态码 */
	private String returnCode = SUCCESS;
	/** 返回信息 */
	private String returnMsg = DEFAULT_RETURN_MSG;

	/** 返回给微信系统商户接收通知成功并校验成功的回调报文 */
	private static final String RESULT_SUCCESS_XML = "<xml>"
			+ "<return_code><![CDATA[SUCCESS]]></return_code>"
			+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

	// public ReturnWechatData(String returnCode, String returnMsg) {
	// this.returnCode = returnCode;
	// this.returnMsg = returnMsg;
	// }

	/**
	 * <pre>
	 * 商户接收通知成功并校验成功返回报文
	 * </pre>
	 *
	 * @return
	 */
	public static String successData() {
		return RESULT_SUCCESS_XML;
	}

	/**
	 * <pre>
	 * 商户 接收通知失败  或者校验失败  返回报文
	 * </pre>
	 *
	 * @param returnMsg
	 * @return
	 */
	public static String failureData(String returnMsg) {
		if (StringUtils.isBlank(returnMsg)) {
			returnMsg = "业务处理失败:未知错误";
		}
		return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA["
				+ returnMsg + "]]></return_msg>" + "</xml> ";
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}
