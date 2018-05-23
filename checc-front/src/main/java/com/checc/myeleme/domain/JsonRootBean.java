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
public class JsonRootBean {

	private Seller seller;
	private List<Goods> goods;
	private List<Ratings> ratings;

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

}