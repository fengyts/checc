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
	
	<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>
	<script type="text/javascript" src="${plugins}/crypto-js/rollups/aes.js"></script>
	<script type="text/javascript" src="${static}/common/crypto.js"></script>
	<script type="text/javascript" src="${static}/common/utils.js"></script>
	<script type="text/javascript" src="${js}/login/user.js"></script>
	
	<script type="text/javascript">
		var domain = "${domain}";
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
            <div class="logo"><a href="index.php">车西西</a></div>
       	<div class="hd_lbar" style="display: block;">
                <a href="/" rel="nofollow">网站首页</a>
            </div>
            
        </div>
    </div>
</div>
 
<script>
	$(function(){
		$(".input_box").click(function(){
			$(this).find(".t_text").hide();	
			$(this).find("input").focus();
		})
		
		$(".input_box").focusin(function(){
			$(this).find(".t_text").hide();
		})
	
		$(".input_box").focusout(function(){
			if($(this).find("input").val() == "")
			{
				$(this).find(".t_text").show();
			}
		})	
	})
</script> 

 
<div class="gloginBg" style="height: 460px; padding-top: 40px;">
	<div id="main" class="cle">
		<div class="g_box">
			<div id="login-box">
				<h2>
					<div class="trig">
						没有帐号？<a href="${domain}/user/register.htm" class="trigger-box">点击注册</a>
					</div>
					登录
				</h2>
				<div class="form-bd">
					<div class="form_box cle" id="login-nala">
						<div class="login_box" id="login-nala-form">
							<form name="formLogin" action="" method="get" onSubmit="">
								<ul class="form">
									<li class="text_input">
										<span class="error_icon"></span>
										<span class="iconfont">Œ</span> 
										<input id="mobile" name="mobile" type="text" class="text" placeholder="用户名/手机号" />
									</li>
									<li class="text_input">
										<span class="error_icon"></span> 
										<span class="iconfont">÷</span> 
										<input id="password" name="password" type="password" class="text" placeholder="密码" />
									</li>
									<li class="login_param">
										<p>
											<a class="forget_psd" href="fgpwd">忘记密码?</a>
											<label> <input type="checkbox" value="1"
												name="remember" id="remember" class="remember-me">请保存我这次的登录信息。
											</label>
										</p>
									</li>
									<li class="last">
										<input type="hidden" name="act" value="act_login" /> 
										<input type="hidden" name="back_act" value="${domain}/index" /> 
										<input type="button" name="submit" class="btn" id="doLogin" value="登 录">
										<#--
										<a href="#" class="btn" id="doLogin" >登 录</a>
										-->
									</li>
								</ul>
							</form>
						</div>
					</div>
				</div>
				<ul class="form other-form">
					<li>
						<h5>使用第三方帐号登录</h5>
					</li>
					<li class="other-login">
						<a href="#" class="qq"></a>
						<a href="#" class="sina"></a>
						<a href="#" class="alipay"></a>
						<a href="#" class="weixin"></a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
 
<#include "/common/footer.ftl" />

	<script>
		$(function(){
			$(window).scroll(function(){
				if($(window).scrollTop()>100){  //距顶部多少像素时，出现返回顶部按钮
					$("#side-bar .gotop").fadeIn();	
				}
				else{
					$("#side-bar .gotop").hide();
				}
			});
			$("#side-bar .gotop").click(function(){
				$('html,body').animate({'scrollTop':0},500); //返回顶部动画 数值越小时间越短
			});
		});
	</script>
	
	<ul id="side-bar" class="side-pannel side-bar">
	      <a href="http://wpa.qq.com/msgrd?v=3&uin=1336372217&site=车西西&menu=yes" target="_blank" class="kefuqq"></a>
	  
	  <a href="javascript:;" class="qr weixin"><i></i></a>
	  <a href="javascript:;" class="gotop" style="display:none;"></a>
	</ul>

</body>
	<script type="text/javascript">
		var process_request = "正在处理您的请求...";
		var username_empty = "用户名不能为空。";
		var username_shorter = "用户名长度不能少于 3 个字符。";
		var username_invalid = "用户名只能是由字母数字以及下划线组成。";
		var password_empty = "登录密码不能为空。";
		var password_shorter = "登录密码不能少于 6 个字符。";
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
		var passwd_balnk = "- 密码中不能包含空格";
		var username_exist = "用户名 %s 已经存在";
	</script>
</html>
