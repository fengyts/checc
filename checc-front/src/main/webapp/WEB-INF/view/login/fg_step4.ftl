<#include "/common/common.ftl" />
<link type="text/css" rel="stylesheet" href="${css}/checc_main.css">
<link type="text/css" rel="stylesheet" href="${css}/my.css">

<style>
	div{
		max-width:500px;
		max-height:400px;
	}
	
	p {
		text-align: center;
		color: #999999;
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
		<a href="javascript:void(0);">立即登陆</a>
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
</script>

<script type="text/javascript" src="${static}/common/common.js"></script>
<script type="text/javascript" src="${js}/login/fgpwd.js"></script>
