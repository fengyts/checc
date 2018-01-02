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
		.content {
		}
		.pay_qrcode{
			padding-top: 10px;
			margin-left: 20px;
			width:150px;
			height:150px;
		}
		img {
			width: 100%;
			height: 100%;
		}
		.hp_info {
			color: #555555;
			width: 250px;
			margin-left: -15px;
			font-size: 14px;
		}
	</style>
	
</header>
<body>

<div class="content_box">
	<div class="content">
		<div class="pay_qrcode">
			<img src="data:image/png;base64,${qrcode}">
		</div>
		<div class="hp_info">
			<div>请用微信扫描二维码,完成支付</div>
			<div>
				若您已经支付成功,请稍后查询您的账户,</br>
				若支付出现问题，请及时联系客服
			</div>
		</div>
		<div style="display:none;">
			<input type="hidden" id="dpOrderNo" value="${dpOrderNo}" />
		</div>
	</div>
</div>

<script type="text/javascript" src="${js}/pay_scan.js"></script>

</body>
</html>

