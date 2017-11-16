package com.checc.service;

import java.util.List;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;

import com.checc.domain.SysUserDO;
import com.checc.vo.SysUserVO;

 /**
 * 后台系统用户 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface SysUserService extends GeneralService<SysUserDO, SysUserDO> {

	/**
	 * <pre>
	 * 插入系统用户同时关联用户和角色关系
	 * </pre>
	 *
	 * @param sysUserDO
	 * @param roleIds
	 * @throws CommonServiceException
	 */
	void saveUserAndRelationRoles(SysUserDO sysUserDO,List<Long> roleIds) throws CommonServiceException;
	
	void updateUserAndRelationRoles(SysUserDO sysUser,List<Long> roleIds) throws CommonServiceException;
	
	/**
	 * <pre>
	 * 根据账号查找用户，可以是账号名称|手机号|邮箱
	 * </pre>
	 *
	 * @param param
	 * @return SysUserDO 返回结果中只包含用户信息
	 */
	SysUserDO findByLoginNameOrEmailOrMobile(String param);
	
	/**
	 * <pre>
	 * 根据账号查找用户，可以是账号名称|手机号|邮箱
	 * </pre>
	 *
	 * @param param
	 * @return SysUserVO 返回结果中包含该用户的角色信息和菜单信息
	 */
	SysUserVO findByAccountContainsMenusAndRoles(String param);
	
	/**
	 * <pre>
	 * 修改密码，同时生成新的散列盐值
	 * </pre>
	 *
	 * @param userId 要修改密码的用户id
	 * @param password
	 * @param operationUserId 操作人id，如果为null则为修改自己的密码
	 * @throws CommonServiceException
	 */
	void updatePassword(Long userId,String password,Long operationUserId) throws CommonServiceException;

}
