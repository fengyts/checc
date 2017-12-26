package com.checc.ao;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.common.model.TokenModel;
import ng.bayue.service.TokenService;
import ng.bayue.util.crypto.AESUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.checc.domain.DepositConfigDO;
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
		TokenModel tm = new TokenModel(userId.toString(),
				TokenTypeConstant.DepositTokenTypeEnum.DEPOSIT.desc);
		tokenService.create(tm);

		AESUtils aes = new AESUtils();
		model.addAttribute("depositTk", aes.encrypt(tm.getToken()));

		return listAll;
	}

}
