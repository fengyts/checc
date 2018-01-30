package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.ExchangeOrderStatusDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.ExchangeOrderStatusDO;
import com.checc.vo.front.ExchangeOrderStatusVO;

import ng.bayue.exception.CommonDAOException;

@Component(value="exchangeOrderStatusDAO")
public class MybatisExchangeOrderStatusDAO extends MybatisBaseDAO implements ExchangeOrderStatusDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ExchangeOrderStatusMapper.MybatisExchangeOrderStatusDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), exchangeOrderStatusDO);
		if (i > 0) {
			return Long.valueOf(exchangeOrderStatusDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), exchangeOrderStatusDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), exchangeOrderStatusDO);
	}

	@Override
	public ExchangeOrderStatusDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), exchangeOrderStatusDO);
	}

	@Override
	public List<ExchangeOrderStatusDO> selectDynamic(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), exchangeOrderStatusDO);
	}

	@Override
	public List<ExchangeOrderStatusDO> selectDynamicPageQuery(ExchangeOrderStatusDO exchangeOrderStatusDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), exchangeOrderStatusDO);
	}

	@Override
	public ExchangeOrderStatusVO selectExchangeOrderDetails(Long recordId) {
		return getSqlSession().selectOne(getStatement("select_order_status_detail"), recordId);
	}
	
	

}
