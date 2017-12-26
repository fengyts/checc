<div class="main_box">
	<div class="deposit_box">
		<div class="deposit_title">
			<span>西币充值</span>
		</div>
		<div class="deposit_list_box">
			<div>
				<span>请选择充值数额</span>
			</div>
			<div class="deposit_list">
				<#if dpList?default([])?size!=0>
					<#list dpList as dpl>
						<div class="dpl_d_box">
							<div class="dpl_amount">${dpl.depositAmount}个</div>
							<#if dpl.discount &gt; 0 && dpl.discount &lt; 1>
								<div class="dpl_discount">${dpl.discount[2..]}折</div>
							</#if>
							<input type="hidden">
							<input type="hidden" value="${dpl.discount * dpl.depositAmount}">
						</div>
					</#list>
				</#if>
			</div>
			<div class="other_amount">
				<span>其他金额:</span>
				<span><input type="text" id="otherAmount" name="otherAmount" value=""></span>
			</div>
		</div>
		<div class="pay_type_box">
			<div class="pay_type_t">
				<span>请选择支付方式</span>
			</div>
			<div class="pay_type_l">
				<span>微信</span>
			</div>
		</div>
		<div class="deposit_btn_box">
			<div class="dpcheck_info">
				<p>您本次充值西币 1000个，需支付￥990.00</p>
			</div>
			<div class="deposit_btn">
				<a href="#">立即支付</a>
			</div>
			<form id="depositListForm" action="">
				<input type="hidden" id="ict" value="">
			</form>
		</div>
		
		
	</div>
</div>