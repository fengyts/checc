package com.checc.wechatpay.util;

/**
 * <pre>
 * 微信支付公共参数设置
 * </pre>
 *
 * @author lenovopc
 * @version $Id: PayConfigUtil.java, v 0.1 2017年12月28日 上午10:32:08 lenovopc Exp $
 */
public class PayConfigUtil {

	public final static String APP_ID = "wxeec85623859fc30e"; // 公众账号appid
	public final static String APP_SECRET = "";
	// 商户号（改为自己实际的）
	public final static String MCH_ID = "1490875382";
	// api_key,设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	public final static String API_KEY = "Rs5LwuHVbUTmb2tchiasLLQkhYUqX6p0";

	// 微信同一下单接口地址
	public final static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信支付回调接口地址
	public final static String NOTIFY_URL = "http://47.94.199.26:8088/test-weixiinpay-priject/wechartpay/callback";
	// 企业向个人账号付款的URL
	public final static String SEND_EED_PACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

	public final static String CREATE_IP = "192.168.0.107";// 发起支付ip（改为自己实际的）

}
