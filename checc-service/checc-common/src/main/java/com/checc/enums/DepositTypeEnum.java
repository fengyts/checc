package com.checc.enums;

/**
 * <pre>
 * 充值类型枚举类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: DepositTypeEnum.java, v 0.1 2017年12月20日 上午9:33:59 lenovopc Exp
 *          $
 */
public enum DepositTypeEnum {

	RECHARGE_CARD("01", "卡密"), ALIPAY("02", "支付宝"), WECHAT("03", "微信"), OTHER("04", "其他");

	public String code;
	public String desc;

	DepositTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (DepositTypeEnum e : DepositTypeEnum.values()) {
			if (code.equals(e.code)) {
				return e.desc;
			}
		}
		return null;
	}

}
