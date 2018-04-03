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
			_param.id = _eoId;
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
		layer.confirm("确认购车？", function (){
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
	
	// 确认发货
	$(".shipments").on('click', function (){
		var _this=$(this);
		var _erId = _this.attr('rId');
		addTab("confirm_shipments_" + _erId,"发货确认","/auctionManager/confirmShipments.htm?recordId=" + _erId + "&iframeName=" + window.name);
	});
	
	// 确认签收
	$(".consignee").on('click', function (){
		var _this=$(this);
		var _eoId = _this.attr('param');
		var _estatus = _this.attr("estatus");
		layer.confirm("确认已签收？", function(){
			$.post('saveShipmentsInfo.htm', {
				"type" : "02",
				"id" : _eoId,
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
	
	/**
	 * 取消按钮 关闭当前tab页
	 */
	$("#btnCancel").on('click',function(){
		var _param = $(this).attr("param");
		parent.window.closeTab("confirm_shipments_" + _param);
	});
	
	/**
	 * 选择快递公司按钮
	 */
	$("#selectExpress").on('click', function (){
		pageii = layer.open({
			type : 2,
			title : "物流公司列表",
			area : [ '850px', '650px' ],
			content : [domain + '/expressInfo/list', 'no']
		});
	});
	
	/**
	 * 确认发货
	 */
	$("#saveShipmentsStatus").on('click', function() {
		var _id = $("#exchangeOrderId").val(),
			_expressId = $("#expressId").val(),
			_name = $("#name").val(),
			_waybillNo = $("#waybillNo").val()
			_remark = $("#remark").val();
		if(undefined == _id || null == _id || '' == _id){
			layer.alert("服务器异常", {icon : 0});
			return;
		}
		if (undefined == _expressId || null == _expressId
				|| '' == _expressId || undefined == _name
				|| null == _name || '' == _name) {
			layer.alert("必须选择一个公司", {icon : 0});
			return;
		}
		if(undefined == _waybillNo || null == _waybillNo || '' == _waybillNo){
			layer.alert("必须输入快递单号", {icon : 0});
			return;
		}
		var _params = {};
		_params.shipmentsStatus = '02'; // 置为已发货状态
		_params.id = _id;
		_params.name = _name;
		_params.expressId = _expressId;
		_params.waybillNo = _waybillNo;
		_params.remark = _remark;
		
		layer.confirm("确认发货？", function(){
			$.post('saveShipmentsInfo.htm', _params, function(res) {
				if (res.result == 1) {
					layer.msg(res.message, {
						time : 1000
					}, function() {
						var listIframeName = $("#listIframeName").val();
						parent.window.frames[listIframeName].location.reload();
						$("#btnCancel").trigger('click');
					});
				} else {
					layer.msg(res.message, {
						time : 1000
					});
				}
			}, 'json');
		});
		
	});
	
	
	/**
	 * 查看物流信息
	 */
	$(".expressage a").on('click',function() {
		var _this = $(this);
		var _eoId = _this.attr("eoId"), _waybillNo = _this.text();
		if (undefined == _eoId || null == _eoId || '' == _eoId
				|| undefined == _waybillNo || null == _waybillNo
				|| '' == _waybillNo) {
			layer.msg("系统异常", {time : 1500});
			return;
		}

		$.post('checkConsignment.htm', {"exchangeOrderId" : _eoId}, function(res) {
			if (res.result == 1) {
				layer.open({
					type : 2,
					title : '物流信息跟踪',
					shade : 0.3,
					maxmin : true,
					fix : false,
					scrollbar : false,
					area : [ '900px', '550px' ],
					content : 'shipmentsInfo.htm?exchangeOrderId=' + _eoId + '&waybillNo=' + _waybillNo,
				});
			} else {
				layer.msg(res.message, {
					time : 2000
				});
			}
		}, 'json');

	});
	

});