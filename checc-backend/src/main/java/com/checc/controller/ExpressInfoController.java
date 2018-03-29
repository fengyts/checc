package com.checc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.ExpressInfoAO;
import com.checc.domain.ExpressInfoDO;
import com.checc.util.ResultMessage;

@Controller
@RequestMapping("/expressInfo")
public class ExpressInfoController {

	@Autowired
	private ExpressInfoAO expressInfoAO;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultMessage list(Model model, ExpressInfoDO expressInfoDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		
		return null;
	}

}
