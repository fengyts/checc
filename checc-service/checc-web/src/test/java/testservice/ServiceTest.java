package testservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.service.ItemService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-beans.xml" })
public class ServiceTest {
	
	@Autowired
	private ItemService itemService;
	
	@Test
	public void test() {
		itemService.selectById(1L);
	}

}
