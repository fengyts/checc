package com.checc.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.CheccUserAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.dto.FgPwdDTO;
import com.checc.dto.LoginDTO;
import com.checc.dto.RegisterDTO;
import com.checc.enums.SmsTypeEnum;
import com.checc.model.SmsCodeRedisModel;
import com.checc.utils.CookieUtils;

import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.TokenModel;
import ng.bayue.util.StringUtils;
import ng.bayue.validate.Validator;

/**
 * <pre>
 * 用户通用控制器,不需要登陆
 * </pre>
 *
 * @author fengyts
 * @version $Id: UserCommonController.java, v 0.1 2017年12月9日 下午7:46:53 fengyts
 *          Exp $
 */
@Controller
@RequestMapping({ "/user" })
public class UserCommonController {

	private static Logger logger = LoggerFactory.getLogger(UserCommonController.class);

	@Autowired
	private CheccUserAO userAO;

	@RequestMapping({ "/login" })
	public String loginIndex(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils cookieUtil = new CookieUtils();
		TokenModel tk = new TokenModel();
		String key = "checcUserLoginCkTk" + tk.getKey();
		String value = tk.getBaseKey();
		cookieUtil.setCookie(request, response, key, value, cookieUtil.getCheccDomain(), 1800);
		return "/login/login";
	}

	@RequestMapping({ "/loginAjax" })
	public String loginAjaxIndex(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils cookieUtil = new CookieUtils();
		TokenModel tk = new TokenModel();
		String key = "checcUserLoginCkTk" + tk.getKey();
		String value = tk.getBaseKey();
		cookieUtil.setCookie(request, response, key, value, cookieUtil.getCheccDomain(), 1800);
		return "/login/login_ajax";
	}

	@RequestMapping({ "/getLoginInfo" })
	@ResponseBody
	public CommonResultMessage getLoginInfo(HttpServletRequest request) {
		CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(
				UserConstants.USER_SESSION_KEY);
		return null != userDO ? new CommonResultMessage(userDO) : CommonResultMessage
				.failure(CommonMessages.UNLOGIN);
	}

	@RequestMapping({ "/logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		request.getSession().removeAttribute(UserConstants.USER_SESSION_KEY);
		if(StringUtils.isBlank(returnUrl)){
			returnUrl = "/";
		}
		return "redirect:" + returnUrl;
	}

	@RequestMapping({ "/register" })
	public String register() {
		return "/login/register";
	}

	@RequestMapping({ "/captcha" })
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		String captcha64 = userAO.getCaptcha(request);
		response.setContentType("image/*"); // 设置返回的文件类型
		try {
			// 对字节数组字符串进行Base64解码并生成图片
			Base64 decoder = new Base64();
			byte[] b = decoder.decode(captcha64);// Base64解码
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = response.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.info("get captcha exception:", e);
		}
	}

	@RequestMapping({ "/sendSms" })
	@ResponseBody
	public CommonResultMessage sendSms(String mobile, String type) {
		if (StringUtils.isBlank(mobile)) {
			return CommonResultMessage.validParameterNull("请输入手机号");
		}
		if (!Validator.isMobile(mobile)) {
			return CommonResultMessage.validParameterNull("手机号格式不对");
		}
		if (userAO.frequencyValid(mobile)) {
			return CommonResultMessage.failure("短信发送过于频繁");
		}
		SmsCodeRedisModel model = new SmsCodeRedisModel();
		model.setMobile(mobile);
		SmsTypeEnum st = SmsTypeEnum.Sms_Other;
		if ("01".equals(type)) {
			st = SmsTypeEnum.Sms_Register;
		} else {
			st = SmsTypeEnum.Sms_Forgot_Password;
		}
		model.setSmsType(st);
		CommonResultMessage msg = userAO.sendSmsCode(model);
		return msg;
	}

	@RequestMapping({ "/validateMobile" })
	@ResponseBody
	public boolean validateMobile(String mobile) {
		return userAO.isExistMobile(mobile);
	}

	@RequestMapping({ "/doRegister" })
	@ResponseBody
	public CommonResultMessage doRegister(HttpServletRequest request, HttpServletResponse response,
			RegisterDTO dto) {
		CommonResultMessage crm = userAO.register(request, dto);
		return crm;
	}

	@RequestMapping({ "/doLogin", "/doLoginAjax" })
	@ResponseBody
	public CommonResultMessage doLogin(HttpServletRequest request, HttpServletResponse response,
			LoginDTO dto) {
		CommonResultMessage crm = userAO.login(request, dto);
		return crm;
	}

	/**
	 * <pre>
	 * 找回密码
	 * </pre>
	 *
	 * @return
	 */
	@RequestMapping(value = { "/fgpwd" }, method = { RequestMethod.GET })
	public String fgPwd() {
		return "/login/fgpwd";
	}

	/**
	 * <pre>
	 * 找回密码form
	 * </pre>
	 *
	 * @param request
	 * @param model
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = { "/fgform" }, method = { RequestMethod.GET })
	public String stepForm(HttpServletRequest request, Model model, FgPwdDTO dto) {
		int stepNum = dto.getStepNum();
		CommonResultMessage crm = null;
		try {
			crm = userAO.recoveredPwd(request, model, dto);
		} catch (Exception e) {
			logger.info("fogot password exception, step-{}, exception: {}", stepNum, e);
			return "/login/fg_invalid";
		}
		if (FgPwdDTO.DEFAULT_STEP_NUM == stepNum) {
			model.addAttribute("stepNum", stepNum + 1);
			return "/login/fg_step1";
		}
		Object dataT = crm.getData();
		if (null == dataT) {
			dataT = -1;
		}
		int data = (int) dataT;
		int result = crm.getResult();

		String mobile = dto.getMobile();
		if (1 == stepNum) {
			model.addAttribute("mobileRv", mobile);
			if (CommonResultMessage.Success == result) {
				model.addAttribute("mobileSecurity", StringUtils.securityMobile(mobile));
				return "/login/fg_step2";
			} else {
				if (-1 == data) {
					return "/login/fg_invalid";
				} else {
					model.addAttribute("captchaRv", dto.getCaptcha());
					model.addAttribute("errorCode", data);
					model.addAttribute("errorMsg", crm.getMessage());
					return "/login/fg_step1";
				}
			}
		}

		if (2 == stepNum) {
			model.addAttribute("mobileRv", mobile);
			if (CommonResultMessage.Success == result) {
				return "/login/fg_step3";
			} else {
				if (-1 == data) {
					return "/login/fg_invalid";
				} else {
					model.addAttribute("errorCode", data);
					model.addAttribute("errorMsg", crm.getMessage());
					return "/login/fg_step2";
				}
			}
		}

		if (3 == stepNum) {
			model.addAttribute("mobileRv", mobile);
			if (CommonResultMessage.Success == result) {
				return "/login/fg_step4";
			} else {
				if (-1 == data) {
					return "/login/fg_invalid";
				} else {
					model.addAttribute("password", dto.getPassword());
					model.addAttribute("password1", dto.getPassword1());
					model.addAttribute("errorMsg", crm.getMessage());
					return "/login/fg_step3";
				}
			}
		}

		return "";
	}
	
	

}
