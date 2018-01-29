<#include "/common/common.ftl" />
<#include "/common/common-js.ftl" />
<#include "/common/page.ftl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<link rel="stylesheet" type="text/css" href="${css}/uc_list.css" />

<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<div class="item_list_box" id="auct_ifm_box">
	<div class="uc_list_data_c">
		<input type="hidden" id="ucAuctActNum" value="${page.totalCount!0}" />
		<#if page.list?default([])?size!=0>
			<#list page.list as ucItem>
				<div class="items_box">
					<div id="items" class="items">
						<div class="uc_ld_items">
							<div class="item_img">
								<a href="#" class="itemsal" reqTime="${.now?long}" tiId="${ucItem.tpId}" preClickTime="">
									<img src="${ucItem.picture}" <#if ucItem.isWinner?? && ucItem.isWinner == 'true'>style="opacity: 0.5;"</#if>>
									<#if ucItem.isWinner?? && ucItem.isWinner == 'true'>
										<input type="hidden" value="1" class="tiIdInp">
									<#else>
										<input type="hidden" value="0" class="tiIdInp">
									</#if>
								</a>
								<#if ucItem.isWinner?? && ucItem.isWinner == 'true'>
									<div class="uc_my_win">
										<span>我已拍得</span>
									</div>
								</#if>
							</div>
						</div>
						<div class="uc_item_info">
							<div class="uc_item_title">${ucItem.itemTitle}</div>
							<div class="uc_item_aucres">
								<#if ucItem.itemStatus=='02'>
									<div><font style='color:#09c762;'>进行中</font></div>
								<#else>
									<#--
									<div class="win_currauc1">￥${ucItem.currenctAuctPrice?string('#0.00')}</div>
									<#if ucItem.isWinner?? && ucItem.isWinner == 'true'>
										<div class="win_currauc2">${(ucItem.deliveryStatus=='01')?string('<font style=color:#0099ff;>已提车</font>','未提车')}</div>
									<#else>
									</#if>
									<div class="win_currauc2"><font style=color:#888888;>已结束</font></div>
									-->
									<div><font style=color:#888888;>已结束</font></div>
								</#if>
							</div>
						</div>
					</div>
				</div>
			</#list>
		<#else>
			<div style="font-size:16px; margin-top:20px; color: #cfcfcf;text-align:center;">
				<span>尚未参与竞拍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</spa>
			</div>
		</#if>
	</div>
	<div class="pager_act">
		<form id="ucAuctionForm" action="${domain}/user/bis/ucAuctionList/auction">
			<@pager  pagination=page  formId="ucAuctionForm" />
		</form>
	</div>
</div>

<script type="text/javascript">
	var _ifh = $("#auct_ifm_box").height();
	window.parent.$("#auct_data_list").height(_ifh + 55);
	window.parent.$("#myAuctActNum").text($("#ucAuctActNum").val());
</script>
<script type="text/javascript" src="${js}/purchase.js"></script>
</body>
</html>
