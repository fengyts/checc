package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.UserCurrencyDAO;
import com.checc.domain.UserCurrencyDO;
import ng.bayue.exception.CommonDAOException;

@Component(value = "userCurrencyDAO")
public class MybatisUserCurrencyDAO extends MybatisBaseDAO implements UserCurrencyDAO {

	private static final String NAMESPACE = "com.checc.domain.UserCurrencyMapper.MybatisUserCurrencyDAO_";

	private static String getStatement(String operation) {
		return NAMESPACE + operation;
	}

	public Long insert(UserCurrencyDO userCurrencyDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), userCurrencyDO);
		if (i > 0) {
			return Long.valueOf(userCurrencyDO.getUserId());
		}
		return 0L;
	}

	@Override
	public Integer update(UserCurrencyDO userCurrencyDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), userCurrencyDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(UserCurrencyDO userCurrencyDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), userCurrencyDO);
	}

	@Override
	public UserCurrencyDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(UserCurrencyDO userCurrencyDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), userCurrencyDO);
	}

	@Override
	public List<UserCurrencyDO> selectDynamic(UserCurrencyDO userCurrencyDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), userCurrencyDO);
	}

	@Override
	public List<UserCurrencyDO> selectDynamicPageQuery(UserCurrencyDO userCurrencyDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), userCurrencyDO);
	}

}
