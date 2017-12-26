package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.DepositConfigDAO;
import com.checc.domain.DepositConfigDO;
import com.checc.service.DepositConfigService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="depositConfigService")
public class DepositConfigServiceImpl  implements DepositConfigService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DepositConfigDAO depositConfigDAO;

	@Override
	public Long insert(DepositConfigDO depositConfigDO) throws CommonServiceException {
		try {
			return depositConfigDAO.insert(depositConfigDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(DepositConfigDO depositConfigDO) throws CommonServiceException {
//		try {
//			return (Integer) depositConfigDAO.updateById(depositConfigDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(DepositConfigDO depositConfigDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) depositConfigDAO.update(depositConfigDO);
			}else{
				return (Integer) depositConfigDAO.updateDynamic(depositConfigDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) depositConfigDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DepositConfigDO depositConfigDO) throws CommonServiceException {
//		try {
//			return (Integer) depositConfigDAO.updateDynamic(depositConfigDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public DepositConfigDO selectById(Long id) throws CommonServiceException {
		try {
			return depositConfigDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DepositConfigDO depositConfigDO) throws CommonServiceException {
		try {
			return depositConfigDAO.selectCountDynamic(depositConfigDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<DepositConfigDO> selectDynamic(DepositConfigDO depositConfigDO) throws CommonServiceException {
		try {
			return depositConfigDAO.selectDynamic(depositConfigDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<DepositConfigDO> selectDynamicPageQuery(DepositConfigDO depositConfigDO) throws CommonServiceException {
		try {
			return depositConfigDAO.selectDynamicPageQuery(depositConfigDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<DepositConfigDO> queryPageListDynamic(DepositConfigDO depositConfigDO) throws CommonServiceException{
		if (depositConfigDO != null) {
			Long totalCount = this.selectCountDynamic(depositConfigDO);

			Page<DepositConfigDO> page = new Page<DepositConfigDO>();
			page.setPageNo(depositConfigDO.getStartPage());
			page.setPageSize(depositConfigDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<DepositConfigDO> resultList = this.selectDynamicPageQuery(depositConfigDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<DepositConfigDO>();
	}
	
	@Override
	public Page<DepositConfigDO> queryPageListDynamicAndStartPageSize(DepositConfigDO depositConfigDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (depositConfigDO != null && startPage>0 && pageSize>0) {
			depositConfigDO.setStartPage(startPage);
			depositConfigDO.setPageSize(pageSize);
			return this.queryPageListDynamic(depositConfigDO);
		}
		return new Page<DepositConfigDO>();
	}
	
	
}
