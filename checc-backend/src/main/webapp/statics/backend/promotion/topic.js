var pageii;
$(function(){
	
	$("#addTopic").on('click', function(){
//		addTab("topic_add","新增专题活动","/topic/add.htm?iframeName=" + window.name);
		pageii = layer.open({
			type : 2,
			title : '专题添加',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '1000px', '550px' ],
			content : domain + '/topic/add.htm',
		});
	});
	
	$(".editBtn").on('click', function(){
		var topicId = $(this).attr("param");
		pageii = layer.open({
			type : 2,
			title : '专题编辑',
			// shadeClose : true,
			shade : 0.3,
			maxmin : true,
			fix : false,
			scrollbar : false,
			area : [ '1000px', '550px' ],
			content : domain + '/topic/edit.htm?id=' + topicId,
		});
	});
	
	// 取消按钮
//	$("#cancelTabBtn").on('click',function(){
//		var type = $(this).attr("param");
//		if(type){
//			parent.window.closeTab("topic_edit"); // 关闭编辑tab页
//		}else{
//			parent.window.closeTab("topic_add"); // 关闭新增tab页
//		}
//	});
	
//	$(".editBtn").on('click', function(){
//		var topicId = $(this).attr("param");
//		addTab("topic_edit","编辑专题活动","/topic/edit.htm?topicId=" + topicId + "&iframeName=" + window.name);
//	});
	
	// 选择图片时生成缩略图预览
	$("#image").change(function(e) {
		$("#imgChanged").val('1');
        var file = e.target.files[0];
        imgview(file);
    });
	
	// 查看关联商品
	$(".associateItemBtn").on('click', function(){
		var topicId = $(this).attr("param"), 
		    name = $(this).attr("param1");
		addTab("topic_associateItem", "专题关联商品" , "/topicItem/list?topicId=" + topicId 
				+ "&name=" + name + "&iframeName=" + window.name);
	});
	
	// 固定期限时,放开专题结束时间可编辑
//	$("#durationType").change(this, function(v){
//		if(this.value == 0){
//			$("#endTime").attr('disabled', false);
//		}else {
//			$("#endTime").attr('disabled', true);
//		}
//	});
	
	
	$("#saveBtn").on('click',function(){
		var _data = $("#topicAddForm").serializeArray();
//		var _o = $.formDataJson("#topicAddForm");
		
		$.ajax({
			url: 'save',
			type: 'POST',
			secureuri: false,
//			fileElementId: 'image',
			dataType: "text",
//			data: { "data" : _o },
			data: _data,
			success: function(response) {
//				layer.close(_loading);
				var data = JSON.parse(response);
				if (data.result == 1) {
					layer.alert(data.message, {icon:1}, function(){
//						var listIframeName = $("#listIframeName").val();
//						parent.window.frames[listIframeName].location.reload();
//						parent.window.closeTab("topic_add");
						parent.window.location.reload();
						parent.layer.close(parent.pageii);
					});
				} else {
					layer.alert(data.message, {icon:0});
				}
			},
			error: function() {
//				layer.close(_loading);
				layer.alert("请求失败", 1);
			}
		});
		
	});
	
	
	$("#updateTopicBtn").on('click', function(){
		var _data = $("#topicEditForm").serializeArray();
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
	
	
});


function imgview1(file) {
    var img = new Image(), url = img.src = URL.createObjectURL(file);
    var $img = $(img);
    img.onload = function() {
        URL.revokeObjectURL(url);
        $('#preview').empty().append($img);
    }
}
/**
 * 上传图片预览
 * @param file
 */
function imgview(file) {
	if(typeof FileReader == 'undefined'){
		$('#preview').append('不能预览');
		return false;
	}
    var reader = new FileReader();
    reader.onload = function(e) {
        var $img = $('<img width="100px" heigth="100px">').attr("src", e.target.result);
        $('#preview').empty().append($img);
    }
    reader.readAsDataURL(file);
}

