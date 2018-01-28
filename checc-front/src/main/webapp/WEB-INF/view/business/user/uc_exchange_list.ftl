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

<div class="item_list_box" id="auct_ifm_box">
	<div class="uc_list_data_c">
		<input type="hidden" id="ucExchangeActNum" value="${page.totalCount!0}" />
		<#if page.list?default([])?size!=0>
			<#list page.list as ucItem>
				<div class="items_box">
					<div id="items" class="items">
						<div class="uc_ld_items">
							<div class="item_img">
								<a href="#" class="itemExchanged" tiId="${ucItem.tpId}" rdId="${ucItem.recordId}" reqTime="${.now?long}" preClickTime="">
									<img src="${ucItem.picture}">
								</a>
							</div>
						</div>
						<div class="uc_item_info">
							<div class="uc_item_title">${ucItem.itemTitle}</div>
						</div>
					</div>
				</div>
			</#list>
		<#else>
			<div style="font-size:16px; margin-top:20px; color: #cfcfcf;text-align:center;">
				<span>尚未参与兑换&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</spa>
			</div>
		</#if>
	</div>
	<div class="pager_act">
		<form id="ucAuctionForm" action="${domain}/user/bis/ucAuctionList/exchange">
			<@pager  pagination=page  formId="ucAuctionForm" />
		</form>
	</div>
</div>
<script type="text/javascript">
	var _ifh = $("#auct_ifm_box").height();
	window.parent.$("#exchange_data_list").height(_ifh + 55);
	window.parent.$("#myExchangeActNum").text($("#ucExchangeActNum").val());
</script>
<script type="text/javascript" src="${js}/purchase.js"></script>
</body>
</html>