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
	<link rel="shortcut icon" href="favicon.ico" />
	<link rel="icon" href="animated_favicon.gif" type="image/gif" />
	
	<link rel="stylesheet" type="text/css" href="${css}/index.css" />
	<link rel="stylesheet" type="text/css" href="${css}/common.css" />
	
	<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
	<#include "/common/common-js.ftl" />
	
	<script type="text/javascript" src="${static}/common.js"></script>
	<script type="text/javascript" src="${static}/index.js"></script>
</head>
<body>
	
	<style>
		.topimg {
			width: 100%;
			height: 50px;
			margin: 0 auto
		}
		
		.topimg a img {
			width: 100%;
			height: 50px;
			margin: 0 auto;
			border: 0px
		}
	</style>
	
	<#include "/common/header.ftl" />
	
	<div class="content">
		<div class="main_box">
			<div class="checc_pos">
				<span>首页</span>
				<span>>></span>
				<span>商品详情</span>
			</div>
			
			<#-- 兑换或者竞拍  01:竞拍;02:兑换  -->
			<div class="detail_meta_box">
				<#if detailVO.topicType == '01'>
					<#include "/business/details/auction.ftl" />
				<#else>
					<#include "/business/details/exchange.ftl" />
				</#if>
			</div>
			
			<#-- 详情描述 -->
			<div class="detail_desc">
				${detailVO.description}
			</div>
		</div>
	</div>

	<#include "/common/footer_both.ftl" />
</body>
</html>