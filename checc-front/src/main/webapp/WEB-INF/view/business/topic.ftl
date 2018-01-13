<div class="topic">
	<#-- 本期竞拍 -->
	<div class="topic_hd">
		<#--
		<div class="topic_tab">
			<span class="current_periods select_tab"><a>本期竞拍(${auctionList?default([])?size!0})</a></span>
			<span class="next_periods"><a>下期预告</a></span>
		</div>
		-->
		<div class="topic_tab">
			<div class="topic_current">本期竞拍</div>
			<#--
			<div class="topic_previous" id="viewPrevious">
				<a href="javascript:void(0);">查看往期竞拍</a>
			</div>
			-->
		</div>
		<div class="auction_time">
			<span>竞拍时间:
				<#if topicStartTime??>
					${topicStartTime?string('yyyy-MM-dd HH:mm:ss')} 
				</#if>
				~ 
				<#if topicEndTime??>
					${topicEndTime?string('yyyy-MM-dd HH:mm:ss')}
				</#if>
			</span>
		</div>
	</div>
	
	<div id="topic_items" class="topic_items">
		<#if auctionList?default([])?size!=0>
			<#assign req_url="${domain}/topicItem/itemDetails/auction" >
			<#list auctionList as auc>
				<div id="items" class="items">
					<div class="item_img">
						<a href="${req_url}/${auc.id}" class="itemsal" reqTime="${.now?long}"><img src="${auc.picture}"></a>
					</div>
					<div class="item_info">
						<div class="item_title">
							<a href="${req_url}/${auc.id}" class="itemsal" reqTime="${.now?long}">${auc.itemTitle}</a>
						</div>
						<div class="pr_box">
							<div class="basic_price">
								<span class="lbtitle">市场指导价:</span>
								<span class="prc">￥${auc.marketPrice?string('0.00')}</span>
							</div>
							<div class="auc_price">
								<span class="lbtitle">当前竞拍价:</span>
								<span class="prc prc_curr">￥${auc.currentAuctionPrice?string('#0.00')}</span>
							</div>
							<div class="auc_btn">
								<span>
									<a class="btn itemsal" href="${req_url}/${auc.id}" reqTime="${.now?long}">我要竞拍</a>
								<span>
							</div>
						</div>
						
					</div>
				</div>
			</#list>
		</#if>
	</div>
	
	<#-- 往期竞拍 -->
	<div class="topic_hd">
		<div class="topic_tab">
			<span class="topic_current select_tab">往期竞拍</span>
		</div>
		<div class="auction_time">
			<span>累计${totalPreviousNum}位用户竞拍成功</span>
		</div>
		<div class="previousViewAll topic_previous">
			<a href="javascript:window.open('${domain}/index/previous');">更多往期竞拍>></a>
		</div>
	</div>
	<div id="auction_items" class="topic_items">
		<#if previousList?default([])?size!=0>
			<#assign req_url="${domain}/topicItem/itemDetails/auction" >
			<#list previousList as auc>
				<div id="items" class="items">
					<div class="item_img">
						<a href="${req_url}/${auc.id}" class="itemsal" reqTime="${.now?long}"><img src="${auc.picture}"></a>
					</div>
					<div class="item_info">
						<div class="item_title">
							<a href="${req_url}/${auc.id}" class="itemsal" reqTime="${.now?long}">${auc.itemTitle}</a>
						</div>
						<div class="pr_box">
							<div class="basic_price">
								<span class="lbtitle">成交价:</span>
								<span class="prc">
									<#if auc.currentAuctionPrice &gt;= auc.floorPrice>
										￥${auc.currentAuctionPrice?string('0.00')}
									<#else>
										流拍
									</#if>
								</span>
							</div>
							<div class="auc_price">
								<span class="lbtitle">拍得者:</span>
								<span class="prc prc_curr">
									<#if auc.currentAuctionPrice &gt;= auc.floorPrice>
										${auc.mobile[0..2] + '****' + auc.mobile[7..]}
									<#else>
										流拍
									</#if>
								</span>
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
			<span class="topic_current select_tab">西币兑换</span>
		</div>
		<div class="auction_time">
			<span>每天上午10:00开始兑换，数量有限先到先得</span>
		</div>
	</div>
	<div id="auction_items" class="topic_items">
		<#if exchangeList?default([])?size!=0>
			<#assign req_url="${domain}/topicItem/itemDetails/exchange" />
			<#list exchangeList as excg>
				<div class="items">
					<div class="item_img">
						<a href="${req_url}/${excg.id}" class="itemsal" reqTime="${.now?long}"><img src="${excg.picture}"></a>
					</div>
					<div class="item_info">
						<div class="item_title">
							<a href="${req_url}/${excg.id}" class="itemsal" reqTime="${.now?long}">${excg.itemTitle}</a>
						</div>
						<div class="pr_box">
							<div class="basic_price">
								<span class="lbtitle">数量:</span>
								<span class="prc">剩${excg.residue!0}份 /共${excg.inventory!10}份</span>
							</div>
							<div class="auc_price">
								<span class="lbtitle">兑换价:</span>
								<span class="prc prc_curr">${excg.exchangeAmount?string['0']!0}西币</span>
							</div>
							<div class="auc_btn">
								<span>
									<a class="btn itemsal" href="${req_url}/${excg.id}" reqTime="${.now?long}">我要兑换</a>
								<span>
							</div>
						</div>
					</div>
					
				</div>
			</#list>
		</#if>
	</div>
	
</div>