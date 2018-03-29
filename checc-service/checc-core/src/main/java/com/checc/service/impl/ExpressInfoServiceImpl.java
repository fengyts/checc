package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ExpressInfoDAO;
import com.checc.domain.ExpressInfoDO;
import com.checc.service.ExpressInfoService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="expressInfoService")
public class ExpressInfoServiceImpl  implements ExpressInfoService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ExpressInfoDAO expressInfoDAO;

	@Override
	public Long insert(ExpressInfoDO expressInfoDO) throws CommonServiceException {
		try {
			return expressInfoDAO.insert(expressInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}


	@Override
	public int update(ExpressInfoDO expressInfoDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) expressInfoDAO.update(expressInfoDO);
			}else{
				return (Integer) expressInfoDAO.updateDynamic(expressInfoDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) expressInfoDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	


	@Override
	public ExpressInfoDO selectById(Long id) throws CommonServiceException {
		try {
			return expressInfoDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	@Override
	public Long selectCountDynamic(ExpressInfoDO expressInfoDO) throws CommonServiceException {
		try {
			return expressInfoDAO.selectCountDynamic(expressInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<ExpressInfoDO> selectDynamic(ExpressInfoDO expressInfoDO) throws CommonServiceException {
		try {
			return expressInfoDAO.selectDynamic(expressInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<ExpressInfoDO> selectDynamicPageQuery(ExpressInfoDO expressInfoDO) throws CommonServiceException {
		try {
			return expressInfoDAO.selectDynamicPageQuery(expressInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<ExpressInfoDO> queryPageListDynamic(ExpressInfoDO expressInfoDO) throws CommonServiceException{
		if (expressInfoDO != null) {
			Long totalCount = this.selectCountDynamic(expressInfoDO);

			Page<ExpressInfoDO> page = new Page<ExpressInfoDO>();
			page.setPageNo(expressInfoDO.getStartPage());
			page.setPageSize(expressInfoDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ExpressInfoDO> resultList = this.selectDynamicPageQuery(expressInfoDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ExpressInfoDO>();
	}
	
	@Override
	public Page<ExpressInfoDO> queryPageListDynamicAndStartPageSize(ExpressInfoDO expressInfoDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (expressInfoDO != null && startPage>0 && pageSize>0) {
			expressInfoDO.setStartPage(startPage);
			expressInfoDO.setPageSize(pageSize);
			return this.queryPageListDynamic(expressInfoDO);
		}
		return new Page<ExpressInfoDO>();
	}
	
	
}
