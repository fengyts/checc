package com.checc.ao;


import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.DepositOrderDO;
import com.checc.dto.DepositDTO;
import com.checc.enums.DepositTypeEnum;
import com.checc.enums.PayOrderStatusEnum;
import com.checc.service.DepositOrderService;

@Service
public class PayOrderAO {

	@Autowired
	private DepositOrderService depositOrderService;

	public CommonResultMessage savePayOrder(DepositDTO dto, Long userId) {
		if (null == userId || userId < 1l) {
			return CommonResultMessage.failure(CommonMessages.UNLOGIN);
		}
		Long discountId = dto.getDiscountId();
		Double discount = dto.getDiscount();
		Integer depositAmt = dto.getDepositAmt();
		if (null == discountId || discountId.longValue() < 0l || null == discount
				|| discount.doubleValue() <= 0d || discount.doubleValue() > 1d
				|| null == depositAmt || depositAmt < 0) {
			return CommonResultMessage.failure("请求异常,参数不合法");
		}

		DepositOrderDO dpOrder = new DepositOrderDO();
		dpOrder.setUserId(userId);
		dpOrder.setDepositAmount(depositAmt);
		dpOrder.setDiscountId(discountId);
		dpOrder.setDiscount(discount);
		dpOrder.setDepositType(DepositTypeEnum.WECHAT.code);
		dpOrder.setOrderStatus(PayOrderStatusEnum.UNPAID.code);

		try {
			CommonResultMessage crm = depositOrderService.productPayOrder(dpOrder);
			return crm;
		} catch (Exception e) {
			return CommonResultMessage.failure(e.getMessage());
		}

	}

}
