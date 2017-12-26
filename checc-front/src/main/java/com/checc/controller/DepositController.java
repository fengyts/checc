package com.checc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.checc.ao.DepositAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.domain.DepositConfigDO;

@Controller
@RequestMapping(value = { "/user/deposit" })
public class DepositController {

	@Autowired
	private DepositAO depositAO;

	@RequestMapping(value = { "/dplist" }, method = { RequestMethod.GET })
	public String depositList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long userId = null;
		CheccUserDO userDO = (CheccUserDO) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if (null != userDO) {
			model.addAttribute("checcUser", userDO);
			userId = userDO.getId();
		}
		List<DepositConfigDO> listAll = depositAO.getDepositList(model, userId);

		model.addAttribute("dpList", listAll);
		return "/business/deposit/deposit_center";
	}

}
