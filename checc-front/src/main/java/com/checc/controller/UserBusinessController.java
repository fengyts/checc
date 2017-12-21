package com.checc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.checc.ao.UserCenterAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;

/**
 * <pre>
 * 用户业务控制器,需要登录后才能访问
 * </pre>
 *
 * @author fengyts
 * @version $Id: UserBusinessController.java, v 0.1 2017年12月9日 下午7:47:31 fengyts
 *          Exp $
 */

@Controller
@RequestMapping(value = { "/user/bis" })
public class UserBusinessController {

	@Autowired
	private UserCenterAO userCenterAO;

	/**
	 * <pre>
	 * 个人中心
	 * </pre>
	 *
	 * @return
	 */
	@RequestMapping(value = { "/membercenter" }, method = { RequestMethod.GET })
	public String memberCenter(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		CheccUserDO userDO = (CheccUserDO) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if (null != userDO) {
			model.addAttribute("checcUser", userDO);
		}
		userCenterAO.userCurrencyInfo(model, userDO);
		return "/business/user/user_center";
	}

	@RequestMapping(value = { "/deposit" }, method = { RequestMethod.GET })
	public String deposit(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		CheccUserDO userDO = (CheccUserDO) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if (null != userDO) {
			model.addAttribute("checcUser", userDO);
		}
		return "/user/deposit";
	}

}
