package com.checc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CommonController {

	/**
	 * 找不到页面跳转到404
	 * 
	 */
//	@RequestMapping(value = "/**")
//	public String noFound(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		response.setStatus(HttpServletResponse.SC_FOUND);
//		if(handler instanceof HandlerMethod){}
//		return "/error/error";
//	}
	
	/**
	 * <pre>
	 * 错误页面404,500等处理
	 * </pre>
	 *
	 * @param statusCode
	 * @return
	 */
	@RequestMapping("/error")
	public String error500(int statusCode){
		//HttpServletResponse.SC_FOUND
		if(HttpStatus.INTERNAL_SERVER_ERROR.value() == statusCode){
			return "/error/500";
		}
		if(HttpStatus.NOT_FOUND.value() == statusCode){
			return "/error/404";
		}
		
		return "/error/error";
	}

}
