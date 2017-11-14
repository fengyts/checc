package com.checc.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.ExchargeRecordDAO;
import com.checc.domain.ExchargeRecordDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="exchargeRecordDAO")
public class MybatisExchargeRecordDAO extends MybatisBaseDAO implements ExchargeRecordDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ExchargeRecordMapper.MybatisExchargeRecordDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ExchargeRecordDO exchargeRecordDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), exchargeRecordDO);
		if (i > 0) {
			return Long.valueOf(exchargeRecordDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ExchargeRecordDO exchargeRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), exchargeRecordDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), exchargeRecordDO);
	}

	@Override
	public ExchargeRecordDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), exchargeRecordDO);
	}

	@Override
	public List<ExchargeRecordDO> selectDynamic(ExchargeRecordDO exchargeRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), exchargeRecordDO);
	}

	@Override
	public List<ExchargeRecordDO> selectDynamicPageQuery(ExchargeRecordDO exchargeRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), exchargeRecordDO);
	}

}
