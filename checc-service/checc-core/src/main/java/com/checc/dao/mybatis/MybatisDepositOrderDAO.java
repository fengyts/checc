package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.DepositOrderDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.DepositOrderDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="depositOrderDAO")
public class MybatisDepositOrderDAO extends MybatisBaseDAO implements DepositOrderDAO {
	
	private static final String NAMESPACE = "com.checc.domain.DepositOrderMapper.MybatisDepositOrderDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(DepositOrderDO depositOrderDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), depositOrderDO);
		if (i > 0) {
			return Long.valueOf(depositOrderDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DepositOrderDO depositOrderDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), depositOrderDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(DepositOrderDO depositOrderDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), depositOrderDO);
	}

	@Override
	public DepositOrderDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(DepositOrderDO depositOrderDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), depositOrderDO);
	}

	@Override
	public List<DepositOrderDO> selectDynamic(DepositOrderDO depositOrderDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), depositOrderDO);
	}

	@Override
	public List<DepositOrderDO> selectDynamicPageQuery(DepositOrderDO depositOrderDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), depositOrderDO);
	}

}
