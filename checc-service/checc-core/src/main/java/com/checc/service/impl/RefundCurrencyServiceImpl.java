package com.checc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.enums.AuctionRecordTypeEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.RefundCurrencyService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;

import ng.bayue.exception.CommonServiceException;

@Service
public class RefundCurrencyServiceImpl implements RefundCurrencyService {

	private static Logger logger = LoggerFactory.getLogger(RefundCurrencyServiceImpl.class);

	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private UserCurrencyService userCurrencyService;

	@Override
	@Transactional
	public synchronized int refundCurrency() throws CommonServiceException {
		logger.info("user currency business service start...");

		List<TopicDO> listTopics = topicService.selectTopicNotRefund();
		if (CollectionUtils.isEmpty(listTopics)) {
			logger.info("no topic need refund currency");
			return 0;
		}
		List<Long> topicIds = new ArrayList<Long>();
		for (TopicDO topic : listTopics) {
			topicIds.add(topic.getId());
		}
		List<TopicItemDO> tpItems = topicItemService.selectByTopicIds(topicIds);

		List<Long> tpIds = new ArrayList<Long>();
		for (TopicItemDO ti : tpItems) {
			tpIds.add(ti.getId());
		}
		List<AuctionRecordDO> listArs = auctionRecordService.selectByTopicItemIds(tpIds);

		// 获取竞拍成功的记录
		List<AuctionRecordDO> listAuctSuccess = new ArrayList<AuctionRecordDO>();
		for (TopicItemDO ti : tpItems) {
			double floorPrice = ti.getFloorPrice();
			long tpItemId = ti.getId();
			double maxAuctionPrice = 0d; // 该商品最终竞拍价
			AuctionRecordDO temp = null;
			for (AuctionRecordDO ar : listArs) {
				long tpItemId1 = ar.getTopicItemId();
				if (tpItemId == tpItemId1) {
					Integer currentAuctPrice = ar.getCurrentAuctPrice();
					if (currentAuctPrice.doubleValue() > maxAuctionPrice) {
						maxAuctionPrice = currentAuctPrice;
						temp = ar;
					}
				}
			}
			if (null != temp && floorPrice <= maxAuctionPrice) {
				listAuctSuccess.add(temp);
			}

		}

		// 用户西币回退列表：key-userId;value-refundCurrency
		Map<String, Integer> refundMap = new HashMap<String, Integer>();
		// 竞拍成功的用户西币不回退
		Map<String, Integer> auctSuccessMap = new HashMap<String, Integer>();
		// 插入回退记录
		List<AuctionRecordDO> listRecords = new ArrayList<AuctionRecordDO>();
		for (AuctionRecordDO ar : listArs) {
			// 计算回退列表,这里以用户为基准,不管用户参与了多少商品的竞拍
			Long userId = ar.getUserId();
			long topicItemId = ar.getTopicItemId();
			String userIdStr = String.valueOf(userId);
			Integer totalCurrency = ar.getTotalCurrency();
			Integer refundCurrency = totalCurrency;

			boolean isSuccessUser = false; // 是否是竞拍成功用户,默认不是
			// 处理竞拍成功的用户
			for (AuctionRecordDO arSuccess : listAuctSuccess) {
				long topicItemId1 = arSuccess.getTopicItemId();
				long userIdSuccess = arSuccess.getUserId();
				if (userId.longValue() == userIdSuccess && topicItemId == topicItemId1) {
					isSuccessUser = true;
					if (auctSuccessMap.containsKey(userIdStr)) {
						refundCurrency += auctSuccessMap.get(userIdStr);
					}
					auctSuccessMap.put(userIdStr, refundCurrency);
				}
			}

			if (isSuccessUser) {
				continue;
			}

			// 处理非竞拍成功用户西币回退
			if (refundMap.containsKey(userIdStr)) {
				refundCurrency += refundMap.get(userIdStr);
			}
			refundMap.put(userIdStr, refundCurrency);

			// 获取用户回退记录，这里以用户为基准：每一件竞拍商品不同用户各有一条回退记录,同一用户可能参与多个商品竞拍
			boolean hasRecord = false; // 是否有该用户的记录了，默认没有
			int refundNum = 1; // 回退次数(即该用户在该商品总共出价了多少次)
			for (AuctionRecordDO record : listRecords) {
				long recordUid = record.getUserId();
				long tpId = record.getTopicItemId();
				if (userId.longValue() == recordUid) {
					if (tpId == topicItemId) { // 已经有记录了,更新该用户在竞拍该商品的回退值
						hasRecord = true;
						refundNum = record.getRefundNum();
						Integer totalCurrency1 = record.getTotalCurrency() + totalCurrency;
						record.setRefundNum(++refundNum);
						record.setTotalCurrency(totalCurrency1);
						continue;
					} else {
						hasRecord = false;
					}
				}
			}

			if (!hasRecord) { // 生成新记录
				AuctionRecordDO arNew = new AuctionRecordDO();
				arNew.setTopicItemId(topicItemId);
				arNew.setRecordType(AuctionRecordTypeEnum.REFUND.code);
				arNew.setUserId(userId);
				arNew.setMobile(ar.getMobile());
				arNew.setNickname(ar.getNickname());
				arNew.setRefundNum(refundNum);
				arNew.setTotalCurrency(totalCurrency);
				arNew.setCreateTime(new Date());

				listRecords.add(arNew);
			}

		}

		if (refundMap.isEmpty() || CollectionUtils.isEmpty(listRecords)) {
			logger.info("refund user currency exception: refund list or refund record list is empty");
			throw new CommonServiceException("用户西币值回退异常,回退列表数据获取异常,--服务器内部处理错误");
		}
		
		int res = 0;
		if (!auctSuccessMap.isEmpty()) {
			res = userCurrencyService.reduceAuctionSuccess(auctSuccessMap);
			if (res <= 0) {
				logger.info("reduce auction success user currency failure: reduce operation exception");
				throw new CommonServiceException("竞拍成功用户西币值扣减异常");
			}
		}
		
		// 回退西币
		res = userCurrencyService.refundCurrency(refundMap);
		if (res <= 0) {
			logger.info("refund user currency failure: refund operation exception");
			throw new CommonServiceException("用户西币值回退异常,回退用户西币异常");
		}
		// 插入回退记录
		res = auctionRecordService.insertBatch(listRecords);
		if (res <= 0) {
			logger.info("refund user currency failure: refund operation exception");
			throw new CommonServiceException("用户西币值回退异常,插入回退记录异常");
		}

		// 更新专题回退状态为已回退
		for (TopicDO topic : listTopics) {
			topic.setRefundCurrencyStatus(true);
			topic.setModifyTime(new Date());
			res = topicService.update(topic, false);
			if (res <= 0) {
				logger.info("refund user currency failure: refund operation exception");
				throw new CommonServiceException("用户西币值回退异常,更新专题回退状态异常");
			}
		}

		return res;
	}

}
