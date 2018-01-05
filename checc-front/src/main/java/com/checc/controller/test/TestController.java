package com.checc.controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.utils.CookieUtils;

import ng.bayue.common.model.TokenModel;

@Controller
@RequestMapping({"checctest"})
public class TestController {
	
	@RequestMapping({ "/mtLoginStyle" })
	public String loginNew(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils cookieUtil = new CookieUtils();
		TokenModel tk = new TokenModel();
		String key = "checcUserLoginCkTk" + tk.getKey();
		String value = tk.getBaseKey();
		cookieUtil.setCookie(request, response, key, value, cookieUtil.getCheccDomain(), 1800);
		return "/test/login_mt";
	}

}
