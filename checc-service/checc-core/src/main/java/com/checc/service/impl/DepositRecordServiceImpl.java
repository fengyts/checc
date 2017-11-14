package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.DepositRecordDAO;
import com.checc.domain.DepositRecordDO;
import com.checc.service.DepositRecordService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="depositRecordService")
public class DepositRecordServiceImpl  implements DepositRecordService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DepositRecordDAO depositRecordDAO;

	@Override
	public Long insert(DepositRecordDO depositRecordDO) throws CommonServiceException {
		try {
			return depositRecordDAO.insert(depositRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(DepositRecordDO depositRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) depositRecordDAO.updateById(depositRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(DepositRecordDO depositRecordDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) depositRecordDAO.update(depositRecordDO);
			}else{
				return (Integer) depositRecordDAO.updateDynamic(depositRecordDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) depositRecordDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DepositRecordDO depositRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) depositRecordDAO.updateDynamic(depositRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public DepositRecordDO selectById(Long id) throws CommonServiceException {
		try {
			return depositRecordDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DepositRecordDO depositRecordDO) throws CommonServiceException {
		try {
			return depositRecordDAO.selectCountDynamic(depositRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<DepositRecordDO> selectDynamic(DepositRecordDO depositRecordDO) throws CommonServiceException {
		try {
			return depositRecordDAO.selectDynamic(depositRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<DepositRecordDO> selectDynamicPageQuery(DepositRecordDO depositRecordDO) throws CommonServiceException {
		try {
			return depositRecordDAO.selectDynamicPageQuery(depositRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<DepositRecordDO> queryPageListDynamic(DepositRecordDO depositRecordDO) throws CommonServiceException{
		if (depositRecordDO != null) {
			Long totalCount = this.selectCountDynamic(depositRecordDO);

			Page<DepositRecordDO> page = new Page<DepositRecordDO>();
			page.setPageNo(depositRecordDO.getStartPage());
			page.setPageSize(depositRecordDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<DepositRecordDO> resultList = this.selectDynamicPageQuery(depositRecordDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<DepositRecordDO>();
	}
	
	@Override
	public Page<DepositRecordDO> queryPageListDynamicAndStartPageSize(DepositRecordDO depositRecordDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (depositRecordDO != null && startPage>0 && pageSize>0) {
			depositRecordDO.setStartPage(startPage);
			depositRecordDO.setPageSize(pageSize);
			return this.queryPageListDynamic(depositRecordDO);
		}
		return new Page<DepositRecordDO>();
	}
	
	
}
