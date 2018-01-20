package com.checc.ao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.service.CheccUserService;
import com.checc.service.UserCurrencyService;
import com.checc.util.Messages;
import com.checc.util.ResultMessage;
import com.checc.vo.CheccUserVO;

@Service
public class StrategyAO {

	@Autowired
	private CheccUserService checcUserService;
	@Autowired
	private UserCurrencyService userCurrencyService;

	public List<CheccUserVO> listAllVMAccount(String mobile) {
		List<CheccUserVO> list = checcUserService.selectAllVMAccount(null, mobile);
		return list;
	}

	public ResultMessage updateVmAccountPwd(String pwd) {
		try {
			checcUserService.recoveredVmAccountPwd(pwd);
			return ResultMessage.success();
		} catch (Exception e) {
			return ResultMessage.failure(e.getMessage());
		}
	}

	public CheccUserVO selectVmAccountInfo(Long userId) {
		List<CheccUserVO> list = checcUserService.selectAllVMAccount(userId, null);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		CheccUserVO uvo = list.get(0);
		return uvo;
	}

	public ResultMessage updateVmAccount(Long userId, Integer totalCurrency) {
		if (null == userId) {
			return ResultMessage.failure(Messages.ServerInnerError);
		}
		if (null == totalCurrency || totalCurrency.intValue() <= 0) {
			return ResultMessage.failure("西币值必须大于0");
		}
		try {
			int res = userCurrencyService.increaseTotalCurrency(userId, totalCurrency);
			if (res <= 0) {
				return ResultMessage.failure("虚拟账号充值失败");
			}
			return ResultMessage.success();
		} catch (Exception e) {
			return ResultMessage.failure("虚拟账号充值失败");
		}
	}

}
