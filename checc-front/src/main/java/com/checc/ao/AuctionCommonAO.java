package com.checc.ao;

import java.util.Date;

import com.checc.enums.TopicStatusEnum;

public class AuctionCommonAO {
	
	public static String getTopicStatus(Date startTime, Date endTime) {
		if (null == startTime || null == endTime) {
			return TopicStatusEnum.End.getCode();
		}
		Date now = new Date();
		if (startTime.after(now)) {
			return TopicStatusEnum.NotStarted.getCode();
		}
		if (endTime.before(now)) {
			return TopicStatusEnum.End.getCode();
		}
		return TopicStatusEnum.InProgress.getCode();

	}

}
