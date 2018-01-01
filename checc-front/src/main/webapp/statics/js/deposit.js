$(function() {
	
	// 充值选项点击事件
	$(".dpl_d_box").on('click', function() {
		var _this = $(this);
		$(".dpl_d_box").removeClass('dpl_d_box_checked');
		_this.addClass('dpl_d_box_checked');
		var _index = _this.attr('index');
		// var _dpamt = $("#dpamt_" + _index).val();
		var _dpamt=$("#dpamt_" + _index).val(), _discountChecked = $("#discount_" + _index).val();
		$("#dpInfo").empty().text(_dpamt);
		$("#dpInfoMo").empty().text($("#dpamtm_" + _index).val());
		$("#depositAmt").val(_dpamt);
		$("#discount").val(_discountChecked);
		
		$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'inline');
		$("#dpckipt_info").css('display', 'none');
		//$("#dpamtInputErr").css('display', 'none');
		$("#discountId").val($("#discountId_" + _index).val());
		
		$("#otherAmount").val("");
	});
	
	// 其他金额输入框焦点事件
	$("#otherAmount").on('focus', function() {
		$(".dpl_d_box").removeClass('dpl_d_box_checked');
		$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'none');
		$("#dpckipt_info").css('display', 'none');
	}).on('blur', function(){
		var _v = $(this).val();
		var reg = /(^[1-9]+([0-9])?)$/;
		if(_v && reg.test(_v)){
			_v = parseFloat(_v);
			//$("#dpamtInputErr").css('display', 'none');
			$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'inline');
			$("#dpckipt_info").css('display', 'none');
			$("#dpInfo").empty().text(Math.floor(_v));
			$("#dpInfoMo").empty().text(_v.toFixed(2));
			$("#discountId").val(0);
			$("#discount").val(1);
			$("#depositAmt").val(_v);
		} else {
			//$("#dpamtInputErr").css('display', 'inline');
			$("#dpCheckInfo").removeClass('dpcheck_info').css('display', 'none');
			if(_v != '' && undefined != _v && null != _v && 'NaN' != _v) {
				$("#dpckipt_info").css('display', 'inline');
			}
			$("#dpInfo").empty();
			$("#dpInfoMo").empty();
			$("#discountId").val(-1);
			$("#discount").val(1);
			$("#depositAmt").val(0);
		}
	});
	
	// 立即支付点击事件
	$("#depositBtnAct").on('click', function(){
		var _discountId = parseInt($("#discountId").val());
		if('NaN' == _discountId || undefined == _discountId || -1 == _discountId){
			layer.alert("请选择或输入合法的充值金额");
			return;
		}
		depositAct();
	});
	
		
})

function depositAct() {
	var _param = {};
	_param.depositTk = $("#depositTk").val();
	_param.discountId = $("#discountId").val(); 
	_param.discount = $("#discount").val();
	_param.depositAmt = $("#depositAmt").val();
	_param.otherAmount = $("#otherAmount").val();
	
//	var _url = domain + '/user/deposit/dptAct';
	var _url = domain + '/pay/wechat/payQRCode';
	$.ajax({
		url : _url,
		method : 'GET',
		dataType : 'html',
		data : _param,
		cache : false,

		success: function(data, status, xhr) { 
	        var errorCode=xhr.getResponseHeader("errorCode");
	        if('999' == errorCode) { // 未登陆
	        	lgn_pg_ii = layer.open({
	        		type: 2,
	        		title: '请先登录',
	        		resize: false,
	        		//scrollbar: false,
	        		//fixed: false,
	        		move:false,
	        		shade: 0.1,
	        		zIndex: 0,
	        		area: ['700px', '500px'],
	        		content: domain + '/user/loginAjax'
	        	});
	        } else { // 已经登陆
	        	lgn_pg_ii = layer.open({
	        		type: 1,
	        		title: '',
	        		resize: false,
	        		//scrollbar: false,
	        		//fixed: false,
	        		move:false,
	        		shade: 0.1,
	        		anim: 5,
	        		area: ['240px', '220px'],
	        		content: data
	        	});
	        }
	    }

	});
}
