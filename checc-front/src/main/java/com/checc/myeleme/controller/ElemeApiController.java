package com.checc.myeleme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import constants.CommonPathConstant;

@Controller
@RequestMapping(value = CommonPathConstant.PATH_API)
public class ElemeApiController {

	@RequestMapping(value = "seller")
	@ResponseBody
	public String seller() {
		return DataParse.toString(DataParse.getSeller());
	}

	@RequestMapping(value = "goods")
	@ResponseBody
	public String goods() {
		return DataParse.toString(DataParse.getGoods());
	}

	@RequestMapping(value = "ratings")
	@ResponseBody
	public String ratings() {
		return DataParse.toString(DataParse.getRatings());
	}
	
	@RequestMapping(value = "allData")
	@ResponseBody
	public String allData() {
		return DataParse.toString(DataParse.parseData());
	}

}
