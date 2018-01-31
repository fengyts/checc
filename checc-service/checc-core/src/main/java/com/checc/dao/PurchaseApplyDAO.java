package com.checc.dao;

import java.util.List;

import com.checc.domain.PurchaseApplyDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.vo.PurchaseApplyVO;

import ng.bayue.service.common.GeneralDAO;

 /**
 * 竞拍商品购买状态，兑换商品发货状态 DAO
 *
 * @author fengyts 2018-01-25 22:01:46
 */
public interface PurchaseApplyDAO extends GeneralDAO<PurchaseApplyDO> {
	
	/**
	 * <pre>
	 * 后台竞拍管理分页查询统计总数量
	 * </pre>
	 *
	 * @param dto
	 * @return
	 */
	Integer countAuctionBackend(PurchaseExchangeStatusDTO dto);
	
	/**
	 * <pre>
	 * 后台竞拍管理分页查询
	 * </pre>
	 *
	 * @param dto
	 * @return
	 */
	List<PurchaseApplyVO> queryPageBackend(PurchaseExchangeStatusDTO dto);
	
	/**
	 * <pre>
	 * 统计该商品参与竞拍的人数
	 * </pre>
	 *
	 * @param topicItemId
	 * @return
	 */
	Integer countTotalAuction(Long topicItemId);

}
