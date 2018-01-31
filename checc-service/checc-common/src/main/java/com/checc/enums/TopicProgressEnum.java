package com.checc.enums;

public enum TopicProgressEnum {

	NotStarted("01", "未开始"), InProgress("02", "进行中"), AUCTION_FLOW("03", "流拍"), AUCTION_SUCCESS("04", "成功");

	public String code;
	public String desc;

	TopicProgressEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (TopicProgressEnum e : TopicProgressEnum.values()) {
			if (code.equals(e.code)) {
				return e.desc;
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
