package com.checc.controller;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.DepositConfigAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.domain.DepositConfigDO;
import com.checc.util.ResultMessage;

import ng.bayue.common.Page;

@Controller
@RequestMapping("/depositConfig")
public class DepositConfigController {

	@Autowired
	private DepositConfigAO depositConfigAO;

	@RequestMapping("/list")
	public String depositConfigList(Model model, DepositConfigDO configDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<DepositConfigDO> page = depositConfigAO.queryPage(configDO, pageNo, pageSize);
		model.addAttribute("page", page);
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "strategy/depositConfig/list";
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage update(DepositConfigDO configDO) {
		
		return depositConfigAO.update(configDO);
	}

}
