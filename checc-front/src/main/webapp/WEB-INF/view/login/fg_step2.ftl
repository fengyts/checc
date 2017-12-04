<#include "/common/common.ftl" />
<link type="text/css" rel="stylesheet" href="${css}/checc_main.css">
<link type="text/css" rel="stylesheet" href="${css}/my.css">

<style>
	div {
		max-width:500px;
		max-height:450px;
	}
	.error_info {
		margin-top: 5px;
		padding-left: 110px;
		color: red;
	}
	#sv_err_msg {
		display:none;
	}
	.msg_info {
		margin-top: -20px;
	}
	p {
		text-align: center;
		color: #999999;
		line-height: 10px;
		font-size: 14px;
		min-width: 600px;
		margin-left: -50px;
	}
	.p2 {
		margin-top: 5px;
		padding: 0;
	} 
	p span{
		color: #ff0000;
	}
	.self {
		margin-right: 132px !important;
	}
</style>

<script>
	var wait = 120, _w = wait; // 2分钟重发
	var _smsFlag = false; // smscode 发送按钮开关,默认关闭
</script>

<div class="step_ifa">
<form id="step_form" class="checc_form checc_form_pane">
	<input type="hidden" id="fgToken" name="fgToken" value="${fgToken}">
	<input type="hidden" id="fgStepToken" name="fgStepToken" value="${fgStepToken}">
	<div class="checc_form_item">
		<p class="p1">已向手机号<span>${mobileSecurity}</span>发送了一条验证码短信(30分钟内有效)，请注意查收！</p>
		<p class="p2">如果未收到，可在2分钟后重新发送。</p>
	</div>
	<div class="checc_form_item">
		<label class="checc_form_label">短信验证码</label>
		<div class="checc_input_inline">
    		<input class="checc_input" type="text" id="smsCode" name="smsCode" placeholder="短信验证码">
		</div>
		<div class="checc_form_mid">
			<input type="hidden" id="mobile" name="mobile" value="${mobileRv}">
			<a class="mybtn self" name="sendSmsCode" id="sendSmsCode"></a>
		</div>
		<div class="error_info" id="smsCode_notice"></div>
	</div>
	<div id="next_step_btn" class="next_step mybtn_normal">
		<input type="hidden" id="stepNum" name="stepNum" value="2">
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
	
	history.pushState(null, null, document.URL);
	window.addEventListener('popstate', function() {
		history.pushState(null, null, document.URL);
	});
	
	//步骤样式
	window.parent.$("#step2 div").addClass("pass");
	
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
	
	countDown($("#sendSmsCode"));
	
	//var wait = 120, _w = wait; // 2分钟重发
	//var _smsFlag = false; // smscode 发送按钮开关,默认关闭
	// sendSmsCode
	$("#sendSmsCode").on('click', function(){
		var _this = $(this);
		if(_smsFlag){
			var _mobile = $("#mobile").val();
			if(Utils.isEmpty(_mobile)){
				//layer.alert("请输入手机号");
				//$("#mobile_notice").html(mobile_empty);
				return;
			}
			sendSms(_mobile, this);
		}
		
	});
	
	function sendSms(mobile, o){
		$.ajax({
			url : 'sendSms',
			dataType : 'text',
			data : {"mobile" : mobile, "type" : "02"},
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
					parent.layer.alert(data.message);
				}
			}
		});
	}
		
	/**
	 * 倒计时
	 * @param o
	 */
	function countDown(o) {
		if (wait == 0) {
			//o.removeAttribute("href");
		    //o.removeAttribute("onclick");
		    _smsFlag = true;
			o.text = "获取验证码";
			$(o).css("cursor", "pointer");
			$(o).text("获取验证码");
			wait = _w;
		} else {
			_smsFlag = false;
			//o.text = "重新发送(" + wait + "s)";
			$(o).text("重新发送(" + wait + "s)");
			$(o).css("cursor", "not-allowed");
			wait--;
			setTimeout(function() {
				countDown(o);
			}, 1000);
		}
	}
	
	$("#next_step_btn").on('click', function() {
		var _smsCode = $("#smsCode").val();
		if (Utils.isEmpty(_smsCode)) { 
			$("#smsCode_notice").empty().text("短信验证码不能为空");
			return false;
		} else { // 表单验证通过，提交后台
			$("#step_form").submit();
		}
	});
	
</script>

