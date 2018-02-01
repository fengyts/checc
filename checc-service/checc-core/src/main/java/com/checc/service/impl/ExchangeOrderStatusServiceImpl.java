package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ExchangeOrderStatusDAO;
import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.service.ExchangeOrderStatusService;
import com.checc.vo.ExchangeOrderVO;
import com.checc.vo.front.ExchangeOrderStatusVO;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="exchangeOrderStatusService")
public class ExchangeOrderStatusServiceImpl  implements ExchangeOrderStatusService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ExchangeOrderStatusDAO exchangeOrderStatusDAO;

	@Override
	public Long insert(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonServiceException {
		try {
			return exchangeOrderStatusDAO.insert(exchangeOrderStatusDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(ExchangeOrderStatusDO exchangeOrderStatusDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) exchangeOrderStatusDAO.update(exchangeOrderStatusDO);
			}else{
				return (Integer) exchangeOrderStatusDAO.updateDynamic(exchangeOrderStatusDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) exchangeOrderStatusDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public ExchangeOrderStatusDO selectById(Long id) throws CommonServiceException {
		try {
			return exchangeOrderStatusDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonServiceException {
		try {
			return exchangeOrderStatusDAO.selectCountDynamic(exchangeOrderStatusDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<ExchangeOrderStatusDO> selectDynamic(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonServiceException {
		try {
			return exchangeOrderStatusDAO.selectDynamic(exchangeOrderStatusDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<ExchangeOrderStatusDO> selectDynamicPageQuery(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonServiceException {
		try {
			return exchangeOrderStatusDAO.selectDynamicPageQuery(exchangeOrderStatusDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<ExchangeOrderStatusDO> queryPageListDynamic(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonServiceException{
		if (exchangeOrderStatusDO != null) {
			Long totalCount = this.selectCountDynamic(exchangeOrderStatusDO);

			Page<ExchangeOrderStatusDO> page = new Page<ExchangeOrderStatusDO>();
			page.setPageNo(exchangeOrderStatusDO.getStartPage());
			page.setPageSize(exchangeOrderStatusDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ExchangeOrderStatusDO> resultList = this.selectDynamicPageQuery(exchangeOrderStatusDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ExchangeOrderStatusDO>();
	}
	
	@Override
	public Page<ExchangeOrderStatusDO> queryPageListDynamicAndStartPageSize(ExchangeOrderStatusDO exchangeOrderStatusDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (exchangeOrderStatusDO != null && startPage>0 && pageSize>0) {
			exchangeOrderStatusDO.setStartPage(startPage);
			exchangeOrderStatusDO.setPageSize(pageSize);
			return this.queryPageListDynamic(exchangeOrderStatusDO);
		}
		return new Page<ExchangeOrderStatusDO>();
	}

	@Override
	public ExchangeOrderStatusVO selectExchangeOrderDetails(Long recordId) {
		if(null == recordId){
			return null;
		}
		return exchangeOrderStatusDAO.selectExchangeOrderDetails(recordId);
	}

	@Override
	public Page<ExchangeOrderVO> queryPageExchangeBackend(PurchaseExchangeStatusDTO dto, Integer pageNo, Integer pageSize) {
		Page<ExchangeOrderVO> page = new Page<ExchangeOrderVO>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		dto.setStartPage(pageNo);
		dto.setPageSize(pageSize);
		Integer totalCount = exchangeOrderStatusDAO.countExchange(dto);
		if(null == totalCount || totalCount.intValue() <= 0){
			return page;
		}
		List<ExchangeOrderVO> list = exchangeOrderStatusDAO.selectExchangeListBackend(dto);
		
		return page;
	}
	
	
}
