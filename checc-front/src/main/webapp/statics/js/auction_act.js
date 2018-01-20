var _times = 1, // 用户输入的竞拍出价次数
_auctionMaxTimes = 10, // 允许最大出价次数
_auctionCurrency = 1, // 单次竞拍需要的西币
_useableCurrency = 0; // 用户可用西币

$(document).ready(function() {
	_useableCurrency = parseInt($("#useableCurrency").val());
	_auctionCurrency = parseInt($("#auctionCurrency").val());
	_times = parseInt($("#auctionTimes").val());
	_auctionMaxTimes = parseInt($("#auctionMaxTimes").val());

	getTotalCurrency();

	if (_times <= 1) {
		$("#auct_reduce").css('cursor', 'not-allowed');
	}
	if (_times >= _auctionMaxTimes) {
		$("#auct_increase").css('cursor', 'not-allowed');
	}

	// 处理次数输入
	$("#auctionTimes").on('blur', function() {
		var _this = $(this);
		var _ti = parseInt(_this.val());
		if (_ti <= 1) {
			_times = 1;
			_this.val(1).attr('value', 1);
			$("#auct_reduce").css('cursor', 'not-allowed');
			$("#auct_increase").css('cursor', 'pointer');
			// $("#act_bid_btn").attr('disabled', false);
			getTotalCurrency();
			return;
		}
		if (_ti >= _auctionMaxTimes) {
			_times = _auctionMaxTimes;
			_this.val(_auctionMaxTimes).attr('value', _auctionMaxTimes);
			$("#auct_increase").css('cursor', 'not-allowed');
			$("#auct_reduce").css('cursor', 'pointer');
			// $("#act_bid_btn").attr('disabled', 'disabled');
			getTotalCurrency();
			return;
		}
		getTotalCurrency(_ti);
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
			getTotalCurrency();
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
			getTotalCurrency();
		}
	});

	// 放弃按钮
	$("#act_abandon_btn").on('click', function() {
		parent.layer.close(parent.layer.index);
		// parent.layer.close(parent.layer.getFrameIndex(window.name));
	});

	// 竞拍
	$("#act_bid_btn").on('click', function() {
		var _disabled = $(this).attr('disabled');
		if (_disabled == 'disabled') {
			return;
		}
		auctionAction();
	});

	// 兑换
	$("#act_exchange_btn").on('click', function() {
		var _disabled = $(this).attr('disabled');
		if (_disabled == 'disabled') {
			return;
		}
		exchangeAction();
	});

});

/**
 * 获取需要花费的西币总数,判断用户可用西币是否足够
 * 
 * @param times
 */
function getTotalCurrency(_t) {
	if (_t) {
		_times = _t;
	}
	var _totalCu = _times * _auctionCurrency;
	var _typeFlag = true;
	if ($("#typeId").val() == 'exchange') {
		_typeFlag = false;
		_totalCu = parseInt($("#exchangeAmount").val());
	}
	$("#totalCurrencyView").text(_totalCu);
	$("#totalCurrency").val(_totalCu).attr('value', _totalCu);

	if (_useableCurrency >= _totalCu) {
		$("#user_currency_info").css('display', 'none');
		$("#act_bid_btn, #act_exchange_btn").removeClass('act_unbid_btn');
		$("#act_bid_btn, #act_exchange_btn").attr('disabled', false);
		if (_typeFlag) {
			$("#act_bid_btn").addClass('act_bid_btn');
			$("#act_bid_btn a").text("出价");
		} else {
			$("#act_exchange_btn").addClass('act_exchange_btn');
			$("#act_exchange_btn a").text("兑换");
		}
	} else {
		$("#user_currency_info").css('display', 'inline-block');
		$("#act_bid_btn, #act_exchange_btn").addClass('act_unbid_btn');
		$("#act_bid_btn, #act_exchange_btn").attr('disabled', 'disabled');
		// $("#act_bid_btn #act_exchange_btn").css('pointer-events', 'none');
		$("#act_bid_btn a, #act_exchange_btn a").text("西币不足");
		if (_typeFlag) {
			$("#act_bid_btn").removeClass('act_bid_btn');
			$("#act_bid_btn a").text("出价");
		} else {
			$("#act_exchange_btn").removeClass('act_exchange_btn');
			$("#act_exchange_btn a").text("兑换");
		}
	}
}

function countDownTime(mss) {
	if (mss < 0) {
		window.parent.location.reload();
		window.parent.layer.close(parent.lgn_pg_ii);
		return;
	}
	setTimeout(function() {
		--mss;
		countDownTime(mss);
	}, 1000);
}

// 竞拍
function auctionAction() {
	var _auctactTK = $("#auctactTK").val(), 
		_tpId = $("#tpId").val(), 
		_auctionTimes = $("#auctionTimes").val(), 
		_totalCurrency = $("#totalCurrency").val();
	var _params = {};
	_params.auctactTK = _auctactTK;
	_params.tpId = _tpId;
	_params.auctionTimes = _auctionTimes;
	_params.totalCurrency = _totalCurrency;

	$.ajax({
		url : domain + '/auction/auctionAct',
		method : 'POST',
		cache : false,
		dataType : 'json',
		data : _params,
		success : function(data, status, xhr) {
			var _tic = 2;
			if (data.result != 1) {
				layer.msg("出价失败：" + data.message, {
					time : 3000
				});
				countDownTime(_tic);
			} else {
				layer.msg('您已成功出价该商品! 当前处于领先状态');
				countDownTime(_tic);
			}
		}
	});

}

// 兑换
function exchangeAction() {
	var _auctactTK = $("#auctactTK").val(), 
		_tpId = $("#tpId").val(), 
		_totalCurrency = $("#exchangeAmount").val();
	var _params = {};
	_params.auctactTK = _auctactTK;
	_params.tpId = _tpId;
	_params.totalCurrency = _totalCurrency;
	
	$.ajax({
		url : domain + '/auction/exchangeAct',
		method : 'POST',
		cache : false,
		dataType : 'json',
		data : _params,
		success : function(data, status, xhr) {
			var _tic = 2;
			if (data.result != 1) {
				//window.parent.layer.close(parent.lgn_pg_ii);
				parent.layer.msg("兑换失败：" + data.message, {
					time : 3000
				});
				countDownTime(_tic);
			} else {
				layer.msg('您已成功兑换该商品! 请至个人中心查看');
				countDownTime(_tic);
			}
		}
	});
}
