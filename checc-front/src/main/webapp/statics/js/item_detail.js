var o;
var mss;
var _timeFlag = false;
$(document).ready(function() {
	
	var _t = $("#tpId").val();
	if(_t){
		cdt();
	}

	// 出价或兑换按钮点击事件
	$("#auc_action").on('click', function() {
		var _auctionType = $("#auctionType").val();
		var _tpId = $("#tpId").val();
		var _url = '', _title = '';
		if('01' == _auctionType){
			_url = domain + '/auction/auctionAction/' + _tpId;
			_title = '竞拍';
		} else {
			_url = domain + '/auction/exchangeAction/' + _tpId;
			_title = '兑换';
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
		        	lgn_pg_ii = layer.open({
		        		type: 2,
		        		title: _title,
		        		resize: false,
		        		//scrollbar: false,
		        		//fixed: false,
		        		move:false,
		        		shade: 0.1,
		        		anim: 5,
		        		content: _url,
		        		area: ['600px', '350px']
		        	});
		        }
		    }

		});
	});
	
	
	// 查看哪些人兑换了
	$("#allExchange").on('click', function(){
		var _url = domain + "/auction/exchangeList?tpId=" + $(this).attr('param');
		
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
		        	lgn_pg_ii = layer.open({
		        		type: 1,
		        		title: "哪些人兑换了",
		        		resize: false,
		        		//scrollbar: false,
		        		//fixed: false,
		        		move:false,
		        		shade: 0.1,
		        		anim: 5,
		        		area: ['500px', '400px'],
		        		content: data
		        	});
		        }
		    }

		});
		
	});

});

/**
 * 倒计时
 */
function cdt() {
	o = $("#countDownTime");
	mss = $("#countDownTimeOrigin").text() / 1000;
	countDownTime(o);
}

function countDownTime(o) {
	if (mss < 0) {
		_timeFlag = true;
		$("#auc_action").css('display', 'none');
		$("#auc_action_over").css('display', 'inline');
		return;
	}
	setTimeout(function() {
		formatTime(mss, o);
		--mss;
		countDownTime(o);
	}, 1000);
}

function formatTime(mss, o) {
	var ss = 1, mi = ss * 60, hh = mi * 60, dd = hh * 24;
	var _days = Math.floor(mss / dd);
	var _hours = Math.floor((mss % dd) / hh);
	var _minutes = Math.floor((mss % hh) / mi);
	var _seconds = Math.floor((mss % mi) / ss);

	if (_days == 0) {
		_days = "";
	} else if (_days > 0 && _days < 10) {
		_days = "0" + _days + "天";
	} else {
		_days += "天";
	}

	if (_hours < 10) {
		_hours = "0" + _hours;
	}
	if (_minutes < 10) {
		_minutes = "0" + _minutes;
	}
	if (_seconds < 10) {
		_seconds = "0" + _seconds;
	}

	// var ctr = _days + _hours + _minutes + _seconds + "秒";
	var ctr = _days + _hours + "小时" + _minutes + "分" + _seconds + "秒";
	o.text(ctr);
}
