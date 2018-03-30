package com.checc.service.impl.generate;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.checc.service.generate.ExpressageInfoService;

import ng.bayue.util.StringUtils;

public class ExpressageInfoServiceImpl implements ExpressageInfoService {

	private Logger logger = LoggerFactory.getLogger(ExpressageInfoServiceImpl.class);

	private static String kuaidi100_query_url = "https://www.kuaidi100.com/query?type=[0]&postid=[1]";

	@Override
	public String getExpressageInfo(String companyCode, String waybillNo) {
		if (StringUtils.isBlank(companyCode) || StringUtils.isBlank(waybillNo)) {
			return "";
		}
		kuaidi100_query_url = kuaidi100_query_url.replace("[0]", companyCode).replace("[1]", waybillNo);
		try {
			URL url = new URL(kuaidi100_query_url);
			Document document = Jsoup.parse(url, 5000);
			String result = document.text();
			
			
		} catch (Exception e) {
			logger.error("获取物流信息失败:{}", e);
		}
		return null;
	}

}
