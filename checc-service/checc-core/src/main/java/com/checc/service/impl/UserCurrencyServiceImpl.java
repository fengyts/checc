package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.UserCurrencyDAO;
import com.checc.domain.UserCurrencyDO;
import com.checc.service.UserCurrencyService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="userCurrencyService")
public class UserCurrencyServiceImpl  implements UserCurrencyService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserCurrencyDAO userCurrencyDAO;

	@Override
	public Long insert(UserCurrencyDO userCurrencyDO) throws CommonServiceException {
		try {
			return userCurrencyDAO.insert(userCurrencyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(UserCurrencyDO userCurrencyDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) userCurrencyDAO.update(userCurrencyDO);
			}else{
				return (Integer) userCurrencyDAO.updateDynamic(userCurrencyDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) userCurrencyDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public UserCurrencyDO selectById(Long id) throws CommonServiceException {
		try {
			return userCurrencyDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(UserCurrencyDO userCurrencyDO) throws CommonServiceException {
		try {
			return userCurrencyDAO.selectCountDynamic(userCurrencyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<UserCurrencyDO> selectDynamic(UserCurrencyDO userCurrencyDO) throws CommonServiceException {
		try {
			return userCurrencyDAO.selectDynamic(userCurrencyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<UserCurrencyDO> selectDynamicPageQuery(UserCurrencyDO userCurrencyDO) throws CommonServiceException {
		try {
			return userCurrencyDAO.selectDynamicPageQuery(userCurrencyDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<UserCurrencyDO> queryPageListDynamic(UserCurrencyDO userCurrencyDO) throws CommonServiceException{
		if (userCurrencyDO != null) {
			Long totalCount = this.selectCountDynamic(userCurrencyDO);

			Page<UserCurrencyDO> page = new Page<UserCurrencyDO>();
			page.setPageNo(userCurrencyDO.getStartPage());
			page.setPageSize(userCurrencyDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<UserCurrencyDO> resultList = this.selectDynamicPageQuery(userCurrencyDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<UserCurrencyDO>();
	}
	
	@Override
	public Page<UserCurrencyDO> queryPageListDynamicAndStartPageSize(UserCurrencyDO userCurrencyDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (userCurrencyDO != null && startPage>0 && pageSize>0) {
			userCurrencyDO.setStartPage(startPage);
			userCurrencyDO.setPageSize(pageSize);
			return this.queryPageListDynamic(userCurrencyDO);
		}
		return new Page<UserCurrencyDO>();
	}

	@Override
	public int freezeCurrency(Long userId, Integer currency) throws CommonServiceException {
		try {
			return userCurrencyDAO.freezeCurrency(userId, currency);
		} catch (CommonDAOException e) {
			logger.error("冻结用户西币异常:{}", e);
		}
		return -1;
	}

	@Override
	public int increaseTotalCurrency(Long userId, Integer currency) throws CommonServiceException {
		try {
			return userCurrencyDAO.increaseTotalCurrency(userId, currency);
		} catch (CommonDAOException e) {
			logger.error("用户增加西币异常：{}", e);
		}
		return -1;
	}

	@Override
	public int reduceExchangeCurrency(Long userId, Integer currency) throws CommonServiceException {
		try {
			return userCurrencyDAO.reduceExchangeCurrency(userId, currency);
		} catch (CommonDAOException e) {
			logger.error("兑换商品扣减西币异常：{}", e);
		}
		return -1;
	}
	
}
