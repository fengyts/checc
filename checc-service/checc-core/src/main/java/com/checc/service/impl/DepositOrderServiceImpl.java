package com.checc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import bean.PayOrderNoGenerator;

import com.checc.dao.DepositConfigDAO;
import com.checc.dao.DepositOrderDAO;
import com.checc.domain.DepositConfigDO;
import com.checc.domain.DepositOrderDO;
import com.checc.service.DepositOrderService;

import ng.bayue.exception.CommonDAOException;
import ng.bayue.exception.CommonServiceException;
import ng.bayue.service.RedisCacheService;
import ng.bayue.util.StringUtils;
import ng.bayue.common.CommonMessages;
import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.Page;

@Service(value = "depositOrderService")
public class DepositOrderServiceImpl implements DepositOrderService {

	private Log logger = LogFactory.getLog(this.getClass());

	/** 支付下单用户锁前缀 */
	private static final String LOCK_PAY_ORDER_KEY = "LOCK_PAY_ORDER_";

	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;

	@Autowired
	private DepositOrderDAO depositOrderDAO;
	@Autowired
	private DepositConfigDAO depositConfigDAO;

	@Override
	public Long insert(DepositOrderDO depositOrderDO) throws CommonServiceException {
		try {
			return depositOrderDAO.insert(depositOrderDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int update(DepositOrderDO depositOrderDO, boolean isAllField)
			throws CommonServiceException {
		try {
			if (isAllField) {
				return (Integer) depositOrderDAO.update(depositOrderDO);
			} else {
				return (Integer) depositOrderDAO.updateDynamic(depositOrderDO);
			}
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws CommonServiceException {
		try {
			return (Integer) depositOrderDAO.deleteById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public DepositOrderDO selectById(Long id) throws CommonServiceException {
		try {
			return depositOrderDAO.selectById(id);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DepositOrderDO depositOrderDO) throws CommonServiceException {
		try {
			return depositOrderDAO.selectCountDynamic(depositOrderDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public List<DepositOrderDO> selectDynamic(DepositOrderDO depositOrderDO)
			throws CommonServiceException {
		try {
			return depositOrderDAO.selectDynamic(depositOrderDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	private List<DepositOrderDO> selectDynamicPageQuery(DepositOrderDO depositOrderDO)
			throws CommonServiceException {
		try {
			return depositOrderDAO.selectDynamicPageQuery(depositOrderDO);
		} catch (CommonDAOException e) {
			logger.error(e);
			throw new CommonServiceException(e);
		}
	}

	@Override
	public Page<DepositOrderDO> queryPageListDynamic(DepositOrderDO depositOrderDO)
			throws CommonServiceException {
		if (depositOrderDO != null) {
			Long totalCount = this.selectCountDynamic(depositOrderDO);

			Page<DepositOrderDO> page = new Page<DepositOrderDO>();
			page.setPageNo(depositOrderDO.getStartPage());
			page.setPageSize(depositOrderDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<DepositOrderDO> resultList = this.selectDynamicPageQuery(depositOrderDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<DepositOrderDO>();
	}

	@Override
	public Page<DepositOrderDO> queryPageListDynamicAndStartPageSize(DepositOrderDO depositOrderDO,
			Integer startPage, Integer pageSize) throws CommonServiceException {
		if (depositOrderDO != null && startPage > 0 && pageSize > 0) {
			depositOrderDO.setStartPage(startPage);
			depositOrderDO.setPageSize(pageSize);
			return this.queryPageListDynamic(depositOrderDO);
		}
		return new Page<DepositOrderDO>();
	}

	@Override
	public DepositOrderDO selectByOrderNo(String orderNo) throws CommonServiceException {
		if (StringUtils.isBlank(orderNo)) {
			return null;
		}
		try {
			return depositOrderDAO.selectByOrderNo(orderNo);
		} catch (CommonDAOException e) {
			logger.error("根据订单编号获取支付订单异常:{}", e);
		}
		return null;
	}

	@Override
	@Transactional
	public CommonResultMessage productPayOrder(DepositOrderDO dpOrder)
			throws CommonServiceException {
		String payOrderNo = generateOrderNo();
		dpOrder.setCreateTime(new Date());
		dpOrder.setModifyTime(new Date());
		dpOrder.setOrderNo(payOrderNo);

		Long userId = dpOrder.getUserId();
		if (null == userId || userId.longValue() <= 0l) {
			return CommonResultMessage.failure(CommonMessages.UNLOGIN);
		}

		Long res = 0L;
		String orderLock = LOCK_PAY_ORDER_KEY + userId;
		try {
			// 同一时间只能有一个用户下单
			if (redisCacheService.lock(orderLock)) {
				Long discountId = dpOrder.getDiscountId();
				// 校验 金额有效性, 防止篡改数据
				if (discountId.longValue() != 0l) { // 非用户输入的金额校验,防止篡改数据
					DepositConfigDO dpConf = depositConfigDAO.selectById(discountId);
					if (null == dpConf) {
						return CommonResultMessage.failure(CommonResultCode.SystemError.REQ_ERROR.desc);
					}
					Integer dpAmt = dpOrder.getDepositAmount();
					if (dpAmt.intValue() != dpConf.getDepositAmount().intValue()) {
						return CommonResultMessage
								.failure(CommonResultCode.SystemError.REQ_ERROR.desc);
					}
				}

				res = this.insert(dpOrder);
				if (res <= 0l) {
					throw new CommonServiceException("保存用户支付订单失败");
				}
			}
		} catch (Exception e) {
			logger.error("insert deposit order failure, exception ：{}", e);
			return CommonResultMessage
					.failure(CommonResultCode.BusinessError.BUSINESS_PROCESS_ERROR.desc);
		} finally {
			try {
				redisCacheService.unLock(orderLock);
			} catch (Exception e) {
				logger.error("redis exception: 用户下单释放下单锁异常：{}", e);
				throw new CommonServiceException("保存用户支付订单失败");
			}
		}
		return new CommonResultMessage(dpOrder);
	}

	private String generateOrderNo() {
		PayOrderNoGenerator generator = new PayOrderNoGenerator();
		String payOrderNo = generator.generatePayOrderNo();
		return payOrderNo;
	}

}
