package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 快递100快递公司大全
 * 
 * @author fengyts Thu Mar 29 14:35:07 CST 2018
 */

public class ExpressInfoDO extends BaseDO {

	private static final long serialVersionUID = 3001827535105721367L;

	/** 主键 */
	private Long id;

	/** 快递公司名称 */
	private String name;

	/** 快递公司编码简码 */
	private String briefCode;

	/** 快递公司编码，用于查询 */
	private String companyCode;

	/** 公司拼音首字母 */
	private String initial;

	/** 是否是常用公司：0-不是常用；1-常用 */
	private Boolean isCommonlyUsed;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 快递公司名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置 快递公司编码简码
	 * 
	 * @param briefCode
	 */
	public void setBriefCode(String briefCode) {
		this.briefCode = briefCode;
	}

	/**
	 * 设置 快递公司编码，用于查询
	 * 
	 * @param companyCode
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * 设置 公司拼音首字母
	 * 
	 * @param initial
	 */
	public void setInitial(String initial) {
		this.initial = initial;
	}

	/**
	 * 设置 是否是常用公司：0-不是常用；1-常用
	 * 
	 * @param isCommonlyUsed
	 */
	public void setIsCommonlyUsed(Boolean isCommonlyUsed) {
		this.isCommonlyUsed = isCommonlyUsed;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取 主键
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 获取 快递公司名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 快递公司编码简码
	 * 
	 * @return briefCode
	 */
	public String getBriefCode() {
		return briefCode;
	}

	/**
	 * 获取 快递公司编码，用于查询
	 * 
	 * @return companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * 获取 公司拼音首字母
	 * 
	 * @return initial
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * 获取 是否是常用公司：0-不是常用；1-常用
	 * 
	 * @return isCommonlyUsed
	 */
	public Boolean getIsCommonlyUsed() {
		return isCommonlyUsed;
	}

	/**
	 * 获取 创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}