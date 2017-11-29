package com.checc.ao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.checc.constants.SmsTemplate;
import com.checc.domain.CheccUserDO;
import com.checc.dto.RegisterDTO;
import com.checc.dto.enums.SmsTypeEnum;
import com.checc.model.SmsCodeRedisModel;
import com.checc.service.CheccUserService;
import com.checc.service.generate.CaptchaRedisService;
import com.checc.service.generate.SmsCodeRedisService;
import com.checc.service.generate.SmsSendService;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.FrequencyModel;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.FrequencyService;
import ng.bayue.util.CaptchaGenerator;
import ng.bayue.util.StringUtils;
import ng.bayue.util.crypto.AESUtils;
import ng.bayue.validate.Validator;

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

		// CaptchaRedisModel model = new CaptchaRedisModel();
		// model.setCaptcha(captcha);
		// captchaRedisService.create(model);

		return cg.toImageBase64(captcha);
	}

	public CommonResultMessage sendSmsCode(SmsCodeRedisModel model) {
		String smsCode = smsCodeDefault;
		String mobile = model.getMobile();
		if (StringUtils.isBlank(mobile)) {
			return CommonResultMessage.validParameterNull("手机号不能为空");
		}
		if (!Validator.isMobile(mobile)) {
			return CommonResultMessage.validParameterNull("手机号格式不对");
		}

		boolean smsRedisFlag = true;
		if ("on".equals(smsSwitch)) { // 短信开关打开
			smsCode = String.valueOf(StringUtils.getRandomNum(4));
			try {
				int tplId = 0;
				SmsTypeEnum smsType = model.getSmsType();
				switch (smsType) {
				case Sms_Register:
					tplId = SmsTemplate.ContentTemplate.Register.getContentId();
					break;
				case Sms_Forgot_Password:
					tplId = SmsTemplate.ContentTemplate.Forgot_Password.getContentId();
					break;
				default:
					break;
				}
				if (0 == tplId) {
					return CommonResultMessage.failure("短信验证码发送失败");
				}
				int res = smsSendService.sendSms(smsType.getCode(), tplId, mobile, smsCode);
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

		return new CommonResultMessage(CommonResultMessage.Success, "短信验证码发送成功,请注意查收");
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

	public CommonResultMessage register(HttpServletRequest request, RegisterDTO dto) {
		String mobile = dto.getMobile();
		String smsCode = dto.getSmsCode();
		String password = dto.getPassword();
		String password1 = dto.getPassword1();
		String captcha = dto.getCaptcha();

		if (StringUtils.isBlank(mobile)) {
			return CommonResultMessage.validParameterNull("手机号不能为空");
		}
		if(!Validator.isMobile(mobile)){
			return CommonResultMessage.validParameterNull("手机号格式不对");
		}
		if (StringUtils.isBlank(smsCode)) {
			return CommonResultMessage.validParameterNull("短信验证码不能为空");
		}
		if (StringUtils.isBlank(captcha)) {
			return CommonResultMessage.validParameterNull("验证码不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return CommonResultMessage.validParameterNull("密码不能为空");
		}
		
		AESUtils aes = new AESUtils();
		password = aes.decrypt(password);
		if(!Validator.isPasswordStrong(password)){
			return CommonResultMessage.validParameterNull("密码长度6~18且必须包含大小写字母和数字");
		}
		if (StringUtils.isBlank(password1)) {
			return CommonResultMessage.validParameterNull("确认密码不能为空");
		}
		password1 = aes.decrypt(password1);
		if(!password.equals(password1)){
			return CommonResultMessage.validParameterNull("两次密码不一样");
		}

		// 校验验证码
		HttpSession session = request.getSession();
		String captchaServer = (String) session.getAttribute("captcha");
		if (StringUtils.isBlank(captchaServer) || !captcha.equalsIgnoreCase(captchaServer)) {
			return CommonResultMessage.failure("验证码不正确");
		}
		// 校验短信验证码
		SmsCodeRedisModel model = new SmsCodeRedisModel();
		model.setMobile(mobile);
		model.setSmsCode(smsCode);
		model.setSmsType(SmsTypeEnum.Sms_Register);
		RedisModelStatusEnum modelStatus = smsCodeRedisService.check(model);
		if(RedisModelStatusEnum.OVERDUE == modelStatus){
			return CommonResultMessage.failure("短信验证码失效");
		}
		if(RedisModelStatusEnum.ERROR == modelStatus){
			return CommonResultMessage.failure("短信验证码不正确");
		}
		
		if(isExistMobile(mobile)){
			return CommonResultMessage.failure("手机号已经存在");
		}
		
		CheccUserDO user = new CheccUserDO();
//		Date date = new Date();
//		user.setCreateTime(date);
//		user.setLastLoginTime(date);
//		user.setModifyTime(date);
		user.setMobile(mobile);
		user.setPassword(password);
		userService.register(user);

		return CommonResultMessage.success();
	}
	
	/**
	 * <pre>
	 * 用户是否存在
	 * </pre>
	 *
	 * @param mobile
	 * @return true-已经存在；false-不存在
	 */
	public boolean isExistMobile(String mobile){
		if(StringUtils.isBlank(mobile)){
			return true;
		}
		if(!Validator.isMobile(mobile)){
			return true;
		}
		CheccUserDO userDO = new CheccUserDO();
		userDO.setMobile(mobile);
		List<CheccUserDO> list = userService.selectDynamic(userDO);
		return CollectionUtils.isNotEmpty(list);
	}

}
