<#include "/common/common.ftl" />
<#include "/common/common-js.ftl" />
<#include "/common/page.ftl" />

<link rel="stylesheet" type="text/css" href="${css}/uc_list.css" />


<div class="item_list_box" id="auct_ifm_box">
	<div class="uc_list_data_c">
		<input type="hidden" id="ucExchangeActNum" value="${page.totalCount!0}" />
		<#if page.list?default([])?size!=0>
			<#list page.list as ucItem>
				<div class="items">
					<div class="uc_ld_items">
						<div class="item_img">
							<a href="javascript:parent.window.location.href='${domain}/topicItem/itemDetails/auction/${ucItem.tpId}';" class="itemsal" reqTime="${.now?long}">
								<img src="${ucItem.picture}">
							</a>
						</div>
					</div>
					<div class="uc_item_info">
						<div class="uc_item_title">${ucItem.itemTitle}</div>
					</div>
				</div>
			</#list>
		<#else>
			<div style="font-size:16px; margin-top:20px; color: #cfcfcf;text-align:center;">
				<span>尚未参与兑换</spa>
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