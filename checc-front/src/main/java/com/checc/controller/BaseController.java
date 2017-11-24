package com.checc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"*"})
public class BaseController {
	
	@RequestMapping
	public String notFound(){
		return "/error/404";
	}

}
