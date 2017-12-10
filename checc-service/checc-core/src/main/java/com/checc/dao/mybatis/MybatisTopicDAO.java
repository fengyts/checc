package com.checc.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.checc.dao.TopicDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.TopicDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="topicDAO")
public class MybatisTopicDAO extends MybatisBaseDAO implements TopicDAO {
	
	private static final String NAMESPACE = "com.checc.domain.TopicMapper.MybatisTopicDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(TopicDO topicDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), topicDO);
		if (i > 0) {
			return Long.valueOf(topicDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), topicDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), topicDO);
	}

	@Override
	public TopicDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), topicDO);
	}

	@Override
	public List<TopicDO> selectDynamic(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), topicDO);
	}

	@Override
	public List<TopicDO> selectDynamicPageQuery(TopicDO topicDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), topicDO);
	}

	@Override
	public List<TopicDO> selectAllDynamic(Map<String, Object> params) {
		return getSqlSession().selectList(getStatement("select_all_byStatus"), params);
	}

}
