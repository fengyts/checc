package com.checc.myeleme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.myeleme.domain.ResultJson;

import constants.CommonPathConstant;

@Controller
@RequestMapping(value = CommonPathConstant.PATH_API)
public class ElemeApiController {

	@RequestMapping(value = "seller")
	@ResponseBody
	public ResultJson seller() {
//		return DataParse.toString(DataParse.getSeller());
		return ResultJson.success(DataParse.getSeller());
	}

	@RequestMapping(value = "goods")
	@ResponseBody
	public String goods() {
		return DataParse.toString(DataParse.getGoods());
//		ResultJson json = ResultJson.success(DataParse.getGoods());
//		return json;
	}

	@RequestMapping(value = "ratings")
	@ResponseBody
	public ResultJson ratings() {
//		return DataParse.toString(DataParse.getRatings());
		return ResultJson.success(DataParse.getRatings());
	}
	
	@RequestMapping(value = "allData")
	@ResponseBody
	public ResultJson allData() {
//		return DataParse.toString(DataParse.parseData());
		return ResultJson.success(DataParse.parseAllData());
		
	}

}
