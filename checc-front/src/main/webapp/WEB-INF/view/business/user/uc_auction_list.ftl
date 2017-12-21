<div class="item_list_box">
	<div class="uc_list_data_c">
		<#if page.list?default([])?size!=0>
			<#list page.list as ucItem>
				<div class="uc_ld_items">
					<div class="item_img">
						<a href="${domain}/topicItem/itemDetails/auction/${ucItem.tpId}" class="itemsal" reqTime="${.now?long}">
							<img src="${ucItem.picture}">
						</a>
					</div>
				</div>
				<div class="uc_item_info">
				</div>
			</#list>
		</#if>
		
		<form id="ucAuctionForm" action="${domain}/user/bis/ucAuctionList/auction">
		
			<@pager  pagination=page  formId="ucAuctionForm" />
		</form>
	</div>
</div>