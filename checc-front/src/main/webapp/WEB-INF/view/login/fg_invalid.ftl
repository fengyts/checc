<#include "/common/common.ftl" />
<link type="text/css" rel="stylesheet" href="${css}/checc_main.css">
<link type="text/css" rel="stylesheet" href="${css}/my.css">

<style>
	body {
		color: #4c4c4c;
	}
	div{
		max-width:500px;
		max-height:400px;
	}
	
	.error_info{
		margin-top: 5px;
		padding-left: 110px;
		color: red;
	}
	
	p {
	    display: block;
	    -webkit-margin-before: 1em;
	    -webkit-margin-after: 1em;
	    -webkit-margin-start: 0px;
	    -webkit-margin-end: 0px;
	    text-align: center;
	}
	
	.error_l1{
		color: #ff0000;
		margin-top: 15px;
	    font-size: 16px;
	    font-weight: 700;
	    line-height: 30px;
	}
	.error_l2{
		margin-bottom: 18px;
	    font-size: 12px;
	    line-height: 20px;
	}
	.retry{
		max-width: 60px;
	    text-align: center;
	    border-radius: 8px;
	    background-color: #1E9FFF;
	    cursor: pointer;
	    padding-left: 200px;
	    margin-left: 120px;
	}
	.retry a{
	    text-decoration: none;
	    text-decoration-line: none;
	    padding: 0 18px;
	    color: #fff;
	    white-space: nowrap;
	    text-align: center;
	    font-size: 20px;
	    border: none;
	    border-radius: 8px;
	    height: 38px;
	    margin-left: -200px;
	    line-height: 2;
	}
</style>

<div class="step_ifa">
	<p class="error_l1">密码找回失败</p>
	<p class="error_l2">您的请求不合法,请按照正常的流程走</p>
	<div id="next_step_btn" class="next_step retry">
		<a href="javascript:window.top.location.reload('${domain}/user/fgpwd')">返回重试</a>
	</div>
</div>