package com.checc.service;

import ng.bayue.service.common.GeneralService;
import ng.bayue.exception.CommonServiceException;
import com.checc.domain.CheccConstantDO;

 /**
 * 车西西常量 Service
 * @author fengyts 2018-03-27 12:59:25
 */
public interface CheccConstantService extends GeneralService<CheccConstantDO, CheccConstantDO> {
	
	/**
	 * <pre>
	 * 根据主键查询
	 * </pre>
	 *
	 * @param primaryKey
	 * @return CheccConstantDO
	 * @throws CommonServiceException
	 */
	CheccConstantDO selectByPrimaryKey(String primaryKey);
	
	/**
	 * <pre>
	 * 根据主键删除
	 * </pre>
	 *
	 * @param primaryKey
	 * @return int
	 * @throws CommonDAOException
	 */
	int deleteByPrimaryKey(String primaryKey);
	
}
