package testdao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.service.RefundCurrencyService;
import com.checc.service.UserCurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestDao {

	@Autowired
	private UserCurrencyService currencyService;
	@Autowired
	private RefundCurrencyService refundCurrencyService;

	@Test
	public void test() {
//		Map<String, Integer> refundList = new HashMap<String, Integer>();
//		refundList.put("1", 100);
//		
//		int res = currencyService.refundCurrency(refundList);
//		System.out.println(res);
		
		refundCurrencyService.refundCurrency();
	}
	

}
