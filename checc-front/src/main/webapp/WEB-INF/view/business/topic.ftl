<div class="topic">
	<div class="topic_hd">
		<div class="topic_tab">
			<span class="current_periods select_tab"><a>本期竞拍(${auctionList?default([])?size!0})</a></span>
			<span class="next_periods"><a>下期预告</a></span>
		</div>
		<div class="auction_time">
			<span>竞拍时间:${topicStartTime?string('yyyy-MM-dd HH:mm:ss')!''} ~ ${topicEndTime?string('yyyy-MM-dd HH:mm:ss')!''}</span>
		</div>
	</div>
	
	<div id="topic_items" class="topic_items">
		<#if auctionList?default([])?size!=0>
			<#list auctionList as auc>
				<div class="items">
					<div class="item_img">
						<a href="${domain}/topicItem/itemDetails/auction/${auc.id}"><img src="${auc.picture}"></a>
					</div>
					<div class="item_info">
						<div class="item_title">
							<a href="${domain}/topicItem/itemDetails/auction/${auc.id}">${auc.itemTitle}</a>
						</div>
						<div class="pr_box">
							<div class="basic_price">
								<span class="lbtitle">市场指导价:</span>
								<span class="prc">￥${auc.marketPrice!0.00}</span>
							</div>
							<div class="auc_price">
								<span class="lbtitle">当前竞拍价:</span>
								<span class="prc prc_curr">￥${auc.currentAuctionPrice!0.00}</span>
							</div>
							<div class="auc_btn">
								<span>
									<a class="btn" href="${domain}/topicItem/itemDetails/auction/${auc.id}">我要竞拍</a>
								<span>
							</div>
						</div>
						
					</div>
				</div>
			</#list>
		</#if>
	</div>
	
	<#-- 兑换商品 -->
	<div class="topic_hd">
		<div class="topic_tab">
			<span class="current_periods select_tab">西币兑换</span>
		</div>
		<div class="auction_time">
			<span>每天上午10:00开始兑换，数量有限先到先得</span>
		</div>
	</div>
	<div id="auction_items" class="topic_items">
		<#if exchangeList?default([])?size!=0>
			<#list exchangeList as excg>
				<div class="items">
					<div class="item_img">
						<a href="${domain}/topicItem/itemDetails/exchange/${excg.id}"><img src="${excg.picture}"></a>
					</div>
					<div class="item_info">
						<div class="item_title">
							<a href="${domain}/topicItem/itemDetails/exchange/${excg.id}">${excg.itemTitle}</a>
						</div>
						<div class="pr_box">
							<div class="basic_price">
								<span class="lbtitle">数量:</span>
								<span class="prc">剩${excg.residue!0}份 /总${excg.inventory!10}份</span>
							</div>
							<div class="auc_price">
								<span class="lbtitle">兑换价:</span>
								<span class="prc prc_curr">${excg.exchangeAmount?string['0']!0}西币</span>
							</div>
							<div class="auc_btn">
								<span>
									<a class="btn" href="${domain}/topicItem/itemDetails/exchange/${excg.id}#">我要兑换</a>
								<span>
							</div>
						</div>
						
					</div>
				</div>
			</#list>
		</#if>
	</div>
	
</div>