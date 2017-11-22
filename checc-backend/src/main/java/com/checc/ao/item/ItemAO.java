package com.checc.ao.item;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.domain.ItemDO;
import com.checc.domain.ItemDescDO;
import com.checc.domain.ItemPictureDO;
import com.checc.dto.ItemDTO;
import com.checc.service.ItemDescService;
import com.checc.service.ItemPictureService;
import com.checc.service.ItemService;
import com.checc.util.ResultMessage;
import com.checc.util.UserHandler;

import ng.bayue.common.Page;
import ng.bayue.fastdfs.ImageUrlUtil;

@Service
public class ItemAO {

	@Autowired
	private ImageUrlUtil imageUrlUtil;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemDescService descService;
	@Autowired
	private ItemPictureService pictureService;

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
	
	public ItemDTO selectByItemId(Long itemId){
		ItemDO itemDO = itemService.selectById(itemId);
		ItemDescDO descDOParam = new ItemDescDO();
		descDOParam.setItemId(itemId);
		ItemDescDO descDO = descService.selectDynamic(descDOParam).get(0);
		ItemPictureDO pictureParam = new ItemPictureDO();
		pictureParam.setItemId(itemId);
		List<ItemPictureDO> listPics = pictureService.selectDynamic(pictureParam);
		for(ItemPictureDO pic : listPics){
			pic.setPicture(imageUrlUtil.getFileFullUrl(pic.getPicture()));
		}
		
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(itemId);
		itemDTO.setItemTitle(itemDO.getItemTitle());
		itemDTO.setStatus(itemDO.getStatus());
		itemDTO.setItemType(itemDO.getItemType());
		itemDTO.setRemark(itemDO.getRemark());
		itemDTO.setDescription(descDO.getDescription());
		itemDTO.setListPictures(listPics);
		
		return itemDTO;
	}
	
	public ResultMessage updateItem(ItemDTO itemDTO){
		itemDTO.setModifyUserId(UserHandler.getUser().getId());
		itemDTO.setModifyTime(new Date());
		itemService.updateItem(itemDTO);
		return new ResultMessage();
	}

}
