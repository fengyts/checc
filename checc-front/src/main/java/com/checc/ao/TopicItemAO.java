package com.checc.ao;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.fastdfs.ImageUrlUtil;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ItemPictureDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.enums.TopicTypeEnum;
import com.checc.service.ItemPictureService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.vo.front.TopicItemVO;

@Service
public class TopicItemAO {

	@Autowired
	private ImageUrlUtil imageUrlUtil;
	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ItemPictureService pictureService;

	/**
	 * <pre>
	 * 获取专题所有商品
	 * </pre>
	 *
	 * @param type
	 *            01-本期商品；02-下期预告
	 * @return
	 */
	public List<TopicItemVO> listAuction(String type, String topicType) {
		List<TopicDO> listTopic = topicService.selectAllDynamic(type, topicType);
		List<TopicItemVO> listResult = new ArrayList<TopicItemVO>();
		if (CollectionUtils.isEmpty(listTopic)) {
			return listResult;
		}

		TopicDO topicDO = listTopic.get(0);
		packageTopicItemVO(topicDO, listResult);
		
		return listResult;
	}

	/**
	 * <pre>
	 * 获取所有兑换商品
	 * </pre>
	 *
	 * @return
	 */
	public List<TopicItemVO> listExchange() {
		List<TopicItemVO> listResult = new ArrayList<TopicItemVO>();
		List<TopicDO> listTopic = topicService.selectAllDynamic(null,
				TopicTypeEnum.TOPIC_EXCHANGE.getCode());
		if (CollectionUtils.isEmpty(listTopic)) {
			return listResult;
		}
		
		TopicDO topicDO = listTopic.get(0);
		
		packageTopicItemVO(topicDO, listResult);
		
		return listResult;
	}
	
	private void packageTopicItemVO (TopicDO topicDO, List<TopicItemVO> listResult){
		Long topicId = topicDO.getId();
		TopicItemDO topicItemDO = new TopicItemDO();
		topicItemDO.setTopicId(topicId);
		List<TopicItemDO> listItems = topicItemService.selectDynamic(topicItemDO);
		if (CollectionUtils.isEmpty(listItems)) {
			return;
		}
		List<Long> itemIds = new ArrayList<Long>();
		for (TopicItemDO item : listItems) {
			itemIds.add(item.getItemId());

			TopicItemVO vo = new TopicItemVO();
			vo.setTopicType(topicDO.getTopicType());
			vo.setStartTime(topicDO.getStartTime());
			vo.setEndTime(topicDO.getEndTime());
			vo.setStatus(topicDO.getStatus());

			vo.setId(item.getId());
			vo.setExchangeAmount(item.getExchangeAmount());
			vo.setInventory(item.getInventory());
			vo.setItemId(item.getItemId());
			vo.setItemTitle(item.getItemTitle());
			vo.setItemStatus(item.getStatus());
			vo.setResidue(item.getResidue());
			vo.setTopicId(topicId);

			listResult.add(vo);
		}
		List<ItemPictureDO> pictures = pictureService.selectByItemIds(itemIds);
		for (TopicItemVO vo : listResult) {
			long itemId = vo.getItemId();
			for (ItemPictureDO picture : pictures) {
				long itemIdP = picture.getItemId();
				if (itemId == itemIdP) {
					vo.setPicture(imageUrlUtil.getFileFullUrl(picture.getPicture()));
					break;
				}
			}
		}
	}

}
