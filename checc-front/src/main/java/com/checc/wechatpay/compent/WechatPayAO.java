package com.checc.wechatpay.compent;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.service.RedisCacheService;
import ng.bayue.util.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.checc.domain.DepositOrderDO;
import com.checc.dto.DepositInsertDTO;
import com.checc.enums.DepositTypeEnum;
import com.checc.enums.PayOrderStatusEnum;
import com.checc.service.DepositOrderService;
import com.checc.service.DepositService;
import com.checc.vo.front.PayOrderQueryVO;
import com.checc.wechatpay.util.HttpUtil;
import com.checc.wechatpay.util.PayConfigUtil;
import com.checc.wechatpay.util.PayToolUtil;
import com.checc.wechatpay.util.XMLUtil4jdom;

@Named(value = "wechatPayAO")
public class WechatPayAO {

	private static final Logger logger = LoggerFactory.getLogger(WechatPayAO.class);

	/** 微信支付回调锁前缀 */
	private static final String LOCK_PAY_CALLBACK_KEY = "LOCK_PAY_CALLBACK_";
	/** 支付订单状态查询锁key前缀 */
	private static final String LOCK_PAYORDER_STATUS_QUERY_KEY = "LOCK_PAYORDER_STATUS_QUERY_";
	/** 微信支付订单状态查询次数计数器 */
	private static final String PAYORDER_QUERY_COUNT_KEY = "PAYORDER_QUERY_COUNT_KEY";

	// 账号信息
	private static final String APPID = PayConfigUtil.APP_ID; // appid
	// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
	private static final String MCH_ID = PayConfigUtil.MCH_ID; // 商业号
	// private static final String API_KEY = PayConfigUtil.API_KEY; // key
//	private static final String NOTIFY_URL = PayConfigUtil.NOTIFY_URL; // 回调接口
	@Value("#{metaf['wechat_notify_url']}")
	private String notify_url;

	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;
	@Autowired
	private DepositOrderService depositOrderService;
	@Autowired
	private DepositService depositService;

	/**
	 * <pre>
	 * 微信支付调用微信支付系统接口预下单
	 * </pre>
	 *
	 * @param request
	 * @param dpOrder
	 * @return
	 * @throws Exception
	 */
	public String wechatPayPreOrder(HttpServletRequest request, DepositOrderDO dpOrder)
			throws Exception {
		logger.info("wechat pay order start, ao method start...");
		String nonce_str = UUID.randomUUID().toString().replace("-", "");
		// String spbill_create_ip = IpUtil.getIpAddr(request); // 获取支付发起电脑 ip
		// String spbill_create_ip = "47.94.199.26"; // 获取支付发起电脑 ip
		String spbill_create_ip = getIpAddr(request); // 获取支付发起电脑 ip
		logger.info("wechat pay order request user ip: {}", spbill_create_ip);

		String out_trade_no = dpOrder.getOrderNo();// 支付订单号

		// 计算支付金额，单位：分
		// 价格的单位为分,即：若参数为1,则充值金额为0.01元。
		// total_fee参数值不能有小数，详情参见微信支付文档说明中的交易金额说明
		// <link>https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2</link>
		Double totalFeeDouble = dpOrder.getDepositAmount() * dpOrder.getDiscount() * 100; // 计算实际充值金额,单位：分
		Integer totalFee = totalFeeDouble.intValue(); // 金额不能有小数

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("notify_url", notify_url);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("trade_type", PayConfigUtil.PayTradeTypeEnum.NATIVE.code);
		packageParams.put("body", PayConfigUtil.BODY + "(" + dpOrder.getDepositAmount() + "个)");
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("spbill_create_ip", spbill_create_ip);

		packageParams.put("total_fee", String.valueOf(totalFee));

		// 封装自定义参数
		String attach = String.valueOf(dpOrder.getUserId());
		packageParams.put("attach", attach);

		// 接口数字签名参数
		String sign = PayToolUtil.createSign(packageParams);
		packageParams.put("sign", sign);

		logger.info("wechat pay params: 调用统一下单接口参数：{}", packageParams);
		// 封装请求参数为xml
		String requestXML = PayToolUtil.packageRequestToXmlStr(packageParams);

		// 调用微信统一下单接口生成二维码链接
		String resXml;
		try {
			resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);
			logger.info("wechat pay qrcode result data: {}", resXml);

			Map<String, String> map = XMLUtil4jdom.doXMLParse(resXml);
			if (null == map || map.isEmpty()) {
				logger.info("request wechat pay order exception, "
						+ "the returned qrcode url is blank;请求微信统一下单接口异常,返回二维码链接字符串为空,无法生成二维码");
				return null;
			}
			String urlCode = (String) map.get("code_url");

			return urlCode;
		} catch (Exception e) {
			logger.error("请求微信支付统一下单接口异常", e);
			throw e;
		}

	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
		return ip;
	}

	public CommonResultMessage callBack(String callbackParams) {
		logger.info("wechat pay callback start, ao params : {}", callbackParams);
		String lockCallback = LOCK_PAY_CALLBACK_KEY;
		try {
			// 解析xml成map
			Map<String, String> m = new HashMap<String, String>();
			m = XMLUtil4jdom.doXMLParse(callbackParams.toString());
			Iterator<String> it = null;
			if (null == m || m.isEmpty()) {
				logger.info("微信支付回调解析参数异常：解析xml报文异常");
				return CommonResultMessage.failure("支付通知结果回调异常：报文解析为空");
			}
			// 过滤空 设置 TreeMap
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			it = m.keySet().iterator();
			while (it.hasNext()) {
				String parameter = it.next();
				String parameterValue = m.get(parameter);
				String v = "";
				if (null != parameterValue) {
					v = parameterValue.trim();
				}
				packageParams.put(parameter, v);
			}
			if (null == packageParams || packageParams.isEmpty()) {
				logger.info("微信支付回调解析参数异常：解析xml报文异常");
				return CommonResultMessage.failure("支付通知结果回调异常：报文解析为空");
			}

			// 签名认证
			String sign = packageParams.get("sign");
			if (StringUtils.isBlank(sign)) {
				logger.info("wechat pay callback failure: sign check failure, sign param is blank!");
				return CommonResultMessage.failure("签名校验失败：sign参数为空");
			}
			if (!PayToolUtil.isTenpaySign(packageParams)) {
				logger.info("wechat pay callback failure: sign check failure, sign checked invalid!");
				return CommonResultMessage.failure("签名校验不通过");
			}

			String return_code = packageParams.get("return_code");
			String return_msg = packageParams.get("return_msg");

			if (!"SUCCESS".equals(return_code)) {
				// 调用支付查询接口

				return CommonResultMessage.failure(StringUtils.isBlank(return_msg) ? "网络连接失败"
						: return_msg);
			}

			// 必填参数校验
			if (!validateParams(packageParams)) {
				logger.info("wechat pay callback params invalid, params : {}", packageParams);
				return CommonResultMessage.failure("必填参数校验错误, 数据可能被篡改");
			}

			String out_trade_no = packageParams.get("out_trade_no"); // 商户订单号
			lockCallback = LOCK_PAY_CALLBACK_KEY + out_trade_no;
			// 同一个订单同一时间只能允许一个操作
			if (redisCacheService.lock(lockCallback)) {
				CommonResultMessage crm = handlerCallback(packageParams);
				return crm;
			} else {
				logger.info("wechat pay result notify is too frequency");
				return CommonResultMessage.failure("微信支付结果重复回调");
			}

		} catch (Exception e) {
			logger.error("微信支付结果通知回调异常：未知错误(unknown error!)", e);
			return CommonResultMessage.failure("微信支付结果通知回调异常：未知错误");
		} finally {
			try {
				redisCacheService.unLock(lockCallback);
			} catch (Exception e) {
				logger.error(
						"{redis}释放微信支付结果通知锁,{redis}release wechat pay callback lock exception：{}",
						e);
			}
		}

	}

	/**
	 * <pre>
	 * 处理回调业务逻辑:更新订单状态，用户西币数量变更
	 * </pre>
	 *
	 * @param packageParams
	 * @return
	 */
	private CommonResultMessage handlerCallback(SortedMap<String, String> packageParams) {
		logger.info("handler wechat pay callback business start, params : {}",
				packageParams.toString());

		// 以下字段只有在 return_code 为SUCCESS时才有返回，详情参考微信支付api文档
		String result_code = packageParams.get("result_code"); // 业务处理结果：SUCCESS/FAIL
		String err_code = packageParams.get("err_code"); // 错误代码
		String err_code_des = packageParams.get("err_code_des"); // 错误返回的信息描述
		// String trade_state = packageParams.get("trade_state");
		String bank_type = packageParams.get("bank_type");
		String total_fee = packageParams.get("total_fee"); // 订单总金额，单位为分
		String transaction_id = packageParams.get("transaction_id");
		String out_trade_no = packageParams.get("out_trade_no"); // 商户订单号
		String time_end = packageParams.get("time_end");
		String attach = packageParams.get("attach"); // 自定义附加数据，这里只保存了本系统用户的id

		DepositOrderDO dpOrder = depositOrderService.selectByOrderNo(out_trade_no);
		if (null == dpOrder) {
			logger.info("wechat pay callback exception: invalid out_trade_no");
			return CommonResultMessage.failure("微信支付结果通知处理失败：无效的-商户订单号");
		}

		// 已经处理过通知且是成功状态
		if (PayOrderStatusEnum.SUCCESS.code.equals(dpOrder.getOrderStatus())) {
			logger.info("wechat pay callback order status is success");
			return CommonResultMessage.success();
		}

		// 校验订单金额, 单位：分
		int depositAmt = dpOrder.getDepositAmount() * 100; // 本系统存的是元,这里需要转成分
		Integer wechatAmt = Integer.valueOf(total_fee);
		if (depositAmt != wechatAmt.intValue()) {
			logger.info(
					"wechat pay callback failure, order amount is invalid, wechat notify amount param: {}",
					wechatAmt);
			return CommonResultMessage.failure("微信支付结果通知处理失败：订单金额与商户订单金额不一致,数据存可能被篡改");
		}

		if (StringUtils.isNotBlank(attach)) {
			int userId = Integer.valueOf(attach);
			if (userId != dpOrder.getUserId().intValue()) {
				logger.info(
						"wechat pay callback failure, attach is invalid, wechat notify attach param: {}",
						attach);
				return CommonResultMessage.failure("微信支付结果通知处理失败：自定义附加数据attach校验失败,数据存可能被篡改");
			}
		}

		// 更新本系统订单状态等数据
		if (!"SUCCESS".equals(result_code)) { // 支付结果状态(是否成功)
			dpOrder.setOrderStatus(PayOrderStatusEnum.FAILURE.code);
		} else {
			dpOrder.setOrderStatus(PayOrderStatusEnum.SUCCESS.code);
		}
		dpOrder.setErrCode(err_code);
		dpOrder.setErrCodeDes(err_code_des);

		dpOrder.setBankType(bank_type);
		dpOrder.setTransactionId(transaction_id);
		dpOrder.setTimeEnd(time_end);

		dpOrder.setModifyTime(new Date());

		int res = depositOrderService.update(dpOrder, false);
		if (res <= 0) { // 更新数据失败
			logger.info("wechat pay callback handler business failure, "
					+ "update deposit order status failure");
			return CommonResultMessage.failure("微信支付结果通知处理失败：商户端更新订单状态异常");
		}

		DepositInsertDTO dpto = new DepositInsertDTO();
		dpto.setUserId(dpOrder.getUserId());
		dpto.setDepositId(dpOrder.getId());
		dpto.setDepositType(DepositTypeEnum.WECHAT.code);
		dpto.setDepositAmount(dpOrder.getDepositAmount());
		dpto.setDiscountId(dpOrder.getDiscountId());
		dpto.setDiscount(dpOrder.getDiscount());

		int restatus = depositService.deposit(dpto);
		if (restatus <= 0) {
			logger.info("wechat pay callback handler business failure: "
					+ "insert deposit record failure or increase user currency failure");
			return CommonResultMessage.failure("微信支付结果通知处理失败：商户端更新用户充值数据异常");
		}

		return CommonResultMessage.success();
	}

	/**
	 * <pre>
	 * 通知结果参数有效性校验
	 * </pre>
	 *
	 * @param packageParams
	 * @return
	 */
	private boolean validateParams(SortedMap<String, String> params) {
		String appid = params.get("appid");
		String mch_id = params.get("mch_id");
		String nonce_str = params.get("nonce_str");
		String result_code = params.get("result_code");
		String openid = params.get("openid");
		String bank_type = params.get("bank_type");
		String total_fee = params.get("total_fee");
		String transaction_id = params.get("transaction_id");
		String out_trade_no = params.get("out_trade_no");
		String time_end = params.get("time_end");

		if (StringUtils.isBlank(appid) || !APPID.equals(appid)) {
			logger.info("callback params - appid invalid failure");
			return false;
		}
		if (StringUtils.isBlank(mch_id) || !MCH_ID.equals(MCH_ID)) {
			logger.info("callback params - mch_id invalid failure");
			return false;
		}
		if (StringUtils.isBlank(nonce_str)) {
			logger.info("callback params - nonce_str invalid failure：nonce_str is blank");
			return false;
		}
		if (StringUtils.isBlank(result_code)) {
			logger.info("callback params - result_code invalid failure： result_code is blank");
			return false;
		}
		if (StringUtils.isBlank(openid)) {
			logger.info("callback params - openid invalid failure： openid is blank");
			return false;
		}
		if (StringUtils.isBlank(bank_type)) {
			logger.info("callback params - bank_type invalid failure： bank_type is blank");
			return false;
		}
		if (StringUtils.isBlank(total_fee) || !StringUtils.isNumeric(total_fee)) {
			logger.info("callback params - total_fee invalid failure： total_fee is blank");
			return false;
		}
		if (StringUtils.isBlank(transaction_id)) {
			logger.info("callback params - transaction_id invalid failure： transaction_id is blank");
			return false;
		}
		if (StringUtils.isBlank(out_trade_no)) {
			logger.info("callback params - out_trade_no invalid failure： out_trade_no is blank");
			return false;
		}
		if (StringUtils.isBlank(time_end)) {
			logger.info("callback params - time_end invalid failure： time_end is blank");
			return false;
		}

		return true;
	}

	public CommonResultMessage queryOrderStatus(String dpOrderNo, Long userId) {
		logger.info("wechat pay order query start, params : {}", dpOrderNo);
		if (StringUtils.isBlank(dpOrderNo) || null == userId || userId.longValue() < 0l) {
			logger.info("wechat pay order query failure, exception: dpOrderNo or userId is blank");
			return new CommonResultMessage(CommonResultMessage.Failure, "微信支付状态异常,为保证充值成功,若微信端支付成功,则请务必联系客服", new PayOrderQueryVO());
		}
		String payOrderQueryLock = LOCK_PAYORDER_STATUS_QUERY_KEY + userId;
		try {
			String payOrderQueryCountKey = PAYORDER_QUERY_COUNT_KEY + userId;
			Integer queryCount = (Integer) redisCacheService.getRedisCache(payOrderQueryCountKey); // 查询次数
			if (null == queryCount) {
				queryCount = 0;
				redisCacheService.setRedisCache(payOrderQueryCountKey, queryCount,
						RedisCacheTimeConstant.ONE_MINUTES);
			}
			int queryWaitTime = 2000; // 2 秒查询一次
			Thread.sleep(queryWaitTime);

			if (queryCount >= 60) { // 如果60次仍然未查询到结果,则中断查询
				logger.info("wechat pay order query times is out limit, times is more than 60");
				return new CommonResultMessage(CommonResultMessage.Failure, "查询订单状态失败：未查询到订单", new PayOrderQueryVO());
			}
			// 同一用户同一时间只能查询一次
			if (redisCacheService.lock(payOrderQueryLock)) {
				DepositOrderDO dpOrder = depositOrderService.selectByOrderNo(dpOrderNo);
				if (null == dpOrder) {
					logger.info("wechat payorder query exception: "
							+ "the deposit order is not existed, dpOrderNo-{}", dpOrderNo);
					return new CommonResultMessage(CommonResultMessage.Failure, "系统中查询不到该支付订单信息", new PayOrderQueryVO());
				}
				
				String nonce_str = UUID.randomUUID().toString().replace("-", "");
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("appid", APPID);
				packageParams.put("mch_id", MCH_ID);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("out_trade_no", dpOrderNo);
				// packageParams.put("transaction_id",
				// "4200000038201712315439426986");
				packageParams.put("sign_type", "MD5");

				String sign = PayToolUtil.createSign(packageParams);
				packageParams.put("sign", sign);

				// 调用查询接口完成查询业务
				logger.info("wechat pay order query params: 调用微信支付查询订单接口参数：{}", packageParams);
				// 封装请求参数为xml
				String requestXML = PayToolUtil.packageRequestToXmlStr(packageParams);

				logger.info("wechat pay order query request params, xml data: {}", requestXML);
				// 调用微信查询订单接口
				String resXml = HttpUtil.postData(PayConfigUtil.ORDER_QUERY_URL, requestXML);

				logger.info("wechat payorder query request success, return result data: {}", resXml);
				Map<String, String> map = XMLUtil4jdom.doXMLParse(resXml);
				if (null == map || map.isEmpty()) {
					logger.info("request wechat pay order exception, "
							+ "the returned result is blank;请求微信支付订单查询异常,返回报文为空");
					return new CommonResultMessage(CommonResultMessage.Failure, "查询订单异常, 返回报文为空", new PayOrderQueryVO());
				}
				
				PayOrderQueryVO vo = new PayOrderQueryVO();
				vo.setDepositAmt(dpOrder.getDepositAmount());
				vo.setDepositType(DepositTypeEnum.WECHAT.code);
				vo.setDepositTypeDesc(DepositTypeEnum.WECHAT.desc);
				vo.setDpOrderNo(dpOrderNo);
				vo.setRealCNY(dpOrder.getDiscount() * dpOrder.getDepositAmount());
				
				logger.info("wechat payorder query request success, return result data - "
						+ "parse xml to map data: {}", map);
				String return_code = map.get("return_code"); // 获取网络通信结果
				if (!"SUCCESS".equals(return_code)) {
					logger.info("wechat payorder query failure: connection timeout");
					vo.setDepositStatus("FAILURE");
					return new CommonResultMessage(8001, "获取支付结果失败,请确认西币是否已经到账,如未到账请务必联系客服", vo);
				}

				// 过滤空 设置 TreeMap
				SortedMap<String, String> sortedMap = new TreeMap<String, String>();
				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String parameter = (String) it.next();
					String parameterValue = map.get(parameter);

					String v = "";
					if (null != parameterValue) {
						v = parameterValue.trim();
					}
					sortedMap.put(parameter, v);
				}

				// 校验查询结果签名
				if (!PayToolUtil.isTenpaySign(sortedMap)) {
					logger.info("wechat payorder query failure: result data sign invalid");
					return new CommonResultMessage(CommonResultMessage.Failure, "获取支付状态失败：查询结果数据签名校验失败", new PayOrderQueryVO());
				}

				String result_code = map.get("result_code"); // 获取业务处理结果
				String trade_state = map.get("trade_state");
				String trade_state_desc = map.get("trade_state_desc");

				String time_end = map.get("time_end");
				if(StringUtils.isNotBlank(time_end)){
					vo.setDepositTime(DateUtils.parseDate(time_end, "yyyyMMddHHmmss"));
				}

				if (!"SUCCESS".equals(result_code)) {
					logger.info("wechat payorder query failure, order status fail, "
							+ "result_code: {}, trade_state: {}, trade_state_desc: {}",
							result_code, trade_state, trade_state_desc);
					vo.setDepositStatus("FAILURE");
					return new CommonResultMessage(8001, "获取支付结果失败,请确认西币是否已经到账,如未到账请务必联系客服", vo);
				}
				
				dpOrder.setTradeState(trade_state);
				dpOrder.setTradeStateDesc(trade_state_desc);
				dpOrder.setErrCode(map.get("err_code"));
				dpOrder.setErrCodeDes(map.get("err_code_des"));
				
				if ("PAYERROR".equals(trade_state)){ // 支付失败
					logger.info("wechat payorder query failure, order status fail, "
							+ "result_code: {}, trade_state: {}, trade_state_desc: {}",
							result_code, trade_state, trade_state_desc);
					dpOrder.setOrderStatus(PayOrderStatusEnum.FAILURE.code);
					depositOrderService.update(dpOrder, false);
					
					vo.setDepositStatus("FAILURE");
					return new CommonResultMessage(8001, "获取支付结果失败,请确认西币是否已经到账,如未到账请务必联系客服", vo);
				}
				
				depositOrderService.update(dpOrder, false);
				
				if("SUCCESS".equals(trade_state)){
					vo.setDepositStatus("SUCCESS");
					return new CommonResultMessage(8008, "支付成功", vo);
				}

				queryCount++;
				redisCacheService.setRedisCache(payOrderQueryCountKey, queryCount,
						RedisCacheTimeConstant.ONE_MINUTES);
				
				return new CommonResultMessage(CommonResultMessage.Failure, "未查询到支付成功或失败信息", vo);

			} else {
				return new CommonResultMessage(CommonResultMessage.Failure, "请求过于频繁", new PayOrderQueryVO());
			}

		} catch (Exception e) {
			logger.info("wechatpay order query exception: {}", e);
			return new CommonResultMessage(CommonResultMessage.Failure, "微信支付状态异常,为保证充值成功,若微信端支付成功,"
					+ "请查询西币是否到账,若未到账,则请务必联系客服", new PayOrderQueryVO());
		} finally {
			try {
				redisCacheService.unLock(payOrderQueryLock);
			} catch (Exception e2) {
				logger.info("redis error:unlock payorder query user lock exception, lock:{}",
						payOrderQueryLock);
			}
		}

	}

}
