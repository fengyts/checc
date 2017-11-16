package com.checc.controller;

import ng.bayue.service.ItemService;
import ng.bayue.service.TestBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private TestBean tb;

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public String list() {
		itemService.selectById(1L);
		tb.testC();
		return "hahahah";
	}

}
