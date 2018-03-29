package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.ExpressInfoDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.ExpressInfoDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="expressInfoDAO")
public class MybatisExpressInfoDAO extends MybatisBaseDAO implements ExpressInfoDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ExpressInfoMapper.MybatisExpressInfoDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ExpressInfoDO expressInfoDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), expressInfoDO);
		if (i > 0) {
			return Long.valueOf(expressInfoDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ExpressInfoDO expressInfoDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), expressInfoDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}
	

	@Override
	public Integer updateDynamic(ExpressInfoDO expressInfoDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), expressInfoDO);
	}

	@Override
	public ExpressInfoDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}
	

	@Override
	public Long selectCountDynamic(ExpressInfoDO expressInfoDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), expressInfoDO);
	}

	@Override
	public List<ExpressInfoDO> selectDynamic(ExpressInfoDO expressInfoDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), expressInfoDO);
	}

	@Override
	public List<ExpressInfoDO> selectDynamicPageQuery(ExpressInfoDO expressInfoDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), expressInfoDO);
	}

}
