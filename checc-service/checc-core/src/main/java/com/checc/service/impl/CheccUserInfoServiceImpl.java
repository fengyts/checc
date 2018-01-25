package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.CheccUserInfoDAO;
import com.checc.domain.CheccUserInfoDO;
import com.checc.service.CheccUserInfoService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="checcUserInfoService")
public class CheccUserInfoServiceImpl  implements CheccUserInfoService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CheccUserInfoDAO checcUserInfoDAO;

	@Override
	public Long insert(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException {
		try {
			return checcUserInfoDAO.insert(checcUserInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException {
//		try {
//			return (Integer) checcUserInfoDAO.updateById(checcUserInfoDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(CheccUserInfoDO checcUserInfoDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) checcUserInfoDAO.update(checcUserInfoDO);
			}else{
				return (Integer) checcUserInfoDAO.updateDynamic(checcUserInfoDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) checcUserInfoDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException {
//		try {
//			return (Integer) checcUserInfoDAO.updateDynamic(checcUserInfoDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public CheccUserInfoDO selectById(Long id) throws CommonServiceException {
		try {
			return checcUserInfoDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException {
		try {
			return checcUserInfoDAO.selectCountDynamic(checcUserInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<CheccUserInfoDO> selectDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException {
		try {
			return checcUserInfoDAO.selectDynamic(checcUserInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<CheccUserInfoDO> selectDynamicPageQuery(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException {
		try {
			return checcUserInfoDAO.selectDynamicPageQuery(checcUserInfoDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<CheccUserInfoDO> queryPageListDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonServiceException{
		if (checcUserInfoDO != null) {
			Long totalCount = this.selectCountDynamic(checcUserInfoDO);

			Page<CheccUserInfoDO> page = new Page<CheccUserInfoDO>();
			page.setPageNo(checcUserInfoDO.getStartPage());
			page.setPageSize(checcUserInfoDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<CheccUserInfoDO> resultList = this.selectDynamicPageQuery(checcUserInfoDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<CheccUserInfoDO>();
	}
	
	@Override
	public Page<CheccUserInfoDO> queryPageListDynamicAndStartPageSize(CheccUserInfoDO checcUserInfoDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (checcUserInfoDO != null && startPage>0 && pageSize>0) {
			checcUserInfoDO.setStartPage(startPage);
			checcUserInfoDO.setPageSize(pageSize);
			return this.queryPageListDynamic(checcUserInfoDO);
		}
		return new Page<CheccUserInfoDO>();
	}
	
	
}
