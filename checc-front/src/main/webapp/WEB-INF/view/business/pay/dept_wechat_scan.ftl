<#include "/common/common.ftl" />

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

<script type="text/javascript">
	var $ = window.parent.jQuery.noConflict(), 
		domain = "${domain}", 
		PCACHE = {}, 
		layer = window.parent.layer;
</script>	

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


<script type="text/javascript">

var timer;
$(function() {
	//pay_status();
	
	// 启动定时器
	timer = self.setInterval(function() {
		pay_status()
	}, 3000);
	
});

function pay_status() {
	 var dpOrderNo = $("#dpOrderNo").val();
//	var dpOrderNo = '877249130680001470';
	if (null == dpOrderNo || "" == dpOrderNo) {
		return;
	}
	$.ajax({
		url : domain + '/pay/wechat/orderQuery',
		dataType : 'html',
		type : 'GET',
		data : {
			'dpOrderNo' : dpOrderNo
		},
		success : function(data, status, xhr) {
			var statusCode = xhr.getResponseHeader("statusCode");
			if('8001' == statusCode || 8008 == statusCode){
				window.clearInterval(timer); // 销毁定时器
				var index = layer.open({
					type : 1,
					title : '',
					resize : false,
					// scrollbar: false,
					// fixed: false,
					move : false,
					shade : 0.1,
					anim : 5,
					area : [ '600px', '350px' ],
					content : data
				});
				return;
			} 
		},
		error : function() {
			alert("网络连接异常");
		},

	});
}


</script>


