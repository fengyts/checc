$(document).ready(function() {

	$(".uc_help_i").on('mouseover', function() {
		var _index = $(this).attr('index');
		var _ct = "";
		if(0 == _index){
			_ct = "<font style='font-size:14px;'><b>竞拍账户：</b></font></br>"
				  + "1、通过西币充值功能成功充值的西币都在本账户，可以直接用来参与竞拍。</br>"
				  + "2、本账户的西币只能用来参与竞拍，不能兑换礼品、提现或兑换现金。";
		}else {
			_ct = "<font style='font-size:14px;'><b>兑换账户：</b></font></br>"
					+ "1、当您参与某辆车的竞拍最终由其他用户拍得时，您在该辆车竞拍所出价的西币会全部退回至本账户。</br>"
					+ "2、当您参与某辆车的竞拍最终由您本人拍得时，你在该辆车竞拍所出价的全部西币均不予退回。</br>"
					+ "3、本账户的西币只能用来兑换礼品，不能参与竞拍、提现或兑换现金。";
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
	
	// 西币交易记录点击事件
	$("#ucCurrencyDetails").on('click', function(){
//		var layerIndex = layer.open({
//			
//		});
		
		lgn_pg_ii = layer.open({
    		type: 2,
    		title: '用户中心',
    		resize: false,
    		//scrollbar: false,
    		//fixed: false,
    		move:false,
    		shade: 0.1,
    		anim: 5,
    		content: [domain + '/user/bis/currencyRecList'],
    		area: ['800px', '500px']
    	});
		
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
