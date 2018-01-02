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

