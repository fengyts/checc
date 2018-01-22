package com.checc.wechatpay.compent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.domain.DepositOrderDO;
import com.checc.enums.DepositTypeEnum;
import com.checc.service.DepositOrderService;
import com.checc.vo.front.PayOrderQueryVO;
import com.checc.wechatpay.util.QRUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.util.DateUtils;

@Controller
@RequestMapping({ "/paytest" })
public class WechatPayTestController {
	
	@Autowired
	private DepositOrderService depositOrderService;
	
	@RequestMapping("/payQRCode")
	public String payQRCode(Model model, String dpOrderNo){
		String qrCode = generateQRCode("www.baidu.com");
		model.addAttribute("qrcode", qrCode);
		model.addAttribute("dpOrderNo", "877249130680001470");
		return "/business/pay/dept_wechat_scan";
	}

	@RequestMapping("/paysuccess")
	public String paySuccess(Model model, String dpOrderNo) throws Exception {
		DepositOrderDO dpOrder = depositOrderService.selectByOrderNo(dpOrderNo);
		PayOrderQueryVO vo = new PayOrderQueryVO();
		vo.setDepositAmt(dpOrder.getDepositAmount());
		vo.setDepositTime(DateUtils.parseDate("20180102102018", "yyyyMMddHHmmss"));
		vo.setDepositType(DepositTypeEnum.WECHAT.code);
		vo.setDepositTypeDesc(DepositTypeEnum.WECHAT.desc);
		vo.setDpOrderNo(dpOrderNo);
		vo.setRealCNY(dpOrder.getDiscount() * dpOrder.getDepositAmount());
		vo.setDepositStatus("SUCCESS");
		
		Thread.sleep(2000);
		CommonResultMessage crm = new CommonResultMessage(vo);
		model.addAttribute("result", crm);
		return "/business/pay/pay_success";
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
			return null;
		}

	}

}
