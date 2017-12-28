package com.checc.wechatpay.test.compent;

/**
 * 
 * @author xgchen
 *
 */
public interface UserService {
	
	String weixinPay(String userId, String productId) throws Exception;
}
