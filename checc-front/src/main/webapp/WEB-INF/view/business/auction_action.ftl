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
				<input type="hidden" id="typeId" value="auction">
				<div class="act_title_t">确定要出价吗?</div>
				<div class="act_title_ins">(出价后不可撤销)</div>
			</div>
			<div class="act_item_title_box">
				<span class="act_label">拍品名称：</span>			
				<span class="act_item_title">${auctionVO.itemTitle}</span>			
			</div>
			
			<div class="act_nums_box">
				<dl>
					<dt>
						<span class="act_label">出价次数：</span>
					</dt>
					<dd>
						<span class="auct_times_stock">
							<a href="javascript:void(0);" title="减1" hidefocus class="auct_reduce tb-iconfont" id="auct_reduce">-</a>
							<input type="hidden" id="auctionMaxTimes" value="${auctionVO.auctionMaxTimes!10}">
							<input type="text" class="auct_times_text" maxlength="8" value="1" title="请输入出价次数" id="auctionTimes" name="auctionTimes">
							<a href="javascript:void(0);" title="加1" hidefocus class="auct_increase tb-iconfont" id="auct_increase">+</a>次
						</span>
						<#--
						<span class="act_max_nums">(最多出价${auctionVO.auctionMaxTimes!10}次)</span>
						-->
					</dd>
	        	</dl>
			</div>
			
			<div class="act_expend_currency">
				<span class="act_label">花费西币：</span>
				<input type="hidden" id="auctionCurrency" value="${auctionVO.auctionCurrency!1}" />
				<span class="act_total_currency" id="totalCurrencyView">${auctionVO.auctionCurrency!1}</span>个
				<input type="hidden" id="totalCurrency" name="totalCurrency" value="${auctionVO.auctionCurrency!1}" /><#-- 计算后的总西比值,传至后台 -->
			</div>
			<div class="act_btn_box">
				<div class="act_abandon_btn" id="act_abandon_btn">
					<a href="#">放弃</a>
				</div>
				<div class="act_bid_btn" id="act_bid_btn" disabled='false'>
					<a href="#">竞拍</a>
				</div>
				<input type="hidden" id="tpId" name="tpId" value="${auctionVO.id}" />
				<input type="hidden" id="auctactTK" name="auctactTK" value="${auctact_tk_key}" />
			</div>
			<div class="user_currency_info" id="user_currency_info">
				<input type="hidden" id="useableCurrency" value="${auctionVO.useableCurrency!0}">
				<span>您的西币剩余${auctionVO.useableCurrency!0}个,不够本次竞拍！</span>
				<span class="auct_deposit_now">
					<a href="#" onclick="javascript:window.open('${domain}/user/deposit/dplist');">去充值>></a>
				</span>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript" src="${js}/auction_act.js"></script>

</html>
