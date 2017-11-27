package com.checc.dto.enums;

public enum SmsTypeEnum {

	Sms_Register("01", "注册"), Sms_Forgot_Password("02", "找回密码"), Sms_Other("03", "其他");

	private String code;
	private String desc;

	private SmsTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (SmsTypeEnum pe : SmsTypeEnum.values()) {
			if (code.equals(pe.code)) {
				return pe.desc;
			}
		}
		return null;
	}
	

}
