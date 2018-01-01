<style>
	.msg_info_box {
		font-size: 16px;
		color: #ff0000;
		width: 230px;
		height: 100px;
		text-align: center;
	}
	.msg_info {
		margin-top: 15px;
		text-align: center;
	}
	
	
</style>

<div class="msg_info_box">
	<div class="msg_info">
		<span>
			<#if errMsg??>
				${errMsg}
			<#else>
				请求异常,请稍后再试.....
			</#if>
		</span>
	</div>
</div>