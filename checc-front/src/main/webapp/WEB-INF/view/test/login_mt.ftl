<#include "/common/common.ftl" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta property="wb:webmaster" content="40245e025685314a" />
		<meta property="qc:admins" content="3535557172265514566375" />
		<title>登录-车西西</title>
		
		<link rel="shortcut icon" href="${favicon}" type="image/x-icon" />
		
		<script>
			var staticVersion = "18651";
			var base = "//user.meitun.com";
		</script>
		

		<link href="${css}/temp/base.css?version=18651" rel="stylesheet"  type="text/css"/>
		<link href="${css}/temp/custom_css.css?version=18651" rel="stylesheet"  type="text/css"/>
		<#--
		<link href="${css}/temp/center_css.css?version=18651" rel="stylesheet"  type="text/css"/>
		-->

		<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>
		<script type="text/javascript" src="${plugins}/crypto-js/rollups/aes.js"></script>
		<script type="text/javascript" src="${static}/common/crypto.js"></script>
		<script type="text/javascript" src="${static}/common/utils.js"></script>
		<script type="text/javascript" src="${js}/login/user.js"></script>

		<link rel="stylesheet" type="text/css" href="${css}/temp/account_css.css">
		<#--
		<script type="text/javascript" src="//static.meitun.com/user/js/user-static/remote/jquery.user.bindMobile.js?version=18651"></script>
		<script type="text/javascript" src="//static.meitun.com/user/js/user-static/remote/jquery.user.captcha.3.0.js?version=18651"></script>
		<script type="text/javascript" src="//static.meitun.com/user/js/user-static/remote/jquery.babytree.sync.js?version=18651"></script>
		<script type="text/javascript" src="//static.meitun.com/user/js/custom/jquery.overlay.js?version=18651"></script>
		-->

		<style>
			.loginF,.logoutF{
			   opacity: 0.9;
			}
			
			.loginF:hover,.logoutF:hover{
			   opacity: 1;
			}
			
			.getcode{
				width:110px;
				height:40px;
				font-size:14px;
				text-align:center;
				background-color:#ffffff;
				margin:auto;
				border:1px solid #ccc;
				border-radius:3px;
			}
			
			.smsCode{
				padding:0;
				margin:0;
				height:40px;
				width:205px;
				box-sizing:border-box;
				border-radius:3px;
				border:1px solid #ccc;
				position:relative;
				top:1px;
			}
		</style>
	</head>
	<body data-loadtrack="login">
		<div class="header">
			<div class="hd_bar" id="userinfo-bar">
		        <div class="bd">
	            <div class="logo"><a href="index.php">车西西</a></div>
		            
		        </div>
		    </div>
		</div>
		
		<!-- 正文 -->
		<!--登录开始-->
		<div class="main h388 clearfix">
			<a class="link" data-url="http://www.meitun.com/generalize" href="javascript:void(0);"><img src="${images}/loginBg1.jpg" class="login-bg" /></a>
			 <form id="login" autocomplete="off" method="post">
				<ul class="loginUl h388">
					<li>
						<h2 style="display:inline-block">登录</h2>
					</li>
					<li class="h65">
						<div class="login_span">
							<label class="usernameIcon"><i class="iconfont icon-bbgzsyonghu"></i></label>
							<input type="text" autocomplete="off" placeholder="手机号/邮箱" value="" name="username" id="username" />
						</div>
					</li>
					<li class="h65">
						<div class="login_span">
							<label class="passwordIcon"><i class="iconfont icon-cipher"></i></label>
							<input type="password" autocomplete="off" placeholder="请输入密码" name="password" id="password" />
						</div>
					</li>
					<li class="h65" style = "display:none" id="smsCodeLi">
						<div class="">
							<input id="getSmsCode"  type="button" value="发送验证码" class="getcode"/>
							<input type="text" autocomplete="off" placeholder="请输入短信验证码" name="smsCode" id="smsCode" class="smsCode"/>
						</div>
					</li>
					<li class="securityCode_li none" style="height: 70px;">
					 
					</li>
					<li class="h30">
						<div class="fast-remb">
							<input id="isRememberMe" name="isRememberMe" type="checkbox" checked="checked" class="vam" data-track="login_L_login1_remember">&nbsp;<label for="isRememberMe" class="vam">记住用户名</label>
						</div>
						<div class="login-tip"><a class="forget-pass" href="//user.meitun.com/user/findPassword" data-track="login_L_login1_forget">忘记密码？</a></div>
					</li>
					<li class="h50 btn-li">
						<input id="loginBtn"  type="button" value="登　录" class="btnLogin mt5 ml3" data-track="login_L_login1_button" />
					</li>
					<li style="margin-top:20px;" class="f14 fc6">
						<a style="float:left;" href="http://www.babytree.com/reg/connect_redirect.php?c=sinat&url=https://user.meitun.com/user/toLogin"><span class="mj-icon iconweibo"></span><span class="mtlogin">微博登录</span></a>
						<a style="float:left;margin-left:15px;" href="http://www.babytree.com/reg/connect_redirect.php?c=qq&url=https://user.meitun.com/user/toLogin"><span class="mj-icon iconqq"></span><span class="mtlogin">QQ登录</span></a>
						<a class="mtregister" data-track="login_L_login1_register" href="//user.meitun.com/user/toRegister">免费注册</a>
					</li>
				</ul>
			</form>
		</div>
		<!--登录结束-->
		<!-- 尾部 -->
		<div class="footer">
			<div class="login-footer">沪ICP备15000384号-1    
			Copyright&copy;2008-2015 <a target="_blank" href="http://www.meitun.com">www.meitun.com</a>. All rights reserved.
			</div>
		</div>

	</body>
</html>
<script type="text/javascript">
_geetest = {
	sendSmsCode:function(captchaObj) {// 发送验证码
			  	var smsBtn = $("#getSmsCode");
				// 1分钟内禁止点击 
				smsBtn.attr("disabled","disabled");  
				var username = $("#username").val();
				var password = $("#password").val();
				var checkCode = $("#checkCode").val();
				var result = captchaObj.getValidate();
				$.post(base+"/user/getSmsCode",
					{	username:username,
						password:password,
						checkCode:checkCode,
						geetest_challenge: result.geetest_challenge,
		                geetest_validate: result.geetest_validate,
		                geetest_seccode: result.geetest_seccode
					},function(result){
						if(result.state){
							_geetest.smsCodeTimer();
						}else{
							if(result.errorCode == 999999){
							var returnUrl = "";
							if(null != 'http://www.meitun.com/' && '' != 'http://www.meitun.com/') 
								returnUrl = 'http://www.meitun.com/';
							else 
								returnUrl = 'http://www.meitun.com';
								
							if(null != '1' && '' != '1' && '0' == '1'){
								returnUrl += result.obj.info;
							}
							
							  bindMobileForm.init({
		                        success:function(){
		                            window.location.href=returnUrl;
		                        },
		                        custom:function(){
		                            $("#dialog .noshadow").click(function(){
		                                window.location.href=returnUrl;
		                            });
		                        }
                   				 });
							}else{
								alert(result.message);
								smsBtn.removeAttr("disabled");
							}
						}
				},"json");
	},
	doLogin:function(captchaObj) {
		var smscode = $("#smsCode").val();
         if(_geetest.isMobile($("#username").val()) && (!smscode || smscode == null)){
         	alert("请输入验证码");
			return;
        }
		$(".btnLogin").attr("disabled","disabled").val("正在登录...").addClass("wait");
        $(this).overlay();
        var result = captchaObj.getValidate();
		$("#login").ajaxSubmit({
        type: "post", 
        url: base+"/user/login", 
        data: {
        	geetest_challenge: result.geetest_challenge,
            geetest_validate: result.geetest_validate,
            geetest_seccode: result.geetest_seccode
        },
        success: function(result) {
            closeOverlay();
			if (result.state) {
				babyTree.login({NL:result.obj.nl});
				var returnUrl = "";
				if(null != 'http://www.meitun.com/' && '' != 'http://www.meitun.com/') 
					returnUrl = 'http://www.meitun.com/';
				else 
					returnUrl = 'http://www.meitun.com';
					
				if(null != '1' && '' != '1' && '0' == '1'){
					returnUrl += result.obj.info;
				}
				
				bindMobileForm.checkBindMobileForDay({
					success:function(){
						window.location.href=returnUrl;
					},
					custom:function(){
						$("#dialog .noshadow").click(function(){
							window.location.href=returnUrl;
						});
					}
				});
			} else {

				if(result.errorCode == 999999){
                    var returnUrl = "";
                    if(null != 'http://www.meitun.com/' && '' != 'http://www.meitun.com/')
                        returnUrl = 'http://www.meitun.com/';
                    else
                        returnUrl = 'http://www.meitun.com';

                    bindMobileForm.checkBindMobileForDay({
                        success:function(){
                            window.location.href=returnUrl;
                        },
                        custom:function(){
                            $("#dialog .noshadow").click(function(){
                                window.location.href=returnUrl;
                            });
                        }
                    });
                }else{
                    $(".btnLogin").removeAttr("disabled").val("登　录").removeClass("wait");
                    _geetest.showError(result.documentKey,result.message);
                    captchaObj.isDone = false;
				}
			}
        }
    });
	},
	showError:function (key,errorMessage){
		if($("#"+key).parent().hasClass("login_span")) {
			$("#"+key).parent().addClass("border-red").addClass("error");
		} else {
			$("#"+key).addClass("border-red").addClass("error");
		}
    	$('<label for="'+key+'" class="error"><i class="iconfont icon-tishi"></i>&nbsp;'+errorMessage+'</label>').appendTo($("#"+key).parent().parent()); 
	},
	isMobile:function(tel){
		var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(tel)) 
		{
		    return false; 
		} 
		return true;
	},
	checkShowSmsCode:function(){
		var username = $("#username").val();
		if(_geetest.isMobile(username)){
			$("#smsCodeLi").show();
		}else{
			$("#smsCodeLi").hide();
		}
	},
	smsCodeTimer:function() {// 发送验证码倒计时，60s内不允许重复获取
		var i = 60;
		var b = 0;
		var pi = window.setInterval(function() {
			if(b <  60){  
		        $("#getSmsCode").val((i - b ) + "秒重新发送"); 
		        b++; 
		    }else{  
		    	 $("#getSmsCode").val("获取验证码");  
		         $("#getSmsCode").removeAttr("disabled"); 
		         b = 0;
		         if(pi){
		         	window.clearInterval(pi);
		         }
		    }  
			
		},1000);
	},
	initData:function() {
		$("#password").val("");
		_geetest.checkShowSmsCode();
	},
	initValidate:function() {
		$("#login").validate({
            rules: {
                username:{required: true,phoneOrEmail:true},
                password:{required: true},
            },
            messages: {
                username: {required:"请输入手机号/邮箱"},
                password: {required:"请输入密码"},
            },
            submitHandler: function (form) {
            	_geetest.submit();
            },
            errorPlacement: function (error, element) {
            	if( $(element).parent().hasClass("login_span")) {
	            	$(element).parent().addClass("border-red").addClass("error");
            	} else {
            		$(element).addClass("border-red").addClass("error");
	            }
        		 error.appendTo(element.parent().parent()); 
 	 			
            },
            success: function (label, element) {
            	if( $(element).parent().hasClass("login_span")) {
	            	$(element).parent().removeClass("border-red").removeClass("error");
            	} else {
            		$(element).removeClass("border-red").removeClass("error");
            	}
                $(label).remove();
            }
		});	
	},
	initEvent:function() {
		$(document).keypress(function(event){
		  if (event.keyCode == 13) {
		  	$("#loginBtn").click();
		  }
		});
		
		$('.login_span input').focus(function(){
			$(this).parent().addClass('focus');
			_geetest.checkShowSmsCode();
		}).blur(function(){
			$(this).parent().removeClass('focus');
			_geetest.checkShowSmsCode();
		});
	
		$(".link").click(function(){
			var url = $(this).attr("data-url");
			if(null!=url&&""!=url){
			 	$(this).attr("href",url);
			 	$(this).attr("target","_blank");
			}
		});
	
		$(".loginF").click(function(){// 跳转个人中心
			window.location.href = "//user.meitun.com/user/userCenter/myMeitun"; 
		});
		
		$(".logoutF").click(function(){// 退出登录
			confirm("确定退出登录?",function(flag){
				if(flag){
					babyTree.logout();
					window.location.href = "//user.meitun.com/user/logout"; 
				}
			},{title:"退出登录"});
		});
	},
	initGeetest:function() {
		$.ajax({
	    	 url:base+"/user/gweetInit?jsoncallback=?&t="+(new Date()).getTime(),
	    	 data:null,
	    	 type:'post',
	    	 dataType:'json',
	    	 success:function(data){
	    		 initGeetest({
		                gt: data.gt,
		                challenge: data.challenge,
		                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
		                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
		                product: "bind", // 产品形式，包括：float，popup
		                width: "300px"
		                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
		            },function(captchaObj){
		            	$('#getSmsCode').unbind('click').click(function(){
		            		captchaObj.isLoginBtn = false;
		            		$('#login').submit();
		            	});
		            	
		            	$('#loginBtn').unbind('click').click(function() {
		            		captchaObj.isLoginBtn = true;
		            		$('#login').submit();
		            	});
		            	// 定义提交函数
		            	_geetest.submit = function() {
		            		if (captchaObj.isLoginBtn && _geetest.isMobile($("#username").val())) {
		            			// 当点击的是登录，且账号是手机号时，则不弹极验直接登录。
		            			var result = captchaObj.getValidate();
		            			if (!result || !captchaObj.isDone) {
		            				//alert("请重新获取验证码");
		            				captchaObj.verify();//弹出验证框
		            			} else {
		            				_geetest.doLogin(captchaObj);
		            			}
		            		} else {
		            			captchaObj.verify();//弹出验证框
		            		}
	            		}
		            	
		            	captchaObj.onSuccess(function () {
		            		captchaObj.isDone = true;
		                    // 用户验证成功后，进行实际的提交行为
		            		if (captchaObj.isLoginBtn) {
		            			_geetest.doLogin(captchaObj);
		            		} else {
		            			_geetest.sendSmsCode(captchaObj);
		            		}
		                });
		            });
	    	 },
	    	 error : function() {  
	             alert("验证码加载失败");  
	         } 
	  });
	},
	init:function() {
		this.initData();
		this.initEvent();
		this.initValidate();
		this.initGeetest();
	}
}

$(function(){
	setTimeout(function(){
	// 预留时间让geetest加载
		_geetest.init();
	},500);
});
</script>	