package com.checc.ao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.model.SmsCodeRedisModel;
import com.checc.service.generate.CaptchaRedisService;
import com.checc.service.generate.SmsCodeRedisService;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.FrequencyModel;
import ng.bayue.service.FrequencyService;
import ng.bayue.util.CaptchaGenerator;

@Service
public class CheccUserAO {
	
	@Autowired
	private CaptchaRedisService captchaRedisService;
	@Autowired
	private SmsCodeRedisService smsRedisService;
	@Autowired
	private FrequencyService frequencyService;
	
	public String getCaptcha(){
		CaptchaGenerator cg = new CaptchaGenerator();
		String captcha = cg.generateCaptcha();
		
		return cg.toImageBase64(captcha);
	}
	
	public CommonResultMessage sendSmsCode(String mobile){
		SmsCodeRedisModel model = new SmsCodeRedisModel();
		model.setMobile(mobile);
		model.setSmsType("SMS_CODE_1");
		FrequencyModel fm = new FrequencyModel();
		fm.setBinuessType("SMS_CODE_1");
		fm.setKey(mobile);
		fm.setSeconds(120);
		fm.setTimes(2);
		boolean fq = frequencyService.overLoad(fm);
		if(!fq){
			return CommonResultMessage.failure("一分钟只能发送2次");
		}
		return CommonResultMessage.success();
	}

}
