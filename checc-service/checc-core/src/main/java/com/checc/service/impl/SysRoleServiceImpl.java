package com.checc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.SysRoleDAO;
import com.checc.domain.SysRoleDO;
import com.checc.service.SysRoleMenuService;
import com.checc.service.SysRoleService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="sysRoleService")
public class SysRoleServiceImpl  implements SysRoleService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysRoleDAO sysRoleDAO;
	@Autowired
	private SysRoleMenuService sysMenuRoleService;

	@Override
	public Long insert(SysRoleDO sysRoleDO) throws CommonServiceException {
		try {
			return sysRoleDAO.insert(sysRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysRoleDO sysRoleDO) throws CommonServiceException {
//		try {
//			return (Integer) sysRoleDAO.updateById(sysRoleDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(SysRoleDO sysRoleDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) sysRoleDAO.update(sysRoleDO);
			}else{
				return (Integer) sysRoleDAO.updateDynamic(sysRoleDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) sysRoleDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysRoleDO sysRoleDO) throws CommonServiceException {
//		try {
//			return (Integer) sysRoleDAO.updateDynamic(sysRoleDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public SysRoleDO selectById(Long id) throws CommonServiceException {
		try {
			return sysRoleDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysRoleDO sysRoleDO) throws CommonServiceException {
		try {
			return sysRoleDAO.selectCountDynamic(sysRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) throws CommonServiceException {
		try {
			return sysRoleDAO.selectDynamic(sysRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<SysRoleDO> selectDynamicPageQuery(SysRoleDO sysRoleDO) throws CommonServiceException {
		try {
			return sysRoleDAO.selectDynamicPageQuery(sysRoleDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<SysRoleDO> queryPageListDynamic(SysRoleDO sysRoleDO) throws CommonServiceException{
		if (sysRoleDO != null) {
			Long totalCount = this.selectCountDynamic(sysRoleDO);

			Page<SysRoleDO> page = new Page<SysRoleDO>();
			page.setPageNo(sysRoleDO.getStartPage());
			page.setPageSize(sysRoleDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<SysRoleDO> resultList = this.selectDynamicPageQuery(sysRoleDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<SysRoleDO>();
	}
	
	@Override
	public Page<SysRoleDO> queryPageListDynamicAndStartPageSize(SysRoleDO sysRoleDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (sysRoleDO != null && startPage>0 && pageSize>0) {
			sysRoleDO.setStartPage(startPage);
			sysRoleDO.setPageSize(pageSize);
			return this.queryPageListDynamic(sysRoleDO);
		}
		return new Page<SysRoleDO>();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertSysRoleAndRoleMenuRelation(SysRoleDO sysRoleDO, List<Long> menuIds) throws CommonServiceException {
		Long id = this.insert(sysRoleDO);
		if (CollectionUtils.isEmpty(menuIds)) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", id);
		map.put("menuIds", menuIds);

		sysMenuRoleService.insertBatch(map);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSysRoleAndRoleMenuRelation(SysRoleDO sysRoleDO, Long roleId, List<Long> menuIds) throws CommonServiceException {
		if (CollectionUtils.isEmpty(menuIds)) {
			update(sysRoleDO, false);
			return;
		}
		sysMenuRoleService.deleteByRoleId(roleId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("menuIds", menuIds);
		sysMenuRoleService.insertBatch(map);
		update(sysRoleDO, false);
	}

	@Override
	public List<SysRoleDO> selectByIds(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return null;
		}
		List<SysRoleDO> list = new ArrayList<SysRoleDO>();
		try {
			list = sysRoleDAO.selectByIds(ids);
			return list;
		} catch (CommonDAOException e) {
			logger.error("", e);
		}
		return list;
	}
	
	
}
