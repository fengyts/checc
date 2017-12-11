package com.checc.ao.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ItemDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.dto.TopicItemDTO;
import com.checc.enums.TopicStatusEnum;
import com.checc.enums.TopicTypeEnum;
import com.checc.service.ItemService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

import ng.bayue.common.Page;

@Service
public class TopicItemAO {

	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ItemService itemService;
	
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

	public Page<TopicItemDTO> queryPageList(TopicItemDO topicItemDO, Integer pageNo, Integer pageSize) {
		Page<TopicItemDTO> pageResult = new Page<TopicItemDTO>();
		Page<TopicItemDO> page = topicItemService.queryPageListDynamicAndStartPageSize(topicItemDO, pageNo, pageSize);
		List<TopicItemDO> list = page.getList();
		
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		pageResult.setTotalCount(page.getTotalCount());
		if(CollectionUtils.isEmpty(list)){
			return pageResult;
		}
		
		Long topicId = topicItemDO.getTopicId();
		TopicDO topicDO = topicService.selectById(topicId);
		topicStatus(topicDO);
		List<TopicItemDTO> listResult = new ArrayList<TopicItemDTO>();
		for(TopicItemDO item : list){
			TopicItemDTO ti = new TopicItemDTO();
			ti.setId(item.getId());
			ti.setTopicId(topicId);
			ti.setStatus(topicDO.getStatus());
			ti.setTopicType(topicDO.getTopicType());
			ti.setStartTime(topicDO.getStartTime());
			ti.setEndTime(topicDO.getEndTime());
			
			ti.setItemId(item.getItemId());
			ti.setInventory(item.getInventory());
			ti.setResidue(item.getResidue());
			ti.setExchangeAmount(item.getExchangeAmount());
			ti.setItemStatus(item.getStatus());
			ti.setItemTitle(item.getItemTitle());
			
			listResult.add(ti);
		}
		
		pageResult.setList(listResult);
		
		return pageResult;
	}
	
	public Page<ItemDO> queryPageItemList(ItemDO itemDO, Integer pageNo, Integer pageSize){
		Page<ItemDO> page = itemService.queryPageListDynamicAndStartPageSize(itemDO, pageNo, pageSize);
		return page;
	}
	
	/**
	 * 是否兑换专题
	 *
	 * @param topicId
	 * @return
	 */
	private boolean isExchangeItem(Long topicId){
		TopicDO topicDO = topicService.selectById(topicId);
		return TopicTypeEnum.TOPIC_EXCHANGE.getCode().equals(topicDO.getTopicType());
	}

	public ResultMessage save(TopicItemDO topicItemDO) {
		Long topicId = topicItemDO.getTopicId();
		if(null == topicItemDO.getInventory()){
			return ResultMessage.validParameterNull("兑换数量不能为空");
		}
		if(null == topicId){
			return ResultMessage.failure("服务器异常：获取专题id失败");
		}
		if(isExchangeItem(topicId)){
			if(null == topicItemDO.getInventory()){
				return ResultMessage.failure("兑换商品数量不能为空");
			}
			if(null == topicItemDO.getExchangeAmount()){
				return ResultMessage.failure("兑换商品价格不能为空");
			}
		}
		
		Date date = new Date();
		Long userId = UserHandler.getUser().getId();
		
		topicItemDO.setResidue(topicItemDO.getInventory());
		topicItemDO.setCreateTime(date);
		topicItemDO.setCreateUserId(userId);
		topicItemDO.setModifyTime(date);
		topicItemDO.setModifyUserId(userId);
		
		topicItemService.insert(topicItemDO);
		
		return ResultMessage.success();
	}
	
	public TopicItemDO selectById(Long id){
		return topicItemService.selectById(id);
	}
	
	
	public ResultMessage update(TopicItemDO topicItemDO) {
		Long topicId = topicItemDO.getTopicId();
		if(null == topicItemDO.getInventory()){
			return ResultMessage.validParameterNull("兑换数量不能为空");
		}
		if(null == topicId){
			return ResultMessage.failure("服务器异常：获取专题id失败");
		}
		if(isExchangeItem(topicId)){
			if(null == topicItemDO.getInventory()){
				return ResultMessage.failure("兑换商品数量不能为空");
			}
			if(null == topicItemDO.getExchangeAmount()){
				return ResultMessage.failure("兑换商品价格不能为空");
			}
		}
		
		Date date = new Date();
		Long userId = UserHandler.getUser().getId();
		
		topicItemDO.setModifyTime(date);
		topicItemDO.setModifyUserId(userId);
		
		topicItemService.update(topicItemDO, false);
		
		return ResultMessage.success();
	}

}
