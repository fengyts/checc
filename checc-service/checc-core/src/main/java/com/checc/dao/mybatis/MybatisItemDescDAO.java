package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.ItemDescDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.ItemDescDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="itemDescDAO")
public class MybatisItemDescDAO extends MybatisBaseDAO implements ItemDescDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ItemDescMapper.MybatisItemDescDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ItemDescDO itemDescDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), itemDescDO);
		if (i > 0) {
			return Long.valueOf(itemDescDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemDescDO itemDescDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), itemDescDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(ItemDescDO itemDescDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), itemDescDO);
	}

	@Override
	public ItemDescDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(ItemDescDO itemDescDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), itemDescDO);
	}

	@Override
	public List<ItemDescDO> selectDynamic(ItemDescDO itemDescDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), itemDescDO);
	}

	@Override
	public List<ItemDescDO> selectDynamicPageQuery(ItemDescDO itemDescDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), itemDescDO);
	}

	@Override
	public int updateByItemId(ItemDescDO itemDescDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_by_itemId"), itemDescDO);
	}

	
}
