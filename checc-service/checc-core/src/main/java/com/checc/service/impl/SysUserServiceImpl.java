package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.SysUserDAO;
import com.checc.domain.SysUserDO;
import com.checc.service.SysUserService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="sysUserService")
public class SysUserServiceImpl  implements SysUserService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysUserDAO sysUserDAO;

	@Override
	public Long insert(SysUserDO sysUserDO) throws CommonServiceException {
		try {
			return sysUserDAO.insert(sysUserDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysUserDO sysUserDO) throws CommonServiceException {
//		try {
//			return (Integer) sysUserDAO.updateById(sysUserDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(SysUserDO sysUserDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) sysUserDAO.update(sysUserDO);
			}else{
				return (Integer) sysUserDAO.updateDynamic(sysUserDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) sysUserDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysUserDO sysUserDO) throws CommonServiceException {
//		try {
//			return (Integer) sysUserDAO.updateDynamic(sysUserDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public SysUserDO selectById(Long id) throws CommonServiceException {
		try {
			return sysUserDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysUserDO sysUserDO) throws CommonServiceException {
		try {
			return sysUserDAO.selectCountDynamic(sysUserDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<SysUserDO> selectDynamic(SysUserDO sysUserDO) throws CommonServiceException {
		try {
			return sysUserDAO.selectDynamic(sysUserDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<SysUserDO> selectDynamicPageQuery(SysUserDO sysUserDO) throws CommonServiceException {
		try {
			return sysUserDAO.selectDynamicPageQuery(sysUserDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<SysUserDO> queryPageListDynamic(SysUserDO sysUserDO) throws CommonServiceException{
		if (sysUserDO != null) {
			Long totalCount = this.selectCountDynamic(sysUserDO);

			Page<SysUserDO> page = new Page<SysUserDO>();
			page.setPageNo(sysUserDO.getStartPage());
			page.setPageSize(sysUserDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<SysUserDO> resultList = this.selectDynamicPageQuery(sysUserDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<SysUserDO>();
	}
	
	@Override
	public Page<SysUserDO> queryPageListDynamicAndStartPageSize(SysUserDO sysUserDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (sysUserDO != null && startPage>0 && pageSize>0) {
			sysUserDO.setStartPage(startPage);
			sysUserDO.setPageSize(pageSize);
			return this.queryPageListDynamic(sysUserDO);
		}
		return new Page<SysUserDO>();
	}
	
	
}
