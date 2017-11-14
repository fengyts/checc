package testdao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.dao.ItemDAO;

import ng.bayue.exception.CommonDAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-beans.xml" })
public class DaoTest {
	
	@Autowired
	private ItemDAO itemDao;

	@Test
	public void test() {
		try {
			itemDao.selectById(1L);
		} catch (CommonDAOException e) {
			e.printStackTrace();
		}
	}

}
