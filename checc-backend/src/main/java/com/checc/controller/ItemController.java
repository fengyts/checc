package com.checc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.service.ItemService;

@Controller
@RequestMapping("/item/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = {"list"})
	@ResponseBody
	public String list(){
		itemService.selectById(1L);
		return "hahah";
	}
	
	
}
