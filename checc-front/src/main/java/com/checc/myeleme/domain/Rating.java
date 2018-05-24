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
public class Rating extends BaseDomain {

	private static final long serialVersionUID = -4655406634256634710L;

	private String username;
	private Long rateTime;
	private Integer deliveryTime;
	private Double score;
	private Integer rateType;
	private String text;
	private String avatar;
	private List<String> recommend;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getRateTime() {
		return rateTime;
	}

	public void setRateTime(Long rateTime) {
		this.rateTime = rateTime;
	}

	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<String> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<String> recommend) {
		this.recommend = recommend;
	}

}