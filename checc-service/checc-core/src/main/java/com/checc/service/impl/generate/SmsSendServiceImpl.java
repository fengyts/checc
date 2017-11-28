package com.checc.service.impl.generate;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.constants.SmsTemplate;
import com.checc.domain.SmsRecordDO;
import com.checc.model.SmsSendResult;
import com.checc.service.SmsRecordService;
import com.checc.service.generate.JuHeSmsSendService;
import com.checc.service.generate.SmsSendService;

import ng.bayue.util.StringUtils;

@Service
public class SmsSendServiceImpl implements SmsSendService {

	private static Logger logger = LoggerFactory.getLogger(SmsSendServiceImpl.class);

	@Autowired
	private JuHeSmsSendService juHeSmsService;
	@Autowired
	private SmsRecordService smsRecordService;

	@Override
	public int sendSms(String smsType, Integer tplId, String mobile, String smsCode) throws Exception {
		if (null == tplId || StringUtils.isBlank(mobile) || StringUtils.isBlank(smsCode)) {
			return -2;
		}
		SmsSendResult ssr = juHeSmsService.sendSms(tplId, mobile, smsCode);
		if (null == ssr || null == ssr.getError_code()) {
			logger.info("1.sms code send failure , submit failure, reason : {}", ssr.getReason());
			return -1;
		}

		SmsRecordDO sr = new SmsRecordDO();

		String reason = ssr.getReason();
		int errorCode = ssr.getError_code();
		if (SmsSendResult.SUCCESS == errorCode) {
			SmsSendResult.Result result = ssr.getResult();
			if (null != result) {
				sr.setSmsId(result.getSid());
			}
			sr.setSmsStatus("01");
		} else {
			logger.info("2.JuHe SmsCode send failure , errorCode :{0} , reason : {1}", errorCode, reason);
			sr.setSmsStatus("02");
		}
		sr.setStatusCode(errorCode);
		sr.setMobile(mobile);
		sr.setContent("");
		sr.setCreateTime(new Date());
		sr.setReason(reason);
		sr.setSmsType(smsType);
		sr.setSpCode(SmsTemplate.SMS_SP_CODE);
		
		try {
			smsRecordService.insert(sr);
		} catch (Exception e) {
			logger.info("3_01.sms code send failure : insert sms recorder exception");
			throw e;
		}

		return errorCode;
	}

}
