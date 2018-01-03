package com.checc.scheduler.jobs;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.checc.scheduler.SynchronizedJob;
import com.checc.service.RefundCurrencyService;

import ng.bayue.service.RedisCacheService;

public class RefundCurrencyJob /* extends SynchronizedJob */ {

	private static final Logger logger = LoggerFactory.getLogger(SynchronizedJob.class);

	private static final String JOB_KEY = "LOCK_REFUND_CURRENCY";

	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;

	@Autowired
	private RefundCurrencyService refundCurrencyService;

	public void doJob() {
		boolean lock = redisCacheService.lock(JOB_KEY);
		if (!lock) {
			return;
		}
		try {
			logger.info("专题商品用户西币回退	定时任务开始...");

			refundCurrencyService.refundCurrency();

			logger.info("专题商品用户西币回退	定时任务完成");
		} catch (Exception e) {
			logger.error("订单状态补偿异常 {}", e.getMessage());
		} finally {
			redisCacheService.unLock(JOB_KEY);
		}
	}

	// @Override
	// protected void jobImpl() {
	// doJob();
	// }

	public void close() {
		redisCacheService.unLock(JOB_KEY); // 释放锁
	}

}
