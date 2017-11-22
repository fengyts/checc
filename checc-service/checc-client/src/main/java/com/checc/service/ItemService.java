package com.checc.service;

import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.common.GeneralService;
import com.checc.domain.ItemDO;
import com.checc.dto.ItemDTO;

 /**
 * 商品 Service
 * @author fengyts 2017-11-16 14:54:40
 */
public interface ItemService extends GeneralService<ItemDO, ItemDO> {
	
	int saveItem(ItemDTO itemDTO) throws CommonServiceException;
	
	int updateItem(ItemDTO itemDTO) throws CommonServiceException;
	
}
