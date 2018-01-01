<style type="text/css">
	.content {
		text-align: center;
	}
	.pay_qrcode{
		padding-top: 10px;
		width:150px;
		height:150px;
	}
	img {
		width: 100%;
		height: 100%;
	}
</style>

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
	function pay_status(){
	     var dpOrderNo = $("#dpOrderNo").val();
	     if(null == dpOrderNo || "" == dpOrderNo){
	     	return;
	     }
	     $.ajax({   
	        url:domain + '',
	        dataType:'html', 
	        type:'post',   
	        data:{'order_id':order_id},  
	        success:function(data){   
	            if(data == '1' ){
	                window.clearInterval(int); //销毁定时器
	                setTimeout(function(){
	                    //跳转到结果页面，并传递状态
	                    window.location.href="http://"+window.location.host+"/home/cart/pay_result?pay_status=success";
	                },1000)
	                
	            }else if(data =='2'){
	                window.clearInterval(int); //销毁定时器
	                setTimeout(function(){
	                    //跳转到结果页面，并传递状态
	                    window.location.href="http://"+window.location.host+"/home/cart/pay_result?pay_status=fail";
	                },1000)
	            }
	        }, 
	        error:function(){   
	            alert("error");
	            
	        },   
	
	  });
	}
	//启动定时器
	var int=self.setInterval(function(){pay_status()},1000);
</script>
