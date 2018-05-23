/**
 * Copyright 2018 bejson.com 
 */
package com.checc.myeleme.domain;

import java.util.List;

/**
 * Auto-generated: 2018-05-23 14:53:49
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Goods extends BaseDomain {

	private static final long serialVersionUID = -1877769273073172633L;
	
	private String name;
	private Integer type;
	private List<Foods> foods;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setFoods(List<Foods> foods) {
		this.foods = foods;
	}

	public List<Foods> getFoods() {
		return foods;
	}

}