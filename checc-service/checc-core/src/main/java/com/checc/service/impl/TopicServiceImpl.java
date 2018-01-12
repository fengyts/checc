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
import com.checc.vo.front.TopicItemVO;

import ng.bayue.common.BaseDO;
import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;

@Service(value = "topicService")
public class TopicServiceImpl implements TopicService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public Long insert(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.insert(topicDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	// @Override
	// public int updateById(TopicDO topicDO) throws CommonServiceException {
	// try {
	// return (Integer) topicDAO.updateById(topicDO);
	// }catch(CommonDAOException e){
	// logger.error(e);
	// throw new CommonServiceException(e);
	// }
	// }

	@Override
	public int update(TopicDO topicDO, boolean isAllField) throws CommonServiceException {
		try {
			if (isAllField) {
				return (Integer) topicDAO.update(topicDO);
			} else {
				return (Integer) topicDAO.updateDynamic(topicDO);
			}
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) topicDAO.deleteById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	// @Override
	// public int updateDynamic(TopicDO topicDO) throws CommonServiceException {
	// try {
	// return (Integer) topicDAO.updateDynamic(topicDO);
	// }catch(CommonDAOException e){
	// logger.error(e);
	// throw new CommonServiceException(e);
	// }
	// }

	@Override
	public TopicDO selectById(Long id) throws CommonServiceException {
		try {
			return topicDAO.selectById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.selectCountDynamic(topicDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public List<TopicDO> selectDynamic(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.selectDynamic(topicDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	private List<TopicDO> selectDynamicPageQuery(TopicDO topicDO) throws CommonServiceException {
		try {
			return topicDAO.selectDynamicPageQuery(topicDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Page<TopicDO> queryPageListDynamic(TopicDO topicDO) throws CommonServiceException {
		if (topicDO != null) {
			Long totalCount = this.selectCountDynamic(topicDO);

			Page<TopicDO> page = new Page<TopicDO>();
			page.setPageNo(topicDO.getStartPage());
			page.setPageSize(topicDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<TopicDO> resultList = this.selectDynamicPageQuery(topicDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<TopicDO>();
	}

	@Override
	public Page<TopicDO> queryPageListDynamicAndStartPageSize(TopicDO topicDO, Integer startPage, Integer pageSize)
			throws CommonServiceException {
		if (topicDO != null && startPage > 0 && pageSize > 0) {
			topicDO.setStartPage(startPage);
			topicDO.setPageSize(pageSize);
			return this.queryPageListDynamic(topicDO);
		}
		return new Page<TopicDO>();
	}

	@Override
	public List<TopicDO> selectTopicByProgress(String topicType, TopicStatusEnum status) {
		List<TopicDO> list = new ArrayList<TopicDO>();
		if (StringUtils.isBlank(topicType)) {
			return list;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("topicType", topicType);
		if (TopicStatusEnum.NotStarted == status) {
			params.put("notStart", status.getCode());
		}
		if (TopicStatusEnum.InProgress == status) {
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

	@Override
	public TopicDO selectPreviousOne(String topicType) {
		if (StringUtils.isBlank(topicType)) {
			return null;
		}
		return topicDAO.selectPreviousOne(topicType);
	}

	@Override
	public Page<TopicItemVO> queryPreviousAuctions(Integer pageNo, Integer pageSize) {
		Page<TopicItemVO> page = new Page<TopicItemVO>();
		List<TopicItemVO> list = null;

		BaseDO baseDO = new BaseDO();
		baseDO.setStartPage(pageNo);
		baseDO.setPageSize(pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", baseDO.getStart());
		params.put("pageSize", baseDO.getPageSize());
		Long totalCount = topicDAO.countPreviousAuctions(params);

		if (null != totalCount && totalCount.longValue() > 0) {
			list = topicDAO.queryPreviousAuctions(params);
		}
		page.setList(null != list ? list : new ArrayList<TopicItemVO>());
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount.intValue());

		return page;
	}

	@Override
	public Long totalPreviousNum() {
		return topicDAO.totalPreviousNum();
	}
	
	

}
