<#include "/common/common.ftl" />
<link type="text/css" rel="stylesheet" href="${css}/fgpwd.css">
<link type="text/css" rel="stylesheet" href="${css}/my.css">

<style>
	div{
		max-width:500px;
		max-height:450px;
	}
	p {
		text-align: center;
		color: #666666;
		line-height: 10px;
		font-size: 15px;
		min-width: 600px;
		margin-left: -50px;
	}
</style>

<div class="step_ifa">
<form id="step_form" class="checc_form checc_form_pane">
	<div class="checc_form_item">
		<p class="p1">恭喜！您已成功设置新的登录密码，以后将使用该密码进行登录。</p>
	</div>
	<div id="next_step_btn" class="next_step mybtn_normal">
		<input type="hidden" id="stepNum" name="stepNum" value="4">
		<a href="javascript:void(0);" onclick="login();">立即登陆</a>
	</div>
</form>
</div>

<div id="sv_err_msg">
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
	window.parent.$("#step2 div,#step3 div,#step4 div").addClass("pass");
	
	function login(){
		window.top.location.href="${domain}/user/login";
	}
</script>

