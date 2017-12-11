package com.checc.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.ExchangeRecordDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.ExchangeRecordDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="exchangeRecordDAO")
public class MybatisExchangeRecordDAO extends MybatisBaseDAO implements ExchangeRecordDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ExchangeRecordMapper.MybatisExchangeRecordDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ExchangeRecordDO exchangeRecordDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), exchangeRecordDO);
		if (i > 0) {
			return Long.valueOf(exchangeRecordDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ExchangeRecordDO exchangeRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), exchangeRecordDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(ExchangeRecordDO exchangeRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), exchangeRecordDO);
	}

	@Override
	public ExchangeRecordDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(ExchangeRecordDO exchangeRecordDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), exchangeRecordDO);
	}

	@Override
	public List<ExchangeRecordDO> selectDynamic(ExchangeRecordDO exchangeRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), exchangeRecordDO);
	}

	@Override
	public List<ExchangeRecordDO> selectDynamicPageQuery(ExchangeRecordDO exchangeRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), exchangeRecordDO);
	}

}
