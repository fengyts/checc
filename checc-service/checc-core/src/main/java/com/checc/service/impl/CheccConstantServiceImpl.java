package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.CheccConstantDAO;
import com.checc.domain.CheccConstantDO;
import com.checc.service.CheccConstantService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="checcConstantService")
public class CheccConstantServiceImpl  implements CheccConstantService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CheccConstantDAO checcConstantDAO;

	@Override
	public Long insert(CheccConstantDO checcConstantDO) throws CommonServiceException {
		try {
			return checcConstantDAO.insert(checcConstantDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}


	@Override
	public int update(CheccConstantDO checcConstantDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) checcConstantDAO.update(checcConstantDO);
			}else{
				return (Integer) checcConstantDAO.updateDynamic(checcConstantDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) checcConstantDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public int deleteByPrimaryKey(String primaryKey) throws CommonServiceException {
		try {
			return (Integer) checcConstantDAO.deleteByPrimaryKey(primaryKey);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}


	@Override
	public CheccConstantDO selectById(Long id) throws CommonServiceException {
		try {
			return checcConstantDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public CheccConstantDO selectByPrimaryKey(String primaryKey) throws CommonServiceException {
		try {
			return checcConstantDAO.selectByPrimaryKey(primaryKey);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CheccConstantDO checcConstantDO) throws CommonServiceException {
		try {
			return checcConstantDAO.selectCountDynamic(checcConstantDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<CheccConstantDO> selectDynamic(CheccConstantDO checcConstantDO) throws CommonServiceException {
		try {
			return checcConstantDAO.selectDynamic(checcConstantDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<CheccConstantDO> selectDynamicPageQuery(CheccConstantDO checcConstantDO) throws CommonServiceException {
		try {
			return checcConstantDAO.selectDynamicPageQuery(checcConstantDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<CheccConstantDO> queryPageListDynamic(CheccConstantDO checcConstantDO) throws CommonServiceException{
		if (checcConstantDO != null) {
			Long totalCount = this.selectCountDynamic(checcConstantDO);

			Page<CheccConstantDO> page = new Page<CheccConstantDO>();
			page.setPageNo(checcConstantDO.getStartPage());
			page.setPageSize(checcConstantDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<CheccConstantDO> resultList = this.selectDynamicPageQuery(checcConstantDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<CheccConstantDO>();
	}
	
	@Override
	public Page<CheccConstantDO> queryPageListDynamicAndStartPageSize(CheccConstantDO checcConstantDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (checcConstantDO != null && startPage>0 && pageSize>0) {
			checcConstantDO.setStartPage(startPage);
			checcConstantDO.setPageSize(pageSize);
			return this.queryPageListDynamic(checcConstantDO);
		}
		return new Page<CheccConstantDO>();
	}
	
	
}
