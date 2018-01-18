<#include "/common/common.ftl" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta property="wb:webmaster" content="40245e025685314a" />
		<meta property="qc:admins" content="3535557172265514566375" />
		<title>登录-车西西</title>
		
		<#include "/common/common-js.ftl" />
		
		<link rel="shortcut icon" href="${favicon}" type="image/x-icon" />
		
		<link rel="stylesheet" type="text/css" href="${css}/login_new.css" />


		<script type="text/javascript" src="${plugins}/crypto-js/rollups/aes.js"></script>
		<script type="text/javascript" src="${static}/common/crypto.js"></script>
		<script type="text/javascript" src="${static}/common/utils.js"></script>
		<script type="text/javascript" src="${js}/login/user.js"></script>

		<style>
		</style>
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
		        	<div class="checc_lg_ti">${bannerTitle}</div>
        		</div>
		        <#--
		        <div class="checc_lg_t"><span class="checc_lg_ti">${bannerTitle}</span></div>
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
				<form name="formLogin" action="" method="get" onSubmit="">
					<ul class="login_ul">
						<li class="login_input_li">
							<h2>
								<div class="trig">
									没有帐号？<a href="${domain}/user/register.htm" class="trigger-box">点击注册</a>
								</div>
								登录
							</h2>
						</li>
						<li class="login_input_li">
							<div class="login_span">
								<span class="iconfont">Œ</span> 
								<input type="text" autocomplete="off" placeholder="手机号" value="" name="mobile" id="mobile" />
								<span class="error_icon"></span>
							</div>							
						</li>
						<li class="login_input_li">
							<div class="login_span">
								<span class="iconfont">÷</span> 
								<input id="password" name="password" type="password" class="text" placeholder="密码" />
								<span class="error_icon"></span> 
							</div>
						</li>
						<li class="h30">
							<div class="fast-remb">
								<input id="remember" name="remember" type="checkbox" checked="checked" class="vam" data-track="login_L_login1_remember">
								&nbsp;<label for="remember" class="vam">记住用户名</label>
							</div>
							<div class="login-tip">
								<a class="forget_psd" href="fgpwd" data-track="login_L_login1_forget">
									忘记密码？
								</a>
							</div>
						</li>
						<li class="last">
							<input type="hidden" name="act" value="act_login" /> 
							<input type="hidden" name="back_act" value="${domain}/" /> 
							<input type="hidden" name="returnUrl" id="returnUrl" value="${returnUrl}" /> 
							<input type="button" name="submit" class="loginBtn" id="doLogin" value="登 录">
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
</html>
