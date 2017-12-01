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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.CheccUserAO;
import com.checc.dto.RegisterDTO;
import com.checc.dto.enums.SmsTypeEnum;
import com.checc.model.SmsCodeRedisModel;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.util.StringUtils;
import ng.bayue.util.crypto.AESUtils;
import ng.bayue.validate.Validator;

@Controller
@RequestMapping({ "/user" })
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private CheccUserAO userAO;

	@RequestMapping({ "/login" })
	public String loginIndex() {
		return "/login/login";
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
	public CommonResultMessage sendSms(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return CommonResultMessage.validParameterNull("请输入手机号");
		}
		if(!Validator.isMobile(mobile)){
			return CommonResultMessage.validParameterNull("手机号格式不对");
		}
		if (userAO.frequencyValid(mobile)) {
			return CommonResultMessage.failure("短信发送过于频繁");
		}
		SmsCodeRedisModel model = new SmsCodeRedisModel();
		model.setMobile(mobile);
		model.setSmsType(SmsTypeEnum.Sms_Register);
		CommonResultMessage msg = userAO.sendSmsCode(model);
		return msg;
	}
	
	@RequestMapping({ "/validateMobile" })
	@ResponseBody
	public boolean validateMobile (String mobile){
		return userAO.isExistMobile(mobile);
	}

	@RequestMapping({ "/doRegister" })
	@ResponseBody
	public CommonResultMessage doLogin(HttpServletRequest request, HttpServletResponse response, RegisterDTO dto) {
		CommonResultMessage crm = userAO.register(request, dto);
		return crm;
	}
	
	@RequestMapping(value = {"/fgpwd"} , method = {RequestMethod.GET})
	public String fgPwd (){
		return "/login/fgpwd";
	}

}
