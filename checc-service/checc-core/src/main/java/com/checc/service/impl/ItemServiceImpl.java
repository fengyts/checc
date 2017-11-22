package com.checc.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ItemDAO;
import com.checc.dao.ItemDescDAO;
import com.checc.dao.ItemPictureDAO;
import com.checc.domain.ItemDO;
import com.checc.domain.ItemDescDO;
import com.checc.domain.ItemPictureDO;
import com.checc.dto.ItemDTO;
import com.checc.dto.enums.ItemPictureTypeEnum;
import com.checc.service.ItemService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;
import ng.bayue.constant.CommonConstant;

@Service(value="itemService")
public class ItemServiceImpl  implements ItemService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private ItemDescDAO itemDescDAO;
	@Autowired
	private ItemPictureDAO pictureDAO;

	@Override
	public Long insert(ItemDO itemDO) throws CommonServiceException {
		try {
			return itemDAO.insert(itemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(ItemDO itemDO) throws CommonServiceException {
//		try {
//			return (Integer) itemDAO.updateById(itemDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(ItemDO itemDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) itemDAO.update(itemDO);
			}else{
				return (Integer) itemDAO.updateDynamic(itemDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) itemDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ItemDO itemDO) throws CommonServiceException {
//		try {
//			return (Integer) itemDAO.updateDynamic(itemDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public ItemDO selectById(Long id) throws CommonServiceException {
		try {
			return itemDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemDO itemDO) throws CommonServiceException {
		try {
			return itemDAO.selectCountDynamic(itemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<ItemDO> selectDynamic(ItemDO itemDO) throws CommonServiceException {
		try {
			return itemDAO.selectDynamic(itemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<ItemDO> selectDynamicPageQuery(ItemDO itemDO) throws CommonServiceException {
		try {
			return itemDAO.selectDynamicPageQuery(itemDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<ItemDO> queryPageListDynamic(ItemDO itemDO) throws CommonServiceException{
		if (itemDO != null) {
			Long totalCount = this.selectCountDynamic(itemDO);

			Page<ItemDO> page = new Page<ItemDO>();
			page.setPageNo(itemDO.getStartPage());
			page.setPageSize(itemDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ItemDO> resultList = this.selectDynamicPageQuery(itemDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ItemDO>();
	}
	
	@Override
	public Page<ItemDO> queryPageListDynamicAndStartPageSize(ItemDO itemDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (itemDO != null && startPage>0 && pageSize>0) {
			itemDO.setStartPage(startPage);
			itemDO.setPageSize(pageSize);
			return this.queryPageListDynamic(itemDO);
		}
		return new Page<ItemDO>();
	}

	@Override
	@Transactional
	public int saveItem(ItemDTO itemDTO) throws CommonServiceException {
		ItemDO itemDO = new ItemDO();
		Date date = new Date();
		Long userId = itemDTO.getCreateUserId();
		try {
			BeanUtils.copyProperties(itemDO, itemDTO);
			itemDO.setCreateTime(date);
			itemDO.setModifyTime(date);

			this.insert(itemDO);
			Long itemId = itemDO.getId();

			String description = itemDTO.getDescription();
			if (StringUtils.isNotBlank(description)) {
				ItemDescDO descDO = new ItemDescDO();
				descDO.setItemId(itemId);
				descDO.setDescription(description);
				descDO.setCreateUserId(userId);
				descDO.setCreateTime(date);
				descDO.setModifyUserId(userId);
				descDO.setModifyTime(date);
				itemDescDAO.insert(descDO);
			}

			List<String> listPicUrls = itemDTO.getListPicUrls();
			if (CollectionUtils.isNotEmpty(listPicUrls)) {
				int count = 0;
				for (String picture : listPicUrls) {
					ItemPictureDO pictureDO = new ItemPictureDO();
					pictureDO.setItemId(itemId);
					pictureDO.setPicture(picture);
					pictureDO.setSort(count);
					pictureDO.setStatus(CommonConstant.STATUS.TRUE);
					pictureDO.setType(ItemPictureTypeEnum.PIC_PRIMARY.getCode());

					pictureDO.setCreateTime(date);
					pictureDO.setCreateUserId(userId);
					pictureDO.setModifyTime(date);
					pictureDO.setModifyUserId(userId);
					
					pictureDAO.insert(pictureDO);

					count++;
				}

			}
			
			return 1;
		} catch (IllegalAccessException | InvocationTargetException | CommonDAOException e) {
			logger.info("插入商品异常", e);
		}
		return 0;
	}
	
	@Override
	@Transactional
	public int updateItem(ItemDTO itemDTO) throws CommonServiceException {
		Date date = new Date();
		Long userId = itemDTO.getCreateUserId();
		Long itemId = itemDTO.getId();
		try {
			ItemDO itemDO = new ItemDO();
			itemDO.setItemTitle(itemDTO.getItemTitle());
			itemDO.setId(itemId);
			itemDO.setMarketPrice(itemDTO.getMarketPrice());
			itemDO.setItemType(itemDTO.getItemType());
			itemDO.setStatus(itemDTO.getStatus());
			itemDO.setRemark(itemDTO.getRemark());
			itemDO.setModifyTime(date);
			itemDO.setModifyUserId(userId);
			
			this.update(itemDO, false);
			
			String description = itemDTO.getDescription();
			if(StringUtils.isNotBlank(description)){
				ItemDescDO descDO = new ItemDescDO();
				descDO.setDescription(description);
				descDO.setItemId(itemId);
				descDO.setModifyTime(date);
				descDO.setModifyUserId(userId);
				
				itemDescDAO.updateByItemId(descDO);
			}
			
			// 修改图片功能待定
			List<String> listPicUrls = itemDTO.getListPicUrls();
			if (CollectionUtils.isNotEmpty(listPicUrls)) {
				for (String picture : listPicUrls) {
				}

			}
			
			return 1;
		} catch (CommonDAOException | CommonServiceException e) {
			logger.info("插入商品异常", e);
		}
		return 0;
	}
	
}
