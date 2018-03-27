var pageii;
$(function() {

	// 编辑备注点击事件
	$(".viewremark").on('click', function() {
		var _this = $(this);
		var _type = _this.attr('type');
		var _title = "";
		if (_type == '01') { // 竞拍备注操作
			var _pcId = _this.attr('param');
			$("#purchaseId").val(_pcId);
			_title = '竞拍备注信息';
		} else { // 兑换备注操作
			var _eoId = _this.attr('param');
			$("#exchangeOrderId").val(_eoId);
			_title = '兑换备注信息';
		}
		var _remark = _this.parent().prev().text();
		_remark = $.trim(_remark);
		$("#remark").val(_remark);
		pageii = layer.open({
			type : 1,
			title : _title,
			area : [ '400px', '300px' ],
			content : $("#editRemarkBox")
		});
	});

	// 保存备注信息
	$("#saveRemarkBtn").on('click', function() {
		var _remark = $("#remark").val();
		if (undefined == _remark || null == _remark || '' == _remark) {
			layer.msg("备注信息不能为空", {
				time : 1000
			});
			return;
		}
		var _param = {}, _url;
		var _type = $(this).attr('type');
		if (_type == '01') { // 竞拍保存备注
			_url = "savePurchaseInfo.htm";
			var _pcId = $("#purchaseId").val();
			_param.purchaseId = _pcId;
			_param.type = '01'; // 此处type表示编辑备注而不是编辑购车状态,为固定值,用于后台操作区分
			_param.remark = _remark;
		} else { // 兑换保存备注
			_url = "saveShipmentsInfo.htm";
			var _eoId = $("#exchangeOrderId").val();
			_param.exchangeOrderId = _eoId;
			_param.type = '01'; // 此处type表示编辑备注而不是发货车状态,为固定值,用于后台操作区分
			_param.remark = _remark;
		}

		$.post(_url, _param, function(res) {
			if (res.result == 1) {
				layer.msg(res.message, {
					time : 1000
				}, function() {
					window.location.reload();
					window.layer.close(pageii);
				});
			} else {
				layer.msg(res.message, {
					time : 1000
				});
			}
		}, 'json');
	});

	// 编辑购车状态点击事件
	$(".viewPurchaseStatus").on('click', function() {
		var _this=$(this);
		var _pcId = _this.attr('param');
		var _pstatus = _this.attr('pstatus');
		layer.confirm("确认？", function (){
			$.post('savePurchaseInfo.htm', {
				"type" : "02",
				"purchaseId" : _pcId,
				"purchaseStatus" : _pstatus
			}, function(res) {
				if (res.result == 1) {
					layer.msg(res.message, {
						time : 1000
					}, function() {
						window.location.reload();
						window.layer.close(pageii);
					});
				} else {
					layer.msg(res.message, {
						time : 1000
					});
				}
			}, 'json');
		});
	});
	
	// 编辑发货状态
	$(".shipments").on('click', function (){
		var _this=$(this);
		var _eoId = _this.attr('param');
		var _estatus = _this.attr("estatus");
		layer.confirm("确认？", function(){
			$.post('saveShipmentsInfo.htm', {
				"type" : "02",
				"exchangeOrderId" : _eoId,
				"shipmentsStatus" : _estatus
			}, function(res) {
				if (res.result == 1) {
					layer.msg(res.message, {
						time : 1000
					}, function() {
						window.location.reload();
						window.layer.close(pageii);
					});
				} else {
					layer.msg(res.message, {
						time : 1000
					});
				}
			}, 'json');
		});
	});

});