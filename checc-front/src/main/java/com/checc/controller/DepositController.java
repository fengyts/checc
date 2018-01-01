package com.checc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ng.bayue.common.CommonResultMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.checc.ao.DepositAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.domain.DepositConfigDO;
import com.checc.dto.DepositDTO;

@Controller
@RequestMapping(value = { "/user/deposit" })
public class DepositController {

	private static Logger logger = LoggerFactory.getLogger(DepositController.class);

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

	@RequestMapping(value = { "/dptAct" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String depositAction(HttpServletRequest request, Model model,
			RedirectAttributes redAttr, DepositDTO dto) {
		Long userId = null;
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(
				UserConstants.USER_SESSION_KEY);
		if (null != userDO) {
			model.addAttribute("checcUser", userDO);
			userId = userDO.getId();
		}
		CommonResultMessage crm = depositAO.depositSecurityAct(dto, userId);
		if (CommonResultMessage.Success != crm.getResult()) {
			return "/business/deposit/dept_act_err";
		}

		try {
			// redAttr.addAttribute("depositTk", dto.getDepositTk());
			// redAttr.addAttribute("depositAmt", dto.getDepositAmt());
			// redAttr.addAttribute("discountId", dto.getDiscountId());
			// redAttr.addAttribute("discount", dto.getDiscount());

			// String redAttrs = JSONObject.toJSONString(dto);
			// AESUtils aes = new AESUtils();
			// redAttrs = aes.encrypt(redAttrs);
			// redAttr.addAttribute("redAttrs", redAttrs);
			return "forward:/pay/wechat/payQRCode";
		} catch (Exception e) {
			logger.info("dpAct:封装微信支付业务参数异常：{}", e);
			return "/business/deposit/dept_act_err";
		}

	}

}
