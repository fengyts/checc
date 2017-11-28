package com.checc.service.generate;

public interface SmsSendService {
	
	/**
	 *
	 * @param smsType 短信类型
	 * @param tplId 聚合短信模板id
	 * @param mobile
	 * @param smsCode
	 * @return
	 * @throws Exception
	 */
	int sendSms(String smsType, Integer tplId, String mobile, String smsCode) throws Exception;
	
}
