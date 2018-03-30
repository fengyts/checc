var pageii;
$(function() {
	
	/**
	 * 选择快递公司确认按钮
	 */
	$("#checkedConfirmBtn").on('click', function() {
		var _checkBox = $("#expressDataList :radio:checked");
		if( _checkBox.length < 1 ){
			layer.alert("必须选择一个公司", {icon : 0});
			return;
		}
		
		var _tds = _checkBox.parent().nextAll();
		var _eId = $(_tds[0]).text(), _eName = $(_tds[1]).text();
		window.parent.$("#expressId").val(_eId);
		window.parent.$("#name").val(_eName);
		
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
	});
	
})