
<style>
	.main_box {
	}
	
	.exchange_data_list {
		margin-top: 20px;
		padding: 0 50px;
		text-align: center;
		font-size: 20px;
		color: #999999;
	}
	
	.ex_title {
		font-style: normal;
    	font-weight: 700;
    	font-size: 30px;
	}
	.ex_list_box {
		margin-top: 20px;
	}
	.ex_list_box>div {
		float: left;
	    /** margin: 10px 0 0 28px; **/
	    margin-top: 10px;
	    width: 27%;
	}
	.ex_list_box>div:nth-of-type(2),.ex_list_box>div:nth-of-type(3) {
		margin-left: 40px;
	}

</style>

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
				<#list exchangeList as obj>
					<div>${obj.mobile[0..2] + '****' + obj.mobile[7..]}</div>
				</#list>
			<#else>
				暂时无人兑换
			</#if>
		</div>
	</div>
</div>

