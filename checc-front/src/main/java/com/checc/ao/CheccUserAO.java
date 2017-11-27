package com.checc.ao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.constants.SmsTemplate;
import com.checc.domain.SmsRecordDO;
import com.checc.model.SmsCodeRedisModel;
import com.checc.service.CheccUserService;
import com.checc.service.SmsRecordService;
import com.checc.service.generate.CaptchaRedisService;
import com.checc.service.generate.SmsCodeRedisService;
import com.checc.service.generate.JuHeSmsSendService;

import net.sf.json.JSONObject;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.FrequencyModel;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.service.FrequencyService;
import ng.bayue.util.CaptchaGenerator;
import ng.bayue.util.StringUtils;

@Service
public class CheccUserAO {

	private static Logger logger = LoggerFactory.getLogger(CheccUserAO.class);

	@Autowired
	private CaptchaRedisService captchaRedisService;
	@Autowired
	private SmsCodeRedisService smsCodeRedisService;
	@Autowired
	private FrequencyService frequencyService;
	@Autowired
	private JuHeSmsSendService smsSendService;

	@Autowired
	private CheccUserService userService;
	@Autowired
	private SmsRecordService smsRecordService;

	public String getCaptcha() {
		CaptchaGenerator cg = new CaptchaGenerator();
		String captcha = cg.generateCaptcha();

		return cg.toImageBase64(captcha);
	}

	public CommonResultMessage sendSmsCode(SmsCodeRedisModel model) {
		String smsCode = String.valueOf(StringUtils.getRandomNum(4));
		String mobile = model.getMobile();
		String result = smsSendService.sendSms(SmsTemplate.ContentTemplate.Register.getContentId(),
				mobile, smsCode);

		if (StringUtils.isBlank(result)) {
			logger.info("sms code send failure : submit failure");
			return CommonResultMessage.failure("短信验证码发送失败");
		}
		JSONObject obj = JSONObject.fromObject(result);
		Object errorCode = obj.get("error_code");
		Object reason = obj.get("reason");
		if (obj.getInt("error_code") == 0) { // 发送成功
			String sid = (String) JSONObject.fromObject(obj.get("result")).get("sid");
			SmsRecordDO sr = new SmsRecordDO();
			sr.setContent(null);
			sr.setCreateTime(new Date());
			sr.setMobile(mobile);
			sr.setSmsId(sid);
			sr.setSmsStatus("01");
			sr.setSpCode(SmsTemplate.SMS_SP_CODE);
			
			smsRecordService.insert(sr);
		} else {
			logger.info("error_code:" + errorCode + "; reason:" + reason);
			return CommonResultMessage.failure("短信验证码发送失败");
		}
		
		model.setSmsCode(smsCode);
		
		smsCodeRedisService.create(model);
		
		return CommonResultMessage.success();
	}

	public boolean frequencyValid(String mobile) {
		FrequencyModel fm = new FrequencyModel();
		fm.setKey(mobile);
		fm.setBinuessType("SMS_CODE_ONE_MINUTES");// 一分钟最多发送2次
		fm.setSeconds(2 * RedisCacheTimeConstant.ONE_MINUTES);
		fm.setTimes(2);
		boolean fqOne = frequencyService.overLoad(fm);
		if (!fqOne) {
			logger.info("sms code send is high frequency: one minutes more than two times !");
			return true;
		}
		fm.setBinuessType("SMS_CODE_HALF_HOUR");// 30 分钟内做多发送3次
		fm.setSeconds(RedisCacheTimeConstant.HALF_HOUR);
		fm.setTimes(3);
		boolean fqHalfHour = frequencyService.overLoad(fm);
		if (!fqHalfHour) {
			logger.info("sms code send is high frequency: half hours more than three times !");
			return true;
		}
		return false;
	}

}
