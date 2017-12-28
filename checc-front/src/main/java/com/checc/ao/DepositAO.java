package com.checc.ao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.TokenModel;
import ng.bayue.service.TokenService;
import ng.bayue.util.crypto.AESUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.druid.util.StringUtils;
import com.checc.domain.DepositConfigDO;
import com.checc.dto.DepositDTO;
import com.checc.service.DepositConfigService;

import constants.TokenTypeConstant;

@Service
public class DepositAO {

	@Autowired
	private DepositConfigService depositConfigService;
	@Autowired
	private TokenService tokenService;

	public List<DepositConfigDO> getDepositList(Model model, Long userId) {
		if (null == userId) {
			return new ArrayList<DepositConfigDO>();
		}
		List<DepositConfigDO> listAll = depositConfigService.selectDynamic(new DepositConfigDO());
		TokenModel tm = new TokenModel(userId.toString(), TokenTypeConstant.DepositTokenTypeEnum.DEPOSIT.desc);
		tokenService.create(tm);

		AESUtils aes = new AESUtils();
		model.addAttribute("depositTk", aes.encrypt(tm.getToken()));

		return listAll;
	}

	public CommonResultMessage depositQrcode(DepositDTO dto) {
		String depositTk = dto.getDepositTk();
		Long discountId = dto.getDiscountId();
		Integer depositAmt = dto.getDepositAmt();
		Double discount = dto.getDiscount();

		if (StringUtils.isEmpty(depositTk)) {
		}

		return null;

	}

	/*public static String getQrocdePay(String notifyUrl, String productName, String orderNum, Double price,
			String ipLocal) throws Exception {
		String url = "";
		// 金额转为分单位
		Double expandPrice = price * 100;
		// 转int类型
		Integer totalFee = expandPrice.intValue();
		// 生成签名
		String timeStamp = Sha1Util.getTimeStamp();
		TreeMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WeChatConfig.APP_ID);
		packageParams.put("mch_id", WeChatConfig.PARTNER);// 设置商户号
		packageParams.put("nonce_str", Sha1Util.getNonceStr());
		packageParams.put("time_stamp", timeStamp);// 时间戳
		packageParams.put("out_trade_no", orderNum); // 商户订单号
		packageParams.put("body", productName); // 商品描述
		packageParams.put("fee_type", "CNY"); // 银行币种
		packageParams.put("total_fee", totalFee); // 商品总金额,以分为单位
		packageParams.put("spbill_create_ip", ipLocal); // 订单生成的机器IP，指用户浏览器端IP
		packageParams.put("notify_url", notifyUrl); // 通知地址
		packageParams.put("trade_type", "NATIVE"); // 交易类型
													// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
		// 调用获取订单接口，并返回相关参数
		try {
			String str = WXPrepay.QRcodePay(packageParams);
			if (str != null) {
				url = TenpayUtil.QRfromOSchina(str);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return url;
	}*/
	
	
}
