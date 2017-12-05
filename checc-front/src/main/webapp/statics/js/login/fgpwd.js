$(function() {
	var _v_mobile = false, _v_captcha = false;

	$("#mobile").on('blur', function() {
		var _this = $(this);
		var _mobile = _this.val();
		if (Utils.isEmpty(_mobile)) {
			_v_mobile = false;
			$("#mobile_notice").empty().text("手机号不能为空");
			return false;
		}
		if (!Utils.isMobile(_mobile)) {
			_v_mobile = false;
			$("#mobile_notice").empty().text("手机号格式不对");
			return false;
		}
		validMobile(_mobile, function(res){
			if(!res){
				_v_mobile = false;
				$("#mobile_notice").empty().text("手机号尚未注册");
				return false;
			}
		});
		_v_mobile = true;
		$("#mobile_notice").empty();
	});

	$("#captcha").on('blur', function() {
		var _this = $(this);
		var _captcha = _this.val();
		if (Utils.isEmpty(_captcha)) {
			_v_captcha = false;
			$("#captcha_notice").empty().text("验证码不能为空");
			return false;
		}
		if (Utils.trim(_captcha).length != 5) {
			_v_captcha = false;
			$("#captcha_notice").empty().text("验证码必须是5位");
			return false;
		}
		_v_captcha = true;
		$("#captcha_notice").empty();
	});

	$("#next_step_btn").on('click', function() {
		$("#mobile").trigger('blur');
		$("#captcha").trigger('blur');
		if (_v_mobile && _v_captcha) { // 表单验证通过，提交后台
//			$.get('fgform.htm',$("#step_form").serialize(),function (res){
//				console.log(123);
//				window.parent.$("#cnit").empty().html(res);
//			},'html');
			$("#step_form").submit();
		} else { // 表单验证不通过
			return false;
			// parent.layer.alert("输入数据有误", {
			// anim : 5,
			// shadeClose : true,
			// shade : 0.1
			// });
		}
	});

});