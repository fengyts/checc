package com.checc.service;

import ng.bayue.common.Page;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.AuctionRecordDO;

/**
 * 竞拍记录 Service
 * 
 * @author fengyts 2017-11-16 14:54:40
 */
public interface AuctionRecordService extends GeneralService<AuctionRecordDO, AuctionRecordDO> {

	/**
	 * <pre>
	 * 获取最新的出价记录
	 * </pre>
	 *
	 * @param topicItemId
	 * @return
	 */
	AuctionRecordDO selectLatestAuction(Long topicItemId);
	
	/**
	 * <pre>
	 * 获取用户是否已经兑换过商品
	 * </pre>
	 *
	 * @param userId
	 * @param topicItemId
	 * @return true:已经兑换过,false:尚未兑换
	 */
	Boolean isExchanged(Long userId, Long topicItemId);

	/**
	 * <pre>
	 *  用户中心获取竞拍或兑换列表
	 * </pre>
	 *
	 * @param userId
	 * @param recordType
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	Page<AuctionRecordDO> queryPageListUCAuction(Long userId, String recordType, Integer startPage,
			Integer pageSize);
	
	

}
