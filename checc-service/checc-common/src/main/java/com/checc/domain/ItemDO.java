package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 商品
* @author fengyts Wed Nov 22 11:23:24 CST 2017
*/

public class ItemDO extends BaseDO {

/** 主键 */
private Long id;

/** 商品名称,前端展示名称 */
private String itemTitle;

/** 商品关联分类id,预留字段 */
private Long catetoryId;

/** 商品类型：01-竞拍商品；02-兑换商品 */
private String itemType;

/** 商品状态(是否上架)：0-未上架；1-已上架；2-作废 */
private Integer status;

/** 市场价 */
private Double marketPrice;

/** 备注 */
private String remark;

/** 添加人 */
private Long createUserId;

/** 添加日期 */
private Date createTime;

/** 修改人 */
private Long modifyUserId;

/** 修改日期 */
private Date modifyTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 商品名称,前端展示名称
* @param itemTitle
*/
	public void setItemTitle(String itemTitle) {
	this.itemTitle = itemTitle;
}
/**
* 设置 商品关联分类id,预留字段
* @param catetoryId
*/
	public void setCatetoryId(Long catetoryId) {
	this.catetoryId = catetoryId;
}
/**
* 设置 商品类型：01-竞拍商品；02-兑换商品
* @param itemType
*/
	public void setItemType(String itemType) {
	this.itemType = itemType;
}
/**
* 设置 商品状态(是否上架)：0-未上架；1-已上架；2-作废
* @param status
*/
	public void setStatus(Integer status) {
	this.status = status;
}
/**
* 设置 市场价
* @param marketPrice
*/
	public void setMarketPrice(Double marketPrice) {
	this.marketPrice = marketPrice;
}
/**
* 设置 备注
* @param remark
*/
	public void setRemark(String remark) {
	this.remark = remark;
}
/**
* 设置 添加人
* @param createUserId
*/
	public void setCreateUserId(Long createUserId) {
	this.createUserId = createUserId;
}
/**
* 设置 添加日期
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 设置 修改人
* @param modifyUserId
*/
	public void setModifyUserId(Long modifyUserId) {
	this.modifyUserId = modifyUserId;
}
/**
* 设置 修改日期
* @param modifyTime
*/
	public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 商品名称,前端展示名称
* @return itemTitle
*/
	public String getItemTitle() {
	return itemTitle;
}
/**
* 获取 商品关联分类id,预留字段
* @return catetoryId
*/
	public Long getCatetoryId() {
	return catetoryId;
}
/**
* 获取 商品类型：01-竞拍商品；02-兑换商品
* @return itemType
*/
	public String getItemType() {
	return itemType;
}
/**
* 获取 商品状态(是否上架)：0-未上架；1-已上架；2-作废
* @return status
*/
	public Integer getStatus() {
	return status;
}
/**
* 获取 市场价
* @return marketPrice
*/
	public Double getMarketPrice() {
	return marketPrice;
}
/**
* 获取 备注
* @return remark
*/
	public String getRemark() {
	return remark;
}
/**
* 获取 添加人
* @return createUserId
*/
	public Long getCreateUserId() {
	return createUserId;
}
/**
* 获取 添加日期
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}
/**
* 获取 修改人
* @return modifyUserId
*/
	public Long getModifyUserId() {
	return modifyUserId;
}
/**
* 获取 修改日期
* @return modifyTime
*/
	public Date getModifyTime() {
	return modifyTime;
}

}