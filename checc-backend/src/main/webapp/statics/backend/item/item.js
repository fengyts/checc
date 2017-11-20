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
	$("#cancelTabBtn").on('click',function(){
		parent.window.closeTab("item_add");
	});
	
	$("#saveBtn").on('click', function(){
		var params = {};
		getData(params);
		var fileIds = new Array();
		$.ajaxFileUpload({
			url:'saveItem',
			type: 'POST',
			secureuri: false,
			fileElementId: fileIds,
			dataType: 'json',
			data: params,
			success: function(res) {
				console.log();
			}
		});
	});
	

});

function getData(params){
	params.itemTitle = $("#itemTitle").val();
	params.marketPrice = $("#marketPrice").val();
	params.itemType = $("#itemType").val();
	params.status = $("#status").val();
	params.remark = $("#remark").val();
	params.description = $("#description").val();
}

