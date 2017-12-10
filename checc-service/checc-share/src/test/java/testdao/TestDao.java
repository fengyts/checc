package testdao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.service.ItemPictureService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestDao {

	@Autowired
	private ItemPictureService pictureService;

	@Test
	public void test() {
		List<Long> itemIds = new ArrayList<Long>();
		itemIds.add(1L);
		itemIds.add(2L);
		pictureService.selectByItemIds(itemIds);
	
	}

}
