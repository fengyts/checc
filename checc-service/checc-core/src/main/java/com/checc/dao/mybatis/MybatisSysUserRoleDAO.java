package com.checc.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;
import com.checc.dao.SysUserRoleDAO;
import com.checc.domain.SysUserRoleDO;
import ng.bayue.exception.CommonDAOException;

@Component(value="sysUserRoleDAO")
public class MybatisSysUserRoleDAO extends MybatisBaseDAO implements SysUserRoleDAO {
	
	private static final String NAMESPACE = "com.checc.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(SysUserRoleDO sysUserRoleDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), sysUserRoleDO);
		if (i > 0) {
			return Long.valueOf(sysUserRoleDO.getUserId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysUserRoleDO sysUserRoleDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), sysUserRoleDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(SysUserRoleDO sysUserRoleDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), sysUserRoleDO);
	}

	@Override
	public SysUserRoleDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(SysUserRoleDO sysUserRoleDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), sysUserRoleDO);
	}

	@Override
	public List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), sysUserRoleDO);
	}

	@Override
	public List<SysUserRoleDO> selectDynamicPageQuery(SysUserRoleDO sysUserRoleDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), sysUserRoleDO);
	}

}
