package com.checc.service.impl.generate;

import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.checc.dto.ExpressageInfoDTO;
import com.checc.service.generate.ExpressageInfoService;

import ng.bayue.util.StringUtils;

@Service
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
			return result;

		} catch (Exception e) {
			logger.error("获取物流信息失败:{}", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * 解析物流信息
	 * </pre>
	 *
	 * @param expressInfoJson 快递100网站返回物流数据(json格式)
	 * @return
	 */
	private ExpressageInfoDTO parseJsonExpressInfo(String expressInfoJson) {
		ExpressageInfoDTO dto = new ExpressageInfoDTO();
		try {
			JSONObject json = JSONObject.parseObject(expressInfoJson);
			String status = json.getString("status");
			JSONArray arry = json.getJSONArray("data");
			String arrayStr = json.getString("data");

			dto.setStatus(status);
			if (!"200".equals(status) || arry.isEmpty()) {
				dto.setMessage("抱歉,暂时未查询到结果");
				return dto;
			}
			List<ExpressageInfoDTO.ExpressData> datas = JSONArray.parseArray(arrayStr,
					ExpressageInfoDTO.ExpressData.class);
			dto.setDatas(datas);
			dto.setMessage("success");
		} catch (Exception e) {
			logger.info("解析物流信息异常", e);
		}
		return dto;
	}

	@Override
	public ExpressageInfoDTO parseExpressInfo(String companyCode, String waybillNo) {
		if (StringUtils.isBlank(companyCode) || StringUtils.isBlank(waybillNo)) {
			return null;
		}
		String expressInfoJson = getExpressageInfo(companyCode, waybillNo);
		ExpressageInfoDTO dto = parseJsonExpressInfo(expressInfoJson);
		return dto;
	}

}
