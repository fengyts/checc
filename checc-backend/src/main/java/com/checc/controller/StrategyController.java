package com.checc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.StrategyAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.util.Messages;
import com.checc.util.ResultMessage;

import ng.bayue.util.StringUtils;

/**
 * <pre>
 * 营销策略管理控制器
 * </pre>
 *
 * @author lenovopc
 * @version $Id: StrategyController.java, v 0.1 2018年1月11日 上午10:50:59 lenovopc
 *          Exp $
 */
@Controller
@RequestMapping({ "/strategy" })
public class StrategyController {
	
	@Autowired
	private StrategyAO strategyAO;

	@RequestMapping("/vmAccountList")
	public String vmAccountList(Model model, String mobile) {
		model.addAttribute("mobile", mobile);
		model.addAttribute("vmAccountList", strategyAO.listAllVMAccount(mobile));
		return BackendConstant.BACKEND_VIEW_PATH + "/strategy/vmaccount/list";
	}
	
	/**
	 * <pre>
	 * 一键修改虚拟账号所有密码
	 * </pre>
	 *
	 * @param userId
	 * @param pwd
	 * @param pwd1
	 * @return
	 */
	@RequestMapping("/modifyVmAccountPwd")
	@ResponseBody
	public ResultMessage updateVmAccountPwd(Long userId, String pwd, String pwd1) {
		if(StringUtils.isBlank(pwd) || StringUtils.isBlank(pwd1) || !pwd.equals(pwd1)){
			return ResultMessage.failure(Messages.HandleFailure);
		}
		return strategyAO.updateVmAccountPwd(pwd);
	}
	
	@RequestMapping("/edit")
	public String editVmAccount(Model model, Long userId){
		model.addAttribute("vmAccount", strategyAO.selectVmAccountInfo(userId));
		return BackendConstant.BACKEND_VIEW_PATH + "/strategy/vmaccount/edit";
	}
	
	@RequestMapping("/updateVmAccount")
	@ResponseBody
	public ResultMessage updateVmAccount(Long userId, Integer totalCurrency) {
		return strategyAO.updateVmAccount(userId, totalCurrency);
	}
	
	@RequestMapping("/agreementAndHelper")
	public String agreementAndHelper(Model model){
		return "/index/agreement_helper";
	}
	
	@RequestMapping("/preview")
	public String preview(Model model, String content){
		model.addAttribute("content", content);
		return "/index/preview";
	}
	
}
