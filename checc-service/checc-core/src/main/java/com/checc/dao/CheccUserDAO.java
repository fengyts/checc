package com.checc.dao;

import com.checc.domain.CheccUserDO;
import ng.bayue.service.common.GeneralDAO;

 /**
 * 车西西用户 DAO
 *
 * @author fengyts 2017-11-16 14:54:40
 */
public interface CheccUserDAO extends GeneralDAO<CheccUserDO> {
	
	CheccUserDO selectByMobile(String mobile);
	
	int updateByMobile(CheccUserDO checcUserDO);

}
