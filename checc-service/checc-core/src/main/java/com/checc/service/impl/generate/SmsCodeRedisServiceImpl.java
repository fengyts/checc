package com.checc.service.impl.generate;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.checc.model.SmsCodeRedisModel;
import com.checc.service.generate.SmsCodeRedisService;

import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.RedisCacheService;
import ng.bayue.util.StringUtils;

@Service
public class SmsCodeRedisServiceImpl implements SmsCodeRedisService {

	public static final String KEY_SMS_CODE = "SMS_CODE_";
	public int LIVE_TIME = RedisCacheTimeConstant.HALF_HOUR; // (单位秒)缓存时间30分钟

	@Resource(name = "redisCacheService1")
	RedisCacheService redisCacheService1;

	@Override
	public void create(SmsCodeRedisModel model) {
		if (null != model.getLiveTime()) {
			LIVE_TIME = model.getLiveTime();
		}
		redisCacheService1.setRedisCacheString(generateKey(model), model.getSmsCode(), LIVE_TIME);
	}

	@Override
	public RedisModelStatusEnum check(SmsCodeRedisModel model) {
		String smsCode = model.getSmsCode();
		String smsCodeCache = redisCacheService1.getRedisCacheString(generateKey(model));
		if (StringUtils.isEmpty(smsCodeCache)) {
			return RedisModelStatusEnum.OVERDUE;
		}
		if (smsCode.equals(smsCodeCache)) {
			return RedisModelStatusEnum.CORRECT;
		}
		return RedisModelStatusEnum.ERROR;
	}

	@Override
	public void remove(SmsCodeRedisModel model) {
		String mobile = model.getMobile();
		if (StringUtils.isNotBlank(mobile)) {
			redisCacheService1.deleteRedisCache(generateKey(model));
		}
	}

	private String generateKey(SmsCodeRedisModel model) {
		return KEY_SMS_CODE + model.getMobile();
	}

}
