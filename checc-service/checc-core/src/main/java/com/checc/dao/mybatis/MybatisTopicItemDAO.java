package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.TopicItemDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.TopicItemDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="topicItemDAO")
public class MybatisTopicItemDAO extends MybatisBaseDAO implements TopicItemDAO {
	
	private static final String NAMESPACE = "com.checc.domain.TopicItemMapper.MybatisTopicItemDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(TopicItemDO topicItemDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), topicItemDO);
		if (i > 0) {
			return Long.valueOf(topicItemDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), topicItemDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), topicItemDO);
	}

	@Override
	public TopicItemDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), topicItemDO);
	}

	@Override
	public List<TopicItemDO> selectDynamic(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), topicItemDO);
	}

	@Override
	public List<TopicItemDO> selectDynamicPageQuery(TopicItemDO topicItemDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), topicItemDO);
	}

	@Override
	public int reduceExchangeResidue(Long id) throws CommonDAOException {
		return getSqlSession().update(getStatement("reduce_exchange_residue"), id);
	}

	@Override
	public List<TopicItemDO> selectByIds(List<Long> ids) {
		return getSqlSession().selectList(getStatement("select_by_ids"), ids);
	}

	@Override
	public List<TopicItemDO> selectByTopicIds(List<Long> topicIds) {
		return getSqlSession().selectList(getStatement("select_by_topicIds"), topicIds);
	}
	
	
}
