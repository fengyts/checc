var pageii;
$(function() {

	// 编辑备注点击事件
	$(".viewremark").on('click', function() {
		var _pcId = $(this).attr('param');
		var _remark = $(this).parent().prev().text();
		_remark = $.trim(_remark);
		$("#remark").val(_remark);
		$("#purchaseId").val(_pcId);
		pageii = layer.open({
			type : 1,
			title : '购车备注信息',
			area : [ '400px', '300px' ],
			content : $("#editRemarkBox")
		});
	});

	// 保存备注信息
	$("#saveRemarkBtn").on('click', function() {
		var _pcId = $("#purchaseId").val();
		var _remark = $("#remark").val();
		if (undefined == _remark || null == _remark || '' == _remark) {
			layer.msg("备注信息不能为空", {
				time : 1000
			});
			return;
		}
		$.post('savePurchaseInfo.htm', {
			"type" : "01",
			"purchaseId" : _pcId,
			"remark" : _remark
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

	// 编辑购车状态点击事件
	$(".viewPurchaseStatus").on('click', function() {
		var _pcId = $(this).attr('param');
		var _pstatus = $(this).attr('pstatus');
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