package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.TopicDAO;
import com.checc.domain.TopicDO;
import com.checc.service.TopicService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="topicService")
public class TopicServiceImpl  implements TopicService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public Long insert(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.insert(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(TopicDO topicDO) throws CommonServiceException {
//		try {
//			return (Integer) topicDAO.updateById(topicDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(TopicDO topicDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) topicDAO.update(topicDO);
			}else{
				return (Integer) topicDAO.updateDynamic(topicDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) topicDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(TopicDO topicDO) throws CommonServiceException {
//		try {
//			return (Integer) topicDAO.updateDynamic(topicDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public TopicDO selectById(Long id) throws CommonServiceException {
		try {
			return topicDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.selectCountDynamic(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<TopicDO> selectDynamic(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.selectDynamic(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<TopicDO> selectDynamicPageQuery(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.selectDynamicPageQuery(topicDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<TopicDO> queryPageListDynamic(TopicDO topicDO) throws CommonServiceException{
		if (topicDO != null) {
			Long totalCount = this.selectCountDynamic(topicDO);

			Page<TopicDO> page = new Page<TopicDO>();
			page.setPageNo(topicDO.getStartPage());
			page.setPageSize(topicDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<TopicDO> resultList = this.selectDynamicPageQuery(topicDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<TopicDO>();
	}
	
	@Override
	public Page<TopicDO> queryPageListDynamicAndStartPageSize(TopicDO topicDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (topicDO != null && startPage>0 && pageSize>0) {
			topicDO.setStartPage(startPage);
			topicDO.setPageSize(pageSize);
			return this.queryPageListDynamic(topicDO);
		}
		return new Page<TopicDO>();
	}
	
	
}
