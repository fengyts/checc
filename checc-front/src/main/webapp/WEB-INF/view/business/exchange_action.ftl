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
	
	<link rel="stylesheet" type="text/css" href="${css}/auction.css" />
	
	<#include "/common/common-js.ftl" />
	
</head>
<body>
	<div class="auction_box">
		<div class="auction_act_content">
			<div class="auction_act_title">
				<input type="hidden" id="typeId" value="exchange">
				<div class="act_title_t">确定要兑换吗?</div>
				<div class="act_title_ins">(兑换后不可撤销)</div>
			</div>
			<div class="act_item_title_box">
				<span class="act_label">商品名称：</span>			
				<span class="act_item_title">${auctionVO.itemTitle}</span>			
			</div>
			<div class="act_expend_currency">
				<span class="act_label">兑换数量：</span>
				<span class="exchange_num" id="exchangeLimitNum">${auctionVO.exchangeLimitNum!1}</span>份
			</div>
			<div class="act_expend_currency">
				<span class="act_label">花费西币：</span>			
				<span class="act_exchange_amt">${auctionVO.exchangeAmount}</span>个
				<input type="hidden" id="exchangeAmount" name="exchangeAmount" value="${auctionVO.exchangeAmount}" />			
			</div>
			<div class="act_btn_box">
				<div class="act_abandon_btn" id="act_abandon_btn">
					<a href="#">放弃</a>
				</div>
				<#if auctionVO.hasExchanged?? && auctionVO.hasExchanged == 'true'>
					<div class="act_exchange_btn" id="act_exchange_btn" disabled='false'>
						<a href="#">已经兑换</a>
					</div>
				<#else>
					<div class="act_exchange_btn" id="act_exchange_btn" disabled='false'>
						<a href="#">兑换</a>
					</div>
					<input type="hidden" id="tpId" name="tpId" value="${auctionVO.id}" />
					<input type="hidden" id="auctactTK" name="auctactTK" value="${auctact_tk_key}" />
				</#if>
			</div>
			<div class="user_currency_info" id="user_currency_info">
				<input type="hidden" id="useableCurrency" value="${auctionVO.useableCurrency!0}">
				<span>您的账户里，退回的西币为${auctionVO.useableCurrency!0}个，不够本次兑换！</span>
				<span class="auct_deposit_now">
					<a href="#" onclick="javascript:window.open('${domain}/user/bis/membercenter');">&nbsp;点击查看详情>></a>
				</span>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript" src="${js}/auction_act.js"></script>

</html>
