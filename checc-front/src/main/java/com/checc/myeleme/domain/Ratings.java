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
public class Ratings {

	private String username;
	private Long rateTime;
	private Integer deliveryTime;
	private Double score;
	private Integer rateType;
	private String text;
	private String avatar;
	private List<String> recommend;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setRateTime(long rateTime) {
		this.rateTime = rateTime;
	}

	public long getRateTime() {
		return rateTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public void setRateType(int rateType) {
		this.rateType = rateType;
	}

	public int getRateType() {
		return rateType;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setRecommend(List<String> recommend) {
		this.recommend = recommend;
	}

	public List<String> getRecommend() {
		return recommend;
	}

}