package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.CheccNoticeDAO;
import com.checc.domain.CheccNoticeDO;
import com.checc.service.CheccNoticeService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="checcNoticeService")
public class CheccNoticeServiceImpl  implements CheccNoticeService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CheccNoticeDAO checcNoticeDAO;

	@Override
	public Long insert(CheccNoticeDO checcNoticeDO) throws CommonServiceException {
		try {
			return checcNoticeDAO.insert(checcNoticeDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(CheccNoticeDO checcNoticeDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) checcNoticeDAO.update(checcNoticeDO);
			}else{
				return (Integer) checcNoticeDAO.updateDynamic(checcNoticeDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) checcNoticeDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public CheccNoticeDO selectById(Long id) throws CommonServiceException {
		try {
			return checcNoticeDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CheccNoticeDO checcNoticeDO) throws CommonServiceException {
		try {
			return checcNoticeDAO.selectCountDynamic(checcNoticeDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<CheccNoticeDO> selectDynamic(CheccNoticeDO checcNoticeDO) throws CommonServiceException {
		try {
			return checcNoticeDAO.selectDynamic(checcNoticeDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<CheccNoticeDO> selectDynamicPageQuery(CheccNoticeDO checcNoticeDO) throws CommonServiceException {
		try {
			return checcNoticeDAO.selectDynamicPageQuery(checcNoticeDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<CheccNoticeDO> queryPageListDynamic(CheccNoticeDO checcNoticeDO) throws CommonServiceException{
		if (checcNoticeDO != null) {
			Long totalCount = this.selectCountDynamic(checcNoticeDO);

			Page<CheccNoticeDO> page = new Page<CheccNoticeDO>();
			page.setPageNo(checcNoticeDO.getStartPage());
			page.setPageSize(checcNoticeDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<CheccNoticeDO> resultList = this.selectDynamicPageQuery(checcNoticeDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<CheccNoticeDO>();
	}
	
	@Override
	public Page<CheccNoticeDO> queryPageListDynamicAndStartPageSize(CheccNoticeDO checcNoticeDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (checcNoticeDO != null && startPage>0 && pageSize>0) {
			checcNoticeDO.setStartPage(startPage);
			checcNoticeDO.setPageSize(pageSize);
			return this.queryPageListDynamic(checcNoticeDO);
		}
		return new Page<CheccNoticeDO>();
	}
	
	
}
