package com.checc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.ao.TopicItemAO;
import com.checc.vo.front.TopicItemDetailVO;

import constants.CommonPathConstant;
import ng.bayue.util.DateUtils;
import ng.bayue.util.StringUtils;

@Controller
@RequestMapping(value = { "/topicItem" })
public class TopicItemController {

	@Autowired
	private TopicItemAO topicItemAO;
	
//	@InitBinder
//	public void binder(WebDataBinder binder){
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
//	}

	/**
	 *
	 * @param model
	 * @param detailType
	 * @param tpId topicItem 主键id
	 * @return
	 */
	@RequestMapping(value = "/itemDetails/{detailType}/{tpId}")
	public String topicItemDetails(Model model, @PathVariable String detailType, @PathVariable Long tpId, Long reqTime) {
		if (StringUtils.isBlank(detailType) || tpId == null) {
			return CommonPathConstant.PATH_ERROR_404;
		}
//		Date date1 = new Date(reqTime);
//		String time1 = DateUtils.formatDateTime(date1);
//		System.out.println(time1);
		
		TopicItemDetailVO detailVO = topicItemAO.topicItemDetails(tpId);
		model.addAttribute("detailVO", detailVO);
		if(null != reqTime || reqTime > 0l){
			model.addAttribute("reqTime", reqTime);
		}
		return "/business/topicItemDetail";
	}

}
