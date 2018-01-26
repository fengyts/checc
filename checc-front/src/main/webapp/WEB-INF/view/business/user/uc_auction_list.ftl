<#include "/common/common.ftl" />
<#include "/common/common-js.ftl" />
<#include "/common/page.ftl" />

<link rel="stylesheet" type="text/css" href="${css}/uc_list.css" />


<div class="item_list_box" id="auct_ifm_box">
	<div class="uc_list_data_c">
		<input type="hidden" id="ucAuctActNum" value="${page.totalCount!0}" />
		<#if page.list?default([])?size!=0>
			<#list page.list as ucItem>
				<div class="items_box">
					<div id="items" class="items">
						<div class="uc_ld_items">
							<div class="item_img">
								<a href="#" class="itemsal" reqTime="${.now?long}" tiId="${ucItem.tpId}">
									<img src="${ucItem.picture}" <#if ucItem.isWinner?? && ucItem.isWinner == 'true'>style="opacity: 0.5;"</#if>>
									<#if ucItem.isWinner?? && ucItem.isWinner == 'true'>
										<div class="uc_my_win">
											<span>我已拍得</span>
										</div>
										<input type="hidden" value="1" class="tiIdInp">
									<#else>
										<input type="hidden" value="0" class="tiIdInp">
									</#if>
								</a>
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
	
	$(function(){
		$(".itemsal").on('click', function(){
			var _tiId = $(this).attr("tiId");
			//var _url = domain + "/topicItem/itemDetails/auction/" + _tiId;
			var _url = domain + "/user/bis/auctionSuccess/auction/" + _tiId;
			$.ajax({
				url : _url,
				method : 'GET',
				dataType : 'html',
				data : {reqTime: new Date().getTime()},
				cache : false,
				success: function(data, status, xhr) { 
			        var errorCode = xhr.getResponseHeader("errorCode");
			        if('999' == errorCode) { // 未登陆
			        	needLoginAjax();
			        } else { // 已经登陆
			        	lgn_pg_ii = window.parent.layer.open({
			        		type: 2,
			        		title: "我拍得的汽车",
			        		resize: false,
			        		//scrollbar: false,
			        		//fixed: false,
			        		move:false,
			        		shade: 0.1,
			        		anim: 5,
			        		content: _url,
			        		area: ['600px', '380px']
			        	});
			        }
			    }
			});
		});
		
	});
</script>