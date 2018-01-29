package com.checc.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.vo.front.TopicItemDetailVO;

@Controller
@RequestMapping({"checctest"})
public class TestController {
	
	
	@RequestMapping("/test")
	public String test(Model model){
		TopicItemDetailVO vo = new TopicItemDetailVO();
		vo.setPicture("http://47.94.199.26/group1/M00/00/01/rBBQ0lpgVDOAXD5zAANvXG8NlyI72..png");
		model.addAttribute("vo", vo);
		return "/business/test/test";
	}

}
