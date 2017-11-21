package com.checc.ao.item;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ItemDO;
import com.checc.dto.ItemDTO;
import com.checc.service.ItemService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

import ng.bayue.common.Page;

@Service
public class ItemAO {

	@Autowired
	private ItemService itemService;

	public Page<ItemDO> queryPageList(ItemDO itemDO, Integer pageNo, Integer pageSize) {
		Page<ItemDO> page = itemService.queryPageListDynamicAndStartPageSize(itemDO, pageNo, pageSize);
		return page;
	}
	
	public ResultMessage saveItem(ItemDTO itemDTO){
		itemDTO.setCreateUserId(UserHandler.getUser().getId());
		itemDTO.setModifyUserId(UserHandler.getUser().getId());
		itemDTO.setCreateTime(new Date());
		itemDTO.setModifyTime(new Date());
		itemService.saveItem(itemDTO);
		return new ResultMessage();
	}

}
