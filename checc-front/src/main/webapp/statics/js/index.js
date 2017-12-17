$(document).ready(function() {
	
	// 首页竞拍和兑换按钮点击事件,获取倒计时
	$(".itemsal").on('click', function(){
		var _href = $(this).attr("href");
		$(this).attr("href", _href + "?reqTime=" + new Date().getTime());
	});
	
	
	//getLoginInfo();
	
	
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