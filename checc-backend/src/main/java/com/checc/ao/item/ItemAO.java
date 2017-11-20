package com.checc.ao.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ItemDO;
import com.checc.service.ItemService;

import ng.bayue.common.Page;

@Service
public class ItemAO {

	@Autowired
	private ItemService itemService;

	public Page<ItemDO> queryPageList(ItemDO itemDO, Integer pageNo, Integer pageSize) {
		Page<ItemDO> page = itemService.queryPageListDynamicAndStartPageSize(itemDO, pageNo, pageSize);
		return page;
	}

}
