var liVmAccount;
var pageii;
$(function() {

	$("#modifyVmAccountPwd").on('click', function() {
		liVmAccount = layer.open({
			type : 1,
			title : '修改虚拟账号密码',
			fixed : false,
			move : false,
			area : [ '330px', '270px' ],
			content : $("#mfVmAccountBox")
		});
	});

	// 编辑
	$(".editBtn").on('click', function() {
		var _userId = $(this).attr('param');
		pageii = layer.open({
			type : 2,
			title : '修改虚拟账号信息',
			fixed : false,
			move : false,
			scrollbar : false,
			area : [ '500px', '450px' ],
			content : domain + "/strategy/edit?userId=" + _userId
		});
	})

	$("#updateVmAccountBtn").on('click', function() {
		var _currency = $("#totalCurrency").val();
		if (undefined == _currency || null == _currency
				|| "" == _currency || _currency <= 0) {
			layer.alert("西币值必须大于0");
			return;
		}

		$.ajax({
			url : "updateVmAccount",
			type : 'POST',
			dataType : "json",
			data : $("#vmAccountEditForm").serialize(),
			success : function(data) {
				if (1 == data.result) {
					window.parent.location.reload();
					layer.msg("修改成功", {time : 1000}, function() {
						window.layer.close(pageii);
					});
				} else {
					layer.alert(data.message);
				}
			}
		});
	});

});