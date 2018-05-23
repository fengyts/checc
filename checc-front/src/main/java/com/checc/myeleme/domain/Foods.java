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
public class Foods extends BaseDomain {

	private static final long serialVersionUID = 852446523270458501L;
	
	private String name;
	private Integer price;
	private Double oldPrice;
	private String description;
	private Integer sellCount;
	private Integer rating;
	private String info;
	private List<Ratings> ratings;
	private String icon;
	private String image;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

}