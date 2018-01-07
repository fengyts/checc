$(document).ready(function() {
	
	// 首页竞拍和兑换按钮点击事件,获取倒计时
	$(".itemsal").on('click', function(){
		var _href = $(this).attr("href");
		$(this).attr("href", _href + "?reqTime=" + new Date().getTime());
	});
	
	
	//getLoginInfo();
	
	$("#viewPrevious").on('mouseover', function(){
		layer.tips("请至车西西官方微信查看，微信扫描网站右上角二维码快速关注车西西。", this, {
			time : 30000,
			tips : [ 1, '#666' ]
		});
	}).on('mouseout', function() {
		layer.closeAll('tips');
	});;
	
	
});

function getLoginInfo () {
	$.ajax({
		url: domain + '/user/getLoginInfo',
		method: 'GET',
		dataType: 'json',
		success: function(res){
			if(1 == res.result){
				$("#loginStatusOld").css("display", "none");
				$("#loginAjax").css("display", "inline");
				$("#hasLogin").prepend(res.data.mobile);
			}
		}
	});
}