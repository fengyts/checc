package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.DepositRecordDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.DepositRecordDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="depositRecordDAO")
public class MybatisDepositRecordDAO extends MybatisBaseDAO implements DepositRecordDAO {
	
	private static final String NAMESPACE = "com.checc.domain.DepositRecordMapper.MybatisDepositRecordDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(DepositRecordDO depositRecordDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), depositRecordDO);
		if (i > 0) {
			return Long.valueOf(depositRecordDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DepositRecordDO depositRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), depositRecordDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(DepositRecordDO depositRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), depositRecordDO);
	}

	@Override
	public DepositRecordDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(DepositRecordDO depositRecordDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), depositRecordDO);
	}

	@Override
	public List<DepositRecordDO> selectDynamic(DepositRecordDO depositRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), depositRecordDO);
	}

	@Override
	public List<DepositRecordDO> selectDynamicPageQuery(DepositRecordDO depositRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), depositRecordDO);
	}

}
