package com.checc.enums;

/**
 * <pre>
 * 支付订单支付状态
 * </pre>
 *
 * @author fengyts
 * @version $Id: PayOrderStatusEnum.java, v 0.1 2017年12月29日 下午12:53:07 fengyts
 *          Exp $
 */
public enum PayOrderStatusEnum {

	UNPAID("01", "待支付"), 
	SUCCESS("02", "支付成功"), 
	FAILURE("03", "支付失败"), 
	OVER_TIME("04", "支付超时");

	public String code;
	public String desc;

	PayOrderStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDescByCode(String code) {
		if (null == code) {
			return null;
		}
		for (PayOrderStatusEnum pe : PayOrderStatusEnum.values()) {
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
