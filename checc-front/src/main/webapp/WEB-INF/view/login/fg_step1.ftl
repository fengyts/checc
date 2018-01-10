<#include "/common/common.ftl" />
<link type="text/css" rel="stylesheet" href="${css}/fgpwd.css">
<link type="text/css" rel="stylesheet" href="${css}/my.css">

<style>
	div{
		max-width:500px;
		max-height:450px;
	}
	.error_info{
		margin-top: 5px;
		padding-left: 110px;
		color: red;
	}
	#sv_err_msg{
		display:none;
	}
	.notice p{
		padding: 0;
		margin-top:20px;
		color: #ff0000;
	}
	.notice p span {
		padding-left:69px;
	}
</style>

<div class="step_ifa">
<form id="step_form" class="checc_form checc_form_pane">
	<input type="hidden" id="fgToken" name="fgToken" value="${fgToken}">
	<input type="hidden" id="fgStepToken" name="fgStepToken" value="${fgStepToken}">
	<div class="checc_form_item">
		<label class="checc_form_label">手机号</label>
		<div class="checc_input_block">
    		<input class="checc_input" type="text" id="mobile" name="mobile" placeholder="请输入手机号" value="${mobileRv}">
		</div>
		<div class="error_info" id="mobile_notice"></div>
	</div>
	<div class="checc_form_item">
		<label class="checc_form_label">验证码</label>
		<div class="checc_input_inline">
    		<input class="checc_input" type="text" id="captcha" name="captcha" placeholder="验证码" value="${captchaRv}">
		</div>
		<div class="checc_form_mid">
			<img src="${domain}/user/captcha.htm" alt="captcha" style="vertical-align:middle; cursor:pointer;" id="captchaImg"
					onclick="this.src='${domain}/user/captcha?'+Math.random()" />
		</div>
		<div class="error_info" id="captcha_notice"></div>
	</div>
	<div id="next_step_btn" class="next_step mybtn_normal">
		<input type="hidden" id="stepNum" name="stepNum" value="1">
		<a href="javascript:void(0);">下一步</a>
	</div>
	<div class="notice">
		<p>温馨提示：如果您之前注册的手机号不再使用，建议您使用新的手机号重新注册
			<span>请在30分钟内完成所有步骤,否则会话将过期</span>
		</p>
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
	
	function atmsg(){
		var _errCode = $("#err_code").text();
		var _errMsg = $("#err_info").text();
		if(_errCode != -1){
			$("#captchaImg").trigger('click');
			window.parent.layer.alert(_errMsg, {
				anim : 5,
				shadeClose : true,
				shade : 0.1
			});
		}
	}
	
	atmsg();
	
</script>

<script type="text/javascript" src="${static}/common/common.js"></script>
<script type="text/javascript" src="${js}/login/fgpwd.js"></script>
