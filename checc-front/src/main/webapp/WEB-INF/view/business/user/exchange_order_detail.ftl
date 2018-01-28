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
		
	<link rel="stylesheet" type="text/css" href="${css}/purchase.css" />
	<#include "/common/common-js.ftl" />
	
</head>
<body>

<div class="content_box">
	<div class="content">
	<div class="main_box">
		<div class="winner_detail_box">
			<input type="hidden" id="tiId" name="tiId" value="${exchangeOrderVO.tiId}" >
			<div class="win_ti_title">
				<a href="${domain}/topicItem/itemDetails/exchange/${exchangeOrderVO.tiId}" target="_blank">
					<span>${exchangeOrderVO.itemTitle}</span>
				</a>
			</div>
			<div class="win_tinfo_box">
				<div class="auct_num_box">
					<span class="label_title">兑换数量:<s></s></span>
					<span class="auct_num">${exchangeOrderVO.auctNum!1}辆</span>
				</div>
				<div class="win_price_box">
					<span class="label_title">花费西币:<s></s></span>
					<span class="final_price">${exchangeOrderVO.exchangeAmount}</span>
				</div>
				<div class="auct_time_box">
					<span class="label_title">兑换时间:<s></s></span>
					<span class="auct_time">${exchangeOrderVO.auctTime?string('yyyy-MM-dd HH:mm:ss')}</span>
				</div>
				<div class="purchase_status_box">
					<span class="label_title">发货状态:<s></s></span> 
					<span class="purchase_status">
						<#list orderStatus as psta>
							<#if psta.code == exchangeOrderVO.shipmentsStatus>
								${psta.desc}
							</#if>
						</#list>
					</span>
				</div>
				<div class="exchange_promopt_box">
					<span>(发货后最长7天内送达，若长时间未收到货品，请联系客服)</span>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>

<script type="text/javascript" src="${js}/purchase.js"></script>
</body>

</html>