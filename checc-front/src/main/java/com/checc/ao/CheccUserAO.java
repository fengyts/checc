package com.checc.ao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.checc.constants.SmsTemplate;
import com.checc.dto.enums.SmsTypeEnum;
import com.checc.model.CaptchaRedisModel;
import com.checc.model.SmsCodeRedisModel;
import com.checc.service.CheccUserService;
import com.checc.service.generate.CaptchaRedisService;
import com.checc.service.generate.SmsCodeRedisService;
import com.checc.service.generate.SmsSendService;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.FrequencyModel;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.service.FrequencyService;
import ng.bayue.util.CaptchaGenerator;
import ng.bayue.util.StringUtils;
import ng.bayue.validate.ValidatorDefault;

@Service
public class CheccUserAO {

	private static Logger logger = LoggerFactory.getLogger(CheccUserAO.class);

	@Value(value = "#{metaf['sms.switch']}")
	private String smsSwitch;
	@Value(value = "#{metaf['sms.defaultCode']}")
	private String smsCodeDefault;

	@Autowired
	private FrequencyService frequencyService;
	@Autowired
	private CaptchaRedisService captchaRedisService;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private SmsCodeRedisService smsCodeRedisService;

	@Autowired
	private CheccUserService userService;

	public String getCaptcha(HttpServletRequest request) {
		CaptchaGenerator cg = new CaptchaGenerator();
		String captcha = cg.generateCaptcha();
		
		HttpSession session = request.getSession();
		session.setAttribute("captcha", captcha);
		
		//CaptchaRedisModel model = new CaptchaRedisModel();
		//model.setCaptcha(captcha);
		//captchaRedisService.create(model);

		return cg.toImageBase64(captcha);
	}

	public CommonResultMessage sendSmsCode(SmsCodeRedisModel model) {
		String smsCode = smsCodeDefault;
		String mobile = model.getMobile();
		if (StringUtils.isBlank(mobile)) {
			return CommonResultMessage.validParameterNull("手机号不能为空");
		}
		if(!new ValidatorDefault(mobile).isMobile()){
			return CommonResultMessage.validParameterNull("手机号格式不对");
		}

		boolean smsRedisFlag = true;
		if ("on".equals(smsSwitch)) { // 短信开关打开
			smsCode = String.valueOf(StringUtils.getRandomNum(4));
			try {
				int res = smsSendService.sendSms(SmsTypeEnum.Sms_Register.getCode(),
						SmsTemplate.ContentTemplate.Register.getContentId(), mobile, smsCode);
				if (0 != res) {
					smsRedisFlag = false;
				}
			} catch (Exception e) {
				logger.info("3.sms code send failure : submit failure");
				return CommonResultMessage.failure("短信验证码发送失败");
			}
		}

		if (smsRedisFlag) {
			model.setSmsCode(smsCode);
			smsCodeRedisService.create(model);
		}

		return new CommonResultMessage(CommonResultMessage.Success,"短信验证码发送成功,请注意查收");
	}

	public boolean frequencyValid(String mobile) {
		FrequencyModel fm = new FrequencyModel();
		fm.setKey(mobile);
		fm.setBinuessType("SMS_CODE_ONE_MINUTES");// 一分钟最多发送2次
		fm.setSeconds(2 * RedisCacheTimeConstant.ONE_MINUTES);
		fm.setTimes(2);
		boolean fqOne = frequencyService.overLoad(fm);
		if (!fqOne) {
			logger.info("11.sms code send is high frequency: one minutes more than two times !");
			return true;
		}
		fm.setBinuessType("SMS_CODE_HALF_HOUR");// 30 分钟内做多发送3次
		fm.setSeconds(RedisCacheTimeConstant.HALF_HOUR);
		fm.setTimes(3);
		boolean fqHalfHour = frequencyService.overLoad(fm);
		if (!fqHalfHour) {
			logger.info("12.sms code send is high frequency: half hours more than three times !");
			return true;
		}
		return false;
	}

}
