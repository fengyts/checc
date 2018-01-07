package testdao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.domain.TopicDO;
import com.checc.service.RefundCurrencyService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestDao {

	@Autowired
	private UserCurrencyService currencyService;
	@Autowired
	private RefundCurrencyService refundCurrencyService;
	@Autowired
	private TopicService topicServie;

	@Test
	public void test() {
		TopicDO topicDO = topicServie.selectPreviousOne("01");
		System.out.println(topicDO.getId());
	}
	

}
