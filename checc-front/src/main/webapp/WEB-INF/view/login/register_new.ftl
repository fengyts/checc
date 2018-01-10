<#include "/common/common.ftl" />

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="" />
	<meta name="Description" content="" />
	
	<title>${webtitle}</title>
	
	<link rel="shortcut icon" href="${favicon}" type="image/x-icon" />
	<link rel="icon" href="animated_favicon.gif" type="image/gif" />
	
	<link rel="stylesheet" type="text/css" href="${css}/login_new.css" />
	
	<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>
	<script type="text/javascript" src="${plugins}/crypto-js/rollups/aes.js"></script>
	<script type="text/javascript" src="${static}/common/crypto.js"></script>
	<script type="text/javascript" src="${static}/common/utils.js"></script>
	<script type="text/javascript" src="${js}/login/user.js"></script>

</head>
<body data-loadtrack="login">
	<div class="header">
	    <div id="checc_lg" class="checc_lg content_pos">
	        <div class="checc_clg">
	            <span class="checc_lgn_text"><a href="${domain}/">车西西</a></span>
	            <span class="checc_lgn_link">
	                <a href="${domain}/">www.checc.cc</a>
	            </span>
	        </div>
	        <div class="checc_lg_t">
	        	<div class="checc_lg_ti">用极低的价格买到喜欢的车</div>
    		</div>
	        <#--
	        <div class="checc_lg_t"><span class="checc_lg_ti">用极低的价格买到喜欢的车</span></div>
	        -->
	        <div class="checc_qrcode"><img src="${images}/checc_qr.jpg"></div>
	    </div>
	</div>
	
	<!-- 正文 -->
	<!--登录开始-->
	<div class="main">
		<div class="login_bg">
			<a data-url="" href="javascript:void(0);">
				<img src="${images}/loginBg1.jpg" class="" />
			</a>
		</div>
		<div id="login_form_box">
			<form id="registerForm" name="registerForm" action="" method="post">
				<input type="hidden" value="C4E1AB9A7DE79D7C750E8916875E7DBE" id="validate">
				<ul class="rg_ul">
					<li class="rg_input_li">
						<h2>
							注册
							<div class="trig">
								已有账号? <a href="${domain}/user/login.htm" class="trigger-box">点击登录</a>
							</div>
						</h2>
					</li>
					<li class="rg_input_li">
						<div class="login_span">
							<#--
							<span class="iconfont">Œ</span>
							-->
							<input type="text" autocomplete="off" placeholder="用户名/手机号" name="mobile" id="mobile" value="" />
							<span class="error_icon"></span>
						</div>
					</li>
					<li class="error_box" id="mobile_notice"><em></em></li>
					<li class="smscode rg_input_li">
						<div class="login_span">
							<#--
							<span class="iconfont">Œ</span>
							-->
							<input type="text" name="smsCode" id="smsCode" placeholder="短信验证码" /> 
							<span class="error_icon"></span> 
						</div>
						<div class="smscode_btn">
							<a class="mybtn" name="sendSmsCode" id="sendSmsCode">获取验证码</a>
						</div>
					</li>
					<li class="error_box" id="smscode_notice"><em></em></li>
					<li class="rg_input_li">
						<div class="login_span">
							<#--
							<span class="iconfont">÷</span> 
							--> 
							<input type="password" name="password" id="password" placeholder="密码必须至少6位且包含大小写字母和数字">
							<span class="error_icon"></span>
						</div>
					</li>
					<li class="error_box" id="password_notice"><em></em></li>
					<li class="rg_input_li">
						<div class="login_span">
							<#--
							<span class="iconfont">÷</span> 
							-->
							<input type="password" name="password1" id="password1" placeholder="请再次确认密码"> 
							<span class="error_icon"></span>
						</div>
					</li>
					<li class="error_box" id="password1_notice"><em></em></li>
					<li class="security_code rg_input_li">
						<div class="login_span">
							<#--
							<span class="t_text">验证码</span>
							-->
							<input type="text" class="code_input" name="captcha" id="captcha" maxlength="6" placeholder="请输入验证码"> 
							<span class="error_icon"></span> 
						</div>
						<img src="${domain}/user/captcha.htm" alt="captcha" style="vertical-align: middle; cursor: pointer;" id="captchaImg"
							onclick="this.src='${domain}/user/captcha?'+Math.random()" />
					</li>
					<li class="error_box" id="captcha_notice"><em></em></li>
					<li class="h30 agreement">
						<div class="fast-remb">
							<input id="agreement" name="agreement" type="checkbox" value="1" checked="checked" tabindex="5" class="remember-me" /> 
								我已看过并接受《
									<a href="article.php?cat_id=-1" style="color: blue" target="_blank">车西西用户协议</a>
								》
						</div>
					</li>
					<li class="error_box" id="agreement_notice"></li>
					<li class="rg_input_li">
						<input type="button" name="registerBtn" class="registerBtn" id="registerBtn" value="同意协议并注册">
						<div class="loginBtnBox"></div>
					</li>
				</ul>
			</form>
		</div>
	</div>
	<!--登录结束-->
	<!-- 尾部 -->
	<div class="footer">
		<div class="login-footer"> 皖ICP备17022956号-2    
			Copyright&copy;2017-${.now?date?string.yyyy} 
			<a target="_blank" href="${domain}/">
				<font style="color: #0099cc">车西西</font>
			</a>
			. All rights reserved.
		</div>
	</div>

</body>
<script type="text/javascript">
	var process_request = "正在处理您的请求...";
	var username_empty = "用户名不能为空";
	var username_shorter = "用户名长度不能少于 3 个字符";
	var username_invalid = "用户名只能是由字母数字以及下划线组成";
	var password_empty = "密码不能为空";
	var passwd_balnk = "- 密码中不能包含空格";
	var password_invalid = "密码必须至少6位且包含大小写字母和数字";
	var confirm_password_invalid = "两次输入密码不一致";
	var email_empty = "Email 为空";
	var email_invalid = "Email 不是合法的地址";
	var mobile_empty = "手机号码不能为空";
	var mobile_invalid = "手机号码不正确";
	var agreement = "您没有接受协议";
	var msn_invalid = "msn地址不是一个有效的邮件地址";
	var qq_invalid = "QQ号码不是一个有效的号码";
	var home_phone_invalid = "家庭电话不是一个有效号码";
	var office_phone_invalid = "办公电话不是一个有效号码";
	var mobile_phone_invalid = "手机号码不是一个有效号码";
	var msg_un_blank = "用户名不能为空";
	var msg_un_length = "用户名最长不得超过7个汉字";
	var msg_un_format = "用户名含有非法字符";
	var msg_un_registered = "用户名已经存在,请重新输入";
	var msg_can_rg = "可以注册";
	var msg_email_blank = "邮件地址不能为空";
	var msg_email_registered = "邮箱已存在,请重新输入";
	var msg_email_format = "邮件地址不合法";
	var msg_blank = "不能为空";
	var no_select_question = "您没有完成密码提示问题的操作";
	var username_exist = "用户名 %s 已经存在";
	var smscode_empty = "短信验证码不能为空";
	var captcha_empty = "验证码不能为空";
</script>
</html>
