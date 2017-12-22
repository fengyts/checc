$(document).ready(function() {

	$(".uc_help_i").on('mouseover', function() {
		var _index = $(this).attr('index');
		var _ct = "";
		if(0 == _index){
			_ct = "<font style='font-size:14px;'><b>充值的西币：</b></font></br>充值的西币全部可以直接用于参加竞拍；您可以通过支付宝或微信进行充值；";
		}else {
			_ct = "<font style='font-size:14px;'><b>已退回的西币：</b></font></br>某件商品竞拍结束后，如果您未拍得商品，则您参与该商品的出价全部退回，如果您拍得了商品，则不退回；退回的西币可用于在本平台首页兑换任意商品；";
		}
		layer.tips(_ct, this, {
			time : 30000,
			tips : [ 1, '#666666' ]
		});
	}).on('mouseout', function() {
		layer.closeAll('tips');
	});
	
	$("iframe").css('width', function(){
		return $(this).parent().width();
	});
	
	$("iframe").resize(function(){
		$(this).css('width', function(){
			return $("iframe").parent().width();
		})
	});
	
});

//function reinitIframe(){
//	var iframe = document.getElementsByTagName("iframe");
//	try{
//		for(var i =0;i<iframe.length;i++){
//			var dHeight = iframe[i].contentWindow.document.documentElement.scrollHeight;
//			iframe[i].height = dHeight;
//			iframe[i].width = $(iframe[i]).parent().width();
//		}
//		/**
//		var iframe = document.getElementById("auct_data_list");
//		//var bHeight = iframe.contentWindow.document.body.scrollHeight;
//		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
//		//var height = Math.max(bHeight, dHeight);
//		iframe.height = dHeight;
//		iframe.width = $("#auct_data_list").parent().width();
//		*/
//	}catch (ex){}
//}
