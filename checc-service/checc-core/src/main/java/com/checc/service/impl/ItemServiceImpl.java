package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ItemDAO;
import com.checc.domain.ItemDO;
import com.checc.service.ItemService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="itemService")
public class ItemServiceImpl  implements ItemService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemDAO itemDAO;

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
	
	
}
