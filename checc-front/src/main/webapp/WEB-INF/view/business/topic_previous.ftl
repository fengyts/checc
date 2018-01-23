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
		
	<link type="text/css" rel="stylesheet" href="${static}/common/base_new.css">
	<link rel="stylesheet" type="text/css" href="${css}/common_new.css" />
	
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
	<#include "/common/page.ftl" />
	
	<div class="content_box">
		<div class="content">
			<div class="main_box">
				<div class="checc_pos">
					<span><a href="${domain}/index">首页</a></span>
					<span class="pos_arrows">>></span>
					<span>往期竞拍(${totalAuctNum})</span>
				</div>
				
				<div id="auction_items" class="topic_items1">
					<#if page.list?default([])?size!=0>
						<#assign req_url="${domain}/topicItem/itemDetails/auction" >
						<#list page.list as auc>
							<div class="items_box1">
								<div id="items" class="items">
									<div class="item_img">
										<a href="${req_url}/${auc.id}" class="itemsal" reqTime="${.now?long}"><img src="${auc.picture}"></a>
									</div>
									<div class="item_info">
										<div class="item_title">
											<a href="${req_url}/${auc.id}" class="itemsal" reqTime="${.now?long}">${auc.itemTitle}</a>
										</div>
										<#--
										<div class="pr_box">
											<div class="basic_price">
												<span class="lbtitle">成交价:</span>
												<span class="prc">
													<#if auc.currentAuctionPrice &gt;= auc.floorPrice>
														￥${auc.currentAuctionPrice?string('0.00')}
													<#else>
														流拍
													</#if>
												</span>
											</div>
											<div class="auc_price">
												<span class="lbtitle">拍得者:</span>
												<span class="prc prc_curr">
													<#if auc.currentAuctionPrice &gt;= auc.floorPrice>
														${auc.mobile[0..2] + '****' + auc.mobile[7..]}
													<#else>
														流拍
													</#if>
												</span>
											</div>
										</div>
										-->
									</div>
									
								</div>
							</div>
						</#list>
					</#if>
				</div>
				
			</div>
			<div class="pager_act">
				<form id="previousAuctionForm" action="${domain}/index/previous">
					<@pager  pagination=page  formId="previousAuctionForm" />
				</form>
			</div>
		</div>
	</div>

	<#include "/common/footer_both.ftl" />
	
<script type="text/javascript" src="${js}/index.js"></script>
	
</body>


</html>