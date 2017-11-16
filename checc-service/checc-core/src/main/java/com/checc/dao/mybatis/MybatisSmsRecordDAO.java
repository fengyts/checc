package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.SmsRecordDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.SmsRecordDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="smsRecordDAO")
public class MybatisSmsRecordDAO extends MybatisBaseDAO implements SmsRecordDAO {
	
	private static final String NAMESPACE = "com.checc.domain.SmsRecordMapper.MybatisSmsRecordDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(SmsRecordDO smsRecordDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), smsRecordDO);
		if (i > 0) {
			return Long.valueOf(smsRecordDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SmsRecordDO smsRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), smsRecordDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(SmsRecordDO smsRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), smsRecordDO);
	}

	@Override
	public SmsRecordDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(SmsRecordDO smsRecordDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), smsRecordDO);
	}

	@Override
	public List<SmsRecordDO> selectDynamic(SmsRecordDO smsRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), smsRecordDO);
	}

	@Override
	public List<SmsRecordDO> selectDynamicPageQuery(SmsRecordDO smsRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), smsRecordDO);
	}

}
