package com.checc.dao;

import java.util.List;

import ng.bayue.service.common.GeneralDAO;

import com.checc.domain.ItemPictureDO;

 /**
 * 商品图片 DAO
 *
 * @author fengyts 2017-11-20 16:27:35
 */
public interface ItemPictureDAO extends GeneralDAO<ItemPictureDO> {
	
	List<ItemPictureDO> selectByItemIds(List<Long> itemIds);
	
}
