package com.checc.dto.refund;

import java.util.List;

import com.checc.domain.AuctionRecordDO;
import com.checc.dto.BaseDTO;

public class RefundTopicItemDTO extends BaseDTO {

	private static final long serialVersionUID = -3636204511667922800L;

	private Long tiId;
	private Long topicId;

	/** 竞拍底价 */
	private Double floorPrice;

	private List<AuctionRecordDO> auctionRecords;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getTiId() {
		return tiId;
	}

	public void setTiId(Long tiId) {
		this.tiId = tiId;
	}

	public Double getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(Double floorPrice) {
		this.floorPrice = floorPrice;
	}

	public List<AuctionRecordDO> getAuctionRecords() {
		return auctionRecords;
	}

	public void setAuctionRecords(List<AuctionRecordDO> auctionRecords) {
		this.auctionRecords = auctionRecords;
	}

}
