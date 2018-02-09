package com.checc.ao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.DepositConfigDO;
import com.checc.service.DepositConfigService;
import com.checc.util.Messages;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

import ng.bayue.common.Page;

@Service
public class DepositConfigAO {

	@Autowired
	private DepositConfigService depositConfigService;

	public Page<DepositConfigDO> queryPage(DepositConfigDO configDO, Integer pageNo, Integer pageSize) {
		Page<DepositConfigDO> page = depositConfigService.queryPageListDynamicAndStartPageSize(configDO, pageNo,
				pageSize);

		return page;
	};

	public ResultMessage update(DepositConfigDO configDO) {
		Long id = configDO.getId();
		if(null == id){
			return ResultMessage.failure(Messages.ServerInnerError);
		}
		Integer damt = configDO.getDepositAmount();
		if(damt == null || damt.intValue() <= 0){
			return ResultMessage.validParameterNull("充值金额不能为空");
		}
		Double discount = configDO.getDiscount();
		if(null == discount || discount.doubleValue() <= 0 || discount.doubleValue() > 1){
			return ResultMessage.validParameterNull("折扣值必须大于0并且≤1");
		}
		
		configDO.setModifyUserId(UserHandler.getUser().getId());
		configDO.setModifyTime(new Date());
		depositConfigService.update(configDO, false);
		
		return ResultMessage.success();
	}

}
