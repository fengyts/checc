package com.checc.enums;

public enum ShipmentsStatusEnum {

	NOT_SHIPMENTS("01", "待发货"), HAS_SHIPMENTS("02", "已发货"), HAS_SIGNFOR("03", "已签收");

	public String code;
	public String desc;

	ShipmentsStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (ShipmentsStatusEnum pe : ShipmentsStatusEnum.values()) {
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
