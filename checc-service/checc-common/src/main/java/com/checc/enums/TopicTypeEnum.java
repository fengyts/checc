package com.checc.enums;

public enum TopicTypeEnum {
	
	TOPIC_AUCTION("01", "竞拍"), TOPIC_EXCHANGE("02", "兑换");

	private String code;
	private String desc;

	private TopicTypeEnum(String code, String desc) {
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

	public static String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (TopicTypeEnum menuTypeEnum : TopicTypeEnum.values()) {
			if (code.equals(menuTypeEnum.code)) {
				return menuTypeEnum.desc;
			}
		}
		return null;
	}
}
