<#include "/common/common.ftl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="车西西" />
	<meta name="Description" content="车西西" />
	<title>${webtitle}</title>
	<link rel="alternate" type="application/rss+xml" title="RSS|${webtitle}" href="javascript:void(0);" />
	<link rel="shortcut icon" href="${favicon}" type="image/x-icon" />
	<link rel="icon" href="animated_favicon.gif" type="image/gif" />
	
	<link type="text/css" rel="stylesheet" href="${static}/common/base.css">
	<link rel="stylesheet" type="text/css" href="${css}/common.css" />
	<#include "/common/common-js.ftl" />
	
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
	
	<div class="content_box">
			<#--
			<#include "/index/auction_flow.ftl" />
			-->			
		<div class="content">
			<#include "/business/topic.ftl" />
		</div>
	</div>

	<#include "/common/footer_both.ftl" />
	
<script type="text/javascript" src="${js}/index.js"></script>
	
</body>


</html>