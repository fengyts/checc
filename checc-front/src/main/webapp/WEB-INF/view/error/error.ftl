<#include "/common/common.ftl">	

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>${webtitle}</title>
		<link type="text/css" rel="stylesheet" href="${static}/common/base.css">
</head>
	
	<body>
		<#include "/common/header_banner.ftl">
	
		<div class="wrap content_pos">
			<div class="error_box">
				<div class="sorry">500很抱歉！您访问的页面不存在......</div>
				<div class="lost"></div>
				<div class="visit">
					<div class="vst">您还可以访问：</div>
					<ul class="clearfix">
						<li><a href="${domain}/">车西西首页</a></li>
						<li class="last"><a href="${domain}/index/helper">帮助中心</a></li>
					</ul>
					<!--div class="hotline">免费客服热线：4000896600</div-->
				</div>
			</div>
		</div>
		
		<#include "/common/footer_both.ftl" />
	</body>
</html>
 