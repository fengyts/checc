package com.checc.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.service.ExpressInfoService;

@Service
public class ExpressInfoAO {
	
	@Autowired
	private ExpressInfoService expressInfoService;

}
