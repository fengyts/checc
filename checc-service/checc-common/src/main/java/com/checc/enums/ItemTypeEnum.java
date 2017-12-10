package com.checc.enums;

public enum ItemTypeEnum {

	ITEM_AUCTION("01", "竞拍商品"), ITEM_EXCHANGE("02", "兑换商品");

	private String code;
	private String desc;

	private ItemTypeEnum(String code, String desc) {
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
		for (ItemTypeEnum menuTypeEnum : ItemTypeEnum.values()) {
			if (code.equals(menuTypeEnum.code)) {
				return menuTypeEnum.desc;
			}
		}
		return null;
	}

}
