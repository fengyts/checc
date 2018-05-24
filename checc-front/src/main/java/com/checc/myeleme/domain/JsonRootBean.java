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
public class JsonRootBean extends BaseDomain {

	private static final long serialVersionUID = -8483007946042819483L;

	private Seller seller;
	private List<Good> goods;
	private List<Rating> ratings;

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

}