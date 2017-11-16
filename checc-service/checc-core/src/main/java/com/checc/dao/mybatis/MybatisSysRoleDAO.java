package com.checc.dao.mybatis;

import java.util.List;

import ng.bayue.exception.CommonDAOException;

import org.springframework.stereotype.Component;

import com.checc.dao.SysRoleDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.SysRoleDO;

@Component(value="sysRoleDAO")
public class MybatisSysRoleDAO extends MybatisBaseDAO implements SysRoleDAO {
	
	private static final String NAMESPACE = "com.checc.domain.SysRoleMapper.MybatisSysRoleDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(SysRoleDO sysRoleDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), sysRoleDO);
		if (i > 0) {
			return Long.valueOf(sysRoleDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysRoleDO sysRoleDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), sysRoleDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(SysRoleDO sysRoleDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), sysRoleDO);
	}

	@Override
	public SysRoleDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(SysRoleDO sysRoleDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), sysRoleDO);
	}

	@Override
	public List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), sysRoleDO);
	}

	@Override
	public List<SysRoleDO> selectDynamicPageQuery(SysRoleDO sysRoleDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), sysRoleDO);
	}

	@Override
	public List<SysRoleDO> selectByIds(List<Long> ids) throws CommonDAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_select_by_ids",ids);
	}
}
