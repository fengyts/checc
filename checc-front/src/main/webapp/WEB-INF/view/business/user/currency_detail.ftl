<div class="user_currency_box">
	<div class="user_currency_detail">
		<div class="center_title">
			<span>个人中心</span>
		</div>
		<div class="center_user_mc">
			<span>${userDetailVO.mobile}</span>
		</div>
		
		<div class="currency_info">
			<div class="total_currency_box">
				<div class="uc_box_t">
					<span>西币余额</span>
					<span class="uc_help_i" index="0"></span>
				</div>
				<div class="uc_ta_num">
					<span>${userDetailVO.totalCurrency!0}</span>
					<span class="num_unit">个</span>
				</div>
				<div class="act_btn_box">
					<div class="to_recharge">
						<a href="javascript:window.open('${domain}/user/deposit/dplist');">去充值</a>
					<s>|</s>
					</div>
					<div class="to_auction"><a href="${domain}/">去竞拍</a></div>
				</div>
			</div>
			<div class="refund_currency_box">
				<div class="uc_box_t">
					<span>退回的西币</span>
					<span class="uc_help_i" index="1"></span>
				</div>
				<div class="uc_ta_num">
					<span>${userDetailVO.refund!0}</span>
					<span class="num_unit">个</span>
				</div>
				<div class="act_btn_box">
					<div><a href="${domain}/">去兑换</a></div>
				</div>
			</div>
			<div class="currency_trade_box">
				<div class="uc_box_t">
					<span>西币交易记录</span>
				</div>
				<div class="uc_ta_num">
					<span>${userDetailVO.currencyTradeNum!0}</span>
					<span class="num_unit">条</span>
				</div>
				<div class="act_btn_box">
					<div id="ucCurrencyDetails"><a href="#">详细记录</a></div>
				</div>
			</div>
		</div>
	
	
	</div>
</div>