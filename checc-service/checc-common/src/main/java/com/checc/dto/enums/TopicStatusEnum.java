package com.checc.dto.enums;

public enum TopicStatusEnum {

	// 01-未开始；02-进行中；03-已结束
	NotStarted("01", "未开始"), InProgress("02", "进行中"), End("03", "已结束");

	private String code;

	private String desc;

	private TopicStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (TopicStatusEnum pe : TopicStatusEnum.values()) {
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
