package com.checc.wechatpay.compent;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ng.bayue.common.CommonResultMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.ao.PayOrderAO;
import com.checc.wechatpay.common.ReturnWechatData;

/**
 * <pre>
 * 微信支付结果通知回调控制器
 * </pre>
 *
 * @author fengyts
 * @version $Id: WechatPayCallbackController.java, v 0.1 2017年12月30日 下午8:05:11
 *          fengyts Exp $
 */
@Controller
@RequestMapping({ "/pay/wechat" })
public class WechatPayCallbackController {

	private static Logger logger = LoggerFactory.getLogger(WechatPayController.class);

	/** 业务异常错误页面 */
	// private static final String ERROR_VIEW_PATH =
	// "/business/deposit/dept_act_err";

	@Autowired
	private PayOrderAO payOrderAO;
	@Autowired
	private WechatPayAO wechatPayAO;

	@RequestMapping({ "/payCallback" })
	public void generatePayQRCode(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		logger.info("wechat pay callback controller start...");
		String resultXml = "";
		InputStream is = null;
		BufferedReader br = null;
		try {
			// 获取通知参数
			StringBuilder notifyParams = new StringBuilder();
			is = request.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (null != (line = br.readLine())) {
				notifyParams.append(line);
			}
			
			logger.info("wechat pay callback notify params: {}", notifyParams);
			CommonResultMessage crm = wechatPayAO.callBack(notifyParams.toString());
			if (CommonResultMessage.Success == crm.getResult()) {
				logger.info("微信支付回调通知成功");
				resultXml = ReturnWechatData.successData();
				packageReturnData(response, resultXml);
			} else {
				logger.info("微信支付回调通知失败-info: {}", crm.getMessage());
				resultXml = ReturnWechatData.failureData(crm.getMessage());
				packageReturnData(response, resultXml);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微信支付回调通知获取参数流异常：{}", e);
			resultXml = ReturnWechatData.failureData("支付结果通知报文异常");
			packageReturnData(response, resultXml);
			return;
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != br)
					br.close();
			} catch (IOException e) {
				logger.error("微信支付回调通知异常, 关闭流异常", e);
			}
		}
		logger.error("微信支付回调通知获取参数流异常：未知错误(wechat pay callback failure: unknown error)");
		resultXml = ReturnWechatData.failureData(null);
		packageReturnData(response, resultXml);
	}

	private void packageReturnData(HttpServletResponse response, String resultXml) {
		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			out.write(resultXml.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("", e);
		}
	}

}
