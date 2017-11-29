package com.checc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.CheccUserDAO;
import com.checc.domain.CheccUserDO;
import com.checc.service.CheccUserService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.util.SecurityUtil;
import ng.bayue.util.StringUtils;
import ng.bayue.common.Page;

@Service(value = "checcUserService")
public class CheccUserServiceImpl implements CheccUserService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CheccUserDAO checcUserDAO;

	@Override
	public Long insert(CheccUserDO checcUserDO) throws CommonServiceException {
		try {
			return checcUserDAO.insert(checcUserDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(CheccUserDO checcUserDO, boolean isAllField) throws CommonServiceException {
		try {
			if (isAllField) {
				return (Integer) checcUserDAO.update(checcUserDO);
			} else {
				return (Integer) checcUserDAO.updateDynamic(checcUserDO);
			}
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) checcUserDAO.deleteById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public CheccUserDO selectById(Long id) throws CommonServiceException {
		try {
			return checcUserDAO.selectById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CheccUserDO checcUserDO) throws CommonServiceException {
		try {
			return checcUserDAO.selectCountDynamic(checcUserDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public List<CheccUserDO> selectDynamic(CheccUserDO checcUserDO) throws CommonServiceException {
		try {
			return checcUserDAO.selectDynamic(checcUserDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	private List<CheccUserDO> selectDynamicPageQuery(CheccUserDO checcUserDO) throws CommonServiceException {
		try {
			return checcUserDAO.selectDynamicPageQuery(checcUserDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Page<CheccUserDO> queryPageListDynamic(CheccUserDO checcUserDO) throws CommonServiceException {
		if (checcUserDO != null) {
			Long totalCount = this.selectCountDynamic(checcUserDO);

			Page<CheccUserDO> page = new Page<CheccUserDO>();
			page.setPageNo(checcUserDO.getStartPage());
			page.setPageSize(checcUserDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<CheccUserDO> resultList = this.selectDynamicPageQuery(checcUserDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<CheccUserDO>();
	}

	@Override
	public Page<CheccUserDO> queryPageListDynamicAndStartPageSize(CheccUserDO checcUserDO, Integer startPage,
			Integer pageSize) throws CommonServiceException {
		if (checcUserDO != null && startPage > 0 && pageSize > 0) {
			checcUserDO.setStartPage(startPage);
			checcUserDO.setPageSize(pageSize);
			return this.queryPageListDynamic(checcUserDO);
		}
		return new Page<CheccUserDO>();
	}

	@Override
	public int register(CheccUserDO checcUserDO) {
		String password = checcUserDO.getPassword();
		// String mobile = checcUserDO.getMobile();
		if (StringUtils.isEmpty(password)) {
			return -1;
		}

		String salt = SecurityUtil.encode(SecurityUtil.Salt.provideSalt());
		String passwdHash = SecurityUtil.hashToStr(password, salt, 2);
		checcUserDO.setPassword(passwdHash);
		checcUserDO.setSalt(salt);

		Date date = new Date();
		checcUserDO.setCreateTime(date);
		checcUserDO.setModifyTime(date);
		checcUserDO.setLastLoginTime(date);

		return this.insert(checcUserDO).intValue();
	}

	@Override
	public int login(String mobile, String password) {
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			return -1;
		}

		return 0;
	}

}
