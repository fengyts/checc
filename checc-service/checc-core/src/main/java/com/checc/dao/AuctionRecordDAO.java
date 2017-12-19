package com.checc.dao;

import com.checc.domain.AuctionRecordDO;

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
	
}
