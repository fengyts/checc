package com.checc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import com.checc.domain.ItemPictureDO;

public class ItemDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -6914019869680731771L;

	/** item主键id */
	private Long id;

	/** 商品名称 */
//	@NotBlank(message = "商品名称不能为空")
	private String itemTitle;

	/** 商品类型：01-竞拍商品；02-兑换商品 */
	private String itemType;

	/** 状态0-未上架 1-上架 2-作废 默认0 */
	private Integer status;

	/** 市场价 */
//	@NotNull(message = "市场价不能为空")
	private Double marketPrice;

	/** 备注 */
	private String remark;

	/** 关联图片id列表 */
	private String pictureIds;

	/** 关联图片地址,多个以逗号分隔 */
	private String picUrls;
	/** 关联图片地址,列表形式 */
	private List<String> listPicUrls;

	/** 关联图片列表 */
	private List<ItemPictureDO> listPictures;

	/** 商品详情描述信息 */
//	@NotBlank(message = "商品描述不能为空")
	private String description;

	private Long createUserId;
	private Date createTime;
	private Date modifyTime;
	private Long modifyUserId;
	
	public List<ItemPictureDO> getListPictures() {
		return listPictures;
	}

	public void setListPictures(List<ItemPictureDO> listPictures) {
		this.listPictures = listPictures;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPictureIds() {
		return pictureIds;
	}

	public void setPictureIds(String pictureIds) {
		this.pictureIds = pictureIds;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public List<String> getListPicUrls() {
		return listPicUrls;
	}

	public void setListPicUrls(List<String> listPicUrls) {
		this.listPicUrls = listPicUrls;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
