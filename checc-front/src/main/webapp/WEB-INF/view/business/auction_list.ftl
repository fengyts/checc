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
	
	<link type="text/css" rel="stylesheet" href="${static}/common/base.css">
	<link rel="stylesheet" type="text/css" href="${css}/common.css" />
	<link rel="stylesheet" type="text/css" href="${plugins}/layer/layui-v2.2.1/layui/css/layui.css" />
	
	<#include "/common/common-js.ftl" />
	
</head>
<body>
	
	<style>
		th,td {
			text-align:center !important;
		}
	</style>
	
	<#include "/common/header.ftl" />
	
	<div class="content_box">
		<div class="content main_box">
			<div class="checc_pos">
				<span><a href="${domain}/index">首页</a></span>
				<span class="pos_arrows">>></span>
				<span><a href="${domain}/topicItem/itemDetails/auction/${auctlVO.tpId}">竞拍详情</a></span>
				<span class="pos_arrows">>></span>
				<span>出价记录</span>
			</div>
			
			<div class="auc_list_title">
				<span>${auctlVO.itemTitle}</span>
			</div>
			<div class="auc_data_list">
				<table class="layui-table">
				  	<colgroup>
				    	<col width="165px">
					    <col width="100px">
					    <col>
					    <col>
					    <col>
				  	</colgroup>
				  	<thead>
					    <tr>
						    <th>出价时间</th>
						    <th>出价人</th>
						    <th>出价次数(次)</th>
						    <th>出价西币(个)</th>
						    <th>当前/最终竞拍价(￥)</th>
						    <th>当前/最终出价次数最多</th>
						</tr> 
					</thead>
					<tbody>
					  	<#if auctlVO.auctionList?default([])?size!=0>
					  		<#list auctlVO.auctionList as auct>
					  			<tr <#if auct.isAhead == 'true'>style="color:red;"</#if>>
					  				<td>${auct.bidTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					  				<td>${auct.bidder}</td>
					  				<td>${auct.bidNum}</td>
					  				<td>${auct.totalCurrency}</td>
					  				<td>${auct.currenctAuctPrice?string('#0.00')}</td>
					  				<td>
					  					<#--
										<#if auct.isAhead == 'true'>
											<font style="color:red;">领先</font>
										<#else>
											出局
										</#if>
										-->
										${auct.bidder}<br/>
										(当前累计${auct.currentAuctCount}次)
									</td>
					  			</tr>
					  		</#list>
						<#else>
							<tr>
								<td colSpan=6><font style="color:#666;font-size:16px;">暂时无人出价</font></td>
							<tr>
					  	</#if>
					</tbody>
				</table>
			</div>
			<div class="list_pager_act">
				<div class="is_only_me" id="is_only_me">
					<a href="#" <#if auctlVO.auctionList?default([])?size!=0>style="display:inline;"<#else>style="display:none;"</#if>>
						<#if auctlVO.isOnlyMe == 'true'>看全部出价<#else>只看我出价</#if>
					</a>
				</div>
				
				<div class="pager_act">
					<#include "/common/page.ftl" />
					<form id="auctionListForm" method="GET" action="${domain}/auction/auctionList?tpId=${auctlVO.tpId}">
						<@pager  pagination=page  formId="auctionListForm" />
						<input type="hidden" id="tpId" name="tpId" value="${auctlVO.tpId}" />
						<input type="hidden" id="isOnlyMe" name="isOnlyMe" value="${auctlVO.isOnlyMe?string('1','0')}" />
					</form>
				</div>
				<#--
				<span><a href="#" class="prepage a_disabled" id="prepage">上一页</a></span>
				<span><a href="#" class="nextpage" id="nextpage">下一页</a></span>
				<span>共${totalCount!0}条记录</span>
				<input type="hidden" id="currentPageNo" name="currentPageNo" value="1" />
				<input type="hidden" id="totalPage" name="totalPage" value="${totalPage}" />
				<input type="hidden" id="totalCount" name="totalCount" value="${totalCount}" />
					<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}" />
				-->
				
			</div>
		</div>
	</div>

	<#include "/common/footer_both.ftl" />
	
	<script type="text/javascript" src="${js}/auction_list.js"></script>
	
</body>


</html>