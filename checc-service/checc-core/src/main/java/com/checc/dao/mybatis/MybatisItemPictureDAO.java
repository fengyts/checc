package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.ItemPictureDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.ItemPictureDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="itemPictureDAO")
public class MybatisItemPictureDAO extends MybatisBaseDAO implements ItemPictureDAO {
	
	private static final String NAMESPACE = "com.checc.domain.ItemPictureMapper.MybatisItemPictureDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(ItemPictureDO itemPictureDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), itemPictureDO);
		if (i > 0) {
			return Long.valueOf(itemPictureDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemPictureDO itemPictureDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), itemPictureDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(ItemPictureDO itemPictureDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), itemPictureDO);
	}

	@Override
	public ItemPictureDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(ItemPictureDO itemPictureDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), itemPictureDO);
	}

	@Override
	public List<ItemPictureDO> selectDynamic(ItemPictureDO itemPictureDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), itemPictureDO);
	}

	@Override
	public List<ItemPictureDO> selectDynamicPageQuery(ItemPictureDO itemPictureDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), itemPictureDO);
	}

	@Override
	public List<ItemPictureDO> selectByItemIds(List<Long> itemIds) {
		return getSqlSession().selectList(getStatement("select_by_itemIds"), itemIds);
	}

	@Override
	public int updateByItemId(Long itemId) {
		return getSqlSession().update(getStatement("update_byItemId"), itemId);
	}

	@Override
	public int updateDeleteStatus() {
		return getSqlSession().update(getStatement("update_deleteStatus"));
	}

}
