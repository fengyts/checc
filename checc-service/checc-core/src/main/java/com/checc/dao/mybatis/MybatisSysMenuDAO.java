package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.SysMenuDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.SysMenuDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="sysMenuDAO")
public class MybatisSysMenuDAO extends MybatisBaseDAO implements SysMenuDAO {
	
	private static final String NAMESPACE = "com.checc.domain.SysMenuMapper.MybatisSysMenuDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(SysMenuDO sysMenuDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), sysMenuDO);
		if (i > 0) {
			return Long.valueOf(sysMenuDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysMenuDO sysMenuDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), sysMenuDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(SysMenuDO sysMenuDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), sysMenuDO);
	}

	@Override
	public SysMenuDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(SysMenuDO sysMenuDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), sysMenuDO);
	}

	@Override
	public List<SysMenuDO> selectDynamic(SysMenuDO sysMenuDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), sysMenuDO);
	}

	@Override
	public List<SysMenuDO> selectDynamicPageQuery(SysMenuDO sysMenuDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), sysMenuDO);
	}
	
	@Override
	public List<SysMenuDO> findListByParentIds(List<SysMenuDO> sysMenuList) throws CommonDAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.findListByParentIds", sysMenuList);
	}
	
	@Override
	public List<SysMenuDO> findListByIds(List<Long> ids) throws CommonDAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.findListByIds", ids);
	}
	
	@Override
	public List<SysMenuDO> findParentMenu() throws CommonDAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.findParentMenu");
	}

	@Override
	public List<SysMenuDO> selectByIds(List<Long> ids){
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.selectByIds", ids);
	}
	
	@Override
	public List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO) throws CommonDAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_select_dynamic_url_is_null", sysMenuDO);
	}

}
