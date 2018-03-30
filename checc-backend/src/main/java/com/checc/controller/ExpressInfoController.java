package com.checc.controller;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.ExpressInfoAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.domain.ExpressInfoDO;
import com.checc.util.ResultMessage;

import ng.bayue.common.Page;

@Controller
@RequestMapping("/expressInfo")
public class ExpressInfoController {

	@Autowired
	private ExpressInfoAO expressInfoAO;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, ExpressInfoDO expressInfoDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<ExpressInfoDO> page = expressInfoAO.queryPage(expressInfoDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("expressInfoDO", expressInfoDO);
		
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		
		return BackendConstant.BACKEND_VIEW_PATH + "express/list_select";
	}

}
