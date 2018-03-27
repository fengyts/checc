package com.checc.domain;

import ng.bayue.common.BaseDO;

/**
 * 车西西常量
 * 
 * @author fengyts Tue Mar 27 10:16:56 CST 2018
 */

public class CheccConstantDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = -9182394518743612175L;

	/** 主键 */
	private String key;

	/** 常量值 */
	private String value;

	/** 常量类型：01-常量 */
	private String type;

	/** 常量描述 */
	private String desc;

	/**
	 * 设置 主键
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 设置 常量值
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 设置 常量类型：01-常量
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 设置 常量描述
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 获取 主键
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 获取 常量值
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获取 常量类型：01-常量
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 获取 常量描述
	 * 
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

}