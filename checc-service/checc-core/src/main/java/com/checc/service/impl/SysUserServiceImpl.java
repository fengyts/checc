package com.checc.service.impl;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.util.SecurityUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.checc.dao.SysUserDAO;
import com.checc.dao.SysUserRoleDAO;
import com.checc.domain.SysUserDO;
import com.checc.domain.SysUserRoleDO;
import com.checc.service.SysUserService;
import com.checc.vo.SysUserVO;

@Service(value="sysUserService")
public class SysUserServiceImpl  implements SysUserService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysUserDAO sysUserDAO;
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;

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
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserAndRelationRoles(SysUserDO sysUserDO, List<Long> roleIds) throws CommonServiceException {
		if (CollectionUtils.isEmpty(roleIds)) {
			insert(sysUserDO);
		} else {
			Long id = insert(sysUserDO);
			List<SysUserRoleDO> list = new ArrayList<SysUserRoleDO>();
			for (Long rId : roleIds) {
				SysUserRoleDO sur = new SysUserRoleDO();
				sur.setUserId(id);
				sur.setRoleId(rId);
				list.add(sur);
			}
			try {
				sysUserRoleDAO.insertBatch(list);
			} catch (CommonDAOException e) {
				logger.error("", e);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUserAndRelationRoles(SysUserDO sysUser, List<Long> roleIds) throws CommonServiceException {
		if (CollectionUtils.isEmpty(roleIds)) {
			update(sysUser, false);
		} else {
			Long userId = sysUser.getId();
			try {
				sysUserRoleDAO.deleteById(userId);
			} catch (CommonDAOException e) {
				logger.error("", e);
			}
			List<SysUserRoleDO> list = new ArrayList<SysUserRoleDO>();
			for (Long roleId : roleIds) {
				SysUserRoleDO sur = new SysUserRoleDO();
				sur.setUserId(userId);
				sur.setRoleId(roleId);
				list.add(sur);
			}
			try {
				sysUserRoleDAO.insertBatch(list);
				update(sysUser, false);
			} catch (CommonDAOException e) {
				logger.error("", e);
			}
		}
	}

	@Override
	public SysUserDO findByLoginNameOrEmailOrMobile(String param) {
		if(StringUtils.isEmpty(param)){ return null;}
		SysUserDO sysUser = sysUserDAO.findByLoginNameOrEmailOrMobile(param);
		return sysUser;
	}
	
	@Override
	public SysUserVO findByAccountContainsMenusAndRoles(String param) {
		if(StringUtils.isEmpty(param)){ return null;}
		return sysUserDAO.nestedList(param);
	}

	@Override
	public void updatePassword(Long userId, String password,Long operationUserId) throws CommonServiceException {
		if(null == userId || userId.longValue() < 1 || StringUtils.isEmpty(password)){
			return;
		}
		SysUserDO sysUser = new SysUserDO();
		sysUser.setId(userId);
		String salt = getSalt();//生成新的盐
		password = SecurityUtil.hashToStr(password, salt, 2);//密码加密
		sysUser.setPassword(password);
		sysUser.setSalt(salt);
		sysUser.setModifyTime(new java.util.Date());
		if(null != operationUserId){
			sysUser.setModifyUserId(operationUserId);
		}
		update(sysUser,false);
	}
	
	private String getSalt(){
		return SecurityUtil.encryptMD5(SecurityUtil.Salt.provideSalt());
	}
	
	
}
