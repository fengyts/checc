package com.checc.dto.refund;

import java.util.Date;
import java.util.List;

import com.checc.dto.BaseDTO;

public class RefundTopicDTO extends BaseDTO {

	private static final long serialVersionUID = -8813692239302576039L;

	private Long tId;
	private String topicType;
	private Date endTime;
	private Boolean refundCurrencyStatus;

	private List<RefundTopicItemDTO> topicItems;

	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getRefundCurrencyStatus() {
		return refundCurrencyStatus;
	}

	public void setRefundCurrencyStatus(Boolean refundCurrencyStatus) {
		this.refundCurrencyStatus = refundCurrencyStatus;
	}

	public List<RefundTopicItemDTO> getTopicItems() {
		return topicItems;
	}

	public void setTopicItems(List<RefundTopicItemDTO> topicItems) {
		this.topicItems = topicItems;
	}

}
