package com.checc.ao;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.CheccNoticeDO;
import com.checc.enums.CheccNoticeTypeEnum;
import com.checc.service.CheccNoticeService;
import com.checc.util.Messages;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

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

	public ResultMessage save(CheccNoticeDO noticeDO) {
		Long userId = UserHandler.getUser().getId();
		String type = noticeDO.getType();
		if (StringUtils.isBlank(type) || StringUtils.isBlank(noticeDO.getContent())) {
			return ResultMessage.failure(Messages.ParameterError);
		}
		noticeDO.setModifyTime(new Date());
		noticeDO.setModifyUserId(userId);
		Long id = noticeDO.getId();
		if (null == id) { // 新增
			noticeDO.setTitle(CheccNoticeTypeEnum.getDescByCode(type));
			noticeDO.setCreateTime(new Date());
			noticeDO.setCreateUserId(userId);
			noticeService.insert(noticeDO);
		} else { // 修改
			noticeService.update(noticeDO, false);
		}
		return ResultMessage.success();
	}

}
