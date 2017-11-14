package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.TopicItemDAO;
import com.checc.domain.TopicItemDO;
import com.checc.service.TopicItemService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="topicItemService")
public class TopicItemServiceImpl  implements TopicItemService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicItemDAO topicItemDAO;

	@Override
	public Long insert(TopicItemDO topicItemDO) throws CommonServiceException {
		try {
			return topicItemDAO.insert(topicItemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(TopicItemDO topicItemDO) throws CommonServiceException {
//		try {
//			return (Integer) topicItemDAO.updateById(topicItemDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(TopicItemDO topicItemDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) topicItemDAO.update(topicItemDO);
			}else{
				return (Integer) topicItemDAO.updateDynamic(topicItemDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) topicItemDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(TopicItemDO topicItemDO) throws CommonServiceException {
//		try {
//			return (Integer) topicItemDAO.updateDynamic(topicItemDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public TopicItemDO selectById(Long id) throws CommonServiceException {
		try {
			return topicItemDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicItemDO topicItemDO) throws CommonServiceException {
		try {
			return topicItemDAO.selectCountDynamic(topicItemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<TopicItemDO> selectDynamic(TopicItemDO topicItemDO) throws CommonServiceException {
		try {
			return topicItemDAO.selectDynamic(topicItemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<TopicItemDO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws CommonServiceException {
		try {
			return topicItemDAO.selectDynamicPageQuery(topicItemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<TopicItemDO> queryPageListDynamic(TopicItemDO topicItemDO) throws CommonServiceException{
		if (topicItemDO != null) {
			Long totalCount = this.selectCountDynamic(topicItemDO);

			Page<TopicItemDO> page = new Page<TopicItemDO>();
			page.setPageNo(topicItemDO.getStartPage());
			page.setPageSize(topicItemDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<TopicItemDO> resultList = this.selectDynamicPageQuery(topicItemDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<TopicItemDO>();
	}
	
	@Override
	public Page<TopicItemDO> queryPageListDynamicAndStartPageSize(TopicItemDO topicItemDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (topicItemDO != null && startPage>0 && pageSize>0) {
			topicItemDO.setStartPage(startPage);
			topicItemDO.setPageSize(pageSize);
			return this.queryPageListDynamic(topicItemDO);
		}
		return new Page<TopicItemDO>();
	}
	
	
}
