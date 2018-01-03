package com.checc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.dao.TopicDAO;
import com.checc.domain.TopicDO;
import com.checc.enums.TopicStatusEnum;
import com.checc.service.TopicService;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;

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

//	@Override
//	@Deprecated
//	public List<TopicDO> selectAllDynamic(String type, String topicType) {
//		List<TopicDO> list = new ArrayList<TopicDO>();
//		if (StringUtils.isBlank(topicType)) {
//			return list;
//		}
//		Map<String, Object> params = new HashMap<String, Object>();
//		if (TopicTypeEnum.TOPIC_AUCTION.getCode().equals(topicType)) { // 竞拍专题
//			if (StringUtils.isBlank(type)) {
//				return list;
//			}
//			if ("01".equals(type)) { // 本期进行中
//				params.put("currentPeriods", true);
//				//params.put("status", TopicStatusEnum.InProgress.getCode());
//			} else { // 下期预告
//				params.put("nextPeriods", true);
//				//params.put("status", TopicStatusEnum.NotStarted.getCode());
//			}
//			params.put("topicType", topicType);
//			// params.put("startTime", now);
//			params.put("currentTime", new Date());
//
//			list = topicDAO.selectAllDynamic(params);
//		} else { // 获取兑换专题
//			Date now = new Date();
//			params.put("topicType", topicType);
//			params.put("exchangeStartTime", DateUtils.getDayBegin(now));
//			params.put("exchangeEndTime", DateUtils.getDayEnd(now));
//			
//			list = topicDAO.selectAllDynamic(params);
//			
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(now);
//			int hour = cal.get(Calendar.HOUR_OF_DAY);
//			if(10 > hour){ // 是否是当天10点钟
//				String code = TopicStatusEnum.NotStarted.getCode();
//				for(TopicDO t : list){
//					t.setStatus(code);
//				}
//			} else {
//				String code = TopicStatusEnum.InProgress.getCode();
//				for(TopicDO t : list){
//					t.setStatus(code);
//				}
//			}
//		}
//		return list;
//	}
	
	@Override
	public List<TopicDO> selectTopicByProgress(String topicType, TopicStatusEnum status) {
		List<TopicDO> list = new ArrayList<TopicDO>();
		if (StringUtils.isBlank(topicType)) {
			return list;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("topicType", topicType);
		if(TopicStatusEnum.NotStarted == status){
			params.put("notStart", status.getCode());
		}
		if(TopicStatusEnum.InProgress == status){
			params.put("inProgress", status.getCode());
		}
		
		list = topicDAO.selectTopicByProgress(params);
		
		return list;
	}

	@Override
	public List<TopicDO> selectTopicNotRefund() {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 20);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timerTime", cal.getTime());
		
		List<TopicDO> list = topicDAO.selectTopicNotRefund(params);
		
		return list;
	}
	
	
	
}
