package com.checc.service;

import ng.bayue.exception.CommonServiceException;

public interface RefundCurrencyService {
	
	/**
	 * <pre>
	 * 竞拍专题结束，用户西币回退
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	int refundCurrency() throws CommonServiceException;

}
