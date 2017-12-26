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
						<div class="dpl_d_box" id="id_${dpl_index}_dpld" index='${dpl_index}'>
							<input type="hidden" id="discountId_${dpl_index}" name="discountId_${dpl.id}" value="${dpl.id}">
							<div class="dpl_amount">${dpl.depositAmount}个</div>
							<#if dpl.discount &gt; 0 && dpl.discount &lt; 1>
								<div class="dpl_discount">${dpl.discount[2..]}折</div>
							</#if>
							<input type="hidden" id="dpamt_${dpl_index}" value="${dpl.depositAmount}">
							<input type="hidden" id="dpamtm_${dpl_index}" value="${(dpl.discount * dpl.depositAmount)?string('#.00')}">
						</div>
					</#list>
				</#if>
			</div>
			<div class="other_amount">
				<span>其他金额:</span>
				<input type="text" id="otherAmount" name="otherAmount" value="">
				<span id="dpamtInputErr" style="font-size:14px;color:#ff0000;">(金额必须是>=0.1的有效值)</span>
			</div>
		</div>
		<div class="pay_type_box">
			<div class="pay_type_t">
				<div>请选择支付方式<span><font style="font-size:15px;color: #0099cc">(暂时只支持微信支付)</font></span></div>
				<div class="pay_model_cbox">
					<div>
						<input type="radio" id="payModeAlipay" name="payMode" value="02" disabled='true'>
					</div>
					<div class="pay_model_dimg">
						<img src="${images}/icon_alipay.png">
					</div>
					<div class="pay_model_ti">支付宝</div>
					
					<div style="margin-left: 20px;">
						<input type="radio" id="payModeWechat" name="payMode" value="03" checked>
					</div>
					<div class="pay_model_dimg">
						<img src="${images}/icon_wechat.png">
					</div>
					<div class="pay_model_ti">微信</div>
				</div>
			</div>
			
		</div>
		<div class="dpex_box">
			<div class="dpcheck_info dpck_info" id="dpCheckInfo">
				<p>
					您本次充值西币<span id="dpInfo"></span>个，需支付￥<span id="dpInfoMo"></span>
				</p>
			</div>
		</div>
		<div class="deposit_btn_box">
			<div class="deposit_btn" id="depositBtnAct">
				<a href="#">立即支付</a>
			</div>
			<form id="depositListForm" action="">
				<input type="hidden" id="discountId" value="-1">
				<input type="hidden" id="depositTk" name="depositTk" value="${depositTk}">
			</form>
		</div>
		
		
	</div>
</div>