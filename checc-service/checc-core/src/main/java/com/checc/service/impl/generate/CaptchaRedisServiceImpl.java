package com.checc.service.impl.generate;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.checc.model.CaptchaRedisModel;
import com.checc.service.generate.CaptchaRedisService;

import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.RedisCacheService;

@Service
public class CaptchaRedisServiceImpl implements CaptchaRedisService {

	public static final String KEY_CAPTCHA = "CAPTCHA_";
	public int LIVE_TIME = RedisCacheTimeConstant.DEFAULT_TIME; // (单位秒)缓存时间30分钟

	@Resource(name = "redisCacheService1")
	RedisCacheService redisCacheService1;

	@Override
	public void create(CaptchaRedisModel model) {
		String key = generateKey(model);
		model.setCaptchaKey(key);

		String captcha = model.getCaptcha();
		model.setCaptcha(captcha);

		redisCacheService1.setRedisCache(key, captcha, LIVE_TIME);
	}

	@Override
	public RedisModelStatusEnum check(CaptchaRedisModel model) {
		return null;
	}

	@Override
	public void remove(CaptchaRedisModel model) {
	}

	@Override
	public String generateKey(CaptchaRedisModel model) {
		return model.getBaseKey();
	}

}
