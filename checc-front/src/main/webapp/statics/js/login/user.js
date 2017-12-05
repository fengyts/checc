var wait = 120, _w = wait; // 2分钟重发
var _smsFlag = true; // smscode 发送按钮开关,默认打开

$(document).ready(function() {
	
	$("#mobile").on('blur', function() {
		var _this = $(this);
		var _mobile = _this.val();
		if(Utils.isEmpty(_mobile)) {
			_v_mobile = false;
			$("#mobile_notice").html(mobile_empty);
			checkFormParam();
		} else if (Utils.isMobile(_mobile)) {
			_v_mobile = true;
			$("#mobile_notice").empty();
			_this.parent().removeClass("params_error").addClass("params_success");
			checkFormParam();
		} else {
			_v_mobile = false;
			_this.parent().removeClass("params_success").addClass("params_error");
			$("#mobile_notice").html(mobile_invalid);
			checkFormParam();
		}
	});
	$("#smsCode").on('blur', function() {
		var _this = $(this);
		if(Utils.isEmpty(_this.val())) {
			_v_smscode = false;
			$("#smscode_notice").html(smscode_empty);
			checkFormParam();
		} else {
			_v_smscode = true;
			$("#smscode_notice").empty();
			checkFormParam();
		}
	});
	$("#password").on('blur', function() {
		var _this = $(this);
		var _pwd = $.trim(_this.val());
		if(Utils.isEmpty(_pwd)) {
			_v_password = false;
			$("#password_notice").html(password_empty);
			checkFormParam();
		} else if(Utils.isSecurityPassword(_pwd)) {
			_v_password = true;
			$("#password_notice").empty();
			_this.parent().removeClass("params_error").addClass("params_success");
			checkFormParam();
		} else {
			_v_password = false;
			$("#password_notice").parent().removeClass("params_success").addClass("params_error");
			$("#password_notice").html(password_invalid);
			checkFormParam();
		}
	});
	$("#password1").on('blur', function() {
		var _this = $(this);
		var _pwd = $("#password").val();
		var _pwd1 = $.trim(_this.val());
		if(Utils.isEmpty(_pwd)){
			_v_password1 = false;
			$("#password1_notice").html(password_empty);
			checkFormParam();
		}else if(_pwd == _pwd1) {
			_v_password1 = true;
			$("#password1_notice").empty();
			_this.parent().removeClass("params_error").addClass("params_success");
			checkFormParam();
		} else {
			_v_password1 = false;
			$("#password1_notice").parent().removeClass("params_success").addClass("params_error");
			$("#password1_notice").html(confirm_password_invalid);
			checkFormParam();
		}
	});
	$("#captcha").on('blur', function() {
		var _this = $(this);
		if(Utils.isEmpty(_this.val())) {
			_v_captcah = false;
			$("#captcha_notice").html(captcha_empty);
			checkFormParam();
		} else {
			_v_captcah = true;
			$("#captcha_notice").empty();
			checkFormParam();
		}
	});
	$("#agreement").on('click', function(){
		if($(this).is(":checked")){
			_v_agreement = true;
			$("#agreement_notice").empty();
			checkFormParam();
		}else {
			_v_agreement = false;
			$("#agreement_notice").html(agreement);
			checkFormParam();
		}
	});
	
	// sendSmsCode
	$("#sendSmsCode").on('click', function(){
		var _this = $(this);
		if(_smsFlag){
			var _mobile = $("#mobile").val();
			if(Utils.isEmpty(_mobile)){
				//layer.alert("请输入手机号");
				$("#mobile_notice").html(mobile_empty);
				return;
			}
			sendSms(_mobile, this);
		}
		
	});
	
	// 用户注册点击事件
	$("#registerBtn").on('click',function(){
		register();
	});
	
});


/**
 * 倒计时
 * @param o
 */
function countDown(o) {
	if (wait == 0) {
		o.removeAttribute("href");
	    o.removeAttribute("onclick");
	    _smsFlag = true;
		o.text = "获取验证码";
		wait = _w;
	} else {
		_smsFlag = false;
		o.text = "重新发送(" + wait + "s)";
		wait--;
		setTimeout(function() {
			countDown(o);
		}, 1000);
	}
}

function sendSms(mobile, o){
	$.ajax({
		url : 'sendSms',
		dataType : 'text',
		data : {"mobile" : mobile, "type": "01"},
		type : "POST",
		cache : false,
		error : function(request) {
			alert("Server Connection Failure...");
		},
		success : function(res) {
			var data = JSON.parse(res);
			if (1 == data.result) {// 成功
				/*layer.alert(data.message, function() {
					layer.close(layer.index); // 再执行关闭
				});*/
				countDown(o);
			} else {// 失败
				countDown(o);
				layer.alert(data.message);
			}
		}
	});
}

var _v_mobile = false,// 手机号是否验证通过
	_v_smscode = false,
	_v_password = false,
	_v_password1 = false,
	_v_captcah = false,
	_v_agreement = true; 

function checkFormParam(){
	if (_v_mobile && _v_smscode && _v_password
			&& _v_password1 && _v_captcah && _v_agreement) {
		return true;
		//$("#registerBtn").removeClass("btn-disable").addClass("btn");
	} else {
		//$("#registerBtn").removeClass("btn").addClass("btn-disable");
		return false;
	}
}

/**
 * 用户注册
 */
function register(){
	if(!_v_mobile){ $("#mobile_notice").html(mobile_invalid); return; }
	if(!_v_smscode){ $("#smscode_notice").html(smscode_empty); return; }
	if(!_v_password){ $("#password_notice").html(password_invalid); return; }
	if(!_v_password1){ $("#password1_notice").html(confirm_password_invalid); return; }
	if(!_v_captcah){ $("#password_notice").html(captcha_empty); return; }
	if(!_v_agreement){ $("#password_notice").html(agreement); return; }
	
	var _params = {};
	_params.mobile = $("#mobile").val();
	_params.smsCode = $("#smsCode").val();
	_params.password = Crypto.encryptAES($("#password").val());
	_params.password1 = Crypto.encryptAES($("#password1").val());
	_params.captcha = $("#captcha").val();
	
	var _data = $("#registerForm").serialize();
	$.ajax({
		url: 'doRegister',
		type: 'POST',
		data: _params,
		dataType: 'json',
		async: false,
		cache : false,
		success : function(res) {
			var data = res;
			if (1 == data.result) {// 成功
				layer.msg("注册成功,请登录", {icon: 1, time:3000}, function() {
					parent.window.location.href= 'login';
					layer.close(layer.index); // 再执行关闭
				});
			} else {// 失败
				$("#captchaImg").trigger('click');
				layer.alert(data.message);
			}
		}
	});
	
}





/*******************************************************************************
 * 修改会员信息
 */
function userEdit() {
	var frm = document.forms['formEdit'];
	var email = frm.elements['email'].value;
	var msg = '';
	var reg = null;
	var passwd_answer = frm.elements['passwd_answer'] ? Utils
			.trim(frm.elements['passwd_answer'].value) : '';
	var sel_question = frm.elements['sel_question'] ? Utils
			.trim(frm.elements['sel_question'].value) : '';

	if (email.length == 0) {
		msg += email_empty + '\n';
	} else {
		if (!(Utils.isEmail(email))) {
			msg += email_error + '\n';
		}
	}

	if (passwd_answer.length > 0 && sel_question == 0
			|| document.getElementById('passwd_quesetion')
			&& passwd_answer.length == 0) {
		msg += no_select_question + '\n';
	}

	for (i = 7; i < frm.elements.length - 2; i++) // 从第七项开始循环检查是否为必填项
	{
		needinput = document.getElementById(frm.elements[i].name + 'i') ? document
				.getElementById(frm.elements[i].name + 'i')
				: '';

		if (needinput != '' && frm.elements[i].value.length == 0) {
			msg += '- ' + needinput.innerHTML + msg_blank + '\n';
		}
	}

	if (msg.length > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

/* 会员修改密码 */
function editPassword() {
	var frm = document.forms['formPassword'];
	var old_password = frm.elements['old_password'].value;
	var new_password = frm.elements['new_password'].value;
	var password1 = frm.elements['password1'].value;

	var msg = '';
	var reg = null;

	if (old_password.length == 0) {
		msg += old_password_empty + '\n';
	}

	if (new_password.length == 0) {
		msg += new_password_empty + '\n';
	}

	if (password1.length == 0) {
		msg += confirm_password_empty + '\n';
	}

	if (new_password.length > 0 && password1.length > 0) {
		if (new_password != password1) {
			msg += both_password_error + '\n';
		}
	}

	if (msg.length > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

/*******************************************************************************
 * 会员找回密码时，对输入作处理
 */
function submitPwdInfo() {
	var frm = document.forms['getPassword'];
	var user_name = frm.elements['user_name'].value;
	var email = frm.elements['email'].value;

	var errorMsg = '';
	if (user_name.length == 0) {
		errorMsg += user_name_empty + '\n';
	}

	if (email.length == 0) {
		errorMsg += email_address_empty + '\n';
	} else {
		if (!(Utils.isEmail(email))) {
			errorMsg += email_address_error + '\n';
		}
	}

	if (errorMsg.length > 0) {
		alert(errorMsg);
		return false;
	}

	return true;
}



/*******************************************************************************
 * 会员登录
 */
function userLogin() {
	var frm = document.forms['formLogin'];
	var username = frm.elements['username'].value;
	var password = frm.elements['password'].value;
	var msg = '';

	if (username.length == 0) {
		msg += username_empty + '\n';
	}

	if (password.length == 0) {
		msg += password_empty + '\n';
	}

	if (msg.length > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

function chkstr(str) {
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) < 127 && !str.substr(i, 1).match(/^\w+$/ig)) {
			return false;
		}
	}
	return true;
}

function check_password(password) {
	if (password.length < 6) {
		$("#password1").parent().removeClass("params_success");
		$("#password1").parent().addClass("params_error");

		document.getElementById('password_notice').innerHTML = password_invalid;
	} else {
		$("#password1").parent().removeClass("params_error");
		$("#password1").parent().addClass("params_success");

		document.getElementById('password_notice').innerHTML = "<em></em>"; // zhouhuan
	}
}

function check_conform_password(password1) {
	password = document.getElementById('password1').value;

	if (password1.length < 6) {
		$("#password1").parent().removeClass("params_success");
		$("#password1").parent().addClass("params_error");

		document.getElementById('conform_password_notice').innerHTML = password_invalid;
		return false;
	}
	if (password1 != password) {
		$("#password1").parent().removeClass("params_success");
		$("#password1").parent().addClass("params_error");

		document.getElementById('conform_password_notice').innerHTML = confirm_password_invalid;
	} else {
		$("#password1").parent().removeClass("params_error");
		$("#password1").parent().addClass("params_success");

		document.getElementById('conform_password_notice').innerHTML = "<em></em>"; // zhouhuan
	}
}

//function is_registered(mobile) {
//	var submit_disabled = false;
//	if(Utils.isEmpty(mobile)){
//		document.getElementById('mobile_notice').innerHTML = mobile_empty;
//		var submit_disabled = true;
//		return;
//	}
//	if(Utils.isMobile(mobile)){
//		console.log(123);
//		document.getElementById('mobile_notice').innerHTML = mobile_phone_invalid;
//		var submit_disabled = true;
//	}
//	if (submit_disabled) {
//		document.forms['formUser'].elements['Submit'].disabled = 'disabled';
//		return false;
//	}
////	Ajax.call('${domain}/user/checkMobile', 'mobile=' + mobile,
////			registed_callback, 'POST', 'TEXT', true, true);
//}


function registed_callback(result) {
	if (result == "true") {

		$("#username").parent().removeClass("params_error");
		$("#username").parent().addClass("params_success");

		document.getElementById('username_notice').innerHTML = "<em></em>"; // zhouhuan
		document.forms['formUser'].elements['Submit'].disabled = '';
	} else {

		$("#username").parent().removeClass("params_success");
		$("#username").parent().addClass("params_error");
		document.getElementById('username_notice').innerHTML = msg_un_registered;
		document.forms['formUser'].elements['Submit'].disabled = 'disabled';
	}
}

function checkEmail(email) {
	var submit_disabled = false;

	if (email == '') {
		document.getElementById('email_notice').innerHTML = msg_email_blank;
		submit_disabled = true;
	} else if (!Utils.isEmail(email)) {
		document.getElementById('email_notice').innerHTML = msg_email_format;
		submit_disabled = true;
	}

	if (submit_disabled) {
		document.forms['formUser'].elements['Submit'].disabled = 'disabled';
		return false;
	}
	Ajax.call('user.php?act=check_email', 'email=' + email,
			check_email_callback, 'GET', 'TEXT', true, true);
}

function checkSmscode(smscode) {
	var submit_disabled = false;
	if (smscode == '') {
		document.getElementById('smscode_notice').innerHTML = smscode_empty;
		submit_disabled = true;
	}
}

function check_email_callback(result) {
	if (result == 'ok') {
		$("#email").parent().removeClass("params_error");
		$("#email").parent().addClass("params_success");

		document.getElementById('email_notice').innerHTML = "<em></em>"; // zhouhuan
		document.forms['formUser'].elements['Submit'].disabled = '';
	} else {

		$("#email").parent().removeClass("params_success");
		$("#email").parent().addClass("params_error");

		document.getElementById('email_notice').innerHTML = msg_email_registered;
		document.forms['formUser'].elements['Submit'].disabled = 'disabled';
	}
}

/*******************************************************************************
 * 处理注册用户
 */
/*
function register() {
	var frm = document.forms['formUser'];
//	var username = Utils.trim(frm.elements['username'].value);
//	var email = frm.elements['email'].value;
	var mobile = Utils.trim(frm.elements['mobile'].value);
	var password = Utils.trim(frm.elements['password'].value);
	var password1 = Utils.trim(frm.elements['password1'].value);
	var checked_agreement = frm.elements['agreement'].checked;
	var msn = frm.elements['extend_field1'] ? Utils
			.trim(frm.elements['extend_field1'].value) : '';
	var qq = frm.elements['extend_field2'] ? Utils
			.trim(frm.elements['extend_field2'].value) : '';
	var home_phone = frm.elements['extend_field4'] ? Utils
			.trim(frm.elements['extend_field4'].value) : '';
	var office_phone = frm.elements['extend_field3'] ? Utils
			.trim(frm.elements['extend_field3'].value) : '';
	var mobile_phone = frm.elements['extend_field5'] ? Utils
			.trim(frm.elements['extend_field5'].value) : '';
	var passwd_answer = frm.elements['passwd_answer'] ? Utils
			.trim(frm.elements['passwd_answer'].value) : '';
	var sel_question = frm.elements['sel_question'] ? Utils
			.trim(frm.elements['sel_question'].value) : '';

	var msg = "";
	// 检查输入
	var msg = '';
//	if (username.length == 0) {
//		msg += username_empty + '\n';
//	} else if (username.match(/^\s*$|^c:\\con\\con$|[%,\'\*\"\s\t\<\>\&\\]/)) {
//		msg += username_invalid + '\n';
//	} else if (username.length < 3) {
//		// msg += username_shorter + '\n';
//	}
//	if (email.length == 0) {
//		msg += email_empty + '\n';
//	} else {
//		if (!(Utils.isEmail(email))) {
//			msg += email_invalid + '\n';
//		}
//	}
	
	if(mobile.length == 0) {
		msg += mobile_empty + '\n';
	} else if(Utils.isMobile(mobile)) {
		msg += mobile_phone_invalid + '\n';
	}
	if(smscode.length == 0){
		msg += smscode_empty + '\n';
	}

	if (password.length == 0) {
		msg += password_empty + '\n';
	} else if (password.length < 6) {
		msg += password_shorter + '\n';
	}
	if (/ /.test(password) == true) {
		msg += passwd_balnk + '\n';
	}
	if (password1 != password) {
		msg += confirm_password_invalid + '\n';
	}
	if (checked_agreement != true) {
		msg += agreement + '\n';
	}

	if (msn.length > 0 && (!Utils.isEmail(msn))) {
		msg += msn_invalid + '\n';
	}

	if (qq.length > 0 && (!Utils.isNumber(qq))) {
		msg += qq_invalid + '\n';
	}

	if (office_phone.length > 0) {
		var reg = /^[\d|\-|\s]+$/;
		if (!reg.test(office_phone)) {
			msg += office_phone_invalid + '\n';
		}
	}
	if (home_phone.length > 0) {
		var reg = /^[\d|\-|\s]+$/;

		if (!reg.test(home_phone)) {
			msg += home_phone_invalid + '\n';
		}
	}
	if (mobile_phone.length > 0) {
		var reg = /^[\d|\-|\s]+$/;
		if (!reg.test(mobile_phone)) {
			msg += mobile_phone_invalid + '\n';
		}
	}
	if (passwd_answer.length > 0 && sel_question == 0
			|| document.getElementById('passwd_quesetion')
			&& passwd_answer.length == 0) {
		msg += no_select_question + '\n';
	}

	for (i = 4; i < frm.elements.length - 4; i++) // 从第五项开始循环检查是否为必填项
	{
		needinput = document.getElementById(frm.elements[i].name + 'i') ? document
				.getElementById(frm.elements[i].name + 'i')
				: '';

		if (needinput != '' && frm.elements[i].value.length == 0) {
			msg += '- ' + needinput.innerHTML + msg_blank + '\n';
		}
	}

	if (msg.length > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}*/

/*******************************************************************************
 * 用户中心订单保存地址信息
 */
function saveOrderAddress(id) {
	var frm = document.forms['formAddress'];
	var consignee = frm.elements['consignee'].value;
	var email = frm.elements['email'].value;
	var address = frm.elements['address'].value;
	var zipcode = frm.elements['zipcode'].value;
	var tel = frm.elements['tel'].value;
	var mobile = frm.elements['mobile'].value;
	var sign_building = frm.elements['sign_building'].value;
	var best_time = frm.elements['best_time'].value;

	if (id == 0) {
		alert(current_ss_not_unshipped);
		return false;
	}
	var msg = '';
	if (address.length == 0) {
		msg += address_name_not_null + "\n";
	}
	if (consignee.length == 0) {
		msg += consignee_not_null + "\n";
	}

	if (msg.length > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

/*******************************************************************************
 * 合并订单检查
 */
function mergeOrder() {
	if (!confirm(confirm_merge)) {
		return false;
	}

	var frm = document.forms['formOrder'];
	var from_order = frm.elements['from_order'].value;
	var to_order = frm.elements['to_order'].value;
	var msg = '';

	if (from_order == 0) {
		msg += from_order_empty + '\n';
	}
	if (to_order == 0) {
		msg += to_order_empty + '\n';
	} else if (to_order == from_order) {
		msg += order_same + '\n';
	}
	if (msg.length > 0) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

/*******************************************************************************
 * 订单中的商品返回购物车 @param int orderId 订单号
 */
function returnToCart(orderId) {
	Ajax.call('user.php?act=return_to_cart', 'order_id=' + orderId,
			returnToCartResponse, 'POST', 'JSON');
}

function returnToCartResponse(result) {
	alert(result.message);
}

/*******************************************************************************
 * 检测密码强度 @param string pwd 密码
 */
function checkIntensity(pwd) {
	var Mcolor = "#FFF", Lcolor = "#FFF", Hcolor = "#FFF";
	var m = 0;

	var Modes = 0;
	for (i = 0; i < pwd.length; i++) {
		var charType = 0;
		var t = pwd.charCodeAt(i);
		if (t >= 48 && t <= 57) {
			charType = 1;
		} else if (t >= 65 && t <= 90) {
			charType = 2;
		} else if (t >= 97 && t <= 122)
			charType = 4;
		else
			charType = 4;
		Modes |= charType;
	}

	for (i = 0; i < 4; i++) {
		if (Modes & 1)
			m++;
		Modes >>>= 1;
	}

	if (pwd.length <= 4) {
		m = 1;
	}

	switch (m) {
	case 1:
		Lcolor = "2px solid red";
		Mcolor = Hcolor = "2px solid #DADADA";
		break;
	case 2:
		Mcolor = "2px solid #f90";
		Lcolor = Hcolor = "2px solid #DADADA";
		break;
	case 3:
		Hcolor = "2px solid #3c0";
		Lcolor = Mcolor = "2px solid #DADADA";
		break;
	case 4:
		Hcolor = "2px solid #3c0";
		Lcolor = Mcolor = "2px solid #DADADA";
		break;
	default:
		Hcolor = Mcolor = Lcolor = "";
		break;
	}
	if (document.getElementById("pwd_lower")) {
		document.getElementById("pwd_lower").style.borderBottom = Lcolor;
		document.getElementById("pwd_middle").style.borderBottom = Mcolor;
		document.getElementById("pwd_high").style.borderBottom = Hcolor;
	}

}

function changeType(obj) {
	if (obj.getAttribute("min") && document.getElementById("ECS_AMOUNT")) {
		document.getElementById("ECS_AMOUNT").disabled = false;
		document.getElementById("ECS_AMOUNT").value = obj.getAttribute("min");
		if (document.getElementById("ECS_NOTICE") && obj.getAttribute("to")
				&& obj.getAttribute('fee')) {
			var fee = parseInt(obj.getAttribute("fee"));
			var to = parseInt(obj.getAttribute("to"));
			if (fee < 0) {
				to = to + fee * 2;
			}
			document.getElementById("ECS_NOTICE").innerHTML = notice_result
					+ to;
		}
	}
}

function calResult() {
	var amount = document.getElementById("ECS_AMOUNT").value;
	var notice = document.getElementById("ECS_NOTICE");

	reg = /^\d+$/;
	if (!reg.test(amount)) {
		notice.innerHTML = notice_not_int;
		return;
	}
	amount = parseInt(amount);
	var frm = document.forms['transform'];
	for (i = 0; i < frm.elements['type'].length; i++) {
		if (frm.elements['type'][i].checked) {
			var min = parseInt(frm.elements['type'][i].getAttribute("min"));
			var to = parseInt(frm.elements['type'][i].getAttribute("to"));
			var fee = parseInt(frm.elements['type'][i].getAttribute("fee"));
			var result = 0;
			if (amount < min) {
				notice.innerHTML = notice_overflow + min;
				return;
			}

			if (fee > 0) {
				result = (amount - fee) * to / (min - fee);
			} else {
				// result = (amount + fee* min /(to+fee)) * (to + fee) / min ;
				result = amount * (to + fee) / min + fee;
			}

			notice.innerHTML = notice_result + parseInt(result + 0.5);
		}
	}
}
