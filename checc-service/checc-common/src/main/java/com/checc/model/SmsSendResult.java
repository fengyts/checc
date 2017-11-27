package com.checc.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SmsSendResult implements Serializable {

	private static final long serialVersionUID = -4570658612294410849L;

	/** 发送成功 */
	public static final int SUCCESS = 0;
	public static final int FAILURE = -1; // 异常错误

	/** 返回码 */
	private Integer error_code;
	private String reason;
	private Result result;

	public SmsSendResult() {
	}

	public SmsSendResult(Integer errorCode, String reason) {
		this(errorCode, reason, null);
	}

	public SmsSendResult(Integer errorCode, String reason, Result result) {
		this.error_code = errorCode;
		this.reason = reason;
		this.result = result;
	}

	public class Result implements Serializable {

		private static final long serialVersionUID = -5013006669768268496L;

		/** 发送数量 */
		private Integer count;
		/** 扣除条数 */
		private Integer fee;
		/** 短信ID */
		private String sid;

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public Integer getFee() {
			return fee;
		}

		public void setFee(Integer fee) {
			this.fee = fee;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}

	}

	public Integer getErrorCode() {
		return error_code;
	}

	public void setErrorCode(Integer error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * <pre>
	 * 聚合接口返回 错误原因
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: SmsSendResult.java, v 0.1 2017年11月27日 下午11:52:51 fengyts
	 *          Exp $
	 */
	public static final class JuHeResultReason {

		public static final String ERR_CODE_0 = "短信发送成功";

		/** 服务级错误码参照(error_code) */
		public static final String ERR_CODE_205401 = "错误的手机号码";
		public static final String ERR_CODE_205402 = "错误的短信模板ID";
		public static final String ERR_CODE_205403 = "网络错误,请重试";
		public static final String ERR_CODE_205404 = "发送失败，具体原因请参考返回reason";
		public static final String ERR_CODE_205405 = "号码异常/同一号码发送次数过于频繁";
		public static final String ERR_CODE_205406 = "不被支持的模板";

		/** 系统级错误码参照 */

		public static final String ERR_CODE_10001 = "错误的请求KEY";
		public static final String ERR_CODE_10002 = "该KEY无请求权限";
		public static final String ERR_CODE_10003 = "KEY过期";
		public static final String ERR_CODE_10004 = "错误的OPENID";
		public static final String ERR_CODE_10005 = "应用未审核超时，请提交认证";

		public static final String ERR_CODE_10007 = "未知的请求源";
		public static final String ERR_CODE_10008 = "被禁止的IP";
		public static final String ERR_CODE_10009 = "被禁止的KEY";

		public static final String ERR_CODE_10011 = "当前IP请求超过限制";
		public static final String ERR_CODE_10012 = "请求超过次数限制";
		public static final String ERR_CODE_10013 = "测试KEY超过请求限制";
		public static final String ERR_CODE_10014 = "系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)";

		public static final String ERR_CODE_10020 = "接口维护";
		public static final String ERR_CODE_10021 = "接口停用";

	}

	public static Map<String, String> msg = new HashMap<String, String>();

	static {
		Class<JuHeResultReason> cl = JuHeResultReason.class;
		Field[] fields = cl.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			// Integer code =
			// Integer.valueOf(name.substring(name.lastIndexOf("_") + 1));
			String code = name.substring(name.lastIndexOf("_") + 1);
			try {
				String value = (String) field.get(name);
				msg.put(code, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
			}
		}
	}

}
