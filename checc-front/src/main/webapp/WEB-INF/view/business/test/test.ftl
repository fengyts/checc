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
	
	<#include "/common/common-js.ftl" />
	
	<script type="text/javascript" src="${plugins}/jquery/jquery-plugins/jquery.imagezoom.min.js"></script>
</head>
<body>
	
	<style>
		.item_img {
			width: 310px;
			height: 310px;
		}
		.itemsal>img {
			width: 100%;
			height: 100%;
		}
		img{vertical-align:top;border:0;}
		.box{width:310px;margin:100px auto;}
		.tb-pic a{display:table-cell;text-align:center;vertical-align:middle;}
		.tb-pic a img{vertical-align:middle;}
		.tb-pic a{display:block;font-family:Arial;line-height:1;}
		.tb-thumb{margin:10px 0 0;overflow:hidden;}
		.tb-thumb li{background:none repeat scroll 0 0 transparent;float:left;height:42px;margin:0 6px 0 0;overflow:hidden;padding:1px;}
		.tb-s310, .tb-s310 a{height:310px;width:310px;}
		.tb-s310, .tb-s310 img{max-height:310px;max-width:310px;}
		.tb-s310 a{font-size:271px;}
		.tb-s40 a{font-size:35px;}
		.tb-s40, .tb-s40 a{height:40px;width:40px;}
		.tb-booth{border:1px solid #CDCDCD;position:relative;z-index:1;}
		
		div.zoomDiv{z-index:999;position:absolute;top:0px;left:0px;width:200px;height:200px;background:#ffffff;border:1px solid #CCCCCC;display:none;text-align:center;overflow:hidden;}
		div.zoomMask{position:absolute;background:url("images/mask.png") repeat scroll 0 0 transparent;cursor:move;z-index:1;}
	</style>
	
	<div class="header">
	</div>
	
	<div class="content_box">
		<div class="content">
			
			<div class="box">
				<div class="tb-booth tb-pic tb-s310 item">
					<a href="images/01.jpg"><img src="${vo.picture}" alt="美女" rel="${vo.picture}" class="jqzoom" /></a>
				</div>
			</div>
		</div>
	</div>

	
<script type="text/javascript">
	$(".jqzoom").imagezoom();
</script>
</body>


</html>