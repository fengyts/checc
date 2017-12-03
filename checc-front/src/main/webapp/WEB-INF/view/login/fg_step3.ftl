<#include "/common/common.ftl" />
<link type="text/css" rel="stylesheet" href="${css}/checc_main.css">
<link type="text/css" rel="stylesheet" href="${css}/my.css">

<style>
	div{
		max-width:500px;
		max-height:400px;
	}
	
	.error_info{
		margin-top: 5px;
		padding-left: 110px;
		color: red;
	}
	#sv_err_msg{
		display:none;
	}
</style>

<div class="step_ifa">
<form id="step_form" class="checc_form checc_form_pane">
	<input type="hidden" id="mobile" name="mobile" value="${mobileRv}">
	<div class="checc_form_item">
		<label class="checc_form_label">新密码</label>
		<div class="checc_input_block">
    		<input class="checc_input" type="password" id="password" name="password" placeholder="请输入新密码">
		</div>
		<div class="error_info" id="password_notice"></div>
	</div>
	<div class="checc_form_item">
		<label class="checc_form_label">确认密码</label>
		<div class="checc_input_block">
    		<input class="checc_input" type="password" id="password1" name="password1" placeholder="请再次输入新密码">
		</div>
		<div class="error_info" id="password1_notice"></div>
	</div>
	<div id="next_step_btn" class="next_step mybtn_normal">
		<input type="hidden" id="stepNum" name="stepNum" value="3">
		<a href="javascript:void(0);">下一步</a>
	</div>
</form>
</div>

<div id="sv_err_msg">
	<span id="err_code">${errorCode!-1}</span>
	<span id="err_info">${errorMsg!'数据输入有误'}</span>
</div>

<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>
<script type="text/javascript" src="${plugins}/crypto-js/rollups/aes.js"></script>
<script type="text/javascript" src="${static}/common/crypto.js"></script>
<script type="text/javascript" src="${static}/common/utils.js"></script>

<script type="text/javascript">
	var layer;
	layui.use('layer', function(){
  		layer = layui.layer;
	}); 
	
	// 父窗口弹出提示信息
	function atmsg(){
		var _errCode = $("#err_code").text();
		var _errMsg = $("#err_info").text();
		if(_errCode != -1){
			window.parent.layer.alert(_errMsg, {
				anim : 5,
				shadeClose : true,
				shade : 0.1
			});
		}
	}
	
	atmsg();
	
	
	var _v_p1 = false, _v_p2 = false;
	$("#password").on('click', function(){
		var _password = $("#password").val();
		if (Utils.isEmpty(_password)) {
			_v_p1 = false;
			$("#password_notice").empty().text("新密码不能为空");
			return false;
		}
		if(Utils.isSecurityPassword(_password)) {
			_v_p1 = false;
			$("#password_notice").empty().text("密码6~18位且必须包含大小写字母和数字");
			return false;
		}
		_v_p1 = true;
		$("#password_notice").empty();
	});
	$("#password1").on('click', function(){
		var _password1 = $("#password1").val();
		if (Utils.isEmpty(_password1)) {
			_v_p2 = false;
			$("#password1_notice").empty().text("确认密码不能为空");
			return false;
		}
		var _password = $("#password").val();
		if(_password != _password1){
			_v_p2 = false;
			$("#password1_notice").empty().text("两次密码不一样");
			return false;
		}
		_v_p2 = true;
		$("#password1_notice").empty();
	});
	
	$("#next_step_btn").on('click', function() {
		$("#password").trigger('blur');
		$("#password1").trigger('blur');
		if(_v_p1 && _v_p2) {
			$("#step_form").submit();
		} else { // 表单验证不通过
			return;
		}
	});
</script>

