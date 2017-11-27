package com.checc.service.generate;

import com.checc.model.SmsSendResult;

public interface JuHeSmsSendService {
	
	/**
	 * <pre>
	 * 校验短信内容合法性
	 * </pre>
	 *
	 * @param appkey 聚合短信平台key
	 * @param content 短信内容
	 * @return
	 */
	String checkContent(String appkey, String content);
	
	/**
	 * <pre>
	 * 短信发送接口
	 * </pre>
	 *
	 * @param templateId
	 * @param mobile
	 * @param content
	 * @return
	 */
	SmsSendResult sendSms(Integer templateId,String mobile, String smsCode);

}
