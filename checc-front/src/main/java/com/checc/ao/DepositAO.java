package com.checc.ao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.TokenModel;
import ng.bayue.enums.RedisModelStatusEnum;
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

	public CommonResultMessage depositQrcode(DepositDTO dto, Long userId) {
		String depositTk = dto.getDepositTk();
		Long discountId = dto.getDiscountId();
		Integer depositAmt = dto.getDepositAmt();
		Double discount = dto.getDiscount();

		if (StringUtils.isEmpty(depositTk)) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}
		if (null == discountId || discountId < 0 || null == depositAmt || depositAmt < 0 || null == discount
				|| discount.doubleValue() < 0) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}

		TokenModel tm = new TokenModel(userId.toString(), TokenTypeConstant.DepositTokenTypeEnum.DEPOSIT.desc);
		RedisModelStatusEnum tkCheckStatus = tokenService.check(tm);
		if (RedisModelStatusEnum.CORRECT != tkCheckStatus) {
			return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
					CommonResultCode.SystemError.REQ_ERROR.desc);
		}

		return CommonResultMessage.success();

	}

}
