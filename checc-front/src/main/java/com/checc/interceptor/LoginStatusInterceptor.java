package com.checc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;

/**
 * <pre>
 * 登陆态校验拦截器
 * </pre>
 *
 * @author fengyts
 * @version $Id: LoginStatusInterceptor.java, v 0.1 2017年12月9日 下午7:30:38 fengyts
 *          Exp $
 */
public class LoginStatusInterceptor extends HandlerInterceptorAdapter {

	private boolean enable = true;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(
				UserConstants.USER_SESSION_KEY);
		if (null == userDO) { // 未登陆
			String contextPath = request.getContextPath();
			// 如果是ajax请求响应头会有，x-requested-with
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				// String returnUrl = ReturnUrl.getReturnUrl(request);
				response.setContentType("text/html;charset=utf-8");
				response.setHeader("errorCode", "999");
				response.addHeader("errorMessage", "用户未登陆");
				response.addHeader(contextPath +"returnUrl", "/user/login?returnUrl=");
			} else {
				request.getSession().setAttribute("returnUrl", request.getRequestURL().toString());
				response.sendRedirect(contextPath + "/user/login?returnUrl=" + request.getRequestURL());
			}
			return false;
		}

		return super.preHandle(request, response, handler);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
