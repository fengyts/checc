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
			<#--
			<div class="win_title"><span>我拍得的汽车</span></div>
			-->
			<input type="hidden" id="tiId" name="tiId" value="${winnerVO.tiId}" >
			<div class="win_ti_title">
				<a href="${domain}/topicItem/itemDetails/auction/${winnerVO.tiId}" target="_blank">
					<span>${winnerVO.itemTitle}</span>
				</a>
			</div>
			<div class="win_tinfo_box">
				<div class="win_price_box">
					<span class="label_title">最终竞拍价:<s></s></span>
					<span class="final_price">￥${winnerVO.finalAuctionPrice?string('#0.00')}</span>
					<span class="compare_marketPrice">市场价 ：￥${winnerVO.marketPrice?string('0.00')}</span>
				</div>
				<div class="auct_num_box">
					<span class="label_title">拍得数量:<s></s></span>
					<span class="auct_num">${winnerVO.auctNum!1}辆</span>
				</div>
				<div class="auct_time_box">
					<span class="label_title">拍得时间:<s></s></span>
					<span class="auct_time">${winnerVO.auctTime?string('yyyy-MM-dd HH:mm:ss')}</span>
				</div>
				<div class="purchase_status_box">
					<span class="label_title">购车状态:<s></s></span> 
					<span class="purchase_status">
						<#list purchaseStatus as psta>
							<#if psta.code == winnerVO.purchaseStatus>
								${psta.desc}
							</#if>
						</#list>
					</span>
					<#if winnerVO.purchaseStatus == '01'>
						<span id="purchaseApplyBtn" class="purchase_apply_btn">申请购车</span>
					</#if>
				</div>
			</div>
			<div class="promopt_info_box">
				<p>温馨提示：</p>
				<p>
					<span>1.</span>
					<span>申请购车后，请将您的身份证正反两面照片发送到车西西公众号里，工作人员会在收到后2个工作日内与您联系。</span>
				</p>
				<p>
					<span>2.</span>
					<span>请在自拍得之日起60天内申请购车，超过期限则视为放弃购车。</span>
				</p>
			</div>
		</div>
	</div>
	</div>
</div>

<script type="text/javascript" src="${js}/purchase.js"></script>
</body>

</html>