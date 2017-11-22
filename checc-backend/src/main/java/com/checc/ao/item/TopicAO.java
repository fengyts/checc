package com.checc.ao.item;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.TopicDO;
import com.checc.dto.enums.TopicStatusEnum;
import com.checc.service.TopicService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

import ng.bayue.common.Page;

@Service
public class TopicAO {

	@Autowired
	private TopicService topicService;

	public Page<TopicDO> queryPageList(TopicDO topicDO, Integer pageNo, Integer pageSize) {
		Page<TopicDO> page = topicService.queryPageListDynamicAndStartPageSize(topicDO, pageNo, pageSize);
		return page;
	}

	public TopicDO selectById(Long id) {
		if (null == id) {
			return new TopicDO();
		}
		TopicDO topicDO = topicService.selectById(id);
		return topicDO;
	}

	public ResultMessage save(TopicDO topicDO) {
		Date startTime = topicDO.getStartTime();
		Date endTime = topicDO.getEndTime();
		if (null == startTime || null == endTime) {
			return ResultMessage.validParameterNull("开始|结束时间不能为空");
		}
		if (startTime.after(endTime)) {
			return new ResultMessage(ResultMessage.Failure, "开始时间必须小于结束时间");
		}
		Date date = new Date();
		if(date.after(endTime) || startTime.before(date)){ // 开始|结束时间不能早于当前时间
			return new ResultMessage(ResultMessage.Failure, "开始|结束时间不能早于当前时间");
		}
		
		Long userId = UserHandler.getUser().getId();
		topicDO.setCreateUserId(userId);
		topicDO.setCreateTime(date);
		topicDO.setModifyUserId(userId);
		topicDO.setModifyTime(date);

		topicService.insert(topicDO);
		
		return ResultMessage.success();
	}

	public ResultMessage update(TopicDO topicDO) {
		Date startTime = topicDO.getStartTime();
		Date endTime = topicDO.getEndTime();
		if (null == startTime || null == endTime) {
			return ResultMessage.validParameterNull("开始|结束时间不能为空");
		}
		if (startTime.after(endTime)) {
			return new ResultMessage(ResultMessage.Failure, "开始时间必须小于结束时间");
		}
		Date date = new Date();
		if(date.after(endTime) || startTime.before(date)){ // 开始|结束时间不能早于当前时间
			return new ResultMessage(ResultMessage.Failure, "开始|结束时间不能早于当前时间");
		}
		
		topicDO.setModifyTime(new Date());
		topicDO.setModifyUserId(UserHandler.getUser().getId());
		topicService.update(topicDO, false);
		
		return ResultMessage.success();
	}

}
