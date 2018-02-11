var pageii, _submitFlag = true, _verifyMsg = "";
$(function() {
	$("#addDepositConfig").on('click', function(){
		var _that = this;
		layer.tips("亲，功能尚未上线", _that, {tips: 2, time: 1500});
	});
	
	$(".editcatabtn").on('click', function() {
		var _this = $(this);
		var _tds = _this.parent().prevAll();
		var _id = _tds.eq(5).text(), _depositAmount = _tds.eq(4).text(), _discount = _tds.eq(3).text();
		$("#id").val(_id);
		$("#depositAmount").val(_depositAmount);
		
		var _dcus = $("#discount option[value='"+_discount+"']");
		if(_dcus.length < 1){ // 判断是否自定义折扣值
			$("#discountCustom").val(_discount).removeAttr('style').attr('readonly', false);
			_discount = 2;
		} else {
			$("#discountCustom").val('').attr('readonly', true).attr('style','background:#e6e6e6;cursor:not-allowed;');
		}
//		$("#discount option:selected").attr("selected", false);
//		$("#discount option[value='"+_discount+"']").attr("selected",'selected');
		$("#discount").val(_discount);
		form.render('select'); // layer-ui 表单api刷新select元素
		//var _dop = $("#discount option:selected").val();
		form.on('select', function(d){
			var _dop = d.value;
			if(_dop == 2 || _dop == '2'){
				$("#discountCustom").removeAttr('style').attr('readonly', false);
			} else {
				$("#discountCustom").val('').attr('readonly', true).attr('style','background:#e6e6e6;cursor:not-allowed;');
			}
		});
		pageii = layer.open({
			type : 1,
			title : '编辑充值配置',
			fixed : false,
			move : false,
			closeBtn: 0,
			offset: '150px',
			area : [ '400px', '420px' ],
			content : $("#editDepositConfig")
		});
	});
	
	// 自定义折扣输入框
	$("#discountCustom").on('blur', function(){
		var _ronly = $("#discountCustom").attr("readonly");
		if(undefined == _ronly || null == _ronly || '' == _ronly){
			var _dcv = $(this).val();
			var _reg = new RegExp('^([1](.0|.00)?|(0.){1}[1-9]{1}[0-9]{0,1})$');
			if(!_reg.test(_dcv)){
				_submitFlag = false; // 禁止表单提交
				_verifyMsg = "折扣值必须大于0且小于等于1";
//				layer.msg("折扣值必须大于0且小于等于1");
			} else {
				_submitFlag = true;
				_verifyMsg = "";
			}
		}
	});
	
	// 取消按钮
	$("#cancel").on('click', function(){
		$("#discountCustom").val('').attr('readonly', true).attr('style','background:#e6e6e6;cursor:not-allowed;');
		layer.close(pageii);
	});
	
	$("#saveUpdate").on('click', function(){
		if(!_submitFlag){
			layer.msg(_verifyMsg, {time: 1500});
			return;
		}
		var _depositAmt = $("#depositAmount").val();
		if(undefined == _depositAmt || null == _depositAmt || '' == _depositAmt){
			layer.msg("充值金额不能为空", {time: 1500});
			return;
		}
		var _reg = new RegExp("^[1-9][0-9]+$");
		if(!_reg.test(_depositAmt)){
			layer.msg("充值金额必须为正整数", {time: 1500});
			return;
		}
		var _discount = $("#discount").val();
		if(_discount == 2 || _discount == '2'){
			_discount = $("#discountCustom").val();
			if(undefined == _discount || null == _discount || '' == _discount){
				layer.msg("请输入合法的折扣值", {time: 1500});
				return;
			}
		}
		$.post('update.htm', {"id":$("#id").val(),"depositAmount":_depositAmt,"discount": _discount}, function(res){
			if(res.result == 1){
				layer.close(pageii);
				window.location.reload();
				layer.msg(res.message, {time: 1500});
			} else {
				layer.msg(res.message, {time: 1500});
			}
		}, 'json');

	});

});