<#include "/common/common.ftl" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="" />
	<meta name="Description" content="" />
	
	<title>用户中心_车西西-值得信赖的网上汽车商城</title>
	
	<#include "/common/common-js.ftl" />
	
	<style type="text/css">
		html,body{
			margin:0;
			padding:0;
		}
		a {
			text-decoration:none;
			color: #0099ff;
		}
		.pay_content {
			width: 400px;
			margin-top: 30px;
			margin-left: 150px;
			font-size:18px;
		}
		.res_status {
			width: 40px;
			height: 40px;
		}
		.res_status_title>div{
			display:inline-block;
			vertical-align:middle;
			text-align: center;
			font-size: 22px;
			font-weight: 600;
			color: #0099ff;
		}
		.res_status_error {
			margin-left: -50px;
			font-size: 16px !important;
			color: #ff0000 !important;
			text-align: left !important;
		}
		.res_dl{
			margin-top: 10px;
		}
		.res_info_label {
			color: #222222;
		}
		.label_c_text{
			color: #666666;
		}
		.real_cny {
			color: #ff0000;
		}
		.res_btn {
			margin-top: 30px;
			font-size: 16px;
		}
		.res_btn>div{
			display: inline-block;
			vertical-align: middle;
		}
		.res_btn>div>a:hover {
			opacity: .6;
			filter: alpha(opacity = 80);
			text-decoration: underline;
		}
		
		.auction_now {
			margin-left: 10px;
		}
	
	</style>
	
</header>
<body>

<div class="pay_success_box">
	<div class="pay_content">
		<#assign resInfo=result.data />
		<div class="res_status_title">
			<#if resInfo?? && resInfo.depositStatus?? && resInfo.depositStatus == 'SUCCESS'>
				<div class="res_status">
					<img src="${images}/icon_correct.png" style="width:100%;height:100%;">
				</div>
				<div class="res_status_t">
					西币充值成功
				</div>
			<#else>
				<div class="res_status_error">
					${result.message}
				</div>					
			</#if>
		</div>
		<div class="res_dl">
			<span class="res_info_label">充值时间: </span>
			<span class="label_c_text">
				<#if resInfo.depositTime??>${resInfo.depositTime?string('yyyy-MM-dd HH:mm:ss')}</#if>
			</span>
		</div>
		<div class="res_dl">
			<span class="res_info_label">充值数量: </span>
			<span class="label_c_text"><#if resInfo.depositAmt??>${resInfo.depositAmt}个</#if></span>
		</div>
		<div class="res_dl">
			<span class="res_info_label">实付金额: </span>
			<span class="label_c_text real_cny"><#if resInfo.realCNY??>￥${resInfo.realCNY?string('#0.00')}</#if></span>
		</div>
		<div class="res_dl">
			<span class="res_info_label">支付方式: </span>
			<span class="label_c_text"><#if resInfo.depositTypeDesc??>${resInfo.depositTypeDesc}支付</#if></span>
		</div>
		<div class="res_btn">
			<div class="view_mycu"><a href="javascript:window.parent.location.href='${domain}/user/bis/membercenter';">查看我的西币</a></div>
			<div class="auction_now"><a href="javascript:window.parent.location.href='${domain}/';">立即参与竞拍</a></div>
		</div>
		
	</div>
</div>


</body>
</html>
