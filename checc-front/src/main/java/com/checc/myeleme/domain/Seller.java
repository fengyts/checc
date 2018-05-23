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
public class Seller extends BaseDomain {

	private static final long serialVersionUID = -2472270702535576117L;

	private String name;
	private String description;
	private Integer deliveryTime;
	private Double score;
	private Double serviceScore;
	private Double foodScore;
	private Double rankRate;
	private Double minPrice;
	private Double deliveryPrice;
	private Integer ratingCount;
	private Integer sellCount;
	private String bulletin;
	private List<Supports> supports;
	private String avatar;
	private List<String> pics;
	private List<String> infos;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getScore() {
		return score;
	}

	public void setServiceScore(double serviceScore) {
		this.serviceScore = serviceScore;
	}

	public double getServiceScore() {
		return serviceScore;
	}

	public void setFoodScore(double foodScore) {
		this.foodScore = foodScore;
	}

	public double getFoodScore() {
		return foodScore;
	}

	public void setRankRate(double rankRate) {
		this.rankRate = rankRate;
	}

	public double getRankRate() {
		return rankRate;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setSupports(List<Supports> supports) {
		this.supports = supports;
	}

	public List<Supports> getSupports() {
		return supports;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setInfos(List<String> infos) {
		this.infos = infos;
	}

	public List<String> getInfos() {
		return infos;
	}

}