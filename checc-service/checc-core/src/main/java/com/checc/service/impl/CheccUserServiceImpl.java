package com.checc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.CheccUserDAO;
import com.checc.domain.CheccUserDO;
import com.checc.enums.CheccUserTypeEnum;
import com.checc.service.CheccUserService;
import com.checc.vo.CheccUserVO;

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
	public int register(CheccUserDO checcUserDO) throws CommonServiceException {
		String password = checcUserDO.getPassword();
		// String mobile = checcUserDO.getMobile();
		if (StringUtils.isEmpty(password)) {
			return -1;
		}

		String salt = SecurityUtil.encode(SecurityUtil.Salt.provideSalt());
		String passwdHash = SecurityUtil.hashToStr(password, salt, 2);
		checcUserDO.setPassword(passwdHash);
		checcUserDO.setSalt(salt);
		checcUserDO.setType(CheccUserTypeEnum.CHECC_USER.code);

		Date date = new Date();
		checcUserDO.setCreateTime(date);
		checcUserDO.setModifyTime(date);
		checcUserDO.setLastLoginTime(date);

		return this.insert(checcUserDO).intValue();
	}

	@Override
	public CheccUserDO login(String mobile, String password) {
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			return new CheccUserDO();
		}

		return checcUserDAO.selectByMobile(mobile);
	}

	@Override
	public int recoveredPwd(String mobile, String password) throws CommonServiceException {
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			return -1;
		}

		CheccUserDO checcUserDO = new CheccUserDO();
		String salt = SecurityUtil.encode(SecurityUtil.Salt.provideSalt());
		String passwdHash = SecurityUtil.hashToStr(password, salt, 2);

		checcUserDO.setMobile(mobile);
		checcUserDO.setPassword(passwdHash);
		checcUserDO.setSalt(salt);
		checcUserDO.setModifyTime(new Date());

		return checcUserDAO.updateByMobile(checcUserDO);

	}

	@Override
	public CheccUserDO selectByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		return checcUserDAO.selectByMobile(mobile);
	}

	@Override
	public List<CheccUserVO> selectAllVMAccount(Long userId, String mobile) {
		return checcUserDAO.selectAllVMAccount(userId, mobile);
	}

	@Override
	@Transactional
	public int recoveredVmAccountPwd(String password) throws CommonServiceException {
		if(StringUtils.isBlank(password)){
			return -1;
		}
		List<CheccUserVO> list = this.selectAllVMAccount(null, null);
		int res = 0;
		for(CheccUserVO uvo : list){
			res = this.recoveredPwd(uvo.getMobile(), password);
			if(res <= 0){
				throw new CommonServiceException("backend-修改虚拟账号密码失败");
			}
		}
		
		return 0;
	}
	
	

}
