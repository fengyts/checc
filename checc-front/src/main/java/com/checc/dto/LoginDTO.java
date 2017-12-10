package com.checc.dto;

public class LoginDTO extends BaseDTO {

	/**  */
	private static final long serialVersionUID = 2928178304893971370L;

	private String mobile;
	private String password;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
