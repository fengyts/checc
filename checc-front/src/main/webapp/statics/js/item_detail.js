var o;
var mss;
var _timeFlag = false;
$(document).ready(function() {
	// o = $("#countDownTime");
	// mss = $("#countDownTimeOrigin").text() / 1000;
	// countDownTime(o);
	cdt();

	// 出价或兑换按钮点击事件
	$("#auc_action").on('click', function() {
		var _auctionType = $("#auctionType").val();
		var _tpId = $("#tpId").val();
		$.ajax({
			url : domain + '/topicItem/auctionAction/' + _auctionType + "/" + _tpId,
			method : 'GET',
			dataType : 'html',
			data : {},
			cache : false,

			success: function(data, status, xhr) { 
		        console.log(xhr.getResponseHeader("Content-Type"));
		        var errorCode=xhr.getResponseHeader("errorCode");
				console.log(errorCode);
		        console.log(xhr);
		        if('999' == errorCode) {
		        	layer.open({
		        		type: 2,
		        		title: '请先登录',
		        		content: domain + '/user/login',
		        		area: ['700px', '500px']
		        	});
		        }
		    }

		});
	});

});

function cdt() {
	o = $("#countDownTime");
	mss = $("#countDownTimeOrigin").text() / 1000;
	countDownTime(o);
}

function countDownTime(o) {
	if (mss < 0) {
		_timeFlag = true;
		return;
	}
	setTimeout(function() {
		formatTime(mss, o);
		mss--;
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
