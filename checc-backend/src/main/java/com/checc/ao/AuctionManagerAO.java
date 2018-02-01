package com.checc.ao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.PurchaseApplyDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.service.ExchangeOrderStatusService;
import com.checc.service.PurchaseApplyService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.PurchaseApplyVO;

import ng.bayue.common.Page;
import ng.bayue.util.StringUtils;

@Service
public class AuctionManagerAO {

	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private ExchangeOrderStatusService exchangeOrderStatusService;

	public Page<PurchaseApplyVO> queryAuctionPageList(PurchaseExchangeStatusDTO dto, Integer pageNo, Integer pageSize) {
		Page<PurchaseApplyVO> pageResult = purchaseApplyService.queryPageBackend(dto, pageNo, pageSize);
		return pageResult;
	}

	public ResultMessage savePurchaseInfo(String type, Long purchaseId, String remark, String purchaseStatus) {
		// type: 操作类型：01-修改备注；02-修改购车状态
		if ("01".equals(type)) {
			if (StringUtils.isBlank(remark)) {
				return ResultMessage.validParameterNull("备注信息不能为空");
			}
		} else {
			if (StringUtils.isBlank(purchaseStatus)) {
				return ResultMessage.validParameterNull("购车状态不能为空");
			}
		}
		PurchaseApplyDO purchaseDO = new PurchaseApplyDO();
		purchaseDO.setId(purchaseId);
		purchaseDO.setRemark(remark);
		purchaseDO.setPurchaseStatus(purchaseStatus);
		purchaseDO.setModifyTime(new Date());
		purchaseDO.setModifyUserId(UserHandler.getUser().getId());

		purchaseApplyService.update(purchaseDO, false);

		return ResultMessage.success();
	}

	public Page<ExchangeOrderVO> queryExchangePageList(PurchaseExchangeStatusDTO dto, Integer pageNo,
			Integer pageSize) {
		Page<ExchangeOrderVO> pageResult = exchangeOrderStatusService.queryPageExchangeBackend(dto, pageNo, pageSize);
		return pageResult;
	}

	public ResultMessage saveShipmentsInfo(String type, Long purchaseId, String remark, String purchaseStatus) {
		
		return ResultMessage.success();
	}

}
