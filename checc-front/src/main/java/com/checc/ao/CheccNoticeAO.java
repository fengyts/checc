package com.checc.ao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.CheccNoticeDO;
import com.checc.service.CheccNoticeService;

@Service
public class CheccNoticeAO {

	@Autowired
	private CheccNoticeService noticeService;

	public CheccNoticeDO selectByType(String type) {
		if (StringUtils.isBlank(type)) {
			return null;
		}
		CheccNoticeDO param = new CheccNoticeDO();
		param.setType(type);
		List<CheccNoticeDO> list = noticeService.selectDynamic(param);
		if (CollectionUtils.isNotEmpty(list)) {
			CheccNoticeDO noticeDO = list.get(0);
			return noticeDO;
		}
		return new CheccNoticeDO();
	}

}
