<div class="detail_meta">
	<div class="meta_item_img">
		<img src="${detailVO.picture}">
	</div>
	<div class="meta_item_info">
		<div class="meta_item_title">${detailVO.itemTitle}</div>
		<div class="meta_item_currauctprice">
			<span>当前价:</span>
			<span>${detailVO.currentAuctionPrice!0.00?string('0.00')}</span>
			<span class="compare_marketPrice">市场价：${detailVO.markgePrice!0.00?string('0.00')}</span>
		</div>
		<div class="meta_currauct">
			<span>当前出价人:</span>
			<span>${detailVO.currentAuction}</span>
			<span><a href="#">查看全部出价记录>></a></span>
		</div>
		<div class="meta_curraucttime">
			<span>当前出价时间:</span>
			<span>${detailVO.currentAuctionTime}</span>
		</div>
		<div class="meta_auction_eninfo">
			<span>若接下来无人出价,${detailVO.currentAuction}将以￥168830.00拍得本件商品</span>
		</div>
		<div class="meta_auction_action">
			<div class="auc_action">
				<span><a href="#">我要出价</a></span>
				<span>20西币每次</span>
			</div>
			<div class="meta_auct_timedown">
				<span style="display:none;" id="countDownTimeOrigin">${countDownTime}</span>
				<span id="countDownTime" class="auct_timedown"></span>
			</div>
		</div>
	</div>
</div>