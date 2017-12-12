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
	
	/**
	 * <pre>
	 * 根据商品id批量修改删除状态，逻辑删除
	 * </pre>
	 *
	 * @param itemId
	 * @return
	 */
	int updateByItemId(Long itemId);
	
	/**
	 * <pre>
	 * 批量删除过期图片，删除图片定时任务调用
	 * </pre>
	 *
	 * @return
	 */
	int updateDeleteStatus();
	
}
