<#include "/common/common.ftl" />

<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>微信扫码支付例子</title>
	
	<#include "/common/common-js.ftl" />
	
	<style type="text/css">
		.content {
			margin-top: 50px;
		}
	</style>
</head>

<body>
	<div class="content">
		<form id="pay_form" method="post" >
			<h1>可乐特价：0.1元/罐 <input id="pay_submit" name="but" type="button" value="微信支付"/></h1>
		</form>
	</div>
	<script>
		$(function(){
			$("#pay_submit").click(function(){
			    buy('001');//传入可乐的ID号
			});
			
		});
	
		/**
		 * 购买
		 */
		function buy(productId){
			//打开付费二维码 -- 微信二维码
			layer.open({
				offset: '50px',
				area: ['300px', '300px'],
		        type: 2,
		        closeBtn: false,
		        title: false,
		        //shift: 2,
		        shadeClose: true,
		        content:'.//qrcode?productId=' + productId
		    });
			
			getPayState(productId);
			//重复执行某个方法 
			//var t1 = window.setInterval("getPayState('" + productId + "')",1500); 
		}
	
		function getPayState(productId){
			var url = './hadPay?productId=' + productId;
			//轮询是否已经付费
			$.ajax({
		    	type:'post',
		   		url:url,
		   		data:{productId:productId},
		   		cache:false,
		   		async:true,
		   		success:function(json){
		   			if(json.result == 0){
		   				location.href = './result.jsp';
		   			}
		   		},
		   	    error:function(){
		   	    	layer.msg("执行错误！", 8);
		   	    }
		   	});
		}
	</script> 
	
</body>

</html>
