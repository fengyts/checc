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
import org.springframework.ui.Model;

import com.checc.constants.SmsTemplate;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.dto.FgPwdDTO;
import com.checc.dto.LoginDTO;
import com.checc.dto.RegisterDTO;
import com.checc.enums.SmsTypeEnum;
import com.checc.model.SmsCodeRedisModel;
import com.checc.service.CheccUserService;
import com.checc.service.generate.SmsCodeRedisService;
import com.checc.service.generate.SmsSendService;

import constants.TokenTypeConstant;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.FrequencyModel;
import ng.bayue.common.model.TokenModel;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.FrequencyService;
import ng.bayue.service.TokenService;
import ng.bayue.util.CaptchaGenerator;
import ng.bayue.util.SecurityUtil;
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
	// @Autowired
	// private CaptchaRedisService captchaRedisService;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private SmsCodeRedisService smsCodeRedisService;
	@Autowired
	private TokenService tokenService;

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

	/**
	 * <pre>
	 * 聚合短信验证码频率控制校验是否超限制
	 * </pre>
	 *
	 * @param mobile
	 * @return true- 频率超过限制；false-未超过
	 */
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
		if (!Validator.isMobile(mobile)) {
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
		if (!Validator.isPasswordStrong(password)) {
			return CommonResultMessage.validParameterNull("密码长度6~18且必须包含大小写字母和数字");
		}
		if (StringUtils.isBlank(password1)) {
			return CommonResultMessage.validParameterNull("确认密码不能为空");
		}
		password1 = aes.decrypt(password1);
		if (!password.equals(password1)) {
			return CommonResultMessage.validParameterNull("两次密码不一样");
		}

		if (isExistMobile(mobile)) {
			return CommonResultMessage.failure("手机号已经存在");
		}

		// 校验验证码
		if (!validCaptcha(request, captcha)) {
			return CommonResultMessage.failure("验证码不正确");
		}
		// 校验短信验证码
		SmsCodeRedisModel model = new SmsCodeRedisModel();
		model.setMobile(mobile);
		model.setSmsCode(smsCode);
		model.setSmsType(SmsTypeEnum.Sms_Register);
		RedisModelStatusEnum modelStatus = smsCodeRedisService.check(model);
		if (RedisModelStatusEnum.OVERDUE == modelStatus) {
			return CommonResultMessage.failure("短信验证码失效");
		}
		if (RedisModelStatusEnum.ERROR == modelStatus) {
			return CommonResultMessage.failure("短信验证码不正确");
		}

		CheccUserDO user = new CheccUserDO();
		// Date date = new Date();
		// user.setCreateTime(date);
		// user.setLastLoginTime(date);
		// user.setModifyTime(date);
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
	public boolean isExistMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return true;
		}
		if (!Validator.isMobile(mobile)) {
			return true;
		}
		CheccUserDO userDO = new CheccUserDO();
		userDO.setMobile(mobile);
		List<CheccUserDO> list = userService.selectDynamic(userDO);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * <pre>
	 * 忘记密码找回
	 * </pre>
	 *
	 * @param dto
	 * @return msg data值：-1-会话过期,非法请求; 0-校验不通过; 1-校验通过; 2-业务数据校验错误
	 */
	public CommonResultMessage recoveredPwd(HttpServletRequest request, Model model, FgPwdDTO dto)
			throws Exception {
		int stepNum = dto.getStepNum();
		AESUtils aes = new AESUtils();
		String mobile = dto.getMobile();
		if (FgPwdDTO.DEFAULT_STEP_NUM == stepNum) { // 刚进入忘记密码页面,生成全局token
			TokenModel tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_GOLBAL);
			tokenService.create(tk);
			model.addAttribute("fgToken", aes.encrypt(tk.getKey() + tk.getToken()));
			return new CommonResultMessage(1);
		} else if (1 == stepNum) {
			final String fgToken = dto.getFgToken();
			if (StringUtils.isBlank(fgToken)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}
			// 校验全局 token
			TokenModel tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_GOLBAL);
			String fgTokenDe = aes.decrypt(fgToken);
			tk.setKey(fgTokenDe.substring(0, 32));
			tk.setToken(fgTokenDe.substring(32));
			if (!validToken(tk, false)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}

			model.addAttribute("fgToken", fgToken);
			String captcha = dto.getCaptcha();

			if (StringUtils.isBlank(captcha)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "验证码不能为空", 2);
			}
			// 校验验证码
			if (!validCaptcha(request, captcha)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "验证码不正确", 2);
			}

			if (StringUtils.isBlank(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "手机号不能为空", 2);
			}
			if (!Validator.isMobile(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "手机号格式不对", 2);
			}

			// 校验手机号是否注册
			if (!isExistMobile(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "手机号尚未注册", 2);
			}

			// 创建分步token
			tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_STEP);
			tokenService.create(tk);
			model.addAttribute("fgStepToken", aes.encrypt(tk.getKey() + tk.getToken()));

			// 发送短信验证码
			if (frequencyValid(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "操作过于频繁", 0);
			}
			SmsCodeRedisModel smsModel = new SmsCodeRedisModel();
			smsModel.setMobile(mobile);
			smsModel.setSmsType(SmsTypeEnum.Sms_Forgot_Password);
			CommonResultMessage crm = sendSmsCode(smsModel);
			/*
			 * if(CommonResultMessage.Failure == crm.getResult()){ return new
			 * CommonResultMessage(CommonResultMessage.Failure, "短信发送失败,请稍后再试",
			 * 0); }
			 */
			return new CommonResultMessage(1);
		} else if (2 == stepNum) {
			final String fgToken = dto.getFgToken();
			if (StringUtils.isBlank(fgToken)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}
			// 校验全局 token
			TokenModel tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_GOLBAL);
			String fgTokenDe = aes.decrypt(fgToken);
			tk.setKey(fgTokenDe.substring(0, 32));
			tk.setToken(fgTokenDe.substring(32));
			if (!validToken(tk, false)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}

			model.addAttribute("fgToken", fgToken);

			// 校验分步 token
			final String fgStepToken = dto.getFgStepToken();
			tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_STEP);
			String fgStepTokenDe = aes.decrypt(fgStepToken);
			tk.setKey(fgStepTokenDe.substring(0, 32));
			tk.setToken(fgStepTokenDe.substring(32));
			if (!validToken(tk, true)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}

			model.addAttribute("fgStepToken", fgStepToken);

			if (StringUtils.isBlank(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "非法请求", 2);
			}
			if (!Validator.isMobile(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "非法请求", 2);
			}
			String smsCode = dto.getSmsCode();
			if (StringUtils.isBlank(smsCode)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "短信验证码不能为空", 2);
			}

			// 校验短信验证码
			SmsCodeRedisModel smsModel = new SmsCodeRedisModel();
			smsModel.setMobile(mobile);
			smsModel.setSmsCode(smsCode);
			smsModel.setSmsType(SmsTypeEnum.Sms_Forgot_Password);
			RedisModelStatusEnum modelStatus = smsCodeRedisService.check(smsModel);
			if (RedisModelStatusEnum.OVERDUE == modelStatus) {
				return new CommonResultMessage(CommonResultMessage.Failure, "验证码失效", 2);
			}
			if (RedisModelStatusEnum.ERROR == modelStatus) {
				return new CommonResultMessage(CommonResultMessage.Failure, "验证码不正确", 2);
			}

			// 手机号验证通过，创建新的分步token
			tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_STEP);
			tokenService.create(tk);
			model.addAttribute("fgStepToken", aes.encrypt(tk.getKey() + tk.getToken()));

		} else if (3 == stepNum) {
			final String fgToken = dto.getFgToken();
			if (StringUtils.isBlank(fgToken)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}
			// 校验全局 token
			TokenModel tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_GOLBAL);
			String fgTokenDe = aes.decrypt(fgToken);
			tk.setKey(fgTokenDe.substring(0, 32));
			tk.setToken(fgTokenDe.substring(32));
			if (!validToken(tk, false)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}

			model.addAttribute("fgToken", fgToken);

			// 校验分步 token
			final String fgStepToken = dto.getFgStepToken();
			tk = new TokenModel();
			tk.setTokenType(TokenTypeConstant.FG_PWD_TK_STEP);
			String fgStepTokenDe = aes.decrypt(fgStepToken);
			tk.setKey(fgStepTokenDe.substring(0, 32));
			tk.setToken(fgStepTokenDe.substring(32));
			if (!validToken(tk, true)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "链接已经失效", -1);
			}

			model.addAttribute("fgStepToken", fgStepToken);

			if (StringUtils.isBlank(mobile)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "请求异常", -1);
			}

			// 校验新密码
			String password = dto.getPassword();
			String password1 = dto.getPassword1();
			
			if (StringUtils.isBlank(password) || StringUtils.isBlank(password1)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "密码不能为空", 2);
			}
			
			password = aes.decrypt(password);
			password1 = aes.decrypt(password1);
			if(!password.equals(password1)){
				return new CommonResultMessage(CommonResultMessage.Failure, "两次输入的密码不一致", 2);
			}
			if (!Validator.isPasswordStrong(password)) {
				return new CommonResultMessage(CommonResultMessage.Failure, "密码6~18位且必须包含大小写字母和数字",
						2);
			}

			// 更新密码
			userService.recoveredPwd(mobile, password);

		} else {
		}

		return CommonResultMessage.success();
	}

	/**
	 * <pre>
	 * 校验验证码
	 * </pre>
	 *
	 * @param request
	 * @param captcha
	 * @return true-校验通过,false-不通过
	 */
	private boolean validCaptcha(HttpServletRequest request, String captcha) {
		HttpSession session = request.getSession();
		String captchaServer = (String) session.getAttribute("captcha");
		if (StringUtils.isBlank(captchaServer) || !captcha.equalsIgnoreCase(captchaServer)) {
			return false;
		}
		return true;
	}

	/**
	 * <pre>
	 * 校验token
	 * </pre>
	 *
	 * @param fgToken
	 * @param isDel校验后是否删除
	 *            ，true-删除,false-不删除
	 * @return true-校验通过，false-校验不通过
	 */
	private boolean validToken(TokenModel tk, Boolean isDel) {
		String fgToken = tk.getToken();
		if (StringUtils.isBlank(fgToken)) {
			return false;
		}
		RedisModelStatusEnum ckStatus = tokenService.check(tk);
		if (RedisModelStatusEnum.ERROR == ckStatus || RedisModelStatusEnum.OVERDUE == ckStatus) {
			return false;
		}
		if (isDel) {
			tokenService.remove(tk);
		}
		return true;
	}

	public CommonResultMessage login(HttpServletRequest request, LoginDTO dto) {
		String mobile = dto.getMobile();
		String password = dto.getPassword();
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
			return CommonResultMessage.failure("用户名或密码错误");
		}
		CheccUserDO userDO = userService.selectByMobile(mobile);
		if(null == userDO){
			return CommonResultMessage.failure("用户名或密码错误");
		}
		AESUtils aes = new AESUtils();
		password = aes.decrypt(password);
		String pwdReq = SecurityUtil.hashToStr(password, userDO.getSalt(), 2);
		if (!pwdReq.equals(userDO.getPassword())) {
			return CommonResultMessage.failure("用户名或密码错误");
		}

		HttpSession session = request.getSession();
		session.setAttribute(UserConstants.USER_SESSION_KEY, userDO);
		//session.setMaxInactiveInterval(10); // 测试用，有效期10秒
		
		// 更新登录时间
		CheccUserDO ud = new CheccUserDO();
		ud.setId(userDO.getId());
		ud.setLastLoginTime(new Date());
		userService.update(ud, false);
		
		return new CommonResultMessage(userDO);
	}

}
