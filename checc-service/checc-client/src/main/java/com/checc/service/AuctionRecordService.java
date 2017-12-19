package com.checc.service;

import ng.bayue.service.common.GeneralService;
import com.checc.domain.AuctionRecordDO;

 /**
 * 竞拍记录 Service
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
	
}
