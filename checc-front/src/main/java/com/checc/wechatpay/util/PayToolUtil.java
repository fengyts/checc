package com.checc.wechatpay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.codec.digest.DigestUtils;

import ng.bayue.constants.CharsetConstant;
import ng.bayue.util.StringUtils;

public class PayToolUtil {

	public static boolean isTenpaySign(SortedMap<String, String> packageParams) {
		return checkSign(CharsetConstant.UTF8, packageParams, PayConfigUtil.API_KEY);
	}
	
	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @return boolean
	 */
	private static boolean checkSign(String characterEncoding,
			SortedMap<String, String> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = packageParams.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + API_KEY);

		// 算出摘要
		String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		String tenpaySign = ((String) packageParams.get("sign"));

		return tenpaySign.equalsIgnoreCase(mysign);
	}

	public static String createSign(SortedMap<String, String> packageParams) {
		return createSign(CharsetConstant.UTF8, packageParams, PayConfigUtil.API_KEY);
	}
	
	public static String createSign(Map<String, String> packageParams) {
		return createSignature(packageParams);
	}
	
	private static String createSignature(Map<String, String> map) {
		StringBuilder signStr = new StringBuilder();
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			String key = entry.getKey();
			if ("sign".equals(key)) {
				continue;
			}
			String value = entry.getValue();
			if (StringUtils.isBlank(value)) {
				continue;
			}
			if (signStr.length()>0) {
				signStr.append('&');
			}
			signStr.append(key).append('=').append(value);
		}
		String key = PayConfigUtil.API_KEY;
		signStr.append("&key=").append(key);
		
		String sign = DigestUtils.md5Hex(signStr.toString()).toUpperCase();
		return sign;
	}

	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	private static String createSign(String characterEncoding,
			SortedMap<String, String> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = packageParams.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String packageRequestToXmlStr(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set<Entry<String, String>> es = parameters.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
					|| "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				//sb.append("<" + k + ">" + v + "</" + k + ">");
				sb.append(String.format("<%s>%s</%s>", k, v, k));
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

}
