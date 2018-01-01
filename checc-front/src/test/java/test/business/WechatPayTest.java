package test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.wechatpay.compent.WechatPayAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
public class WechatPayTest {
	
	@Autowired
	private WechatPayAO wechatPayAO;
	
	@Test
	public void payOrderQueryTest(){
		String out_trade_no = "877249130680001470";
		wechatPayAO.queryOrderStatus(out_trade_no, 1l);
		
        String orderQueryResultXml = "<xml>"
        		+ "<return_code><![CDATA[SUCCESS]]></return_code>"
        		+ "<return_msg><![CDATA[OK]]></return_msg>"
        		+ "<appid><![CDATA[wxeec85623859fc30e]]></appid>"
        		+ "<mch_id><![CDATA[1490875382]]></mch_id>"
        		+ "<nonce_str><![CDATA[yjimuzrE3ajCcpG7]]></nonce_str>"
        		+ "<sign><![CDATA[401387A8A34E2D2DBD5FB27C85246E15]]></sign>"
        		+ "<result_code><![CDATA[SUCCESS]]></result_code>"
        		+ "<openid><![CDATA[o8qMy0zIbp3UxBGDa__Aqc5ks92o]]></openid>"
        		+ "<is_subscribe><![CDATA[Y]]></is_subscribe>"
        		+ "<trade_type><![CDATA[NATIVE]]></trade_type>"
        		+ "<bank_type><![CDATA[CFT]]></bank_type>"
        		+ "<total_fee>100</total_fee><fee_type><![CDATA[CNY]]></fee_type>"
        		+ "<transaction_id><![CDATA[4200000038201712315439426986]]></transaction_id>"
        		+ "<out_trade_no><![CDATA[877249130680001470]]></out_trade_no>"
        		+ "<attach><![CDATA[1]]></attach>"
        		+ "<time_end><![CDATA[20171231205531]]></time_end>"
        		+ "<trade_state><![CDATA[SUCCESS]]></trade_state>"
        		+ "<cash_fee>100</cash_fee>"
        		+ "</xml>";
		
	}
	
	/**
	 * <pre>
	 * 微信充值回调测试
	 * </pre>
	 *
	 */
	@Test 
	public void payOrderCallBackTest(){
		String paramsXmlStr = "<xml>"
				+ "<appid><![CDATA[wxeec85623859fc30e]]></appid>"
				+ "<attach><![CDATA[1]]></attach>"
				+ "<bank_type><![CDATA[CFT]]></bank_type>"
				+ "<cash_fee><![CDATA[100]]></cash_fee>"
				+ "<fee_type><![CDATA[CNY]]></fee_type>"
				+ "<is_subscribe><![CDATA[Y]]></is_subscribe>"
				+ "<mch_id><![CDATA[1490875382]]></mch_id>"
				+ "<nonce_str><![CDATA[c97e73dbdeb14d80921c75159720f0f5]]></nonce_str>"
				+ "<openid><![CDATA[o8qMy0zIbp3UxBGDa__Aqc5ks92o]]></openid>"
				+ "<out_trade_no><![CDATA[877249130680001470]]></out_trade_no>"
				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
				+ "<return_code><![CDATA[SUCCESS]]></return_code>"
				+ "<sign><![CDATA[BF8C6EC383DFD2C107997DD3F1BD04E3]]></sign>"
				+ "<time_end><![CDATA[20171231205531]]></time_end>"
				+ "<total_fee>100</total_fee>"
				+ "<trade_type><![CDATA[NATIVE]]></trade_type>"
				+ "<transaction_id><![CDATA[4200000038201712315439426986]]></transaction_id>"
				+ "</xml>";
		String paramsMapStr = "{appid=wxeec85623859fc30e, attach=1, bank_type=CFT, cash_fee=100, "
				+ "fee_type=CNY, is_subscribe=Y, mch_id=1490875382, nonce_str=c97e73dbdeb14d80921c75159720f0f5, "
				+ "openid=o8qMy0zIbp3UxBGDa__Aqc5ks92o, out_trade_no=877249130680001470, result_code=SUCCESS, "
				+ "return_code=SUCCESS, sign=BF8C6EC383DFD2C107997DD3F1BD04E3, time_end=20171231205531, "
				+ "total_fee=100, trade_type=NATIVE, transaction_id=4200000038201712315439426986}";
		
		wechatPayAO.callBack(paramsXmlStr);
		
	}

}
