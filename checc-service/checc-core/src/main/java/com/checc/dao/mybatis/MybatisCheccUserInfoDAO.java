package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.CheccUserInfoDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.CheccUserInfoDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="checcUserInfoDAO")
public class MybatisCheccUserInfoDAO extends MybatisBaseDAO implements CheccUserInfoDAO {
	
	private static final String NAMESPACE = "com.checc.domain.CheccUserInfoMapper.MybatisCheccUserInfoDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(CheccUserInfoDO checcUserInfoDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), checcUserInfoDO);
		if (i > 0) {
			return Long.valueOf(checcUserInfoDO.getUserId());
		}
		return 0L;
	}

	@Override
	public Integer update(CheccUserInfoDO checcUserInfoDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), checcUserInfoDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), checcUserInfoDO);
	}

	@Override
	public CheccUserInfoDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), checcUserInfoDO);
	}

	@Override
	public List<CheccUserInfoDO> selectDynamic(CheccUserInfoDO checcUserInfoDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), checcUserInfoDO);
	}

	@Override
	public List<CheccUserInfoDO> selectDynamicPageQuery(CheccUserInfoDO checcUserInfoDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), checcUserInfoDO);
	}

}
