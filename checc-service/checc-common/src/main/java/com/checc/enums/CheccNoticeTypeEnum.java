package com.checc.enums;

public enum CheccNoticeTypeEnum {

	OTHER("01", "其他"), HELPER_CENTER("02", "帮助中心"), AGREEMENT("03", "用户协议");

	public String code;
	public String desc;

	CheccNoticeTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (CheccNoticeTypeEnum pe : CheccNoticeTypeEnum.values()) {
			if (code.equals(pe.code)) {
				return pe.desc;
			}
		}
		return null;
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
	
}
