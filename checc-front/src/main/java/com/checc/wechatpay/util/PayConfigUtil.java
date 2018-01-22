package com.checc.wechatpay.util;

/**
 * <pre>
 * 微信支付公共参数设置
 * </pre>
 *
 * @author lenovopc
 * @version $Id: PayConfigUtil.java, v 0.1 2017年12月28日 上午10:32:08 lenovopc Exp $
 */
public final class PayConfigUtil {

	public final static String APP_ID = "wxeec85623859fc30e"; // 公众账号appid
	public final static String APP_SECRET = "";
	// 商户号（改为自己实际的）
	public final static String MCH_ID = "1490875382";
	// api_key,设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	public final static String API_KEY = "Rs5LwuHVbUTmb2tchiasLLQkhYUqX6p0";

	// 微信统一下单接口地址
	public final static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信支付订单查询接口地址
	public final static String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 企业向个人账号付款的URL
	public final static String SEND_EED_PACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	// 微信支付回调接口地址
//	public final static String NOTIFY_URL = "http://47.94.199.26:8080/checc-front/pay/wechat/payCallback";

	public final static String CREATE_IP = "192.168.0.107";// 发起支付ip（改为自己实际的）

	// 微信下单接口body参数值(商品描述 或者 网站title值)
	public final static String BODY = "车西西-西币充值";

	/**
	 * <pre>
	 * 交易类型常量类，详见微信支付文档：
	 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: PayConfigUtil.java, v 0.1 2017年12月29日 下午3:03:21 fengyts Exp
	 *          $
	 */
	public static enum PayTradeTypeEnum {
		/** 前面三种均为：统一下单接口trade_type的传参 */
		JSAPI("JSAPI", "公众号"), NATIVE("NATIVE", "原生扫码支付"), APP("APP", "app支付"),

		/** 刷卡支付有单独的支付接口，不调用统一下单接口 */
		MICROPAY("MICROPAY", "刷卡支付");

		public String code;
		public String desc;

		PayTradeTypeEnum(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

	}

}
