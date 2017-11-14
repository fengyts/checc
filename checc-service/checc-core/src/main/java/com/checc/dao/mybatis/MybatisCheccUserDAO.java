package com.checc.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.CheccUserDAO;
import com.checc.domain.CheccUserDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="checcUserDAO")
public class MybatisCheccUserDAO extends MybatisBaseDAO implements CheccUserDAO {
	
	private static final String NAMESPACE = "com.checc.domain.CheccUserMapper.MybatisCheccUserDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(CheccUserDO checcUserDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), checcUserDO);
		if (i > 0) {
			return Long.valueOf(checcUserDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(CheccUserDO checcUserDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), checcUserDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(CheccUserDO checcUserDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), checcUserDO);
	}

	@Override
	public CheccUserDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(CheccUserDO checcUserDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), checcUserDO);
	}

	@Override
	public List<CheccUserDO> selectDynamic(CheccUserDO checcUserDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), checcUserDO);
	}

	@Override
	public List<CheccUserDO> selectDynamicPageQuery(CheccUserDO checcUserDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), checcUserDO);
	}

}
