package com.checc.service.generate;

/**
 * <pre>
 * 获取物流信息
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ExpressageInfoService.java, v 0.1 2018年3月30日 下午5:11:48 lenovopc Exp $
 */
public interface ExpressageInfoService {
	
	/**
	 * <pre>
	 * 查询物流公司信息,爬取快递100网站的信息
	 * </pre>
	 *
	 * @param companyCode: 物流公司编码
	 * @param waybillNo：物流运单号
	 * @return
	 */
	String getExpressageInfo(String companyCode, String waybillNo);
	
}
