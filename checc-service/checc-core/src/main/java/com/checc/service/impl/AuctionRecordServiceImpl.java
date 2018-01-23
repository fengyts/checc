package com.checc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.dao.AuctionRecordDAO;
import com.checc.domain.AuctionRecordDO;
import com.checc.dto.refund.RefundTopicDTO;
import com.checc.enums.AuctionRecordTypeEnum;
import com.checc.service.AuctionRecordService;

@Service(value = "auctionRecordService")
public class AuctionRecordServiceImpl implements AuctionRecordService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private AuctionRecordDAO auctionRecordDAO;

	@Override
	public Long insert(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.insert(auctionRecordDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(AuctionRecordDO auctionRecordDO, boolean isAllField) throws CommonServiceException {
		try {
			if (isAllField) {
				return (Integer) auctionRecordDAO.update(auctionRecordDO);
			} else {
				return (Integer) auctionRecordDAO.updateDynamic(auctionRecordDO);
			}
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) auctionRecordDAO.deleteById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public AuctionRecordDO selectById(Long id) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectCountDynamic(auctionRecordDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public List<AuctionRecordDO> selectDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectDynamic(auctionRecordDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	private List<AuctionRecordDO> selectDynamicPageQuery(AuctionRecordDO auctionRecordDO)
			throws CommonServiceException {
		try {
			return auctionRecordDAO.selectDynamicPageQuery(auctionRecordDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Page<AuctionRecordDO> queryPageListDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		if (auctionRecordDO != null) {
			Long totalCount = this.selectCountDynamic(auctionRecordDO);

			Page<AuctionRecordDO> page = new Page<AuctionRecordDO>();
			page.setPageNo(auctionRecordDO.getStartPage());
			page.setPageSize(auctionRecordDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<AuctionRecordDO> resultList = this.selectDynamicPageQuery(auctionRecordDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<AuctionRecordDO>();
	}

	@Override
	public Page<AuctionRecordDO> queryPageListDynamicAndStartPageSize(AuctionRecordDO auctionRecordDO,
			Integer startPage, Integer pageSize) throws CommonServiceException {
		if (auctionRecordDO != null && startPage > 0 && pageSize > 0) {
			auctionRecordDO.setStartPage(startPage);
			auctionRecordDO.setPageSize(pageSize);
			return this.queryPageListDynamic(auctionRecordDO);
		}
		return new Page<AuctionRecordDO>();
	}

	@Override
	public AuctionRecordDO selectLatestAuction(Long topicItemId) {
		if (null == topicItemId) {
			return null;
		}
		return auctionRecordDAO.selectLatestAuction(topicItemId);
	}

	@Override
	public Boolean isExchanged(Long userId, Long topicItemId) {
		AuctionRecordDO ar = new AuctionRecordDO();
		ar.setUserId(userId);
		ar.setTopicItemId(topicItemId);
		ar.setRecordType(AuctionRecordTypeEnum.EXCHANGE.code);
		List<AuctionRecordDO> list = this.selectDynamic(ar);
		return CollectionUtils.isNotEmpty(list);
	}

	@Override
	public Page<AuctionRecordDO> queryPageListUCAuction(Long userId, String recordType, Integer startPage,
			Integer pageSize) {
		if (startPage > 0 && pageSize > 0) {
			Page<AuctionRecordDO> page = new Page<AuctionRecordDO>();
			Map<String, Object> params = new HashMap<String, Object>();
			int start = startPage < 0 || pageSize < 0 ? 0 : (startPage - 1) * pageSize;
			params.put("start", start);
			params.put("pageSize", pageSize);
			params.put("userId", userId);
			params.put("recordType", recordType);

			Long totalCount = auctionRecordDAO.selectUCAuctionCount(params);
			List<AuctionRecordDO> list = new ArrayList<AuctionRecordDO>();
			if (totalCount.longValue() > 0) {
				list = auctionRecordDAO.selectUCAuctionList(params);
			}
			page.setList(list);
			page.setPageNo(startPage);
			page.setPageSize(pageSize);
			page.setTotalCount(totalCount.intValue());

			return page;
		}
		return new Page<AuctionRecordDO>();
	}

	@Override
	public List<AuctionRecordDO> selectByTopicItemIds(List<Long> tpIds) {
		if (CollectionUtils.isEmpty(tpIds)) {
			return new ArrayList<AuctionRecordDO>();
		}
		return auctionRecordDAO.selectByTopicItemIds(tpIds);
	}

	@Override
	public int insertBatch(List<AuctionRecordDO> list) throws CommonServiceException {
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		try {
			return auctionRecordDAO.insertBatch(list);
		} catch (CommonDAOException e) {
			logger.error("批量插入操作记录异常:{}", e);
		}
		return -1;
	}

	@Override
	public List<RefundTopicDTO> selectRefundTopicRecords() {
		return auctionRecordDAO.selectRefundTopicRecords();
	}
	
	
	
}
