package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.CheccNoticeDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.CheccNoticeDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="checcNoticeDAO")
public class MybatisCheccNoticeDAO extends MybatisBaseDAO implements CheccNoticeDAO {
	
	private static final String NAMESPACE = "com.checc.domain.CheccNoticeMapper.MybatisCheccNoticeDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(CheccNoticeDO checcNoticeDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), checcNoticeDO);
		if (i > 0) {
			return Long.valueOf(checcNoticeDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(CheccNoticeDO checcNoticeDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), checcNoticeDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(CheccNoticeDO checcNoticeDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), checcNoticeDO);
	}

	@Override
	public CheccNoticeDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(CheccNoticeDO checcNoticeDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), checcNoticeDO);
	}

	@Override
	public List<CheccNoticeDO> selectDynamic(CheccNoticeDO checcNoticeDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), checcNoticeDO);
	}

	@Override
	public List<CheccNoticeDO> selectDynamicPageQuery(CheccNoticeDO checcNoticeDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), checcNoticeDO);
	}

}
