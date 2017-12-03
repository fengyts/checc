package com.checc.service.impl.generate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import ng.bayue.util.StringUtils;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.checc.constants.SmsTemplate;
import com.checc.model.SmsSendResult;
import com.checc.service.generate.JuHeSmsSendService;


@Service
public class JuHeSmsSendServiceImpl implements JuHeSmsSendService {

	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	public static final String checkUrl = "http://v.juhe.cn/sms/black"; // 短信内容校验接口地址
	public static final String sendUrl = "http://v.juhe.cn/sms/send"; // 短信发送接口地址

	@Override
	public String checkContent(String appkey, String content) {
		return null;
	}

	@Override
	public SmsSendResult sendSms(Integer templateId, String mobile, String smsCode) {
		String result = null;
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("mobile", mobile);// 接收短信的手机号码
		params.put("tpl_id", templateId);// 短信模板ID，请参考个人中心短信模板设置
		params.put("key", SmsTemplate.APP_KEY);// 应用APPKEY(应用详细页查询)
		params.put("dtype", "json");// 返回数据的格式,xml或json，默认json
		
		String value = "#app#=车西西&#code#=" + smsCode;
		try {
			value = URLEncoder.encode(value, DEF_CHATSET);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		params.put("tpl_value", value); // 变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlEncode编码后再传递，<a
										// href="http://www.juhe.cn/news/index/id/50"
										// target="_blank">详细说明></a>
		SmsSendResult ssr = new SmsSendResult(SmsSendResult.FAILURE, "发送短信异常");
		try {
			result = doRequest(sendUrl, params, "GET");
			if(StringUtils.isBlank(result)){
				return new SmsSendResult(SmsSendResult.FAILURE, "发送短信异常,聚合返回为空");
			}
			// net.sf.JSONObject Integer 转 String 会有警告,此处使用fastjson
			JSONObject obj = JSONObject.parseObject(result);
			ssr = JSONObject.toJavaObject(obj, SmsSendResult.class);
		} catch (Exception e) {
			return new SmsSendResult(SmsSendResult.FAILURE, "发送短信异常,网络错误|聚合返回值解析异常");
		}
		return ssr;
	}

	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String doRequest(String strUrl, Map<String, Object> params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlEncode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlEncode(params));
				} catch (Exception e) {
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlEncode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", DEF_CHATSET)).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	

}
