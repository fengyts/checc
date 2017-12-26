package com.checc.ao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.DepositConfigDO;
import com.checc.service.DepositConfigService;

@Service
public class DepositAO {

	@Autowired
	private DepositConfigService depositConfigService;

	public List<DepositConfigDO> getDepositList() {
		List<DepositConfigDO> listAll = depositConfigService.selectDynamic(new DepositConfigDO());
		return listAll;
	}

}
