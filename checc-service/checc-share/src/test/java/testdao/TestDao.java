package testdao;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.domain.CheccUserDO;
import com.checc.domain.TopicDO;
import com.checc.dto.refund.RefundTopicDTO;
import com.checc.service.AuctionRecordService;
import com.checc.service.CheccUserService;
import com.checc.service.RefundCurrencyService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;
import com.checc.vo.CheccUserVO;

import ng.bayue.common.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestDao {

	@Autowired
	private CheccUserService userService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private RefundCurrencyService refundService;

	@Test
	public void test() {
		refundService.refundCurrency();
	}
	

}
