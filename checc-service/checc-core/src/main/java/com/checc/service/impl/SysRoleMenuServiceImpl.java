package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.SysRoleMenuDAO;
import com.checc.domain.SysRoleMenuDO;
import com.checc.service.SysRoleMenuService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="sysRoleMenuService")
public class SysRoleMenuServiceImpl  implements SysRoleMenuService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysRoleMenuDAO sysRoleMenuDAO;

	@Override
	public Long insert(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException {
		try {
			return sysRoleMenuDAO.insert(sysRoleMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException {
//		try {
//			return (Integer) sysRoleMenuDAO.updateById(sysRoleMenuDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(SysRoleMenuDO sysRoleMenuDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) sysRoleMenuDAO.update(sysRoleMenuDO);
			}else{
				return (Integer) sysRoleMenuDAO.updateDynamic(sysRoleMenuDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) sysRoleMenuDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException {
//		try {
//			return (Integer) sysRoleMenuDAO.updateDynamic(sysRoleMenuDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public SysRoleMenuDO selectById(Long id) throws CommonServiceException {
		try {
			return sysRoleMenuDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException {
		try {
			return sysRoleMenuDAO.selectCountDynamic(sysRoleMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<SysRoleMenuDO> selectDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException {
		try {
			return sysRoleMenuDAO.selectDynamic(sysRoleMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<SysRoleMenuDO> selectDynamicPageQuery(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException {
		try {
			return sysRoleMenuDAO.selectDynamicPageQuery(sysRoleMenuDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<SysRoleMenuDO> queryPageListDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonServiceException{
		if (sysRoleMenuDO != null) {
			Long totalCount = this.selectCountDynamic(sysRoleMenuDO);

			Page<SysRoleMenuDO> page = new Page<SysRoleMenuDO>();
			page.setPageNo(sysRoleMenuDO.getStartPage());
			page.setPageSize(sysRoleMenuDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<SysRoleMenuDO> resultList = this.selectDynamicPageQuery(sysRoleMenuDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<SysRoleMenuDO>();
	}
	
	@Override
	public Page<SysRoleMenuDO> queryPageListDynamicAndStartPageSize(SysRoleMenuDO sysRoleMenuDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (sysRoleMenuDO != null && startPage>0 && pageSize>0) {
			sysRoleMenuDO.setStartPage(startPage);
			sysRoleMenuDO.setPageSize(pageSize);
			return this.queryPageListDynamic(sysRoleMenuDO);
		}
		return new Page<SysRoleMenuDO>();
	}
	
	
}
