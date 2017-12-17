var _times = 1, // 用户输入的竞拍次数
_auctionMaxTimes = 10, // 允许最大次数
_auctionCurrency = 1, // 单次竞拍需要的西币
_useableCurrency = 0; // 用户可用西币

$(document).ready(function() {
	_useableCurrency = $("#useableCurrency").val();
	_auctionCurrency = $("#auctionCurrency").val();
	_times = $("#auctionTimes").val();
	_auctionMaxTimes = $("#auctionMaxTimes").val();

	getTotalCurrency(_times);

	if (_times <= 1) {
		$("#auct_reduce").css('cursor', 'not-allowed');
	}
	if (_times >= _auctionMaxTimes) {
		$("#auct_increase").css('cursor', 'not-allowed');
	}

	// 处理次数输入
	$("#auctionTimes").on('blur', function() {
		var _this = $(this);
		var _ti = _this.val();
		if (_ti <= 1) {
			_times = 1;
			_this.val(1).attr('value', 1);
			$("#auct_reduce").css('cursor', 'not-allowed');
			$("#auct_increase").css('cursor', 'pointer');
			getTotalCurrency(_times);
			return;
		}
		if (_ti >= _auctionMaxTimes) {
			_times = _auctionMaxTimes;
			_this.val(_auctionMaxTimes).attr('value', _auctionMaxTimes);
			$("#auct_increase").css('cursor', 'not-allowed');
			$("#auct_reduce").css('cursor', 'pointer');
			getTotalCurrency(_times);
			return;
		}
		getTotalCurrency(_times);
	});

	// 次数减
	$("#auct_reduce").on('click', function(event) {
		var _this = $(this);
		if (_times <= 1) {
			_this.css('cursor', 'not-allowed');
			event.preventDefault();
		} else {
			--_times;
			$("#auctionTimes").val(_times).attr('value', _times);
			$("#auct_increase").css('cursor', 'pointer');
			getTotalCurrency(_times);
		}
	});

	// 次数加
	$("#auct_increase").on('click', function(event) {
		var _this = $(this);
		if (_times >= _auctionMaxTimes) {
			_this.css('cursor', 'not-allowed');
			event.preventDefault();
		} else {
			++_times;
			$("#auctionTimes").val(_times).attr('value', _times);
			$("#auct_reduce").css('cursor', 'pointer');
			getTotalCurrency(_times);
		}
	});

	// 放弃按钮
	$("#act_abandon_btn").on('click', function() {
		parent.layer.close(parent.layer.index);
//		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});

});

/**
 * 获取需要花费的西币总数,判断用户可用西币是否足够
 * 
 * @param times
 */
function getTotalCurrency(times) {
	var _totalCu = _times * _auctionCurrency;
	$("#totalCurrencyView").text(_totalCu);
	$("#totalCurrency").val(_totalCu).attr('value', _totalCu);

	if (_useableCurrency >= _totalCu) {
		$("#user_currency_info").css('display', 'none');
		$("#act_bid_btn").removeClass('act_unbid_btn');
		$("#act_bid_btn").addClass('act_bid_btn');
		$("#act_bid_btn a").text("出价");
	} else {
		$("#user_currency_info").css('display', 'inline-block');
		$("#act_bid_btn").removeClass('act_bid_btn');
		$("#act_bid_btn").addClass('act_unbid_btn');
		$("#act_bid_btn a").text("西币不足");
	}
}
