$(function() {

	$(".dpl_d_box").on('click', function() {
		var _this = $(this);
		$(".dpl_d_box").removeClass('dpl_d_box_checked');
		_this.addClass('dpl_d_box_checked');
		var _index = _this.attr('index');
		// var _dpamt = $("#dpamt_" + _index).val();
		$("#dpInfo").empty().text($("#dpamt_" + _index).val());
		$("#dpInfoMo").empty().text($("#dpamtm_" + _index).val());
		
		$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'inline');
		//$("#dpamtInputErr").css('display', 'none');
		$("#discountId").val($("#discountId_" + _index).val());
	});

	$("#otherAmount").on('focus', function() {
		$(".dpl_d_box").removeClass('dpl_d_box_checked');
		$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'none');
	}).on('blur', function(){
		var _v = $(this).val();
		var reg = /(^[+]?[0-9]+(\.[0-9]{1,2})?)$/;
		if(_v && reg.test(_v)){
			_v = parseFloat(_v);
			//$("#dpamtInputErr").css('display', 'none');
			$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'inline');
			$("#dpInfo").empty().text(Math.floor(_v));
			$("#dpInfoMo").empty().text(_v.toFixed(2));
			$("#discountId").val(0);
		} else {
			//$("#dpamtInputErr").css('display', 'inline');
			$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'none');
			$("#dpInfo").empty();
			$("#dpInfoMo").empty();
			$("#discountId").val(-1);
		}
	});
	
	$("#depositBtnAct").on('click', function(){
		var _discountId = parseInt($("#discountId").val());
		if('NaN' == _discountId || undefined == _discountId || -1 == _discountId){
			layer.alert("请选择或输入合法的充值金额");
			return;
		}
	});

})