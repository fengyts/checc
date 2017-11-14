package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.AuctionRecordDAO;
import com.checc.domain.AuctionRecordDO;
import com.checc.service.AuctionRecordService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="auctionRecordService")
public class AuctionRecordServiceImpl  implements AuctionRecordService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private AuctionRecordDAO auctionRecordDAO;

	@Override
	public Long insert(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.insert(auctionRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) auctionRecordDAO.updateById(auctionRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(AuctionRecordDO auctionRecordDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) auctionRecordDAO.update(auctionRecordDO);
			}else{
				return (Integer) auctionRecordDAO.updateDynamic(auctionRecordDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) auctionRecordDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) auctionRecordDAO.updateDynamic(auctionRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public AuctionRecordDO selectById(Long id) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectCountDynamic(auctionRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<AuctionRecordDO> selectDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectDynamic(auctionRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<AuctionRecordDO> selectDynamicPageQuery(AuctionRecordDO auctionRecordDO) throws CommonServiceException {
		try {
			return auctionRecordDAO.selectDynamicPageQuery(auctionRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<AuctionRecordDO> queryPageListDynamic(AuctionRecordDO auctionRecordDO) throws CommonServiceException{
		if (auctionRecordDO != null) {
			Long totalCount = this.selectCountDynamic(auctionRecordDO);

			Page<AuctionRecordDO> page = new Page<AuctionRecordDO>();
			page.setPageNo(auctionRecordDO.getStartPage());
			page.setPageSize(auctionRecordDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<AuctionRecordDO> resultList = this.selectDynamicPageQuery(auctionRecordDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<AuctionRecordDO>();
	}
	
	@Override
	public Page<AuctionRecordDO> queryPageListDynamicAndStartPageSize(AuctionRecordDO auctionRecordDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (auctionRecordDO != null && startPage>0 && pageSize>0) {
			auctionRecordDO.setStartPage(startPage);
			auctionRecordDO.setPageSize(pageSize);
			return this.queryPageListDynamic(auctionRecordDO);
		}
		return new Page<AuctionRecordDO>();
	}
	
	
}
