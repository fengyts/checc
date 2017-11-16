package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.ItemDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.ItemDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="itemDAO")
public class MybatisItemDAO extends MybatisBaseDAO implements ItemDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ItemMapper.MybatisItemDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ItemDO itemDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), itemDO);
		if (i > 0) {
			return Long.valueOf(itemDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemDO itemDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), itemDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(ItemDO itemDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), itemDO);
	}

	@Override
	public ItemDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(ItemDO itemDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), itemDO);
	}

	@Override
	public List<ItemDO> selectDynamic(ItemDO itemDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), itemDO);
	}

	@Override
	public List<ItemDO> selectDynamicPageQuery(ItemDO itemDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), itemDO);
	}

}
