package testdao;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.domain.CheccUserDO;
import com.checc.domain.TopicDO;
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

	@Test
	public void test() {
		CheccUserDO t = new CheccUserDO();
		Page<CheccUserDO> page = userService.queryPageListDynamicAndStartPageSize(t, 1, 5);
		System.out.println(page.getTotalCount());
	}
	

}
