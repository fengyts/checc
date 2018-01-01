package testdao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.domain.UserCurrencyDO;
import com.checc.service.UserCurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestDao {

	@Autowired
	private UserCurrencyService currencyService;

	@Test
	public void test() {
//		int res = currencyService.reduceExchangeCurrency(1l, 1000);
//		System.out.println(res);
		UserCurrencyDO t = currencyService.selectByUserId(2l);
		System.out.println(t.getTotalCurrency());
	}

}
