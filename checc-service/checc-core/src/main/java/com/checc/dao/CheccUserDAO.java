package com.checc.dao;

import java.util.List;

import com.checc.domain.CheccUserDO;
import com.checc.vo.CheccUserVO;

import ng.bayue.service.common.GeneralDAO;

 /**
 * 车西西用户 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface CheccUserDAO extends GeneralDAO<CheccUserDO> {
	
	CheccUserDO selectByMobile(String mobile);
	
	int updateByMobile(CheccUserDO checcUserDO);
	
	/**
	 * <pre>
	 * 根据用户id或者用户手机号    获取所有虚拟账号列表, 查询单条记录时, 参数userId 和 mobile二者任传一个即可
	 * </pre>
	 *
	 * @param userId
	 * @param mobile
	 * @return
	 */
	List<CheccUserVO> selectAllVMAccount(Long userId, String mobile);

}
