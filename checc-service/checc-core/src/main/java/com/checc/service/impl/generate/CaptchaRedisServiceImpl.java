package com.checc.service.impl.generate;

import org.springframework.stereotype.Service;

import com.checc.model.CaptchaRedisModel;
import com.checc.service.generate.CaptchaRedisService;

import ng.bayue.enums.RedisModelStatusEnum;

@Service
public class CaptchaRedisServiceImpl implements CaptchaRedisService{

	@Override
	public void create(CaptchaRedisModel model) {
	}

	@Override
	public RedisModelStatusEnum check(CaptchaRedisModel model) {
		return null;
	}

	@Override
	public void remove(CaptchaRedisModel model) {
	}

}
