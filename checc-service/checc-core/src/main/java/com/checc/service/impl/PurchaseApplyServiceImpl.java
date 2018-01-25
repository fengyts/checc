package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.PurchaseApplyDAO;
import com.checc.domain.PurchaseApplyDO;
import com.checc.service.PurchaseApplyService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="purchaseApplyService")
public class PurchaseApplyServiceImpl  implements PurchaseApplyService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private PurchaseApplyDAO purchaseApplyDAO;

	@Override
	public Long insert(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
		try {
			return purchaseApplyDAO.insert(purchaseApplyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
//		try {
//			return (Integer) purchaseApplyDAO.updateById(purchaseApplyDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(PurchaseApplyDO purchaseApplyDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) purchaseApplyDAO.update(purchaseApplyDO);
			}else{
				return (Integer) purchaseApplyDAO.updateDynamic(purchaseApplyDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) purchaseApplyDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
//		try {
//			return (Integer) purchaseApplyDAO.updateDynamic(purchaseApplyDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public PurchaseApplyDO selectById(Long id) throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectCountDynamic(purchaseApplyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<PurchaseApplyDO> selectDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectDynamic(purchaseApplyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<PurchaseApplyDO> selectDynamicPageQuery(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException {
		try {
			return purchaseApplyDAO.selectDynamicPageQuery(purchaseApplyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<PurchaseApplyDO> queryPageListDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonServiceException{
		if (purchaseApplyDO != null) {
			Long totalCount = this.selectCountDynamic(purchaseApplyDO);

			Page<PurchaseApplyDO> page = new Page<PurchaseApplyDO>();
			page.setPageNo(purchaseApplyDO.getStartPage());
			page.setPageSize(purchaseApplyDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<PurchaseApplyDO> resultList = this.selectDynamicPageQuery(purchaseApplyDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<PurchaseApplyDO>();
	}
	
	@Override
	public Page<PurchaseApplyDO> queryPageListDynamicAndStartPageSize(PurchaseApplyDO purchaseApplyDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (purchaseApplyDO != null && startPage>0 && pageSize>0) {
			purchaseApplyDO.setStartPage(startPage);
			purchaseApplyDO.setPageSize(pageSize);
			return this.queryPageListDynamic(purchaseApplyDO);
		}
		return new Page<PurchaseApplyDO>();
	}
	
	
}
