package com.checc.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.SysRoleMenuDAO;
import com.checc.domain.SysRoleMenuDO;
import ng.bayue.exception.CommonDAOException;

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

}
