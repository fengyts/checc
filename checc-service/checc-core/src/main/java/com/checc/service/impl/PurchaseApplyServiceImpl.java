package com.checc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.RedisCacheService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checc.dao.PurchaseApplyDAO;
import com.checc.domain.AuctionRecordDO;
import com.checc.domain.PurchaseApplyDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.enums.PurchaseStatusEnum;
import com.checc.enums.TopicProgressEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.PurchaseApplyService;
import com.checc.vo.PurchaseApplyVO;

@Service(value = "purchaseApplyService")
public class PurchaseApplyServiceImpl implements PurchaseApplyService {

	private Log logger = LogFactory.getLog(this.getClass());

	private static final String LOCK_PURCHASE_APPLY_KEY = "AUCTION_SUCCESS_PURCHASE_APPLY_";

	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;

	@Autowired
	private PurchaseApplyDAO purchaseApplyDAO;
	@Autowired
	private AuctionRecordService auctionRecordService;

	@Override
	public Long insert(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
		try {
			return purchaseApplyDAO.insert(purchaseApplyDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(PurchaseApplyDO purchaseApplyDO, boolean isAllField)
			throws CommonServiceException {
		try {
			if (isAllField) {
				return (Integer) purchaseApplyDAO.update(purchaseApplyDO);
			} else {
				return (Integer) purchaseApplyDAO.updateDynamic(purchaseApplyDO);
			}
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) purchaseApplyDAO.deleteById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public PurchaseApplyDO selectById(Long id) throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectCountDynamic(purchaseApplyDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public List<PurchaseApplyDO> selectDynamic(PurchaseApplyDO purchaseApplyDO)
			throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectDynamic(purchaseApplyDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	private List<PurchaseApplyDO> selectDynamicPageQuery(PurchaseApplyDO purchaseApplyDO)
			throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectDynamicPageQuery(purchaseApplyDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Page<PurchaseApplyDO> queryPageListDynamic(PurchaseApplyDO purchaseApplyDO)
			throws CommonServiceException {
		if (purchaseApplyDO != null) {
			Long totalCount = this.selectCountDynamic(purchaseApplyDO);

			Page<PurchaseApplyDO> page = new Page<PurchaseApplyDO>();
			page.setPageNo(purchaseApplyDO.getStartPage());
			page.setPageSize(purchaseApplyDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<PurchaseApplyDO> resultList = this.selectDynamicPageQuery(purchaseApplyDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<PurchaseApplyDO>();
	}

	@Override
	public Page<PurchaseApplyDO> queryPageListDynamicAndStartPageSize(
			PurchaseApplyDO purchaseApplyDO, Integer startPage, Integer pageSize)
			throws CommonServiceException {
		if (purchaseApplyDO != null && startPage > 0 && pageSize > 0) {
			purchaseApplyDO.setStartPage(startPage);
			purchaseApplyDO.setPageSize(pageSize);
			return this.queryPageListDynamic(purchaseApplyDO);
		}
		return new Page<PurchaseApplyDO>();
	}
	
	@Override
	public int updatePurchaseStatusToNotApply(List<Long> topicItemIds) {
		if(CollectionUtils.isEmpty(topicItemIds)){
			
		}
		return 0;
	}

	@Override
	@Transactional
	public CommonResultMessage successAuctPurchaseApply(Long userId, Long tiId)
			throws CommonServiceException {
		if (null == userId || null == tiId) {
			return CommonResultMessage.failure(CommonMessages.ReqException);
		}

		String applyLock = LOCK_PURCHASE_APPLY_KEY + userId + "_" + tiId;
		try {
			boolean lockFlag = redisCacheService.lock(applyLock);
			if (lockFlag) {
				// 校验是否是拍得者
				AuctionRecordDO ar = auctionRecordService.selectLatestAuction(tiId);
				if (null == ar) {
					return CommonResultMessage.failure(CommonMessages.ReqException);
				}
				if (ar.getUserId().longValue() != userId.longValue()) {
					return CommonResultMessage.failure("请求异常：您不是拍得者");
				}
				// 校验购车申请状态是否是待购车
				PurchaseApplyDO purchaseDO = new PurchaseApplyDO();
				purchaseDO.setPurchaseStatus(PurchaseStatusEnum.NOT_APPLY.code);
				purchaseDO.setTopicItemId(tiId);
				List<PurchaseApplyDO> list = this.selectDynamic(purchaseDO);
				if (CollectionUtils.isEmpty(list) || list.size() != 1) {
					return CommonResultMessage.failure("请求异常：竞拍信息存在异常");
				}
				
				Long purchaseId = list.get(0).getId();
				purchaseDO.setId(purchaseId);
				purchaseDO.setPurchaseStatus(PurchaseStatusEnum.WAIT_BUY.code);
				purchaseDO.setApplyTime(new Date());
				
				this.update(purchaseDO, false);
				return CommonResultMessage.success();
			} else {
				return CommonResultMessage
						.failure(CommonResultCode.BusinessError.BUSINESS_IS_BUSY.desc);
			}
		} catch (CommonServiceException e) {
			logger.error("申请购车异常", e);
			throw e;
		} finally {
			redisCacheService.unLock(applyLock);
		}

	}

	@Override
	public Page<PurchaseApplyVO> queryPageBackend(PurchaseExchangeStatusDTO dto, Integer pageNo, Integer pageSize) {
		Page<PurchaseApplyVO> page = new Page<PurchaseApplyVO>();
		dto.setStartPage(pageNo);
		dto.setPageSize(pageSize);
		Integer totalCount = purchaseApplyDAO.countAuctionBackend(dto);
		if (null == totalCount || totalCount <= 0) {
			return page;
		}
		List<PurchaseApplyVO> list = purchaseApplyDAO.queryPageBackend(dto);
		for (PurchaseApplyVO vo : list) {
			// Long topicItemId = vo.getTiId();
			// Integer countAuctionNum = purchaseApplyDAO.countTotalAuction(topicItemId);
			// vo.setCountAuctionNum(countAuctionNum);
			// 获取专题状态
			String status = "";
			Date startTime = vo.getStartTime();
			Date endTime = vo.getEndTime();
			Date now = new Date();
			if (endTime.before(now)) {
				double floorPrice = vo.getFloorPrice();
				Double finalAuctionPrice = vo.getFinalAuctionPrice();
				if (null == finalAuctionPrice || finalAuctionPrice.doubleValue() < floorPrice) {
					status = TopicProgressEnum.AUCTION_FLOW.code;
				} else {
					status = TopicProgressEnum.AUCTION_SUCCESS.code;
				}
			} else {
				if (startTime.before(now)) {
					status = TopicProgressEnum.InProgress.code;
				} else {
					status = TopicProgressEnum.NotStarted.code;
				}
			}
			vo.setStatus(status);
		}

		page.setList(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);

		return page;
	}
	
	

}
