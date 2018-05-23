package com.checc.myeleme.domain;

import java.io.Serializable;

public class ResultJson implements Serializable {

	private static final long serialVersionUID = 405256736925023332L;

	public static final int SUCCESS = 1;
	public static final int FAILURE = 0;

	private int errno = SUCCESS;
	private Object data;

	public ResultJson() {
		super();
	}

	public ResultJson(int error, Object data) {
		this.errno = error;
		this.data = data;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultJson success(Object data) {
		return new ResultJson(SUCCESS, data);
	}

	public static ResultJson failure(Object data) {
		return new ResultJson(FAILURE, data);
	}

}
