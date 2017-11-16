package com.checc.dao.mybatis;

import java.util.List;
import java.util.Map;

import ng.bayue.exception.CommonDAOException;

import org.springframework.stereotype.Component;

import com.checc.dao.SysRoleMenuDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.SysRoleMenuDO;

@Component(value="sysRoleMenuDAO")
public class MybatisSysRoleMenuDAO extends MybatisBaseDAO implements SysRoleMenuDAO {
	
	private static final String NAMESPACE = "com.checc.domain.SysRoleMenuMapper.MybatisSysRoleMenuDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(SysRoleMenuDO sysRoleMenuDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), sysRoleMenuDO);
		if (i > 0) {
			return Long.valueOf(sysRoleMenuDO.getMenuId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysRoleMenuDO sysRoleMenuDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), sysRoleMenuDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), sysRoleMenuDO);
	}

	@Override
	public SysRoleMenuDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), sysRoleMenuDO);
	}

	@Override
	public List<SysRoleMenuDO> selectDynamic(SysRoleMenuDO sysRoleMenuDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), sysRoleMenuDO);
	}

	@Override
	public List<SysRoleMenuDO> selectDynamicPageQuery(SysRoleMenuDO sysRoleMenuDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), sysRoleMenuDO);
	}
	
	@Override
	public Integer deleteByRoleId(Long roleId) throws CommonDAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_deleteByRoleId", roleId);
	}

	@Override
	public List<SysRoleMenuDO> selectByRoleId(Long roleId) throws CommonDAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_selectByRoleId", roleId);
	}

	@Override
	public void insertBatch(Map<String,Object> map) throws CommonDAOException {
		getSqlSession().insert("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_insert_batch", map);
	}

}
