package com.checc.model;

import ng.bayue.common.BaseRedisModel;

public class CaptchaRedisModel extends BaseRedisModel {

	private static final long serialVersionUID = -9021981320858892721L;

	private String captchaKey;
	private String captcha;

	public String getCaptchaKey() {
		return captchaKey;
	}

	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
