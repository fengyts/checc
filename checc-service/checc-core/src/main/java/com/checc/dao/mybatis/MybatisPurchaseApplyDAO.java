package com.checc.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import com.checc.dao.PurchaseApplyDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.PurchaseApplyDO;
import com.checc.dto.PurchaseExchangeStatusDTO;
import com.checc.vo.PurchaseApplyVO;

import ng.bayue.exception.CommonDAOException;

@Component(value="purchaseApplyDAO")
public class MybatisPurchaseApplyDAO extends MybatisBaseDAO implements PurchaseApplyDAO {
	
	private static final String NAMESPACE = "com.checc.domain.PurchaseApplyMapper.MybatisPurchaseApplyDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(PurchaseApplyDO purchaseApplyDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), purchaseApplyDO);
		if (i > 0) {
			return Long.valueOf(purchaseApplyDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(PurchaseApplyDO purchaseApplyDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), purchaseApplyDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), purchaseApplyDO);
	}

	@Override
	public PurchaseApplyDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), purchaseApplyDO);
	}

	@Override
	public List<PurchaseApplyDO> selectDynamic(PurchaseApplyDO purchaseApplyDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), purchaseApplyDO);
	}

	@Override
	public List<PurchaseApplyDO> selectDynamicPageQuery(PurchaseApplyDO purchaseApplyDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), purchaseApplyDO);
	}
	
	@Override
	public int updatePurchaseStatusToNotApply(List<Long> topicItemIds) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_success_auct_purchase"), topicItemIds);
	}

	@Override
	public Integer countAuctionBackend(PurchaseExchangeStatusDTO dto) {
		return getSqlSession().selectOne(getStatement("select_auction_count"), dto);
	}

	@Override
	public List<PurchaseApplyVO> queryPageBackend(PurchaseExchangeStatusDTO dto) {
		return getSqlSession().selectList(getStatement("select_auction_page"), dto);
	}

	@Override
	public Integer countTotalAuction(Long topicItemId) {
		return getSqlSession().selectOne(getStatement("count_total_auction"), topicItemId);
	}

	
}
