package com.checc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.dao.ItemDescDAO;
import com.checc.domain.ItemDescDO;
import com.checc.service.ItemDescService;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.common.Page;

@Service(value="itemDescService")
public class ItemDescServiceImpl  implements ItemDescService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemDescDAO itemDescDAO;

	@Override
	public Long insert(ItemDescDO itemDescDO) throws CommonServiceException {
		try {
			return itemDescDAO.insert(itemDescDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateById(ItemDescDO itemDescDO) throws CommonServiceException {
//		try {
//			return (Integer) itemDescDAO.updateById(itemDescDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public int update(ItemDescDO itemDescDO,boolean isAllField) throws CommonServiceException {
		try {
			if(isAllField){
				return (Integer) itemDescDAO.update(itemDescDO);
			}else{
				return (Integer) itemDescDAO.updateDynamic(itemDescDO);
			}
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) itemDescDAO.deleteById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ItemDescDO itemDescDO) throws CommonServiceException {
//		try {
//			return (Integer) itemDescDAO.updateDynamic(itemDescDO);
//		}catch(CommonDAOException e){
//			logger.error(e);
//            throw new CommonServiceException(e);
//		}
//	}

	@Override
	public ItemDescDO selectById(Long id) throws CommonServiceException {
		try {
			return itemDescDAO.selectById(id);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemDescDO itemDescDO) throws CommonServiceException {
		try {
			return itemDescDAO.selectCountDynamic(itemDescDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}

	@Override
	public List<ItemDescDO> selectDynamic(ItemDescDO itemDescDO) throws CommonServiceException {
		try {
			return itemDescDAO.selectDynamic(itemDescDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	

	private List<ItemDescDO> selectDynamicPageQuery(ItemDescDO itemDescDO) throws CommonServiceException {
		try {
			return itemDescDAO.selectDynamicPageQuery(itemDescDO);
		}catch(CommonDAOException e){
			logger.error(e);
            throw new CommonServiceException(e);
		}
	}
	
	@Override
	public Page<ItemDescDO> queryPageListDynamic(ItemDescDO itemDescDO) throws CommonServiceException{
		if (itemDescDO != null) {
			Long totalCount = this.selectCountDynamic(itemDescDO);

			Page<ItemDescDO> page = new Page<ItemDescDO>();
			page.setPageNo(itemDescDO.getStartPage());
			page.setPageSize(itemDescDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ItemDescDO> resultList = this.selectDynamicPageQuery(itemDescDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ItemDescDO>();
	}
	
	@Override
	public Page<ItemDescDO> queryPageListDynamicAndStartPageSize(ItemDescDO itemDescDO, Integer startPage, Integer pageSize) throws CommonServiceException {
		if (itemDescDO != null && startPage>0 && pageSize>0) {
			itemDescDO.setStartPage(startPage);
			itemDescDO.setPageSize(pageSize);
			return this.queryPageListDynamic(itemDescDO);
		}
		return new Page<ItemDescDO>();
	}
	
	
}
