package com.checc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ng.bayue.common.model.TokenModel;
import ng.bayue.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.checc.constants.UserConstants;

import annotation.Token;

/**
 * <pre>
 * token验证拦截器
 * </pre>
 *
 * @author fengyts
 * @version $Id: TokenInterceptor.java, v 0.1 2017年12月9日 下午12:46:10 fengyts Exp
 *          $
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean enable = true;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Token token = handlerMethod.getMethodAnnotation(Token.class);
			if (null == token) { // 不需要token验证
				return super.preHandle(request, response, handler);
			}
			// 校验token
			HttpSession session = request.getSession();
			String tokenServer = (String) session
					.getAttribute(UserConstants.VisitConstant.REQUEST_TOKEN);
			if (StringUtils.isBlank(tokenServer)) { // 服务器token失效
				logger.error(request.getRequestURI()
						+ "validate visit token failure : server visit token is empty!");
				throw new Exception(request.getRequestURI()
						+ "validate visit token failure : server visit token is empty!");
			}
			String tokenReq = request.getParameter(UserConstants.VisitConstant.REQUEST_TOKEN);
			if (StringUtils.isBlank(tokenReq)) { // 请求中未携带token,非法请求
				logger.error(request.getRequestURI()
						+ "validate visit token failure : request visit token is empty!");
				throw new Exception(request.getRequestURI()
						+ "validate visit token failure : request visit token is empty!");
			}
			generateToken(request);

			if (!tokenServer.equals(tokenReq)) {
				logger.error(request.getRequestURI()
						+ "validate visit token failure : visit token is illegality!");
				throw new Exception(request.getRequestURI()
						+ "validate visit token failure : visit token is illegality!");
			}

		}
		return super.preHandle(request, response, handler);
	}

	private void generateToken(HttpServletRequest request) {
		TokenModel model = new TokenModel();
		request.getSession().setAttribute(UserConstants.VisitConstant.REQUEST_TOKEN,
				model.getBaseKey());
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
