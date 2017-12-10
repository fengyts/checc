package com.checc.dao;

import java.util.List;
import java.util.Map;

import ng.bayue.service.common.GeneralDAO;

import com.checc.domain.TopicDO;

 /**
 * 专题 DAO
 *
 * @author fengyts 2017-11-16 14:54:39
 */
public interface TopicDAO extends GeneralDAO<TopicDO> {
	
	List<TopicDO> selectAllDynamic(Map<String, Object> params);
	
}
