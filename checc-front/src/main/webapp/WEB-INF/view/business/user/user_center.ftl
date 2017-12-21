<#include "/common/common.ftl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="车西西" />
	<meta name="Description" content="车西西" />
	<title>车西西-值得信赖的网上汽车商城</title>
	<link rel="alternate" type="application/rss+xml" title="RSS|车西西-值得信赖的网上汽车商城" href="javascript:void(0);" />
	<#--
	<link rel="shortcut icon" href="favicon.ico" />
	<link rel="icon" href="animated_favicon.gif" type="image/gif" />
	-->
	<link rel="stylesheet" type="text/css" href="${css}/common.css" />
	<link rel="stylesheet" type="text/css" href="${plugins}/layer/layui-v2.2.1/layui/css/layui.css" />
	
	<#include "/common/common-js.ftl" />
	
</head>
<body>
	
	<style>
		th,td {
			text-align:center !important;
		}
	</style>
	
	<#include "/common/header.ftl" />
	
	<div class="content_box">
		<div class="content main_box">
			<#--
			<div class="checc_pos">
				<span><a href="${domain}/index">首页</a></span>
				<span class="pos_arrows">>></span>
				<span><a href="#">个人中心</a></span>
			</div>
			-->
			
			<#include "/business/user/currency_detail.ftl" />
			
			<#include "/business/user/user_auction_list.ftl" />
			
		</div>
	</div>

	<#include "/common/footer_both.ftl" />
	
	<script type="text/javascript" src="${js}/user_center.js"></script>
	
</body>


</html>