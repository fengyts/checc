package com.checc.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.common.CheccSecirytyAO;

import ng.bayue.common.CommonResultMessage;

@Controller
@RequestMapping({ "/check" })
public class TokenController {
	
	@Autowired
	private CheccSecirytyAO securityAO;

	@RequestMapping(value = { "checcSecurity" }, method = { RequestMethod.GET })
	@ResponseBody
	public CommonResultMessage checcSecurity(HttpServletRequest request, HttpServletResponse response) {
		
		return CommonResultMessage.success();
	}

}
