package com.checc.dto;

/**
 * <pre>
 * 找回密码
 * </pre>
 *
 * @author fengyts
 * @version $Id: FgPwdDTO.java, v 0.1 2017年12月2日 下午8:48:57 fengyts Exp $
 */
public class FgPwdDTO extends BaseDTO {

	private static final long serialVersionUID = 3557249879378457879L;

	/** 默认第1步 */
	public static final int DEFAULT_STEP_NUM = 0;

	/** 找回全局token,所有步骤公用,所有步骤会话有效期30分钟 */
	private String fgToken;

	/** 上一步验证通过后会生成新的局部token,供下一步验证,从步骤2开始,此参数必须,否则认为是无效请求 */
	private String fgStepToken;

	/** 当前找回步骤,默认第一步 */
	private Integer stepNum = DEFAULT_STEP_NUM;

	private String mobile;
	private String captcha;
	private String smsCode;

	private String password;
	private String password1;

	public String getFgToken() {
		return fgToken;
	}

	public void setFgToken(String fgToken) {
		this.fgToken = fgToken;
	}

	public String getFgStepToken() {
		return fgStepToken;
	}

	public void setFgStepToken(String fgStepToken) {
		this.fgStepToken = fgStepToken;
	}

	public Integer getStepNum() {
		return stepNum;
	}

	public void setStepNum(Integer stepNum) {
		this.stepNum = stepNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

}
