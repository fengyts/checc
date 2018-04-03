package com.checc.dto;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 物流信息
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ExpressageInfoDTO.java, v 0.1 2018年4月2日 上午10:34:21 lenovopc Exp
 *          $
 */
public class ExpressageInfoDTO extends BaseDTO {

	private static final long serialVersionUID = 2264024819431342L;
	
	/** 发货时间 */
	private Date shipmentsTime;
	
	/** 物流公司名称 */
	private String company;
	/** 物流公司编码 */
	private String companyCode;
	/** 快递单号 */
	private String waybillNo;

	/** 物流查询结果状态码：200-获取成功 */
	private String status;
	private String message;
	private List<ExpressData> datas;

	/**
	 * <pre>
	 * 物流信息
	 * </pre>
	 *
	 * @author lenovopc
	 * @version $Id: ExpressageInfoDTO.java, v 0.1 2018年4月2日 上午10:39:44 lenovopc
	 *          Exp $
	 */
	public static class ExpressData {
		/** 时间 */
		private Date time;
		/** 物流信息 */
		private String context;

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public String getContext() {
			return context;
		}

		public void setContext(String context) {
			this.context = context;
		}

	}

	public Date getShipmentsTime() {
		return shipmentsTime;
	}

	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ExpressData> getDatas() {
		return datas;
	}

	public void setDatas(List<ExpressData> datas) {
		this.datas = datas;
	}

}
