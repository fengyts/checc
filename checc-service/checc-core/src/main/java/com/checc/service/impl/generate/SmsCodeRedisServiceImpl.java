package com.checc.service.impl.generate;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.checc.model.SmsCodeRedisModel;
import com.checc.service.generate.SmsCodeRedisService;

import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.RedisCacheService;
import ng.bayue.util.StringUtils;

@Service
public class SmsCodeRedisServiceImpl implements SmsCodeRedisService {

	public static final String KEY_SMS_CODE = "SMS_CODE_";
	public int LIVE_TIME = 60 * 2; // (单位秒)缓存时间2分钟

	@Resource(name = "redisCacheService1")
	RedisCacheService redisCacheService1;

	@Override
	public void create(SmsCodeRedisModel model) {
		if (null != model.getLiveTime()) {
			LIVE_TIME = model.getLiveTime();
		}
		String smsCode = String.valueOf(StringUtils.getRandomNum(4));
		model.setSmsCode(smsCode);
		redisCacheService1.setRedisCache(generateKey(model), model, LIVE_TIME);
	}

	@Override
	public RedisModelStatusEnum check(SmsCodeRedisModel model) {
		return null;
	}

	@Override
	public void remove(SmsCodeRedisModel model) {
	}

	private String generateKey(SmsCodeRedisModel model) {
		return KEY_SMS_CODE + model.getMobile();
	}

}
