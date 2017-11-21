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
		$("#temFileIds :input").each(function(i,v){
			console.log(v);
			var _id = $(v).attr("id");
			fileIds.push(_id);
		});
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
	

});

function getData(params){
	params.itemTitle = $("#itemTitle").val();
	params.marketPrice = $("#marketPrice").val();
	params.itemType = $("#itemType").val();
	params.status = $("#status").val();
	params.remark = $("#remark").val();
	params.description = $("#description").val();
}

