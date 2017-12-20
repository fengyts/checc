<div class="detail_meta">
	<div class="item_detail_imgs">
		<div class="meta_item_img">
			<img src="${detailVO.picture}">
		</div>
		<div class="detail_img_list"></div>
	</div>
	<div class="meta_item_info">
		<div class="meta_item_title">${detailVO.itemTitle}</div>
		<div class="auc_info_t">
			<div class="meta_item_currauctprice">
				<span class="curraucttile">当前价:</span>
					<#if detailVO.currentAuctionPrice??>
				<span class="currauctprice">
						${detailVO.currentAuctionPrice?string('0.##')}西币
				</span>
					<#else>
						暂时无人出价
					</#if>
				<span class="compare_marketPrice">市场价：￥${detailVO.marketPrice?string('0.00')}</span>
			</div>
			<div class="meta_currauctper">
				<span class="curraucttile">当前出价人:</span>
				<span class="currauctperson">${detailVO.currentBidder!'暂时无人出价'}</span>
				<span class="auctall"><a href="#">查看全部出价记录>></a></span>
			</div>
			<div class="meta_curraucttime">
				<span class="curraucttile">当前出价时间:</span>
				<span class="curraucttime">
					<#if detailVO.currentBidTime??>
						${detailVO.currentBidTime?string('yyyy-MM-dd HH:mm:ss')}
					<#else>
						暂时无人出价
					</#if>
				</span>
			</div>
			<div class="meta_auction_eninfo">
				<p>
					<#if detailVO.currentBidder??>
						若接下来无人出价,
						${detailVO.currentBidder}将以
						<span class="eninfo_price">${detailVO.currentAuctionPrice?string('0.##')}西币</span>
						拍得本件商品
					<#else>
						(若一直无人出价,本商品将流拍)
					</#if>
				</p>
			</div>
		</div>
		<div class="meta_auction_action">
			<#if detailVO.status?? && detailVO.status=='02'>
				<div>
					<form id="auction_action_form">
						<input type="hidden" id="tpId" name="tpId" value="${detailVO.id}">
						<input type="hidden" id="auctionType" name="auctionType" value="${detailVO.topicType}">
					</form>
				</div>
				<div id="auc_action" class="auc_action auc_act_btn_p">
					<p class="auc_act_btn"><a href="#">我要出价</a></p>
					<p class="auc_currency"><span>(${detailVO.auctionCurrency!1}西币每次)</span></p>
				</div>
				<div class="meta_auct_timedown">
					<span style="display:none;" id="countDownTimeOrigin">${countDownTime}</span>
					<span>剩余时间:</span>
					<span id="countDownTime" class="auct_timedown"></span>
				</div>
			<#elseif detailVO.status?? && detailVO.status=='03'>
				<div class="auc_action_over auc_action auc_act_btn_p">
					<p class="auc_act_btn">已经结束</p>
					<p class="auc_currency"><span>(${detailVO.auctionCurrency!1}西币每次)</span></p>
				</div>
			<#else>
				<div class="auc_action_over auc_action auc_act_btn_p">
					<p class="auc_act_btn">未开始</p>
					<p class="auc_currency"><span>(${detailVO.auctionCurrency!1}西币每次)</span></p>
				</div>
			</#if>
			<div id="auc_action_over" class="auc_action_over auc_action auc_act_btn_p" style="display:none;">
				<p class="auc_act_btn">已经结束</p>
				<p class="auc_currency"><span>(${detailVO.auctionCurrency!1}西币每次)</span></p>
			</div>
		</div>
	</div>
	
</div>