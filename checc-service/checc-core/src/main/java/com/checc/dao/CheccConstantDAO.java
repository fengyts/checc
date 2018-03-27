package com.checc.dao;

import com.checc.domain.CheccConstantDO;
import ng.bayue.service.common.GeneralDAO;
import ng.bayue.exception.CommonDAOException;

 /**
 * 车西西常量 DAO
 *
 * @author fengyts 2018-03-27 12:59:25
 */
public interface CheccConstantDAO extends GeneralDAO<CheccConstantDO> {
	
	/**
	 * <pre>
	 * 根据主键查询
	 * </pre>
	 *
	 * @param primaryKey
	 * @return CheccConstantDO
	 * @throws CommonDAOException
	 */
	CheccConstantDO selectByPrimaryKey(String primaryKey) throws CommonDAOException;
	
	/**
	 * <pre>
	 * 根据主键删除
	 * </pre>
	 *
	 * @param primaryKey
	 * @return int
	 * @throws CommonDAOException
	 */
	int deleteByPrimaryKey(String primaryKey) throws CommonDAOException;

}
