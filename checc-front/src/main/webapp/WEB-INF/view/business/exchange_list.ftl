<div class="main_box">
	<div class="exchange_data_list">
		<div class="ex_title">
			<span>全部兑换记录</span>
		</div>
		<div class="ex_total">
			<span>共${totalExchange!0}人兑换</span>
		</div>
		<div class="ex_list_box">
			<#if exchangeList?default([])?size!=0>
				<#list exchangeList as mobile>
					<div>${mobile[0..2] + '****' + mobile[7..]}</div>
				</#list>
			<#else>
				暂时无人兑换
			</#if>
		</div>
	</div>
</div>

