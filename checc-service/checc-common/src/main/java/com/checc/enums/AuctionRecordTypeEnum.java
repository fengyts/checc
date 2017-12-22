package com.checc.enums;

/**
 * <pre>
 * 交易记录类型枚举类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: AuctionRecordTypeEnum.java, v 0.1 2017年12月20日 上午9:28:55
 *          lenovopc Exp $
 */
public enum AuctionRecordTypeEnum {

	AUCTION("01", "竞拍"), EXCHANGE("02", "兑换"), DEPOSIT("03", "充值"), REFUND("04", "竞拍退回");

	public String code;
	public String desc;

	AuctionRecordTypeEnum(String code, String desc) {
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
		for (AuctionRecordTypeEnum recordTypeEnum : AuctionRecordTypeEnum.values()) {
			if (code.equals(recordTypeEnum.code)) {
				return recordTypeEnum.desc;
			}
		}
		return null;
	}

}
