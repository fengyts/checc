var pageii;

$(function() {
	
	/**
	 * 商品新增
	 */
	$("#addItemInfo").on('click',function(){
		addTab("item_add","商品新增","/item/addItem.htm?iframeName=" + window.name);
	});
	/**
	 * 新增页面取消按钮 关闭当前tab页
	 */
	$("#cancelAddTabBtn").on('click',function(){
		parent.window.closeTab("item_add");
	});
	
	/**
	 * 商品编辑
	 */
	$(".editBtn").on('click', function(){
		var itemId = $(this).attr("param");
		addTab("item_edit","商品编辑","/item/editItem.htm?iframeName=" + window.name 
				+ "&itemId=" + itemId);
	})
	/**
	 * 编辑页面取消按钮 关闭当前tab页
	 */
	$("#cancelEditTabBtn").on('click',function(){
		parent.window.closeTab("item_edit");
	});
	
	
	$("#saveBtn").on('click', function(){
		var _data = $("#itemInfoAddForm").serialize();
		$.ajax({
			url:'saveItem',
			type: 'POST',
			secureuri: false,
			dataType: 'text',
			data: _data,
			success: function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {icon:1}, function() {
						//'mainIframe_tabli_14'
						var listIframeName = $("#listIframeName").val();
						parent.window.frames[listIframeName].location.reload();
						parent.window.closeTab("item_add");
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	
	$("#updateBtn").on('click', function(){
		var _data = $("#itemInfoEditForm").serialize();
		$.ajax({
			url:'updateItem',
			type: 'POST',
			secureuri: false,
			dataType: 'text',
			data: _data,
			success: function(res) {
				var data = JSON.parse(res);
				if (1 == data.result) {// 成功
					layer.alert(data.message, {icon:1}, function() {
						//'mainIframe_tabli_14'
						var listIframeName = $("#listIframeName").val();
						parent.window.frames[listIframeName].location.reload();
						parent.window.closeTab("item_edit");
					});
				} else {// 失败
					layer.alert(data.message, 8);
				}
			}
		});
	});
	

});


