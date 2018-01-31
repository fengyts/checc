package com.checc.service;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.Page;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import java.util.List;

import com.checc.domain.PurchaseApplyDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.vo.PurchaseApplyVO;

 /**
 * 竞拍商品购买状态，兑换商品发货状态 Service
 * @author fengyts 2018-01-25 22:01:46
 */
public interface PurchaseApplyService extends GeneralService<PurchaseApplyDO, PurchaseApplyDO> {
	
	/**
	 * <pre>
	 * 竞拍成功商品更新购车状态为待申请
	 * </pre>
	 *
	 * @param topicItemIds
	 * @return
	 */
	public int updatePurchaseStatusToNotApply(List<Long> topicItemIds) throws CommonServiceException;

	/**
	 * <pre>
	 * 竞拍成功购车申请
	 * </pre>
	 *
	 * @param userId
	 * @param tiId
	 * @return
	 */
	public CommonResultMessage successAuctPurchaseApply(Long userId, Long tiId) throws CommonServiceException;
	
	/**
	 * <pre>
	 * 竞拍管理后台分页查询
	 * </pre>
	 *
	 * @param dto
	 * @return
	 */
	public Page<PurchaseApplyVO> queryPageBackend(PurchaseExchangeStatusDTO dto, Integer pageNo, Integer pageSize);

}
