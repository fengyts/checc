package com.checc.ao.item;

import java.util.Date;
import java.util.List;

import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.TopicDO;
import com.checc.enums.TopicStatusEnum;
import com.checc.service.TopicService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

@Service
public class TopicAO {

	@Autowired
	private TopicService topicService;
	
	private void topicStatus(TopicDO topicDO) {
		Date startTime = topicDO.getStartTime();
		Date endTime = topicDO.getEndTime();
		Date now = new Date();
		String status = TopicStatusEnum.InProgress.getCode();
		if (startTime.after(now)) {
			status = TopicStatusEnum.NotStarted.getCode();
		} else if (endTime.before(now)) {
			status = TopicStatusEnum.End.getCode();
		}
		topicDO.setStatus(status);
	}

	public Page<TopicDO> queryPageList(TopicDO topicDO, Integer pageNo, Integer pageSize) {
		Page<TopicDO> page = topicService.queryPageListDynamicAndStartPageSize(topicDO, pageNo, pageSize);
		List<TopicDO> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)){
			for(TopicDO t : list){
				topicStatus(t);
			}
		}
		return page;
	}

	public TopicDO selectById(Long id) {
		if (null == id) {
			return new TopicDO();
		}
		TopicDO topicDO = topicService.selectById(id);
		topicStatus(topicDO);
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
		
		topicStatus(topicDO);
		
		Long userId = UserHandler.getUser().getId();
		topicDO.setCreateUserId(userId);
		topicDO.setCreateTime(date);
		topicDO.setModifyUserId(userId);
		topicDO.setModifyTime(date);
		topicDO.setRefundCurrencyStatus(false);

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
		topicStatus(topicDO);
		topicService.update(topicDO, false);
		
		return ResultMessage.success();
	}

}
