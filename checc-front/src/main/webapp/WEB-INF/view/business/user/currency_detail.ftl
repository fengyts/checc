<div class="user_currency_box">
	<div class="user_currency_detail">
		<div class="center_title">
			<span>会员中心</span>
		</div>
		<div class="center_user_mc">
			<span>我的西币账户</span>
		</div>
		
		<div class="currency_info">
			<div class="cinfo">
				<div class="total_currency_box">
					<div class="uc_box_t">
						<span>竞拍账户</span>
					</div>
					<div class="uc_ta_num">
						<span>${userDetailVO.totalCurrency!0}</span>
						<span class="num_unit">个</span>
					</div>
					<div class="act_btn_box">
						<div class="to_recharge">
							<a href="${domain}/user/deposit/dplist" target="_blank">去充值</a>
						<s>|</s>
						</div>
						<div class="to_auction"><a href="${domain}/">去竞拍</a></div>
					</div>
				</div>
				<div class="help_icon hi0"><span class="uc_help_i" index="0"></span></div>
			</div>
			<div class="cinfo">
				<div class="refund_currency_box">
					<div class="uc_box_t">
						<span>兑换账户</span>
					</div>
					<div class="uc_ta_num">
						<span>${userDetailVO.refund!0}</span>
						<span class="num_unit">个</span>
					</div>
					<div class="act_btn_box">
						<div><a href="${domain}/">去兑换</a></div>
					</div>
				</div>
				<div class="help_icon"><span class="uc_help_i" index="1"></span></div>
			</div>
			<div class="cinfo">
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
</div>