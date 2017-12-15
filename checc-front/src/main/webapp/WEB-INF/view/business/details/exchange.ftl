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
			<div class="meta_item_exchangeprice">
				<span>价值:</span>
				<span class="exchangeprice">${detailVO.exchangeAmount!0}西币</span>
			</div>
			<div class="meta_inventory">
				<span>数量:</span>
				<span>剩${detailVO.residue!0}份/共${detailVO.inventory}份</span>
			</div>
			<div class="meta_exchange_eninfo">
				<span>每人限兑换${detailVO.exchangeLimitNum!1}份，兑换后不可撤销</span>
			</div>
		</div>
		<div class="meta_exchange_action meta_auction_action">
			<#if detailVO.status?? && detailVO.status=='02'>
				<div id="auc_action" class="auc_exchange auc_action auc_act_btn_p">
					<span style="display:none;" id="countDownTimeOrigin">${countDownTime}</span>
					<p class="auc_act_btn"><a href="#">立即兑换</a></p>
					<p class="auc_currency">
						(剩余时间:<span id="countDownTime">${detailVO.timeCountDown}</span>)
					</p>
				</div>
			<#elseif detailVO.status?? && detailVO.status=='03'>
				<div class="auc_action_over auc_act_btn_p">
					<p class="exchange_act_btn">已经结束</p>
					<p class="auc_currency"><span></span></p>
				</div>
			<#else>
				<div class="auc_action_over auc_act_btn_p">
					<p class="exchange_act_btn">未开始</p>
					<p class="auc_currency"><span></span></p>
				</div>
			</#if>
			<div class="auc_exchanged_list">
				<a href="#">看看哪些人兑换了>></a>
			</div>
		</div>
	</div>
</div>