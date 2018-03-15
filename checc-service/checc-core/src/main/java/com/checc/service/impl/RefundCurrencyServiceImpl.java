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
import com.checc.dto.LeadEdgeDTO;
import com.checc.dto.refund.RefundTopicDTO;
import com.checc.dto.refund.RefundTopicItemDTO;
import com.checc.enums.AuctionRecordTypeEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.PurchaseApplyService;
import com.checc.service.RefundCurrencyService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;

import ng.bayue.exception.CommonServiceException;

@Service
public class RefundCurrencyServiceImpl implements RefundCurrencyService {

	private static Logger logger = LoggerFactory.getLogger(RefundCurrencyServiceImpl.class);

	@Autowired
	private TopicService topicService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private UserCurrencyService userCurrencyService;
	@Autowired
	private PurchaseApplyService purchaseApplyService;

	@Override
	@Transactional
	public synchronized int refundCurrency() throws CommonServiceException {
		List<RefundTopicDTO> refundTopicList = auctionRecordService.selectRefundTopicRecords();
		if (CollectionUtils.isEmpty(refundTopicList)) {
			logger.info("no topic need refund currency");
			return 0;
		}
		Date nowDate = new Date();
		// 退回的西币记录，每一个用户在同一件商品中只有一条退回记录
		List<AuctionRecordDO> insertRecords = new ArrayList<AuctionRecordDO>();
		// 流拍回退 所有参与的专题商品的 西币到余额账户(竞拍账户)
		Map<String, Integer> refundFlowMap = new HashMap<String, Integer>();
		// 有人竞拍成功 所有非竞拍成功者 参与的专题商品的西币 回退到退回账户
		Map<String, Integer> refundNotFlowMap = new HashMap<String, Integer>();
		// 竞拍成功者 扣减西币余额账户
		Map<String, Integer> refundSuccessMap = new HashMap<String, Integer>();
		// 竞拍用户信息
		Map<String, Map<String, Object>> auctionUserInfoMap = new HashMap<String, Map<String, Object>>();
		// 封装某一竞拍商品下不同用户竞拍话费的西币
		Map<String, Integer> recordItemMap = null;
		// 竞拍成功的商品更新购车状态为待申请购车
		List<Long> topicItemIds = new ArrayList<Long>();
		for (RefundTopicDTO rt : refundTopicList) {
			List<RefundTopicItemDTO> topicItemList = rt.getTopicItems();
			if (CollectionUtils.isEmpty(topicItemList)) { // 该专题未关联商品
				continue;
			}
			for (RefundTopicItemDTO rti : topicItemList) {
				long tiId = rti.getTiId();
				double floorPrice = rti.getFloorPrice();
				List<AuctionRecordDO> recordList = rti.getAuctionRecords(); // 获取该商品所有的竞拍记录

				boolean isFlowAuction = true; // 本商品是否流拍,true-流拍；false-未流拍
				long auctSuccessUserId = 0; // 本商品竞拍成功的用户id
				// 流拍场景：1.最终竞拍出价 小于 底价; 2.该商品没有人参与竞拍，即recordList列表为空；不做任何处理
				if (CollectionUtils.isNotEmpty(recordList)) {
					// recordList 按照记录id倒序，所以第一条记录为该商品最终出价记录
					AuctionRecordDO finalAuctionRecord = recordList.get(0);
					if (floorPrice <= finalAuctionRecord.getCurrentAuctPrice().doubleValue()) {
						isFlowAuction = false;
//						auctSuccessUserId = finalAuctionRecord.getUserId();
						LeadEdgeDTO leadEdgeDto = auctionRecordService.selectLeadEdge(tiId);
						auctSuccessUserId = leadEdgeDto.getUserId();
					}
				} else { // 该商品没有人出价，即没有用户需要回退处理
					continue;
				}

				recordItemMap = new HashMap<String, Integer>();

				// 统计回退数据，并生成回退记录
				if (isFlowAuction) {
					// 商品流拍,所有用户西币都回退到竞拍账户,这部分西币可以用于再次竞拍
					for (AuctionRecordDO record : recordList) {
						String userIdStr = String.valueOf(record.getUserId());
						int totalCurrency = record.getTotalCurrency();
						int countCurrency = totalCurrency; // 统计西币累加
						if (refundFlowMap.containsKey(userIdStr)) {
							countCurrency += refundFlowMap.get(userIdStr);
							refundFlowMap.put(userIdStr, countCurrency);
						} else {
							refundFlowMap.put(userIdStr, countCurrency);
						}
						// 统计该商品下所有参与用户花费的西币值
						if (recordItemMap.containsKey(userIdStr)) {
							totalCurrency += recordItemMap.get(userIdStr);
							recordItemMap.put(userIdStr, totalCurrency);
						} else {
							recordItemMap.put(userIdStr, totalCurrency);
						}
						// 获取竞拍用户信息
						if (!auctionUserInfoMap.containsKey(userIdStr)) {
							Map<String, Object> userInfoMap = new HashMap<String, Object>();
							userInfoMap.put("mobile", record.getMobile());
							userInfoMap.put("nickname", record.getNickname());
							auctionUserInfoMap.put(userIdStr, userInfoMap);
						}
					}

					// 生成回退记录
					for (Map.Entry<String, Integer> entry : recordItemMap.entrySet()) {
						String userIdStr = entry.getKey();
						long userId = Long.valueOf(userIdStr);
						Integer refundCurrency = entry.getValue();
						AuctionRecordDO insertRecord = new AuctionRecordDO();
						insertRecord.setUserId(userId);
						insertRecord.setMobile((String) auctionUserInfoMap.get(userIdStr).get("mobile"));
						insertRecord.setNickname((String) auctionUserInfoMap.get(userIdStr).get("nickname"));
						insertRecord.setTotalCurrency(refundCurrency);
						insertRecord.setCreateTime(nowDate);
						insertRecord.setRecordType(AuctionRecordTypeEnum.REFUND.code);
						insertRecord.setTopicItemId(tiId);

						insertRecords.add(insertRecord);
					}
				} else {
					// 商品有人竞拍成功，竞拍成功者西币不退，其他失败者西币退还到 兑换账户,该退回的西币不得再次用于竞拍，只能兑换
					for (AuctionRecordDO record : recordList) {
						long userId = record.getUserId();
						String userIdStr = String.valueOf(userId);
						int totalCurrency = record.getTotalCurrency();
						int countCurrency = totalCurrency; // 统计西币累加
						// 过滤竞拍成功者, 直接扣减西币余额，同时清除冻结西币
						if (userId == auctSuccessUserId) {
							if (refundSuccessMap.containsKey(userIdStr)) {
								countCurrency += refundSuccessMap.get(userIdStr);
								refundSuccessMap.put(userIdStr, countCurrency);
							} else {
								refundSuccessMap.put(userIdStr, countCurrency);
							}
							continue;
						}
						// 统计非流拍，且非竞拍成功者参与竞拍的西币
						if (refundNotFlowMap.containsKey(userIdStr)) {
							countCurrency += refundNotFlowMap.get(userIdStr);
							refundNotFlowMap.put(userIdStr, countCurrency);
						} else {
							refundNotFlowMap.put(userIdStr, countCurrency);
						}
						// 统计该商品下所有参与用户花费的西币值
						if (recordItemMap.containsKey(userIdStr)) {
							totalCurrency += recordItemMap.get(userIdStr);
							recordItemMap.put(userIdStr, totalCurrency);
						} else {
							recordItemMap.put(userIdStr, totalCurrency);
						}
						// 获取竞拍用户信息
						if (!auctionUserInfoMap.containsKey(userIdStr)) {
							Map<String, Object> userInfoMap = new HashMap<String, Object>();
							userInfoMap.put("mobile", record.getMobile());
							userInfoMap.put("nickname", record.getNickname());
							auctionUserInfoMap.put(userIdStr, userInfoMap);
						}
					}

					// 生成回退记录
					for (Map.Entry<String, Integer> entry : recordItemMap.entrySet()) {
						String userIdStr = entry.getKey();
						long userId = Long.valueOf(userIdStr);
						Integer refundCurrency = entry.getValue();
						AuctionRecordDO insertRecord = new AuctionRecordDO();
						insertRecord.setUserId(userId);
						insertRecord.setMobile((String) auctionUserInfoMap.get(userIdStr).get("mobile"));
						insertRecord.setNickname((String) auctionUserInfoMap.get(userIdStr).get("nickname"));
						insertRecord.setTotalCurrency(refundCurrency);
						insertRecord.setCreateTime(nowDate);
						insertRecord.setRecordType(AuctionRecordTypeEnum.REFUND.code);
						insertRecord.setTopicItemId(tiId);

						insertRecords.add(insertRecord);
					}
					
					topicItemIds.add(tiId);
					
				}

			}
		}

		int res = 0; // 操作结果

		// 更新专题回退状态为已回退
		for (RefundTopicDTO rt : refundTopicList) {
			TopicDO topic = new TopicDO();
			topic.setId(rt.gettId());
			topic.setRefundCurrencyStatus(true);
			topic.setModifyTime(new Date());
			res = topicService.update(topic, false);
			if (res <= 0) {
				logger.info("refund user currency failure: refund operation exception");
				throw new CommonServiceException("用户西币值回退异常,更新专题回退状态异常");
			}
		}

		// 没有需要回退的用户
		if (refundFlowMap.isEmpty() && refundNotFlowMap.isEmpty() && CollectionUtils.isEmpty(insertRecords)) {
			return 0;
		}

		// 更新用户西币账户
		Map<String, Map<String, Integer>> params = new HashMap<String, Map<String, Integer>>();
		params.put("refundFlowMap", refundFlowMap);
		params.put("refundSuccessMap", refundSuccessMap);
		params.put("refundNotFlowMap", refundNotFlowMap);
		res = userCurrencyService.refund(params);
		if (res <= 0) {
			logger.info("refund user currency failure: refund operation exception");
			throw new CommonServiceException("用户西币值回退异常,更新用户西币账户异常");
		}

		// 插入回退记录
		res = auctionRecordService.insertBatch(insertRecords);
		if (res <= 0) {
			logger.info("refund user currency failure: refund operation exception");
			throw new CommonServiceException("用户西币值回退异常,插入回退记录异常");
		}
		
		// 更新竞拍成功专题商品购车状态为待申请
		res = purchaseApplyService.updatePurchaseStatusToNotApply(topicItemIds);
		if(res < 0){
			logger.info("update auction success topic item purchase status exception");
			throw new CommonServiceException("更新竞拍成功专题商品购车状态为待申请异常");
		}

		return res;
	}

}
