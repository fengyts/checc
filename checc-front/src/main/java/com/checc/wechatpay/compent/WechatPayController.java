package com.checc.wechatpay.compent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ng.bayue.common.CommonResultMessage;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.checc.ao.DepositAO;
import com.checc.ao.PayOrderAO;
import com.checc.constants.UserConstants;
import com.checc.domain.CheccUserDO;
import com.checc.domain.DepositOrderDO;
import com.checc.dto.DepositDTO;
import com.checc.wechatpay.util.QRUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * <pre>
 * 微信支付控制器
 * </pre>
 *
 * @author lenovopc
 * @version $Id: WechatPayController.java, v 0.1 2017年12月28日 下午5:11:30 lenovopc
 *          Exp $
 */
@Controller
@RequestMapping({ "/pay/wechat" })
public class WechatPayController {

	private static Logger logger = LoggerFactory.getLogger(WechatPayController.class);

	/** 业务异常错误页面 */
	private static final String ERROR_VIEW_PATH = "/business/deposit/dept_act_err";
	
	@Autowired
	private DepositAO depositAO;
	@Autowired
	private PayOrderAO payOrderAO;
	@Autowired
	private WechatPayAO wechatPayAO;

	/**
	 * <pre>
	 * 扫码支付(模式二):生成支付二维码
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @param redAttrs
	 * @return
	 */
	@RequestMapping(value = { "/payQRCode" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String generatePayQRCode(HttpServletRequest request, HttpServletResponse response,
			Model model, /*String redAttrs,*/ DepositDTO dto) {
		try {
			Long userId = null;
			CheccUserDO userDO = (CheccUserDO) request.getSession().getAttribute(
					UserConstants.USER_SESSION_KEY);
			if (null == userDO) {
				logger.info("获取微信支付二维码异常：用户未登陆");
				return ERROR_VIEW_PATH;
			} else {
				userId = userDO.getId();
			}
			
			CommonResultMessage crmSecurityCheck = depositAO.depositSecurityAct(dto, userId);
			if (CommonResultMessage.Success != crmSecurityCheck.getResult()) {
				model.addAttribute("errMsg", crmSecurityCheck.getMessage());
				return ERROR_VIEW_PATH;
			}

			/*if (StringUtils.isBlank(redAttrs)) {
				logger.info("获取微信支付二维码异常：重定向参数异常");
				model.addAttribute("errMsg", CommonResultCode.SystemError.REQ_ERROR.desc);
				return ERROR_VIEW_PATH;
			}
			DepositDTO dto = null;
			try {
				AESUtils aes = new AESUtils();
				redAttrs = aes.decrypt(redAttrs);
				dto = JSONObject.parseObject(redAttrs, DepositDTO.class);
			} catch (Exception e) {
				logger.info("获取微信支付二维码异常：重定向参数异常");
				model.addAttribute("errMsg", CommonResultCode.SystemError.REQ_ERROR.desc);
				return ERROR_VIEW_PATH;
			}
			*/

			// 支付本系统生成支付订单
			CommonResultMessage crm = payOrderAO.savePayOrder(dto, userId);
			if (CommonResultMessage.Success != crm.getResult()) {
				logger.info("微信支付下单异常：{}", crm.getMessage());
				return ERROR_VIEW_PATH;
			}

			DepositOrderDO dpOrder = (DepositOrderDO) crm.getData();
			if (null == dpOrder) {
				logger.info("微信支付下单异常：{},返回订单为空...");
				return ERROR_VIEW_PATH;
			}
			
			// 调用微信系统统一下单接口生成预付单
			logger.info("request wechat pay order start, dpOrderNo :{}", dpOrder.getOrderNo());
			String codeUrl = wechatPayAO.wechatPayPreOrder(request, dpOrder);
			if (StringUtils.isBlank(codeUrl)) {
				model.addAttribute("errMsg", "生成支付二维码失败,请重试!");
				return ERROR_VIEW_PATH;
			}

			logger.info("request wechat pay order end, codeUrl :{}", codeUrl);
			String qrCode = generateQRCode(codeUrl);
			if (StringUtils.isBlank(qrCode)) {
				model.addAttribute("errMsg", "生成支付二维码失败,请重试!");
				return ERROR_VIEW_PATH;
			}
			model.addAttribute("qrcode", qrCode);
			model.addAttribute("dpOrderNo", dpOrder.getOrderNo());
		} catch (Exception e) {
			logger.info("获取微信支付二维码异常：{}", e);
			return ERROR_VIEW_PATH;
		}
		return "/business/pay/dept_wechat_scan";
	}

	public String generateQRCode(String codeUrl) {
		try {
			// 二维码格式设置
			int width = 300, height = 300;
			String format = "png";// 二维码的图片格式
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");// 内容所使用编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,
					width, height, hints);
			// 生成二维码
			BufferedImage read = QRUtil.toBufferedImage(bitMatrix);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(read, format, baos);
			String base64Img = Base64.encodeBase64String(baos.toByteArray());

			return base64Img;
		} catch (IOException | WriterException e) {
			logger.error("generate wechat prepare pay order QRCode exception:{}", e);
			return null;
		}

	}

}
