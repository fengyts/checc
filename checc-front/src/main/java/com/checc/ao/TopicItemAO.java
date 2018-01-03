package com.checc.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.ItemDescDO;
import com.checc.domain.ItemPictureDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.enums.ItemPictureTypeEnum;
import com.checc.enums.TopicStatusEnum;
import com.checc.enums.TopicTypeEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.ItemDescService;
import com.checc.service.ItemPictureService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.vo.front.TopicItemDetailVO;
import com.checc.vo.front.TopicItemVO;

import ng.bayue.constants.CommonConstant;
import ng.bayue.fastdfs.ImageUrlUtil;
import ng.bayue.util.StringUtils;

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
	@Autowired
	private ItemDescService itemDescService;
	@Autowired
	private AuctionRecordService auctionRecordService;

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
		List<TopicDO> listTopic = topicService.selectTopicByProgress(topicType, TopicStatusEnum.InProgress);
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
		// 获取正在进行中的兑换专题，若为空则获取即将进行的兑换专题
		List<TopicDO> listTopic = topicService.selectTopicByProgress(TopicTypeEnum.TOPIC_EXCHANGE.getCode(), TopicStatusEnum.InProgress);
		if (CollectionUtils.isEmpty(listTopic)) {
			listTopic = topicService.selectTopicByProgress(TopicTypeEnum.TOPIC_EXCHANGE.getCode(), TopicStatusEnum.NotStarted);
		}
		if (CollectionUtils.isEmpty(listTopic)) {
			return listResult;
		}

		TopicDO topicDO = listTopic.get(0);

		packageTopicItemVO(topicDO, listResult);

		return listResult;
	}

	private void packageTopicItemVO(TopicDO topicDO, List<TopicItemVO> listResult) {
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
			vo.setItemStatus(AuctionCommonAO.getTopicStatus(topicDO.getStartTime(), topicDO.getEndTime()));
			vo.setResidue(item.getResidue());
			vo.setTopicId(topicId);
			vo.setMarketPrice(item.getMarketPrice());

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

	public TopicItemDetailVO topicItemDetails(Long userId, Long tpId) {
		TopicItemDO item = topicItemService.selectById(tpId);
		Long topicId = item.getTopicId();
		Long itemId = item.getItemId();

		TopicDO topic = topicService.selectById(topicId);
		String topicType = topic.getTopicType();
		Date startTime = topic.getStartTime();
		Date endTime = topic.getEndTime();
		String status = AuctionCommonAO.getTopicStatus(startTime, endTime);

		TopicItemDetailVO vo = new TopicItemDetailVO();
		vo.setId(item.getId());
		vo.setItemId(itemId);
		vo.setItemTitle(item.getItemTitle());
		vo.setMarketPrice(item.getMarketPrice());
		vo.setExchangeAmount(item.getExchangeAmount());
		vo.setInventory(item.getInventory());
		vo.setAuctionCurrency(item.getAuctionCurrency());
		vo.setFloorPrice(item.getFloorPrice());
		vo.setExchangeLimitNum(item.getExchangeLimitNum());
		Integer residue = item.getResidue();
		vo.setItemStatus(status);
		vo.setResidue(residue);

		vo.setTopicId(topicId);

		vo.setStatus(status);
		vo.setTopicType(topicType);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);

		if (TopicTypeEnum.TOPIC_EXCHANGE.getCode().equals(topicType)) { // 兑换
			if (residue <= 0) { // 商品已经兑光
				vo.setHasExchangeOut(true);
			}
			// 用户是否已经兑换过
			Boolean hasExchanged = false;
			if(null != userId){
				hasExchanged = auctionRecordService.isExchanged(userId, tpId);
			}
			vo.setHasExchanged(hasExchanged);
		} else {
			//vo.setHasExchangeOut(false);
			AuctionRecordDO recordDO = auctionRecordService.selectLatestAuction(tpId);
			if (null != recordDO) {
				vo.setCurrentBidder(StringUtils.securityMobile(recordDO.getMobile()));
				vo.setCurrentBidTime(recordDO.getCreateTime());
				vo.setCurrentAuctionPrice(recordDO.getCurrentAuctPrice().doubleValue());
			} else {
				vo.setCurrentAuctionPrice(item.getFloorPrice());
			}
		}

		ItemPictureDO pdo = new ItemPictureDO();
		pdo.setItemId(itemId);
		pdo.setType(ItemPictureTypeEnum.PIC_PRIMARY.getCode());
		pdo.setStatus(CommonConstant.STATUS.TRUE);
		List<ItemPictureDO> listPics = pictureService.selectDynamic(pdo);
		if (CollectionUtils.isNotEmpty(listPics)) {
			ItemPictureDO picture = listPics.get(0);
			vo.setPicture(imageUrlUtil.getFileFullUrl(picture.getPicture()));
		}

		ItemDescDO descDO = new ItemDescDO();
		descDO.setItemId(itemId);
		List<ItemDescDO> listDesc = itemDescService.selectDynamic(descDO);
		if (CollectionUtils.isNotEmpty(listDesc)) {
			vo.setDescription(listDesc.get(0).getDescription());
		}

		return vo;
	}

}
