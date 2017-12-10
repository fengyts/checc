package com.checc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ItemPictureDAO;
import com.checc.domain.ItemPictureDO;
import com.checc.service.ItemPictureService;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="itemPictureService")
public class ItemPictureServiceImpl  implements ItemPictureService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemPictureDAO itemPictureDAO;

	@Override
	public Long insert(ItemPictureDO itemPictureDO) throws CommonServiceException {
		try {
			return itemPictureDAO.insert(itemPictureDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(ItemPictureDO itemPictureDO) throws CommonServiceException {
//		try {
//			return (Integer) itemPictureDAO.updateById(itemPictureDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(ItemPictureDO itemPictureDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) itemPictureDAO.update(itemPictureDO);
			}else{
				return (Integer) itemPictureDAO.updateDynamic(itemPictureDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) itemPictureDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ItemPictureDO itemPictureDO) throws CommonServiceException {
//		try {
//			return (Integer) itemPictureDAO.updateDynamic(itemPictureDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public ItemPictureDO selectById(Long id) throws CommonServiceException {
		try {
			return itemPictureDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemPictureDO itemPictureDO) throws CommonServiceException {
		try {
			return itemPictureDAO.selectCountDynamic(itemPictureDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<ItemPictureDO> selectDynamic(ItemPictureDO itemPictureDO) throws CommonServiceException {
		try {
			return itemPictureDAO.selectDynamic(itemPictureDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<ItemPictureDO> selectDynamicPageQuery(ItemPictureDO itemPictureDO) throws CommonServiceException {
		try {
			return itemPictureDAO.selectDynamicPageQuery(itemPictureDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<ItemPictureDO> queryPageListDynamic(ItemPictureDO itemPictureDO) throws CommonServiceException{
		if (itemPictureDO != null) {
			Long totalCount = this.selectCountDynamic(itemPictureDO);

			Page<ItemPictureDO> page = new Page<ItemPictureDO>();
			page.setPageNo(itemPictureDO.getStartPage());
			page.setPageSize(itemPictureDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ItemPictureDO> resultList = this.selectDynamicPageQuery(itemPictureDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ItemPictureDO>();
	}
	
	@Override
	public Page<ItemPictureDO> queryPageListDynamicAndStartPageSize(ItemPictureDO itemPictureDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (itemPictureDO != null && startPage>0 && pageSize>0) {
			itemPictureDO.setStartPage(startPage);
			itemPictureDO.setPageSize(pageSize);
			return this.queryPageListDynamic(itemPictureDO);
		}
		return new Page<ItemPictureDO>();
	}

	@Override
	public List<ItemPictureDO> selectByItemIds(List<Long> itemIds) {
		if(CollectionUtils.isEmpty(itemIds)){
			return new ArrayList<ItemPictureDO>();
		}
		return itemPictureDAO.selectByItemIds(itemIds);
	}
	
	
}
