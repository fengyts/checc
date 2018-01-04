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

		// 回退列表：key-userId;value-refundCurrency
		Map<String, Integer> refundMap = new HashMap<String, Integer>();
		// 插入回退记录
		List<AuctionRecordDO> listRecords = new ArrayList<AuctionRecordDO>();
		for (AuctionRecordDO ar : listArs) {
			// 计算回退列表,这里以用户为基准,不管用户参与了多少商品的竞拍
			Long userId = ar.getUserId();
			String userIdStr = String.valueOf(userId);
			Integer totalCurrency = ar.getTotalCurrency();
			Integer refundCurrency = totalCurrency;
			if (refundMap.containsKey(userIdStr)) {
				refundCurrency += refundMap.get(userIdStr);
			}
			refundMap.put(userIdStr, refundCurrency);

			// 获取用户回退记录，这里以用户为基准：每一件竞拍商品不同用户各有一条回退记录,同一用户可能参与多个商品竞拍
			boolean hasRecord = false; // 是否有该用户的记录了
			int refundNum = 1; //回退次数(即该用户在该商品总共出价了多少次)
			long topicItemId = ar.getTopicItemId();
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
			throw new CommonServiceException("用户西币值回退异常,回退列表数据获取异常");
		}

		int res = 0;
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
		for(TopicDO topic : listTopics){
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
