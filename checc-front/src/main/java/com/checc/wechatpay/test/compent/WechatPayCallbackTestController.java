package com.checc.wechatpay.test.compent;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checc.wechatpay.util.PayConfigUtil;
import com.checc.wechatpay.util.PayToolUtil;
import com.checc.wechatpay.util.XMLUtil4jdom;

@Controller
@RequestMapping({"/wechartpay"})
public class WechatPayCallbackTestController {
	
	/**
	 * 微信平台发起的回调方法，
	 * 调用我们这个系统的这个方法接口，将扫描支付的处理结果告知我们系统
	 * @throws JDOMException
	 * @throws Exception
	 */
	@RequestMapping(value={"/callback"})
	public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws JDOMException, Exception{
	       //读取参数  
	       InputStream inputStream ;  
	       StringBuffer sb = new StringBuffer();  
	       inputStream = request.getInputStream();  
	       String s ;  
	       BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	       while ((s = in.readLine()) != null){  
	           sb.append(s);
	       }
	       in.close();
	       inputStream.close();
	  
	       //解析xml成map  
	       Map<String, String> m = new HashMap<String, String>();  
	       m = XMLUtil4jdom.doXMLParse(sb.toString());  
	        
	       //过滤空 设置 TreeMap  
	       SortedMap<String,String> packageParams = new TreeMap<String,String>();        
	       Iterator<String> it = m.keySet().iterator();  
	       while (it.hasNext()) {  
	           String parameter = (String) it.next();
	           String parameterValue = m.get(parameter);
	            
	           String v = "";  
	           if(null != parameterValue) {
	               v = parameterValue.trim();  
	           }  
	           packageParams.put(parameter, v);  
	       }  
	          
	       // 账号信息  
	       String key = PayConfigUtil.API_KEY; //key  
	  
	       //判断签名是否正确  
	       if(PayToolUtil.isTenpaySign(packageParams)) {  
	           //------------------------------  
	           //处理业务开始  
	           //------------------------------  
	           String resXml = "";  
	           if("SUCCESS".equals((String)packageParams.get("result_code"))){  
	               // 这里是支付成功  
	               //////////执行自己的业务逻辑////////////////  
	               String mch_id = (String)packageParams.get("mch_id");  
	               String openid = (String)packageParams.get("openid");  
	               String is_subscribe = (String)packageParams.get("is_subscribe");  
	               String out_trade_no = (String)packageParams.get("out_trade_no");  
	                
	               String total_fee = (String)packageParams.get("total_fee");  
	                
	               //////////执行自己的业务逻辑//////////////// 
	               //暂时使用最简单的业务逻辑来处理：只是将业务处理结果保存到session中
	               //（根据自己的实际业务逻辑来调整，很多时候，我们会操作业务表，将返回成功的状态保留下来）
	               request.getSession().setAttribute("_PAY_RESULT", "OK");
	                
	               System.out.println("支付成功");  
	               //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
	               resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
	                       + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
	                  
	           } else {
	               resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
	                       + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
	           }
	           //------------------------------  
	           //处理业务完毕  
	           //------------------------------  
	           BufferedOutputStream out = new BufferedOutputStream(  
	                   response.getOutputStream());  
	           out.write(resXml.getBytes());  
	           out.flush();  
	           out.close();  
	       } else{  
	         System.out.println("通知签名验证失败");  
	       }
	          
	}
}
