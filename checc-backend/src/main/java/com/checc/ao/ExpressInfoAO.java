package com.checc.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ExpressInfoDO;
import com.checc.service.ExpressInfoService;

import ng.bayue.common.Page;

@Service
public class ExpressInfoAO {

	@Autowired
	private ExpressInfoService expressInfoService;

	public Page<ExpressInfoDO> queryPage(ExpressInfoDO expressInfoDO, Integer pageNo, Integer pageSize) {
		Page<ExpressInfoDO> page = expressInfoService.queryPageListDynamicAndStartPageSize(expressInfoDO, pageNo,
				pageSize);

		return page;
	}

}
