package com.checc.service.impl;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.dao.TopicItemDAO;
import com.checc.domain.TopicItemDO;
import com.checc.service.TopicItemService;
import com.checc.vo.PurchaseDetailVO;

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

	@Override
	public int reduceExchangeResidue(Long id) throws CommonServiceException {
		try {
			return topicItemDAO.reduceExchangeResidue(id);
		} catch (CommonDAOException e) {
			logger.error("兑换商品扣减剩余数量异常：{}", e);
		}
		
		return -1;
	}

	@Override
	public List<TopicItemDO> selectByIds(List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)){
			return new ArrayList<TopicItemDO>();
		}
		return topicItemDAO.selectByIds(ids);
	}

	@Override
	public List<TopicItemDO> selectByTopicIds(List<Long> topicIds) {
		if(CollectionUtils.isEmpty(topicIds)){
			return new ArrayList<TopicItemDO>();
		}
		return topicItemDAO.selectByTopicIds(topicIds);
	}

	@Override
	public PurchaseDetailVO selectWinnerDetailInifo(Long topicItemId) {
		try {
			return topicItemDAO.selectWinnerDetailInifo(topicItemId);
		} catch (CommonDAOException e) {
			logger.error("", e);
			return null;
		}
	}
	
	
	
}
