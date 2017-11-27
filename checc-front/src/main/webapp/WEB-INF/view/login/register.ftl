<#include "/common/common.ftl" />

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="" />
	<meta name="Description" content="" />
	
	<title>用户中心_车西西-值得信赖的网上汽车商城</title>
	
	<link rel="shortcut icon" href="favicon.ico" />
	<link rel="icon" href="animated_favicon.gif" type="image/gif" />
	<link rel="stylesheet" type="text/css" href="${css}/login.css" />
	<link rel="stylesheet" type="text/css" href="${css}/my.css" />
	
	<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>
	<script type="text/javascript" src="${static}/common/utils.js"></script>
	<script type="text/javascript" src="${js}/login/user.js"></script>
	<#--
	<script type="text/javascript" src="${static}/common/jquery.json.js"></script>
	<script type="text/javascript" src="${static}/common/transport_jquery.js"></script>
	<script type="text/javascript" src="${static}/common/jquery.SuperSlide.js"></script>
	<script type="text/javascript" src="${static}/common/lizi_common.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	-->
	<script type="text/javascript">
		var layer;
		layui.use('layer', function(){
	  		layer = layui.layer;
		}); 
	</script>
</header>

<body>
	<div id="header">
		<div class="hd_bar" id="userinfo-bar">
			<div class="bd">
				<div class="logo">
					<a href="index.php">车西西</a>
				</div>
				<div class="hd_lbar" style="display: block;">
					<a href="/" rel="nofollow">网站首页</a>
				</div>

			</div>
		</div>
	</div>
	<script>
		$(function() {
			$(".input_box").click(function() {
				$(this).find(".t_text").hide();
				$(this).find("input").focus();
			})

			$(".input_box").focusin(function() {
				$(this).find(".t_text").hide();
			})

			$(".input_box").focusout(function() {
				if ($(this).find("input").val() == "") {
					$(this).find(".t_text").show();
				}
			})
		})
	</script>

<div class="gloginBg" style="min-height: 500px; padding: 30px 0 50px 0;">
<div id="main" class="cle">
<div class="g_box">

	<div id="register_box">
	<form action="user.php" method="post" name="formUser" onsubmit="return register();">
		<input type="hidden" value="C4E1AB9A7DE79D7C750E8916875E7DBE" id="validate">
		<h2>
			注册
			<div class="trig">
				已有账号? <a href="${domain}/user/login.htm" class="trigger-box">点击登录</a>
			</div>
		</h2>
		<div class="register_infor">
			<input type="hidden" id="sendtype">
			<ul>
				<li class="input_box">
					<span class="t_text">手机号</span> 
					<input type="text" name="mobile" id="mobile"> 
					<span class="error_icon"></span>
				</li>
				<li class="error_box" id="mobile_notice"><em></em></li>
				<li class="smscode input_box">
					<span class="t_text">短信验证码</span>
					<input type="text" name="smscode" id="smscode"> 
					<span class="error_icon"></span> 
					<a class="mybtn" name="sendSmsCode" id="sendSmsCode">获取验证码</a>
				</li>
				<li class="error_box" id="smscode_notice"><em></em></li>
				<li class="input_box">
					<span class="t_text">密码必须至少6位且包含大小写字母和数字</span> 
					<input type="password" name="password" id="password">
					<span class="error_icon"></span>
				</li>
				<li class="error_box" id="password_notice"><em></em></li>
				<li class="input_box">
					<span class="t_text">确认密码</span> 
					<input type="password" name="password1" id="password1"> 
					<span class="error_icon"></span>
				</li>
				<li class="error_box" id="password1_notice"><em></em>
				</li>
				<li class="security_code input_box">
					<span class="t_text">验证码</span>
					<input type="text" class="code_input" name="captcha" id="captcha" maxlength="6"> 
					<span class="error_icon"></span> 
					<img src="${domain}/user/captcha.htm" alt="captcha" style="vertical-align: middle; cursor: pointer;" 
						onclick="this.src='${domain}/user/captcha?'+Math.random()" />
				</li>
				<li class="error_box" id="captcha_notice"><em></em></li>
				<li class="lizi_law">
					<label> 
					<input name="agreement" type="checkbox" value="1" checked="checked" tabindex="5" class="remember-me" /> 
						我已看过并接受《
							<a href="article.php?cat_id=-1" style="color: blue" target="_blank">用户协议</a>
						》
					</label>
				</li>
				<li class="error_box"><em></em></li>
				<li class="go2register">
					<input name="act" type="hidden" value="act_register"> 
					<input type="hidden" name="back_act" value="" /> 
					<input name="Submit" type="submit" value="同意协议并注册" class="btn submit_btn">
				</li>
			</ul>
		</div>
	</form>
	</div>
	
</div>
</div>
</div>


<#include "/index/footer.ftl" />

	<script>
		$(function() {
			$(window).scroll(function() {
				if ($(window).scrollTop() > 100) { //距顶部多少像素时，出现返回顶部按钮
					$("#side-bar .gotop").fadeIn();
				} else {
					$("#side-bar .gotop").hide();
				}
			});
			$("#side-bar .gotop").click(function() {
				$('html,body').animate({
					'scrollTop' : 0
				}, 500); //返回顶部动画 数值越小时间越短
			});
		});
	</script>
	<ul id="side-bar" class="side-pannel side-bar">
		<a href="http://wpa.qq.com/msgrd?v=3&uin=1336372217&site=车西西&menu=yes"
			target="_blank" class="kefuqq"></a>

		<a href="javascript:;" class="qr weixin"><i></i></a>
		<a href="javascript:;" class="gotop" style="display: none;"></a>
	</ul>

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
