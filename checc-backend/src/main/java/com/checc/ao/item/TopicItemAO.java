package com.checc.ao.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

	public Page<TopicItemDTO> queryPageList(Model model, TopicItemDO topicItemDO, Integer pageNo, Integer pageSize) {
		Page<TopicItemDTO> pageResult = new Page<TopicItemDTO>();
		Page<TopicItemDO> page = topicItemService.queryPageListDynamicAndStartPageSize(topicItemDO, pageNo, pageSize);
		List<TopicItemDO> list = page.getList();

		Long topicId = topicItemDO.getTopicId();
		TopicDO topicDO = topicService.selectById(topicId);
		model.addAttribute("topicType", topicDO.getTopicType());

		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		pageResult.setTotalCount(page.getTotalCount());
		if (CollectionUtils.isEmpty(list)) {
			return pageResult;
		}

		topicStatus(topicDO);
		List<TopicItemDTO> listResult = new ArrayList<TopicItemDTO>();
		for (TopicItemDO item : list) {
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

			ti.setAuctionCurrency(item.getAuctionCurrency());
			ti.setAuctionMaxTimes(item.getAuctionMaxTimes());
			ti.setExchangeLimitNum(item.getExchangeLimitNum());
			ti.setFloorPrice(item.getFloorPrice());

			listResult.add(ti);
		}

		pageResult.setList(listResult);

		return pageResult;
	}

	public Page<ItemDO> queryPageItemList(ItemDO itemDO, Integer pageNo, Integer pageSize) {
		Page<ItemDO> page = itemService.queryPageListDynamicAndStartPageSize(itemDO, pageNo, pageSize);
		return page;
	}

	public ResultMessage save(TopicItemDO topicItemDO) {
		Long topicId = topicItemDO.getTopicId();
		if (null == topicId) {
			return ResultMessage.failure("服务器异常：获取专题id失败");
		}
		String itemTitle = topicItemDO.getItemTitle();
		Long itemId = topicItemDO.getItemId();
		if (StringUtils.isBlank(itemTitle) || null == itemId) {
			return ResultMessage.failure("请选择商品");
		}
		Integer inventory = topicItemDO.getInventory();
		TopicDO topicDO = topicService.selectById(topicId);
		if (TopicTypeEnum.TOPIC_EXCHANGE.getCode().equals(topicDO.getTopicType())) {
			if (null == inventory || inventory.intValue() < 1) {
				return ResultMessage.failure("兑换专题：兑换商品数量必须大于0");
			}
			Double exchangeAmount = topicItemDO.getExchangeAmount();
			if (null == exchangeAmount) {
				return ResultMessage.failure("兑换专题：兑换商品价格必须大于0");
			}
		}
		if (TopicTypeEnum.TOPIC_AUCTION.getCode().equals(topicDO.getTopicType())) {
			Double floor = topicItemDO.getFloorPrice();
			if (null == floor || floor.doubleValue() < 1d) {
				return ResultMessage.failure("竞拍专题：竞拍低价必须大于0");
			}
			Integer auctionCurrency = topicItemDO.getAuctionCurrency();
			if (null == auctionCurrency || auctionCurrency.intValue() < 1) {
				return ResultMessage.failure("竞拍专题：单次竞拍西币必须大于0");
			}
		}

		if (checkTopicItemExist(topicId, itemId)) {
			return ResultMessage.failure("该专题已经存在此商品");
		}

		Date date = new Date();
		Long userId = UserHandler.getUser().getId();

		topicItemDO.setResidue(inventory);
		topicItemDO.setCreateTime(date);
		topicItemDO.setCreateUserId(userId);
		topicItemDO.setModifyTime(date);
		topicItemDO.setModifyUserId(userId);

		topicItemService.insert(topicItemDO);

		return ResultMessage.success();
	}

	private synchronized Boolean checkTopicItemExist(Long topicId, Long itemId) {
		if (null == topicId || null == itemId) {
			return true;
		}
		TopicItemDO t = new TopicItemDO();
		t.setTopicId(topicId);
		t.setItemId(itemId);
		List<TopicItemDO> list = topicItemService.selectDynamic(t);

		return CollectionUtils.isNotEmpty(list);
	}

	public TopicItemDO selectById(Long id) {
		return topicItemService.selectById(id);
	}

	public ResultMessage update(TopicItemDO topicItemDO) {
		Long topicId = topicItemDO.getTopicId();
		if (null == topicId) {
			return ResultMessage.failure("服务器异常：获取专题id失败");
		}
		String itemTitle = topicItemDO.getItemTitle();
		Long itemId = topicItemDO.getItemId();
		if (StringUtils.isBlank(itemTitle) || null == itemId) {
			return ResultMessage.failure("请选择商品");
		}
		Integer inventory = topicItemDO.getInventory();
		TopicDO topicDO = topicService.selectById(topicId);
		if (TopicTypeEnum.TOPIC_EXCHANGE.getCode().equals(topicDO.getTopicType())) {
			if (null == inventory || inventory.intValue() < 1) {
				return ResultMessage.failure("兑换专题：兑换商品数量必须大于0");
			}
			Double exchangeAmount = topicItemDO.getExchangeAmount();
			if (null == exchangeAmount) {
				return ResultMessage.failure("兑换专题：兑换商品价格必须大于0");
			}
		}
		if (TopicTypeEnum.TOPIC_AUCTION.getCode().equals(topicDO.getTopicType())) {
			Double floor = topicItemDO.getFloorPrice();
			if (null == floor || floor.doubleValue() < 1d) {
				return ResultMessage.failure("竞拍专题：竞拍低价必须大于0");
			}
			Integer auctionCurrency = topicItemDO.getAuctionCurrency();
			if (null == auctionCurrency || auctionCurrency.intValue() < 1) {
				return ResultMessage.failure("竞拍专题：单次竞拍西币必须大于0");
			}
		}

		TopicItemDO t = new TopicItemDO();
		t.setTopicId(topicId);
		t.setItemId(itemId);
		List<TopicItemDO> list = topicItemService.selectDynamic(t);
		if (CollectionUtils.isNotEmpty(list)) {
			if (list.size() > 1) {
				return ResultMessage.failure("该专题已经存在此商品");
			}
			if (list.get(0).getId().longValue() != topicItemDO.getId().longValue()) {
				return ResultMessage.failure("该专题已经存在此商品");
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
