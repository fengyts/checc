package com.checc.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checc.service.ExchangeOrderStatusService;
import com.checc.vo.front.ExchangeOrderStatusVO;

@Service
public class ExchangeOrderStatusAO {

	@Autowired
	private ExchangeOrderStatusService exchangeOrderStatusService;

	public ExchangeOrderStatusVO selectExchangeOrderDetails(Long recordId) {
		ExchangeOrderStatusVO vo = exchangeOrderStatusService.selectExchangeOrderDetails(recordId);

		return null != vo ? vo : new ExchangeOrderStatusVO();
	}

}
