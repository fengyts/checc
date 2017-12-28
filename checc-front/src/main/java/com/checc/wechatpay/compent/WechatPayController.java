package com.checc.wechatpay.compent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 *  微信支付控制器
 * </pre>
 *
 * @author lenovopc
 * @version $Id: WechatPayController.java, v 0.1 2017年12月28日 下午5:11:30 lenovopc Exp $
 */
@Controller
@RequestMapping({"/pay/wechat"})
public class WechatPayController {
	
	@RequestMapping({"/payQRCode"})
	public String generatePayQRCode(){
		
		return "/business/pay/dept_wechat_scan";
	}

}
