package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.DepositCardDAO;
import com.checc.domain.DepositCardDO;
import com.checc.service.DepositCardService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="depositCardService")
public class DepositCardServiceImpl  implements DepositCardService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DepositCardDAO depositCardDAO;

	@Override
	public Long insert(DepositCardDO depositCardDO) throws CommonServiceException {
		try {
			return depositCardDAO.insert(depositCardDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(DepositCardDO depositCardDO) throws CommonServiceException {
//		try {
//			return (Integer) depositCardDAO.updateById(depositCardDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(DepositCardDO depositCardDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) depositCardDAO.update(depositCardDO);
			}else{
				return (Integer) depositCardDAO.updateDynamic(depositCardDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) depositCardDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DepositCardDO depositCardDO) throws CommonServiceException {
//		try {
//			return (Integer) depositCardDAO.updateDynamic(depositCardDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public DepositCardDO selectById(Long id) throws CommonServiceException {
		try {
			return depositCardDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DepositCardDO depositCardDO) throws CommonServiceException {
		try {
			return depositCardDAO.selectCountDynamic(depositCardDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<DepositCardDO> selectDynamic(DepositCardDO depositCardDO) throws CommonServiceException {
		try {
			return depositCardDAO.selectDynamic(depositCardDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<DepositCardDO> selectDynamicPageQuery(DepositCardDO depositCardDO) throws CommonServiceException {
		try {
			return depositCardDAO.selectDynamicPageQuery(depositCardDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<DepositCardDO> queryPageListDynamic(DepositCardDO depositCardDO) throws CommonServiceException{
		if (depositCardDO != null) {
			Long totalCount = this.selectCountDynamic(depositCardDO);

			Page<DepositCardDO> page = new Page<DepositCardDO>();
			page.setPageNo(depositCardDO.getStartPage());
			page.setPageSize(depositCardDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<DepositCardDO> resultList = this.selectDynamicPageQuery(depositCardDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<DepositCardDO>();
	}
	
	@Override
	public Page<DepositCardDO> queryPageListDynamicAndStartPageSize(DepositCardDO depositCardDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (depositCardDO != null && startPage>0 && pageSize>0) {
			depositCardDO.setStartPage(startPage);
			depositCardDO.setPageSize(pageSize);
			return this.queryPageListDynamic(depositCardDO);
		}
		return new Page<DepositCardDO>();
	}
	
	
}
