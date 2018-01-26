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
		
	<link rel="stylesheet" type="text/css" href="${css}/common_new.css" />
	<#include "/common/common-js.ftl" />
	
</head>
<body>

<div class="content_box">
	<div class="content">
	<div class="main_box">
		<div class="winner_detail_box">
			<div class="win_title"><span>我拍得的汽车</span></div>
			<div class="win_ti_title">
				<a href="#><span>${winnerVO.itemTitle}</span></a>
			</div>
			<div class="win_tinfo_box">
				<div class="win_price_box">
					<span class="label_title">最终竞拍价 :</span>
					<span class="final_price">${winnerVO.finalPrice?string('#0.00')}</span>
					<span class="compare_marketPrice">市场价 ：￥${winnerVO.marketPrice?string('0.00')}</span>
				</div>
				<div class="auct_num_box">
					<span class="label_title">拍得数量 :</span>
					<span class="auct_num">${winnerVO.auctNum}</span>
				</div>
				<div class="auct_time_box">
					<span class="label_title">拍得时间 :</span>
					<span class="auct_time">${winnerVO.auctTime?string('yyyy-MM-dd HH:mm:ss')}</span>
				</div>
				<div class="purchase_status_box">
					<span class="label_title">购车状态 :</span>
					<span class="purchase_status">${winnerVO.purchaseStatus}</span>
					<span id="purchaseStatus" class="purchase_apply_btn">申请购车</span>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>

</body>


</html>