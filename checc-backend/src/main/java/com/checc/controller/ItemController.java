package com.checc.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checc.ao.item.ItemAO;
import com.checc.backend.constants.BackendConstant;
import com.checc.base.BaseController;
import com.checc.domain.ItemDO;
import com.checc.dto.ItemDTO;
import com.checc.enums.ItemStatusEnum;
import com.checc.enums.ItemTypeEnum;
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
		model.addAttribute("itemDO", itemDO);
		model.addAttribute("itemTypes", ItemTypeEnum.values());
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return BackendConstant.BACKEND_VIEW_PATH + "item/list";
	}
	
	@RequestMapping({ "/addItem" })
	public String addItemInfo(Model model, String iframeName) {
		model.addAttribute("listIframeName", iframeName);
		model.addAttribute("itemTypes", ItemTypeEnum.values());
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		return BackendConstant.BACKEND_VIEW_PATH + "item/add";
	}
	
	@RequestMapping({"/saveItem"})
	@ResponseBody
	public ResultMessage saveItem(ItemDTO itemDTO, Errors error){
//		if(error.hasErrors()){
//			List<ObjectError> list = error.getAllErrors();
//			ObjectError oe = list.get(0);
//			String message = oe.getDefaultMessage();
//			return new ResultMessage(ResultMessage.Failure, message);
//		}
		if(StringUtils.isBlank(itemDTO.getItemTitle())){
			return ResultMessage.validParameterNull("商品名称");
		}
		if(StringUtils.isBlank(itemDTO.getDescription())){
			return ResultMessage.validParameterNull("商品描述");
		}
		if(StringUtils.isNumeric(String.valueOf(itemDTO.getMarketPrice()))){
			return ResultMessage.validParameterNull("市场价不能为空");
		}
		return itemAO.saveItem(itemDTO);
	}
	
	@RequestMapping({"/editItem"})
	public String editItem(Model model, String iframeName, Long itemId){
		model.addAttribute("listIframeName", iframeName);
		model.addAttribute("itemTypes", ItemTypeEnum.values());
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		
		ItemDTO itemDTO = itemAO.selectByItemId(itemId);
		model.addAttribute("itemDO", itemDTO);
		model.addAttribute("description", itemDTO.getDescription());
		model.addAttribute("picUrlList", itemDTO.getListPictures());
		
		return BackendConstant.BACKEND_VIEW_PATH + "item/edit";
	}
	
	@RequestMapping({"/updateItem"})
	@ResponseBody
	public ResultMessage updateItem(ItemDTO itemDTO, Boolean hasNewPic){
		if(StringUtils.isBlank(itemDTO.getItemTitle())){
			return ResultMessage.validParameterNull("商品名称");
		}
		if(StringUtils.isBlank(itemDTO.getDescription())){
			return ResultMessage.validParameterNull("商品描述");
		}
		if(StringUtils.isNumeric(String.valueOf(itemDTO.getMarketPrice()))){
			return ResultMessage.validParameterNull("市场价不能为空");
		}
		return itemAO.updateItem(itemDTO);
	}

}
