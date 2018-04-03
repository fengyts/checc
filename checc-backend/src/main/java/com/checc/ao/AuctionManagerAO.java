package com.checc.ao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.domain.ExpressInfoDO;
import com.checc.domain.PurchaseApplyDO;
import com.checc.dto.ExpressageInfoDTO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.enums.ShipmentsStatusEnum;
import com.checc.service.ExchangeOrderStatusService;
import com.checc.service.ExpressInfoService;
import com.checc.service.PurchaseApplyService;
import com.checc.service.generate.ExpressageInfoService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.PurchaseApplyVO;
import com.checc.vo.front.ExchangeOrderStatusVO;

import ng.bayue.common.Page;
import ng.bayue.util.StringUtils;

@Service
public class AuctionManagerAO {
	
	@Autowired
	private PurchaseApplyService purchaseApplyService;
	@Autowired
	private ExchangeOrderStatusService exchangeOrderStatusService;
	@Autowired
	private ExpressageInfoService expressageInfoService;
	@Autowired
	private ExpressInfoService expressInfoService;

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

	public ResultMessage saveShipmentsInfo(String type, ExchangeOrderStatusDO eosDO) {
		Long id = eosDO.getId();
		if(null == id || id.longValue() < 1){
			return ResultMessage.serverInnerError();
		}
		// type: 操作类型：01-修改备注；02-修改发货状态
		String remark = eosDO.getRemark();
		String shipmentsStatus = eosDO.getShipmentsStatus();
		if ("01".equals(type)) {
			if (StringUtils.isBlank(remark)) {
				return ResultMessage.validParameterNull("备注信息不能为空");
			}
		} else {
			if (StringUtils.isBlank(shipmentsStatus)) {
				return ResultMessage.validParameterNull("发货状态不能为空");
			}
		}
		
		if(!ShipmentsStatusEnum.HAS_SIGNFOR.code.equals(type)){
			eosDO.setShipmentsTime(new Date());
		}
		eosDO.setModifyUserId(UserHandler.getUser().getId());
		
		exchangeOrderStatusService.update(eosDO, false);

		return ResultMessage.success();
	}
	
	public ExchangeOrderStatusVO selectExchangeOrderDetails(Long recordId) {
		ExchangeOrderStatusVO vo = exchangeOrderStatusService.selectExchangeOrderDetails(recordId);
		return null != vo ? vo : new ExchangeOrderStatusVO();
	}
	
	/**
	 * <pre>
	 * 是否已经发货：true-已经发货；false-未发货
	 * </pre>
	 *
	 * @return
	 */
	public boolean hasConsign(Long exchangeOrderId) {
		if (null == exchangeOrderId) {
			return false;
		}
		ExchangeOrderStatusDO eosDO = exchangeOrderStatusService.selectById(exchangeOrderId);
		if (null == eosDO) {
			return false;
		}
		if (ShipmentsStatusEnum.NOT_SHIPMENTS.code.equals(eosDO.getShipmentsStatus())
				|| StringUtils.isBlank(eosDO.getWaybillNo())) {
			return false;
		}
		return true;
	}
	
	/**
	 * <pre>
	 * 获取物流信息
	 * </pre>
	 *
	 * @param exchangeOrderId
	 * @param waybillNo
	 * @return
	 */
	public ExpressageInfoDTO getShipmentsInfo(Long exchangeOrderId, String waybillNo) {
		if (null == exchangeOrderId || StringUtils.isBlank(waybillNo)) {
			return null;
		}
		ExchangeOrderStatusDO eosDO = exchangeOrderStatusService.selectById(exchangeOrderId);

		ExpressInfoDO expressInfo = expressInfoService.selectById(eosDO.getExpressId());

		ExpressageInfoDTO dto = expressageInfoService.parseExpressInfo(expressInfo.getCompanyCode(), waybillNo);
		dto.setShipmentsTime(eosDO.getShipmentsTime());
		dto.setCompany(expressInfo.getName());
		dto.setCompanyCode(expressInfo.getCompanyCode());
		dto.setWaybillNo(waybillNo);
		
		return dto;

	}
	

}
