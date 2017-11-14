package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ExchargeRecordDAO;
import com.checc.domain.ExchargeRecordDO;
import com.checc.service.ExchargeRecordService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="exchargeRecordService")
public class ExchargeRecordServiceImpl  implements ExchargeRecordService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ExchargeRecordDAO exchargeRecordDAO;

	@Override
	public Long insert(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException {
		try {
			return exchargeRecordDAO.insert(exchargeRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) exchargeRecordDAO.updateById(exchargeRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(ExchargeRecordDO exchargeRecordDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) exchargeRecordDAO.update(exchargeRecordDO);
			}else{
				return (Integer) exchargeRecordDAO.updateDynamic(exchargeRecordDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) exchargeRecordDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) exchargeRecordDAO.updateDynamic(exchargeRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public ExchargeRecordDO selectById(Long id) throws CommonServiceException {
		try {
			return exchargeRecordDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException {
		try {
			return exchargeRecordDAO.selectCountDynamic(exchargeRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<ExchargeRecordDO> selectDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException {
		try {
			return exchargeRecordDAO.selectDynamic(exchargeRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<ExchargeRecordDO> selectDynamicPageQuery(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException {
		try {
			return exchargeRecordDAO.selectDynamicPageQuery(exchargeRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<ExchargeRecordDO> queryPageListDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonServiceException{
		if (exchargeRecordDO != null) {
			Long totalCount = this.selectCountDynamic(exchargeRecordDO);

			Page<ExchargeRecordDO> page = new Page<ExchargeRecordDO>();
			page.setPageNo(exchargeRecordDO.getStartPage());
			page.setPageSize(exchargeRecordDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ExchargeRecordDO> resultList = this.selectDynamicPageQuery(exchargeRecordDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ExchargeRecordDO>();
	}
	
	@Override
	public Page<ExchargeRecordDO> queryPageListDynamicAndStartPageSize(ExchargeRecordDO exchargeRecordDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (exchargeRecordDO != null && startPage>0 && pageSize>0) {
			exchargeRecordDO.setStartPage(startPage);
			exchargeRecordDO.setPageSize(pageSize);
			return this.queryPageListDynamic(exchargeRecordDO);
		}
		return new Page<ExchargeRecordDO>();
	}
	
	
}
