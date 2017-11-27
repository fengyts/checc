package com.checc.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
* 发送短信记录
* @author fengyts Mon Nov 27 12:40:43 CST 2017
*/

public class SmsRecordDO extends BaseDO {

/** 主键 */
private Long id;

/** 短信类型：01-注册；02-充值密码；03-其他 */
private String smsType;

/** 用户手机号 */
private String mobile;

/** 短信内容 */
private String content;

/** 短信id，短信供应商返回 */
private String smsId;

/** 短信服务商代号 */
private String spCode;

/** 短信发送状态：01-提交成功；02-提交失败；03-发送成功；04-发送失败。 */
private String smsStatus;

/** 短信发送时间 */
private Date createTime;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 短信类型：01-注册；02-充值密码；03-其他
* @param smsType
*/
	public void setSmsType(String smsType) {
	this.smsType = smsType;
}
/**
* 设置 用户手机号
* @param mobile
*/
	public void setMobile(String mobile) {
	this.mobile = mobile;
}
/**
* 设置 短信内容
* @param content
*/
	public void setContent(String content) {
	this.content = content;
}
/**
* 设置 短信id，短信供应商返回
* @param smsId
*/
	public void setSmsId(String smsId) {
	this.smsId = smsId;
}
/**
* 设置 短信服务商代号
* @param spCode
*/
	public void setSpCode(String spCode) {
	this.spCode = spCode;
}
/**
* 设置 短信发送状态：01-提交成功；02-提交失败；03-发送成功；04-发送失败。
* @param smsStatus
*/
	public void setSmsStatus(String smsStatus) {
	this.smsStatus = smsStatus;
}
/**
* 设置 短信发送时间
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 短信类型：01-注册；02-充值密码；03-其他
* @return smsType
*/
	public String getSmsType() {
	return smsType;
}
/**
* 获取 用户手机号
* @return mobile
*/
	public String getMobile() {
	return mobile;
}
/**
* 获取 短信内容
* @return content
*/
	public String getContent() {
	return content;
}
/**
* 获取 短信id，短信供应商返回
* @return smsId
*/
	public String getSmsId() {
	return smsId;
}
/**
* 获取 短信服务商代号
* @return spCode
*/
	public String getSpCode() {
	return spCode;
}
/**
* 获取 短信发送状态：01-提交成功；02-提交失败；03-发送成功；04-发送失败。
* @return smsStatus
*/
	public String getSmsStatus() {
	return smsStatus;
}
/**
* 获取 短信发送时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}

}