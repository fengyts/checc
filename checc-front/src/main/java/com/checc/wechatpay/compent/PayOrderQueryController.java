package com.checc.wechatpay.compent;

import javax.servlet.http.HttpServletRequest;

import ng.bayue.common.CommonResultMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;

/**
 * <pre>
 * 微信支付订单查询控制器
 * </pre>
 *
 * @author fengyts
 * @version $Id: PayOrderQueryController.java, v 0.1 2017年12月30日 下午8:04:37
 *          fengyts Exp $
 */
@Controller
@RequestMapping({ "/pay/wechat" })
public class PayOrderQueryController {

	private static Logger logger = LoggerFactory.getLogger(PayOrderQueryController.class);

	/** 业务异常错误页面 */
	private static final String ERROR_VIEW_PATH = "/business/deposit/dept_act_err";

	@Autowired
	private WechatPayAO wechatPayAO;

	@RequestMapping(value = { "/orderQuery" }, method = { RequestMethod.GET })
	public String payOrderQuery(HttpServletRequest request, Model model, String dpOrderNo) {
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(
				UserConstants.USER_SESSION_KEY);
		if (null == userDO) {
			model.addAttribute("errMsg", "服务器异常,请联系客服...");
			return ERROR_VIEW_PATH;
		}

		if (StringUtils.isBlank(dpOrderNo)) {
			model.addAttribute("errMsg", "服务器异常,请联系客服...");
			return ERROR_VIEW_PATH;
		}

		CommonResultMessage crm = wechatPayAO.queryOrderStatus(dpOrderNo, userDO.getId());
		
		model.addAttribute("result", crm);
		return "/business/pay/pay_success";
	}

}
