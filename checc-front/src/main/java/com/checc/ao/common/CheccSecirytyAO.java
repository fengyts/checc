package com.checc.ao.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import ng.bayue.common.CommonResultMessage;

@Service
public class CheccSecirytyAO {

	public CommonResultMessage createToken(HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		request.getSession().setAttribute("checcFgPwdTk", token);
		return CommonResultMessage.success();
	}

}
