package com.checc.controller.sys;

import java.util.List;

import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.sys.SysUserAO;
import com.checc.domain.SysUserDO;
import com.checc.domain.SysUserRoleDO;
import com.checc.util.Messages;
import com.checc.util.ResultMessage;
import com.checc.vo.SysUserDTO;

/**
 * <pre>
 * 系统用户控制器
 * </pre>
 *
 * @author fengyts
 * @version $Id: SysUserController.java, v 0.1 2017年11月17日 下午11:03:13 fengyts
 *          Exp $
 */

@Controller
@RequestMapping({ "/sys/sysUser" })
public class SysUserController {

	private static final String BASE_VIEW_PATH = "/sys/sysUser/";

	@Autowired
	private SysUserAO sysUserAO;

	@RequestMapping({ "/list" })
	public void sysUserList(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			SysUserDO sysUserDO) {
		Page<SysUserDTO> page = sysUserAO.queryPage(sysUserDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("sysUserDO", sysUserDO);
	}

	@RequestMapping({ "add" })
	public void add() {
	}
	
	@RequestMapping({ "save" })
	@ResponseBody
	public ResultMessage sysUserSave(SysUserDO sysUserDO, String roleIds, String password2) {
		String loginName = sysUserDO.getLoginName();
		String userName = sysUserDO.getUserName();
		String email = sysUserDO.getEmail();
		String mobile = sysUserDO.getMobile();
		String passwd = sysUserDO.getPassword();
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(userName)
				|| StringUtils.isEmpty(email) || StringUtils.isEmpty(mobile)
				|| StringUtils.isEmpty(passwd) || StringUtils.isEmpty(password2)) {
			return ResultMessage.validParameterNull("数据不能为空");
		}
		if (!passwd.equals(password2)) {
			return new ResultMessage(ResultMessage.Failure, "两次输入密码不一样");
		}
		sysUserAO.save(sysUserDO, roleIds);
		return new ResultMessage();
	}

	@RequestMapping({ "edit" })
	public void edit(Model model, Long userId) {
		List<SysUserRoleDO> list = sysUserAO.selectRolesByUserId(userId);
		if (CollectionUtils.isNotEmpty(list)) {
			String roleStr = "";
			for (SysUserRoleDO sur : list) {
				roleStr += sur.getRoleId() + ",";
			}
			model.addAttribute("roleIds", roleStr.substring(0, roleStr.length() - 1));
		}
		SysUserDO sysUser = sysUserAO.selectByUserId(userId);
		model.addAttribute("sysUser", sysUser);
	}
	
	@RequestMapping({ "update" })
	@ResponseBody
	public ResultMessage sysUserUpdate(SysUserDO sysUserDO, String roleIds) {
		String loginName = sysUserDO.getLoginName();
		String userName = sysUserDO.getUserName();
		String email = sysUserDO.getEmail();
		String mobile = sysUserDO.getMobile();
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(userName)
				|| StringUtils.isEmpty(email) || StringUtils.isEmpty(mobile)) {
			return ResultMessage.validParameterNull(Messages.ParameterNull);
		}
		sysUserAO.sysUserUpdate(sysUserDO, roleIds);
		return new ResultMessage();
	}

	@RequestMapping({ "resetpassword" })
	public void resetPassword(Long userId, String loginName) {
		sysUserAO.resetPassword(userId, loginName);
	}

	@RequestMapping({ "modifyPwd" })
	public String updatepwd() {
		return "/index/updatepwd";
	}

	@RequestMapping({ "updatePassword" })
	@ResponseBody
	public ResultMessage updatepwd(String password, String password1, String password2) {
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(password1)
				|| StringUtils.isEmpty(password2)) {
			return ResultMessage.validParameterNull("数据为空");
		}
		if(!password1.equals(password2)){
			return new ResultMessage(ResultMessage.Failure,"两次密码不一样");
		}
		return sysUserAO.updatePassword(password, password1);
	}

}
