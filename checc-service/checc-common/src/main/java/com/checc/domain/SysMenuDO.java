package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 系统菜单
* @author fengyts Thu Nov 16 14:54:40 CST 2017
*/

public class SysMenuDO extends BaseDO {
	
	public static Integer MENU_TYPE_1 = 1;
	public static Integer MENU_TYPE_2 = 2;

/** 主键 */
private Long id;

/** 菜单code */
private String code;

/** 父菜单id，如果为空，则为根菜单 */
private Long parentId;

/** 菜单名称 */
private String name;

/** 菜单请求链接 */
private String url;

/** 菜单类型（0:根菜单，1 :主导航,2：菜单,3：按钮 */
private String menuType;

/** 菜单排序值 */
private Integer sort;

/** 菜单状态 */
private Boolean status;

/** 创建人 */
private Long createUserId;

/** 创建时间 */
private Date createTime;

/** 修改人 */
private Long modifyUserId;

/** 修改时间 */
private Date modifyTime;

/**  */
private Long location;



public Long getLocation() {
	return location;
}
public void setLocation(Long location) {
	this.location = location;
}
/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 菜单code
* @param code
*/
	public void setCode(String code) {
	this.code = code;
}
/**
* 设置 父菜单id，如果为空，则为根菜单
* @param parentId
*/
	public void setParentId(Long parentId) {
	this.parentId = parentId;
}
/**
* 设置 菜单名称
* @param name
*/
	public void setName(String name) {
	this.name = name;
}
/**
* 设置 菜单请求链接
* @param url
*/
	public void setUrl(String url) {
	this.url = url;
}
/**
* 设置 菜单类型（0:根菜单，1 :主导航,2：菜单,3：按钮
* @param menuType
*/
	public void setMenuType(String menuType) {
	this.menuType = menuType;
}
/**
* 设置 菜单排序值
* @param sort
*/
	public void setSort(Integer sort) {
	this.sort = sort;
}
/**
* 设置 菜单状态
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
* 获取 菜单code
* @return code
*/
	public String getCode() {
	return code;
}
/**
* 获取 父菜单id，如果为空，则为根菜单
* @return parentId
*/
	public Long getParentId() {
	return parentId;
}
/**
* 获取 菜单名称
* @return name
*/
	public String getName() {
	return name;
}
/**
* 获取 菜单请求链接
* @return url
*/
	public String getUrl() {
	return url;
}
/**
* 获取 菜单类型（0:根菜单，1 :主导航,2：菜单,3：按钮
* @return menuType
*/
	public String getMenuType() {
	return menuType;
}
/**
* 获取 菜单排序值
* @return sort
*/
	public Integer getSort() {
	return sort;
}
/**
* 获取 菜单状态
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