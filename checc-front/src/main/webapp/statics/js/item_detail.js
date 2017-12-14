var o;
var mss;
var _timeFlag = false;
$(document).ready(function() {
	o = $("#countDownTime");
	mss = $("#countDownTimeOrigin").text() / 1000;
	countDownTime(o);
});

function countDownTime(o) {
	if(mss < 0) {
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
	
	if(_days < 10){ _days = "0" + _days; }
	if(_hours < 10){ _hours = "0" + _hours; }
	if(_minutes < 10){ _minutes = "0" + _minutes; }
	if(_seconds < 10){ _seconds = "0" + _seconds; }
	
	var ctr = _days + "天" + _hours + "小时" + _minutes + "分" + _seconds + "秒";
	o.text(ctr);
}
