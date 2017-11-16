package com.checc.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.SysUserRoleDAO;
import com.checc.domain.SysUserRoleDO;
import com.checc.service.SysUserRoleService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="sysUserRoleService")
public class SysUserRoleServiceImpl  implements SysUserRoleService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;

	@Override
	public Long insert(SysUserRoleDO sysUserRoleDO) throws CommonServiceException {
		try {
			return sysUserRoleDAO.insert(sysUserRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysUserRoleDO sysUserRoleDO) throws CommonServiceException {
//		try {
//			return (Integer) sysUserRoleDAO.updateById(sysUserRoleDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(SysUserRoleDO sysUserRoleDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) sysUserRoleDAO.update(sysUserRoleDO);
			}else{
				return (Integer) sysUserRoleDAO.updateDynamic(sysUserRoleDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) sysUserRoleDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysUserRoleDO sysUserRoleDO) throws CommonServiceException {
//		try {
//			return (Integer) sysUserRoleDAO.updateDynamic(sysUserRoleDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public SysUserRoleDO selectById(Long id) throws CommonServiceException {
		try {
			return sysUserRoleDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysUserRoleDO sysUserRoleDO) throws CommonServiceException {
		try {
			return sysUserRoleDAO.selectCountDynamic(sysUserRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO) throws CommonServiceException {
		try {
			return sysUserRoleDAO.selectDynamic(sysUserRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<SysUserRoleDO> selectDynamicPageQuery(SysUserRoleDO sysUserRoleDO) throws CommonServiceException {
		try {
			return sysUserRoleDAO.selectDynamicPageQuery(sysUserRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<SysUserRoleDO> queryPageListDynamic(SysUserRoleDO sysUserRoleDO) throws CommonServiceException{
		if (sysUserRoleDO != null) {
			Long totalCount = this.selectCountDynamic(sysUserRoleDO);

			Page<SysUserRoleDO> page = new Page<SysUserRoleDO>();
			page.setPageNo(sysUserRoleDO.getStartPage());
			page.setPageSize(sysUserRoleDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<SysUserRoleDO> resultList = this.selectDynamicPageQuery(sysUserRoleDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<SysUserRoleDO>();
	}
	
	@Override
	public Page<SysUserRoleDO> queryPageListDynamicAndStartPageSize(SysUserRoleDO sysUserRoleDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (sysUserRoleDO != null && startPage>0 && pageSize>0) {
			sysUserRoleDO.setStartPage(startPage);
			sysUserRoleDO.setPageSize(pageSize);
			return this.queryPageListDynamic(sysUserRoleDO);
		}
		return new Page<SysUserRoleDO>();
	}
	
	@Override
	public void insertBatch(List<SysUserRoleDO> list) throws CommonServiceException {
		if(CollectionUtils.isEmpty(list)){return;}
		try {
			sysUserRoleDAO.insertBatch(list);
		} catch (CommonDAOException e) {
			logger.error("", e);
		}
	}

	@Override
	public List<SysUserRoleDO> selectByUserIds(List<Long> userIds) {
		if(CollectionUtils.isEmpty(userIds)){
			return null;
		}
		List<SysUserRoleDO> list = sysUserRoleDAO.selectByUserIds(userIds);
		return list;
	}
	
	
}
