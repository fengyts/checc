package com.checc.dto.enums;

public enum ItemPictureTypeEnum {

	PIC_PRIMARY("01", "主图"), PIC_DETAILS("02", "详情图片");

	private String code;
	private String desc;

	private ItemPictureTypeEnum(String code, String desc) {
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
		for (ItemPictureTypeEnum menuTypeEnum : ItemPictureTypeEnum.values()) {
			if (code.equals(menuTypeEnum.code)) {
				return menuTypeEnum.desc;
			}
		}
		return null;
	}
}
