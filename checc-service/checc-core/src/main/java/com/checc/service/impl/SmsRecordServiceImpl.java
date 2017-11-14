package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.SmsRecordDAO;
import com.checc.domain.SmsRecordDO;
import com.checc.service.SmsRecordService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="smsRecordService")
public class SmsRecordServiceImpl  implements SmsRecordService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SmsRecordDAO smsRecordDAO;

	@Override
	public Long insert(SmsRecordDO smsRecordDO) throws CommonServiceException {
		try {
			return smsRecordDAO.insert(smsRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(SmsRecordDO smsRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) smsRecordDAO.updateById(smsRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(SmsRecordDO smsRecordDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) smsRecordDAO.update(smsRecordDO);
			}else{
				return (Integer) smsRecordDAO.updateDynamic(smsRecordDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) smsRecordDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SmsRecordDO smsRecordDO) throws CommonServiceException {
//		try {
//			return (Integer) smsRecordDAO.updateDynamic(smsRecordDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public SmsRecordDO selectById(Long id) throws CommonServiceException {
		try {
			return smsRecordDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SmsRecordDO smsRecordDO) throws CommonServiceException {
		try {
			return smsRecordDAO.selectCountDynamic(smsRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<SmsRecordDO> selectDynamic(SmsRecordDO smsRecordDO) throws CommonServiceException {
		try {
			return smsRecordDAO.selectDynamic(smsRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<SmsRecordDO> selectDynamicPageQuery(SmsRecordDO smsRecordDO) throws CommonServiceException {
		try {
			return smsRecordDAO.selectDynamicPageQuery(smsRecordDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<SmsRecordDO> queryPageListDynamic(SmsRecordDO smsRecordDO) throws CommonServiceException{
		if (smsRecordDO != null) {
			Long totalCount = this.selectCountDynamic(smsRecordDO);

			Page<SmsRecordDO> page = new Page<SmsRecordDO>();
			page.setPageNo(smsRecordDO.getStartPage());
			page.setPageSize(smsRecordDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<SmsRecordDO> resultList = this.selectDynamicPageQuery(smsRecordDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<SmsRecordDO>();
	}
	
	@Override
	public Page<SmsRecordDO> queryPageListDynamicAndStartPageSize(SmsRecordDO smsRecordDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (smsRecordDO != null && startPage>0 && pageSize>0) {
			smsRecordDO.setStartPage(startPage);
			smsRecordDO.setPageSize(pageSize);
			return this.queryPageListDynamic(smsRecordDO);
		}
		return new Page<SmsRecordDO>();
	}
	
	
}
