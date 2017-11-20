package com.checc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.item.ItemAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.base.BaseController;
import com.checc.domain.ItemDO;
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
	public String addItemInfo(Model model) {
		model.addAttribute("itemTypes", ItemTypeEnum.values());
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		return BackendConstant.BACKEND_VIEW_PATH + "item/add";
	}
	
	@RequestMapping({"/saveItem"})
	@ResponseBody
	public ResultMessage saveItem(HttpServletRequest request){
		
		return new ResultMessage();
	}

}
