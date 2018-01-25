package com.checc.enums;

public enum PurchaseStatusEnum {

	NOT_APPLY("01", "待申请"), WAIT_BUY("02", "待购车"), HAS_BUY("03", "已购车"), ABANDON("04", "放弃购车");

	public String code;
	public String desc;

	PurchaseStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (PurchaseStatusEnum pe : PurchaseStatusEnum.values()) {
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
