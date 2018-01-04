package com.checc.dao;

import java.util.List;
import java.util.Map;

import com.checc.domain.AuctionRecordDO;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralDAO;

 /**
 * 竞拍记录 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface AuctionRecordDAO extends GeneralDAO<AuctionRecordDO> {

	/**
	 * <pre>
	 * 获取最新的出价记录
	 * </pre>
	 *
	 * @param topicItemId
	 * @return
	 */
	AuctionRecordDO selectLatestAuction(Long topicItemId); 
	
	Long selectUCAuctionCount(Map<String, Object> params);
	
	/**
	 * <pre>
	 * 获取用户竞拍或兑换列表
	 * </pre>
	 *
	 * @param params
	 * @return
	 */
	List<AuctionRecordDO> selectUCAuctionList(Map<String, Object> params);
	
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
	int insertBatch(List<AuctionRecordDO> list) throws CommonDAOException;
	
}
