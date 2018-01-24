package com.checc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.CheccNoticeAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.domain.CheccNoticeDO;
import com.checc.util.ResultMessage;

/**
 * <pre>
 * 用户公告 & 帮助中心 & 用户协议 控制器
 * </pre>
 *
 * @author lenovopc
 * @version $Id: CheccNoticeController.java, v 0.1 2018年1月24日 下午1:52:44 lenovopc
 *          Exp $
 */
@Controller
@RequestMapping({ "/notice" })
public class CheccNoticeController {

	@Autowired
	private CheccNoticeAO noticeAO;

	@RequestMapping("/agreementAndHelper")
	public String agreementAndHelper(Model model, String type) {
		CheccNoticeDO noticeDO = noticeAO.selectByType(type);
		model.addAttribute("noticeDO", noticeDO);
		model.addAttribute("type", type);
		model.addAttribute("description", noticeDO.getContent());
		return BackendConstant.BACKEND_VIEW_PATH + "notice/agreement_helper";
	}

	/**
	 * <pre>
	 * 预览
	 * </pre>
	 *
	 * @param model
	 * @param content
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/preview")
	public String preview(Model model, String content, HttpServletRequest request) throws Exception {
		model.addAttribute("content", content);
		return BackendConstant.BACKEND_VIEW_PATH + "notice/preview";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultMessage save(CheccNoticeDO noticeDO, String description){
		noticeDO.setContent(description);
		return noticeAO.save(noticeDO);
	}
	

}
