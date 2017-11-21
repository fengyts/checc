package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 商品图片
* @author fengyts Tue Nov 21 13:31:45 CST 2017
*/

public class ItemPictureDO extends BaseDO {

/** 主键 */
private Long id;

/** 商品id */
private Long itemId;

/** 图片地址 */
private String picture;

/** 图片类型：01-主图；02-详情图片 */
private String type;

/** 排序值 */
private Integer sort;

/** 是否删除,真实图片物理删除，记录逻辑删除：0-已删除；1-未删除 */
private Boolean status;

/** 创建人 */
private Long createUserId;

/** 创建时间 */
private Date createTime;

/** 修改人 */
private Long modifyUserId;

/** 修改时间 */
private Date modifyTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 商品id
* @param itemId
*/
	public void setItemId(Long itemId) {
	this.itemId = itemId;
}
/**
* 设置 图片地址
* @param picture
*/
	public void setPicture(String picture) {
	this.picture = picture;
}
/**
* 设置 图片类型：01-主图；02-详情图片
* @param type
*/
	public void setType(String type) {
	this.type = type;
}
/**
* 设置 排序值
* @param sort
*/
	public void setSort(Integer sort) {
	this.sort = sort;
}
/**
* 设置 是否删除,真实图片物理删除，记录逻辑删除：0-已删除；1-未删除
* @param status
*/
	public void setStatus(Boolean status) {
	this.status = status;
}
/**
* 设置 创建人
* @param createUserId
*/
	public void setCreateUserId(Long createUserId) {
	this.createUserId = createUserId;
}
/**
* 设置 创建时间
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
* 设置 修改时间
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
* 获取 商品id
* @return itemId
*/
	public Long getItemId() {
	return itemId;
}
/**
* 获取 图片地址
* @return picture
*/
	public String getPicture() {
	return picture;
}
/**
* 获取 图片类型：01-主图；02-详情图片
* @return type
*/
	public String getType() {
	return type;
}
/**
* 获取 排序值
* @return sort
*/
	public Integer getSort() {
	return sort;
}
/**
* 获取 是否删除,真实图片物理删除，记录逻辑删除：0-已删除；1-未删除
* @return status
*/
	public Boolean getStatus() {
	return status;
}
/**
* 获取 创建人
* @return createUserId
*/
	public Long getCreateUserId() {
	return createUserId;
}
/**
* 获取 创建时间
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
* 获取 修改时间
* @return modifyTime
*/
	public Date getModifyTime() {
	return modifyTime;
}

}