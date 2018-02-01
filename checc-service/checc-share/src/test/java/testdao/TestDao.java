package testdao;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.dao.PurchaseApplyDAO;
import com.checc.service.AuctionRecordService;
import com.checc.service.CheccUserService;
import com.checc.service.RefundCurrencyService;

import ng.bayue.exception.CommonDAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestDao {

	@Autowired
	private CheccUserService userService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private RefundCurrencyService refundService;
	@Autowired
	private PurchaseApplyDAO purchaseDao;

	@Test
	public void test() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		ids.add(12L);
		ids.add(13L);
		purchaseDao.updatePurchaseStatusToNotApply(ids);
	}
	

}
