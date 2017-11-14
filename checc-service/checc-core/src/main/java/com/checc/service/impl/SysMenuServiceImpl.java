package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.SysMenuDAO;
import com.checc.domain.SysMenuDO;
import com.checc.service.SysMenuService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="sysMenuService")
public class SysMenuServiceImpl  implements SysMenuService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysMenuDAO sysMenuDAO;

	@Override
	public Long insert(SysMenuDO sysMenuDO) throws CommonServiceException {
		try {
			return sysMenuDAO.insert(sysMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysMenuDO sysMenuDO) throws CommonServiceException {
//		try {
//			return (Integer) sysMenuDAO.updateById(sysMenuDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(SysMenuDO sysMenuDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) sysMenuDAO.update(sysMenuDO);
			}else{
				return (Integer) sysMenuDAO.updateDynamic(sysMenuDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) sysMenuDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysMenuDO sysMenuDO) throws CommonServiceException {
//		try {
//			return (Integer) sysMenuDAO.updateDynamic(sysMenuDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public SysMenuDO selectById(Long id) throws CommonServiceException {
		try {
			return sysMenuDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysMenuDO sysMenuDO) throws CommonServiceException {
		try {
			return sysMenuDAO.selectCountDynamic(sysMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<SysMenuDO> selectDynamic(SysMenuDO sysMenuDO) throws CommonServiceException {
		try {
			return sysMenuDAO.selectDynamic(sysMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<SysMenuDO> selectDynamicPageQuery(SysMenuDO sysMenuDO) throws CommonServiceException {
		try {
			return sysMenuDAO.selectDynamicPageQuery(sysMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<SysMenuDO> queryPageListDynamic(SysMenuDO sysMenuDO) throws CommonServiceException{
		if (sysMenuDO != null) {
			Long totalCount = this.selectCountDynamic(sysMenuDO);

			Page<SysMenuDO> page = new Page<SysMenuDO>();
			page.setPageNo(sysMenuDO.getStartPage());
			page.setPageSize(sysMenuDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<SysMenuDO> resultList = this.selectDynamicPageQuery(sysMenuDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<SysMenuDO>();
	}
	
	@Override
	public Page<SysMenuDO> queryPageListDynamicAndStartPageSize(SysMenuDO sysMenuDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (sysMenuDO != null && startPage>0 && pageSize>0) {
			sysMenuDO.setStartPage(startPage);
			sysMenuDO.setPageSize(pageSize);
			return this.queryPageListDynamic(sysMenuDO);
		}
		return new Page<SysMenuDO>();
	}
	
	
}
