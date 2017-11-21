package com.checc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.checc.ao.item.ItemAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.base.BaseController;
import com.checc.domain.ItemDO;
import com.checc.dto.ItemDTO;
import com.checc.dto.enums.ItemStatusEnum;
import com.checc.dto.enums.ItemTypeEnum;
import com.checc.util.ResultMessage;

import ng.bayue.common.Page;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

	@Autowired
	private ItemAO itemAO;

	@RequestMapping(value = { "/list" })
	public String list(Model model, ItemDO itemDO, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<ItemDO> page = itemAO.queryPageList(itemDO, pageNo, pageSize);
		model.addAttribute("page", page);
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "item/list";
	}
	
	@RequestMapping({ "/addItem" })
	public String addItemInfo(Model model, String iframeName) {
		model.addAttribute("itemTypes", ItemTypeEnum.values());
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		model.addAttribute("listIframeName", iframeName);
		return BackendConstant.BACKEND_VIEW_PATH + "item/add";
	}
	
	@RequestMapping({"/saveItem"})
	@ResponseBody
	public ResultMessage saveItem(@Valid ItemDTO itemDTO, Errors error){
		if(error.hasErrors()){
			List<ObjectError> list = error.getAllErrors();
			ObjectError oe = list.get(0);
			String message = oe.getDefaultMessage();
			return new ResultMessage(ResultMessage.Failure, message);
		}
		return itemAO.saveItem(itemDTO);
	}

}
