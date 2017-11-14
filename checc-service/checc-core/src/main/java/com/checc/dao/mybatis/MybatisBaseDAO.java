package com.checc.dao.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.BaseDAO;

public class MybatisBaseDAO extends SqlSessionDaoSupport implements BaseDAO{
	
	@Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
      		super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
}