package com.checc.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import ng.bayue.common.CommonResultMessage;

import com.checc.model.SmsCodeRedisModel;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.CheccUserAO;
import com.checc.dto.enums.SmsTypeEnum;

@Controller
@RequestMapping({ "/user" })
public class LoginController {
	
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
	public void captcha(HttpServletResponse response) {
//		String captcha64 = userAO.getCaptcha();
		String captcha64 = "";
//		String captcha64 = cg.toImageBase64(captcha);

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
			e.printStackTrace();
		}
	}
	
	@RequestMapping({"/sendSms"})
	@ResponseBody
	public CommonResultMessage sendSms(String mobile){
		if(userAO.frequencyValid(mobile)){
			return CommonResultMessage.failure("短信发送过于频繁");
		}
		SmsCodeRedisModel model = new SmsCodeRedisModel();
		model.setMobile(mobile);
		model.setSmsType(SmsTypeEnum.Sms_Register.getCode());
		CommonResultMessage msg = userAO.sendSmsCode(model);
		return msg;
	}

}
