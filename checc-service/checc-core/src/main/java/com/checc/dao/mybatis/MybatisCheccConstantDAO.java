package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.CheccConstantDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.CheccConstantDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="checcConstantDAO")
public class MybatisCheccConstantDAO extends MybatisBaseDAO implements CheccConstantDAO {
	
	private static final String NAMESPACE = "com.checc.domain.CheccConstantMapper.MybatisCheccConstantDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(CheccConstantDO checcConstantDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), checcConstantDO);
		if (i > 0) {
			return Long.valueOf(checcConstantDO.getKey());
		}
		return 0L;
	}

	@Override
	public Integer update(CheccConstantDO checcConstantDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), checcConstantDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}
	
	@Override
	public int deleteByPrimaryKey(String primaryKey) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteByPrimaryKey"), primaryKey);
	}

	@Override
	public Integer updateDynamic(CheccConstantDO checcConstantDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), checcConstantDO);
	}

	@Override
	public CheccConstantDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}
	
	@Override
	public CheccConstantDO selectByPrimaryKey(String primaryKey) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectByPrimaryKey"), primaryKey);
	}

	@Override
	public Long selectCountDynamic(CheccConstantDO checcConstantDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), checcConstantDO);
	}

	@Override
	public List<CheccConstantDO> selectDynamic(CheccConstantDO checcConstantDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), checcConstantDO);
	}

	@Override
	public List<CheccConstantDO> selectDynamicPageQuery(CheccConstantDO checcConstantDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), checcConstantDO);
	}

}
