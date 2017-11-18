package com.checc.controller.sys;

import java.util.List;

import ng.bayue.common.Page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.sys.SysMenuAO;
import com.checc.ao.sys.SysMenuRoleAO;
import com.checc.ao.sys.SysRoleAO;
import com.checc.domain.SysRoleDO;
import com.checc.util.HanyuPinyinUtil;
import com.checc.util.ResultMessage;

@Controller
@RequestMapping({ "/sys/sysRole" })
public class SysRoleController {

	@Autowired
	private SysRoleAO sysRoleAO;
	@Autowired
	private SysMenuAO sysMenuAO;
	@Autowired
	private SysMenuRoleAO sysMenuRoleAO;

	@RequestMapping({ "list" })
	public void sysRoleList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, SysRoleDO sysRoleDO) {
		Page<SysRoleDO> page = sysRoleAO.queryPage(sysRoleDO, pageNo, pageSize);
		model.addAttribute("page", page);
	}

	@RequestMapping({ "add" })
	public void sysRoleAdd(Model model) {
//		List<SysMenuDO> sysMenus = sysMenuAO.listAllMenus();
//		model.addAttribute("sysMenus", sysMenus);
	}

	@RequestMapping({ "save" })
	@ResponseBody
	public ResultMessage sysRoleSave(SysRoleDO sysRoleDO,String menuIds) {
		String name = sysRoleDO.getRoleName();
		if (StringUtils.isEmpty(name)) {
			return ResultMessage.validParameterNull("name");
		}
		String code = sysRoleDO.getRoleCode();
		if (StringUtils.isEmpty(code)) {
			String codeHanyu = HanyuPinyinUtil.hanyuToPinyin(name);
			if (StringUtils.isEmpty(codeHanyu)) {
//				return ResultMessage.serverInnerError();
				sysRoleDO.setRoleCode(code);
			}
			sysRoleDO.setRoleCode(codeHanyu);
		}
		return sysRoleAO.saveSysRole(sysRoleDO, menuIds);
	}

	@RequestMapping({ "edit" })
	public void sysRoleEdit(Model model, Long id) {
		model.addAttribute("sysRole", sysRoleAO.selectById(id));
		model.addAttribute("menuIds", sysMenuRoleAO.selectMenuIdsByRoleId(id));
	}

	@RequestMapping({ "update" })
	@ResponseBody
	public ResultMessage sysRoleUpdate(SysRoleDO sysRoleDO, String menuIds) {
		String name = sysRoleDO.getRoleName();
		if (StringUtils.isEmpty(name)) {
			return ResultMessage.validParameterNull("name");
		}
		if (StringUtils.isEmpty(sysRoleDO.getRoleCode())) {
			String code = HanyuPinyinUtil.hanyuToPinyin(name);
			if (StringUtils.isEmpty(code)) {
				return ResultMessage.serverInnerError();
			}
			sysRoleDO.setRoleCode(code);
		}
		return sysRoleAO.updateSysRole(sysRoleDO,menuIds);
		
	}
	
	@RequestMapping({"rolesAll"})
	@ResponseBody
	public List<SysRoleDO> rolesAll(){
		List<SysRoleDO> list = sysRoleAO.selectAllRole();
		return list;
	}

}
