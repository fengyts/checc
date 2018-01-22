package test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.service.RefundCurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
public class CurrencyRefundTest {
	
	@Autowired
	private RefundCurrencyService refundService;
	
	@Test
	public void test(){
		refundService.refundCurrency();
	}

}
