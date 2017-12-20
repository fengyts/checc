package com.checc.dto;

public class AuctionListDTO extends BaseDTO {

	private static final long serialVersionUID = -2646285134067642787L;

	public static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 20;

	private Long tpId;
	/** 是否只看我的出价, 默认查看全部 */
	private Boolean isOnlyMe = false;

	private Integer pageNo = DEFAULT_PAGE_NO;
	private Integer pageSize = DEFAULT_PAGE_SIZE;

	public Long getTpId() {
		return tpId;
	}

	public void setTpId(Long tpId) {
		this.tpId = tpId;
	}

	public Boolean getIsOnlyMe() {
		return isOnlyMe;
	}

	public void setIsOnlyMe(Boolean isOnlyMe) {
		this.isOnlyMe = isOnlyMe;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
