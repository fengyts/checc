package com.checc.service;

import java.util.List;

import ng.bayue.service.common.GeneralService;

import com.checc.domain.ItemPictureDO;

 /**
 * 商品图片 Service
 * @author fengyts 2017-11-20 16:27:35
 */
public interface ItemPictureService extends GeneralService<ItemPictureDO, ItemPictureDO> {

	List<ItemPictureDO> selectByItemIds(List<Long> itemIds);
	
}
