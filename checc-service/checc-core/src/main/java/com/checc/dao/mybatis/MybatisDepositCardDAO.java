package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.DepositCardDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.DepositCardDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="depositCardDAO")
public class MybatisDepositCardDAO extends MybatisBaseDAO implements DepositCardDAO {
	
	private static final String NAMESPACE = "com.checc.domain.DepositCardMapper.MybatisDepositCardDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(DepositCardDO depositCardDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), depositCardDO);
		if (i > 0) {
			return Long.valueOf(depositCardDO.getCardNo());
		}
		return 0L;
	}

	@Override
	public Integer update(DepositCardDO depositCardDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), depositCardDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(DepositCardDO depositCardDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), depositCardDO);
	}

	@Override
	public DepositCardDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(DepositCardDO depositCardDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), depositCardDO);
	}

	@Override
	public List<DepositCardDO> selectDynamic(DepositCardDO depositCardDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), depositCardDO);
	}

	@Override
	public List<DepositCardDO> selectDynamicPageQuery(DepositCardDO depositCardDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), depositCardDO);
	}

}
