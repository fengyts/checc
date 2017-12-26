package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.DepositOrderDAO;
import com.checc.domain.DepositOrderDO;
import com.checc.service.DepositOrderService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="depositOrderService")
public class DepositOrderServiceImpl  implements DepositOrderService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DepositOrderDAO depositOrderDAO;

	@Override
	public Long insert(DepositOrderDO depositOrderDO) throws CommonServiceException {
		try {
			return depositOrderDAO.insert(depositOrderDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(DepositOrderDO depositOrderDO) throws CommonServiceException {
//		try {
//			return (Integer) depositOrderDAO.updateById(depositOrderDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(DepositOrderDO depositOrderDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) depositOrderDAO.update(depositOrderDO);
			}else{
				return (Integer) depositOrderDAO.updateDynamic(depositOrderDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) depositOrderDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DepositOrderDO depositOrderDO) throws CommonServiceException {
//		try {
//			return (Integer) depositOrderDAO.updateDynamic(depositOrderDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public DepositOrderDO selectById(Long id) throws CommonServiceException {
		try {
			return depositOrderDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DepositOrderDO depositOrderDO) throws CommonServiceException {
		try {
			return depositOrderDAO.selectCountDynamic(depositOrderDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<DepositOrderDO> selectDynamic(DepositOrderDO depositOrderDO) throws CommonServiceException {
		try {
			return depositOrderDAO.selectDynamic(depositOrderDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<DepositOrderDO> selectDynamicPageQuery(DepositOrderDO depositOrderDO) throws CommonServiceException {
		try {
			return depositOrderDAO.selectDynamicPageQuery(depositOrderDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<DepositOrderDO> queryPageListDynamic(DepositOrderDO depositOrderDO) throws CommonServiceException{
		if (depositOrderDO != null) {
			Long totalCount = this.selectCountDynamic(depositOrderDO);

			Page<DepositOrderDO> page = new Page<DepositOrderDO>();
			page.setPageNo(depositOrderDO.getStartPage());
			page.setPageSize(depositOrderDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<DepositOrderDO> resultList = this.selectDynamicPageQuery(depositOrderDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<DepositOrderDO>();
	}
	
	@Override
	public Page<DepositOrderDO> queryPageListDynamicAndStartPageSize(DepositOrderDO depositOrderDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (depositOrderDO != null && startPage>0 && pageSize>0) {
			depositOrderDO.setStartPage(startPage);
			depositOrderDO.setPageSize(pageSize);
			return this.queryPageListDynamic(depositOrderDO);
		}
		return new Page<DepositOrderDO>();
	}
	
	
}
