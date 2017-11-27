package com.checc.model;

import ng.bayue.common.BaseRedisModel;

public class SmsCodeRedisModel extends BaseRedisModel {

	private static final long serialVersionUID = -1545700063218578296L;

	private String mobile;
	/** 短信类型 */
	private String smsType;
	/** 短信验证码 */
	private String smsCode;
	/** 存活时间 */
	private Integer liveTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Integer getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(Integer liveTime) {
		this.liveTime = liveTime;
	}

}
