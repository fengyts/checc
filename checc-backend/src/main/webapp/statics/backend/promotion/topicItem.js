var pageii;
$(function() {
	
	$("#addTopicItem").on('click', function(){
		var _topicId = $("#topicId").val();
		pageii = layer.open({
			type : 2,
			title : '专题商品添加',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '1000px', '600px' ],
			content : domain + '/topicItem/add.htm?topicId=' + _topicId,
		});
	});
	
	$(".editTopicItemBtn").on('click', function(){
		var _disable = $(this).attr("disabled");
		if(_disable){
			return;
		}
		var _id = $(this).attr("param");
		pageii = layer.open({
			type : 2,
			title : '专题商品编辑',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '1000px', '600px' ],
			content : domain + '/topicItem/edit.htm?id=' + _id,
		});
	});
	
	$("#selectItem").on('click', function() {
		pageii == layer.open({
			type : 2,
			title : '商品列表',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '800px', '450px' ],
			content : domain + '/topicItem/itemList.htm',
		});
	});
	
	
	$("#saveTopicItemBtn").on('click', function() {
		var _data = $("#topicItemAddForm").serializeArray();
		$.ajax({
			url: 'save',
			type: 'POST',
			secureuri: false,
			dataType: "text",
			data: _data,
			success: function(response) {
				var data = JSON.parse(response);
				if (data.result == 1) {
					layer.alert(data.message, {icon:1}, function(){
						parent.window.location.reload();
						parent.layer.close(parent.pageii);
					});
				} else {
					layer.alert(data.message, {icon:0});
				}
			},
			error: function() {
				layer.alert("请求失败", 1);
			}
		});
	});
	
	
	$("#updateTopicItemBtn").on('click', function() {
		var _data = $("#topicItemEditForm").serializeArray();
		$.ajax({
			url: 'update',
			type: 'POST',
			secureuri: false,
			dataType: "text",
			data: _data,
			success: function(response) {
				var data = JSON.parse(response);
				if (data.result == 1) {
					layer.alert(data.message, {icon:1}, function(){
						parent.window.location.reload();
						parent.layer.close(parent.pageii);
					});
				} else {
					layer.alert(data.message, {icon:0});
				}
			},
			error: function() {
				layer.alert("请求失败", 1);
			}
		});
	});
	
	
	
	
	/**
	 * 
	 * itemList js
	 * 
	 */
//	$("#selectAll").on('click', function(){
//		if($(this).is(":checked")){
//			$("#itemListData :checkbox").prop("checked", true);
//		} else {
//			$("#itemListData :checkbox").prop("checked", false);
//		}
//	});
//	$("input[name='checkItem']").on('click', function(){
//		if( $("#itemListData :checkbox:checked").length == $("#itemListData :checkbox").length ){
//			$("#selectAll").prop("checked", true);
//		} else {
//			$("#selectAll").prop("checked", false);
//		}
//	});
	
	$("#checkItemConfirmBtn").on('click', function(){
		var _checkBox = $("#itemListData :radio:checked");
		if( _checkBox.length < 1 ){
			layer.alert("必须选择一个商品",{icon : 0});
			return;
		}
		var _trs = _checkBox.parent().nextAll();
		var _itemId = _checkBox.val();
		var _itemTitle = $(_trs[0]).text();
		var _marketPrice = $(_trs[1]).text();
		window.parent.$("#itemId").val(_itemId);
		window.parent.$("#itemTitle").val(_itemTitle);
		window.parent.$("#marketPrice").val(_marketPrice);
		
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
	});
	
	
});