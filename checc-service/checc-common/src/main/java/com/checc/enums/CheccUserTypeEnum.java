package com.checc.enums;

public enum CheccUserTypeEnum {

	VM_ACCOUNT("01", "虚拟账号"), CHECC_USER("02", "车西西客户");

	public String code;
	public String desc;

	private CheccUserTypeEnum(String code, String desc) {
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

}
