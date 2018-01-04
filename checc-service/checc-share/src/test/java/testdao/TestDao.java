package testdao;

import java.util.HashMap;
import java.util.Map;

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
	
	public static void main(String[] args) {
		Map<Integer, String> map1 = new HashMap<Integer, String>();  
        map1.put(11, "11");  
        map1.put(22, "22");  
        long key1 = 11;  
        System.out.println(map1.get(key1));  // null  
          
        Map<Long, String> map2 = new HashMap<Long, String>();  
        map2.put(11L, "11");  
        map2.put(22L, "22");  
        int key2 = 11;  
        System.out.println(map1.get(key2));  // 11  
	}

}
