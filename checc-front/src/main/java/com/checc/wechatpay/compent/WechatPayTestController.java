package com.checc.wechatpay.compent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.domain.DepositOrderDO;
import com.checc.enums.DepositTypeEnum;
import com.checc.service.DepositOrderService;
import com.checc.vo.front.PayOrderQueryVO;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.util.DateUtils;

@Controller
@RequestMapping({ "/paytest" })
public class WechatPayTestController {
	
	@Autowired
	private DepositOrderService depositOrderService;
	
	@RequestMapping("/payQRCode")
	public String payQRCode(Model model, String dpOrderNo){
		
		return "/business/test/pay_qrcode_scan";
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
		
		CommonResultMessage crm = new CommonResultMessage(vo);
		model.addAttribute("result", crm);
		return "/business/pay/pay_success";
	}

}
