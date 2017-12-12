<div class="detail_meta">
	<div class="meta_item_img">
		<img src="${detailVO.picture}">
	</div>
	<div class="meta_item_info">
		<div class="meta_item_title">${detailVO.itemTitle}</div>
		<div class="meta_item_exchangeprice">
			<span>价值:</span>
			<span>${detailVO.exchangeAmount!0}</span>
		</div>
		<div class="meta_inventory">
			<span>数量:</span>
			<span>${detailVO.residue}/${detailVO.inventory}</span>
		</div>
		<div class="meta_exchange_eninfo">
			<span>每人限兑换一份，兑换后不可撤销</span>
		</div>
		<div class="meta_exchange_action">
			<div class="auc_action">
				<div><a href="#">立即兑换</a></div>
				<div class="meta_auct_timedown">
					<span>(剩余时间:${detailVO.timeCountDown})</span>
				</div>
			</div>
			<div class="meta_exchange_list">
				<a href="#">看看哪些人兑换了</a>
			</div>
		</div>
	</div>
</div>