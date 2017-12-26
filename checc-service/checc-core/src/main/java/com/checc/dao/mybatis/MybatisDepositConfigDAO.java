package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.DepositConfigDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.DepositConfigDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="depositConfigDAO")
public class MybatisDepositConfigDAO extends MybatisBaseDAO implements DepositConfigDAO {
	
	private static final String NAMESPACE = "com.checc.domain.DepositConfigMapper.MybatisDepositConfigDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(DepositConfigDO depositConfigDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), depositConfigDO);
		if (i > 0) {
			return Long.valueOf(depositConfigDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DepositConfigDO depositConfigDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), depositConfigDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(DepositConfigDO depositConfigDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), depositConfigDO);
	}

	@Override
	public DepositConfigDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(DepositConfigDO depositConfigDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), depositConfigDO);
	}

	@Override
	public List<DepositConfigDO> selectDynamic(DepositConfigDO depositConfigDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), depositConfigDO);
	}

	@Override
	public List<DepositConfigDO> selectDynamicPageQuery(DepositConfigDO depositConfigDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), depositConfigDO);
	}

}
