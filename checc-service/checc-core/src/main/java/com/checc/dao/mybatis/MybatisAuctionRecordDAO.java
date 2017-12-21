package com.checc.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.checc.dao.AuctionRecordDAO;
import com.checc.dao.base.MybatisBaseDAO;
import com.checc.domain.AuctionRecordDO;

import ng.bayue.exception.CommonDAOException;

@Component(value="auctionRecordDAO")
public class MybatisAuctionRecordDAO extends MybatisBaseDAO implements AuctionRecordDAO {
	
	private static final String NAMESPACE = "com.checc.domain.AuctionRecordMapper.MybatisAuctionRecordDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(AuctionRecordDO auctionRecordDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), auctionRecordDO);
		if (i > 0) {
			return Long.valueOf(auctionRecordDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(AuctionRecordDO auctionRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), auctionRecordDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(AuctionRecordDO auctionRecordDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), auctionRecordDO);
	}

	@Override
	public AuctionRecordDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(AuctionRecordDO auctionRecordDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), auctionRecordDO);
	}

	@Override
	public List<AuctionRecordDO> selectDynamic(AuctionRecordDO auctionRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), auctionRecordDO);
	}

	@Override
	public List<AuctionRecordDO> selectDynamicPageQuery(AuctionRecordDO auctionRecordDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), auctionRecordDO);
	}

	@Override
	public AuctionRecordDO selectLatestAuction(Long topicItemId) {
		return getSqlSession().selectOne(getStatement("select_latest"), topicItemId);
	}
	
	@Override
	public Long selectUCAuctionCount(Map<String, Object> param) {
		return getSqlSession().selectOne(getStatement("select_uc_count"), param);
	}
	
	@Override
	public List<AuctionRecordDO> selectUCAuctionList(Map<String, Object> param) {
		return getSqlSession().selectOne(getStatement("select_uc_list"), param);
	}

}
