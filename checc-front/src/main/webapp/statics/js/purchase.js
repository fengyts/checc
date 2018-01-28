$(function(){
	// 个人中心我竞拍得的汽车点击事件
	$(".itemsal").on('click', function(){
		var _this = $(this);
		var _tiId = _this.attr("tiId");
		var _url = domain + "/user/bis/auctionSuccess/auction/" + _tiId;
		var _isWinner = _this.find("input[type='hidden']").val();
		if(1 == _isWinner) {
			// 防止点击过快,导致重复弹框
			var _preClickTime = _this.attr("preClickTime");
			var _nowClickTime = new Date().getTime();
			if(undefined != _preClickTime && '' != _preClickTime){
				_this.attr("preClickTime", _nowClickTime);
				var _timeDif = _nowClickTime - _preClickTime;
				if(_timeDif < 1000){
					return false;
				}
			} else {
				_this.attr("preClickTime", _nowClickTime);
			}
			auctSuccess(_url);
		} else {
			window.open(domain + '/topicItem/itemDetails/auction/' + _tiId);
		}
	});
	
	// 申请购车按钮点击事件
	$("#purchaseApplyBtn").on('click', function (){
		var _tiId = $("#tiId").val();
		var _tiId = $("#tiId").val();
		var _url = domain + "/user/bis/purchaseApply/" + _tiId;
		$.ajax({
			url : _url,
			method : 'GET',
			dataType : 'html',
			data : {reqTime: new Date().getTime()},
			cache : false,
			success: function(data, status, xhr) { 
		        var errorCode = xhr.getResponseHeader("errorCode");
		        if('999' == errorCode) { // 未登陆
		        	needLoginAjax();
		        } else { // 已经登陆
		        	var res = JSON.parse(data);
		        	console.log(res);
		        	if(1 == res.result){
		        		layer.msg("申请购车成功", {time: 2000}, function(){
		        			window.parent.location.reload();
		        			window.parent.layer.close(parent.lgn_pg_ii);
		        		});
		        	} else {
		        		layer.alert(res.message);
		        	}
		        }
		    }
		});
	});
	
	// 个人中心我的兑换点击事件
	$(".itemExchanged").on('click', function(){
		var _this = $(this);
		var _recordId = _this.attr("rdId");
		var _url = domain + "/user/bis/exchangeOrderDetail/" + _recordId;
		// 防止点击过快,导致重复弹框
		var _preClickTime = _this.attr("preClickTime");
		var _nowClickTime = new Date().getTime();
		if(undefined != _preClickTime && '' != _preClickTime){
			_this.attr("preClickTime", _nowClickTime);
			var _timeDif = _nowClickTime - _preClickTime;
			if(_timeDif < 1000){
				return false;
			}
		} else {
			_this.attr("preClickTime", _nowClickTime);
		}
		$.ajax({
			url : _url,
			method : 'GET',
			dataType : 'html',
			data : {reqTime: new Date().getTime()},
			cache : false,
			success: function(data, status, xhr) { 
		        var errorCode = xhr.getResponseHeader("errorCode");
		        if('999' == errorCode) { // 未登陆
		        	needLoginAjax();
		        } else { // 已经登陆
		        	lgn_pg_ii = window.parent.layer.open({
		        		type: 2,
		        		title: "我兑换的商品",
		        		resize: false,
		        		//scrollbar: false,
		        		//fixed: false,
		        		move:false,
		        		shade: 0.1,
		        		anim: 5,
		        		content: _url,
		        		area: ['600px', '380px']
		        	});
		        }
		    }
		});
	});
		
});
	
function auctSuccess(_url){
	$.ajax({
		url : _url,
		method : 'GET',
		dataType : 'html',
		data : {reqTime: new Date().getTime()},
		cache : false,
		success: function(data, status, xhr) { 
	        var errorCode = xhr.getResponseHeader("errorCode");
	        if('999' == errorCode) { // 未登陆
	        	needLoginAjax();
	        } else { // 已经登陆
	        	lgn_pg_ii = window.parent.layer.open({
	        		type: 2,
	        		title: "我拍得的汽车",
	        		resize: false,
	        		//scrollbar: false,
	        		//fixed: false,
	        		move:false,
	        		shade: 0.1,
	        		anim: 5,
	        		content: _url,
	        		area: ['600px', '470px']
	        	});
	        }
	    }
	});
}