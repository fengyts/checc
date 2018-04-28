package testjava;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ng.bayue.constants.CharsetConstant;
import ng.bayue.util.StringUtils;

public class OnlineReader {
	
//	private static final String CHARSET = CharsetConstant.GBK;
	private static final String CHARSET = CharsetConstant.UTF8;
	
//	public static final String url = "http://www.biqukan.com/1_1680/18577593.html";
	public static final String url = "https://www.xxbiquge.com/76_76060/120394.html";
	
	public static void read(){
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			HttpEntity entityParams = new UrlEncodedFormEntity(parameters, CHARSET);
			post.setEntity(entityParams);
			post.setHeader("Content-Type", "text/html;charset=UTF-8");
			HttpResponse response = client.execute(post);
			HttpEntity resEntity = response.getEntity();
			InputStream is = resEntity.getContent();
			
			
//			String content = EntityUtils.toString(resEntity);
//			System.out.println(content);
			
			StringBuilder resultData = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSET));
			String line = null;
			String nextPage = "";
			while(null != (line = br.readLine())){
				resultData.append(line).append("//r//n"); 
				System.out.println(line);
				if(line.startsWith("var next_page = \"/1_1680/")){
					nextPage = line.substring(25, line.lastIndexOf("."));
				}
				if(StringUtils.isNotBlank(nextPage)){
					if(line.startsWith("var nextpage")){
						nextPage = line.substring(24, line.lastIndexOf("."));
					}
				}
				if(line.startsWith("<h1>第")){
					System.out.println(line);
				}
				if(line.startsWith("<div id=\"content\"")){
					String t = line.replaceAll("<br /><br />", "\r\n");
					System.out.println(t);
					System.out.println("nextPage:" + nextPage);
					break;
				}
			}
			
			/*
			String regex = "<div id=\"content\" class=\"showtxt\">.*?<div class=\"page_chapter\">";
			Pattern pattern = Pattern.compile(regex);
			String str = resultData.toString();
			Matcher matcher = pattern.matcher(str);
			while(matcher.find()){
				String r = matcher.group();
				System.out.println(r);
			}
			*/
			
			
//			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void read1 (){
		try {
//			String url = "https://www.qu.la/book/236/187807.html";
			URL sourceLink = new URL(url);
			Document html = Jsoup.parse(sourceLink, 5000);
//			System.out.println(html.toString());
			Element h1 = html.getElementsByTag("h1").get(0);
			String title = h1.text();
			System.out.println(title);
			Element contentDiv = html.getElementById("content");
			String contentStr = contentDiv.html();
//			System.out.println(contentStr);
			contentStr = contentStr.replaceAll("<br>&nbsp;&nbsp;&nbsp;&nbsp;", "").replaceAll("\n<br>", "");
//			contentStr = contentStr.replaceAll("<br>　　\n<br>　　&nbsp;&nbsp;&nbsp;&nbsp;", "");
			BufferedReader br = new BufferedReader(new StringReader(contentStr));
			String ctx = "";
			StringBuilder sb = new StringBuilder();
			while (null != (ctx = br.readLine())) {
				int len = ctx.length();
//				if (len > 100) {
//					String t = ctx.substring(100);
//					ctx = ctx.substring(0, 100) + "\n" + t;
//				}
				int l = len / 110;
				if (len > 110) {
					StringBuilder st = new StringBuilder(ctx);
					for (int j = 1; j <= l; j++) {
						st.insert(110 * j, "\n");
					}
					sb.append(st).append("\n");
				} else {
					sb.append(ctx).append("\n");
				}
				
			}
			System.out.println(sb.toString());
			
			String line = "";
			
			Elements js = html.getElementsByTag("script");//("var nextpage=");
			Element e = js.last();
//			Element e = js.get(js.size()-2);
			br = new BufferedReader(new StringReader(e.html()));
			while(null != (line = br.readLine())){
				if(line.startsWith("var nextpage=")){
					line = line.substring(21, line.lastIndexOf("."));
//					line = line.substring(14, line.lastIndexOf("."));
					break;
				}
			}
			System.out.println("nextPage:" + line);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		read1();
	}

}
