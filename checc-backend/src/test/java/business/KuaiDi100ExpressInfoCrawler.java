package business;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.checc.domain.ExpressInfoDO;
import com.checc.service.ExpressInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context.xml" })
public class KuaiDi100ExpressInfoCrawler {

	@Autowired
	private ExpressInfoService expressInfoService;

	private static final String kuaiDi100Url = "https://www.kuaidi100.com/all/";
	
	/** 常用快递公司列表 */
	private static Map<String, String> commonlyUsed = new HashMap<String, String>();
	
	static {
		commonlyUsed.put("htky", "百世快递");
		commonlyUsed.put("dbwl", "德邦物流");
		commonlyUsed.put("ems", "EMS快递");
		commonlyUsed.put("sf", "顺丰速运");
		commonlyUsed.put("st", "申通快递");
		commonlyUsed.put("yt", "圆通速递");
		commonlyUsed.put("yd", "韵达快递");
		commonlyUsed.put("yzgn", "邮政国内");
		commonlyUsed.put("zt", "中通快递");
		commonlyUsed.put("zjs", "宅急送");
	}

	@Test
	public void test() {
		try {
			URL url = new URL(kuaiDi100Url);
			Document document = Jsoup.parse(url, 3000);
			Elements list1 = document.getElementsByClass("column-1");
			Elements list2 = document.getElementsByClass("column-2");
			Element e1 = list1.get(0);
			Element e2 = list2.get(0);
			Elements dls = e1.getElementsByTag("dl");
			dls.addAll(e2.getElementsByTag("dl"));
			System.out.println(dls.toString());
			
			List<ExpressInfoDO> datas = new ArrayList<ExpressInfoDO>();
			for(Element e : dls){
				String initial = e.getElementsByTag("dt").get(0).text();
				Elements dds = e.getElementsByTag("dd");
				for(Element dd : dds){
					Elements as = dd.getElementsByTag("a");
					for(Element a : as){
						String tagA = a.attr("href");
						tagA = tagA.replaceAll("\\s", "");
						//获取公司编码
						Connection connect = Jsoup.connect(tagA);
						Document cpDoc = connect.get();
						Element cpE = cpDoc.getElementById("companyCode");
						String companyCode = cpE.attr("value");
						
						// 公司编码简码
						String briefCode = tagA.substring(tagA.lastIndexOf("/") + 1, tagA.lastIndexOf("."));
						boolean isCommonlyUsed = false;
						if(commonlyUsed.containsKey(briefCode)){
							isCommonlyUsed = true;
						}
						
						ExpressInfoDO express = new ExpressInfoDO();
						express.setInitial(initial);
						express.setBriefCode(briefCode);
						express.setCompanyCode(companyCode);
						express.setName(a.text());
						express.setIsCommonlyUsed(isCommonlyUsed);
						express.setCreateTime(new Date());
						express.setModifyTime(new Date());
						
						datas.add(express);
						
						// 插入数据库
						expressInfoService.insert(express);
					}
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
//			String us = "https://www.kuaidi100.com/chaxun?com=zhongtong&nu=479747916226";
//			String us = "https://www.kuaidi100.com/query?type=zhongtong&postid=479747916226&id=1&valicode=&temp=0.6520891530983083";
//			String us = "https://www.kuaidi100.com/query?type=zhongtong&postid=479747916226";
			String us = "https://www.kuaidi100.com/query?type=huitongkuaidi&postid=71262523919018";
			URL url = new URL(us);
			Document document = Jsoup.parse(url, 3000);
			System.out.println(document.html());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
