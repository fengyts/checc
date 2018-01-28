package com.checc.service;

import ng.bayue.common.CommonResultMessage;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.PurchaseApplyDO;

 /**
 * 竞拍商品购买状态，兑换商品发货状态 Service
 * @author fengyts 2018-01-25 22:01:46
 */
public interface PurchaseApplyService extends GeneralService<PurchaseApplyDO, PurchaseApplyDO> {

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

}
