package com.checc.service;

import java.util.List;

import com.checc.domain.AuctionRecordDO;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

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
	
	/**
	 * <pre>
	 *  根据topicItemId查询商品	竞拍的记录
	 * </pre>
	 *
	 * @param tpIds
	 * @return
	 */
	List<AuctionRecordDO> selectByTopicItemIds(List<Long> tpIds);
	
	/**
	 * <pre>
	 * 批量插入操作记录
	 * </pre>
	 *
	 * @param list
	 * @return
	 * @throws CommonServiceException
	 */
	int insertBatch(List<AuctionRecordDO> list) throws CommonServiceException;

}
