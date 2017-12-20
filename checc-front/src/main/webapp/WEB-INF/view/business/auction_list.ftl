<#include "/common/common.ftl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="Generator" content="ECSHOP v2.7.3" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="车西西" />
	<meta name="Description" content="车西西" />
	<title>车西西-值得信赖的网上汽车商城</title>
	<link rel="alternate" type="application/rss+xml" title="RSS|车西西-值得信赖的网上汽车商城" href="javascript:void(0);" />
	<#--
	<link rel="shortcut icon" href="favicon.ico" />
	<link rel="icon" href="animated_favicon.gif" type="image/gif" />
	-->
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
				${auctlVO.itemTitle}
			</div>
			<div>
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
					      <th>当前状态</th>
					      <th>商品当前价</th>
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
					  				<td>
										<#if auct.isAhead == 'true'>
											<font style="color:red;">领先</font>
										<#else>
											出局
										</#if>
									</td>
					  				<td>${auct.currenctAuctPrice}</td>
					  			</tr>
					  		</#list>
					  	</#if>
					  
					  </tbody>
					</table>
			</div>
			<div class="list_pager">
				<span class="is_only_me" id="is_only_me">
					<a href="#">只看我出价</a>
					<input type="hidden" id="isOnlyMe" name="isOnlyMe" value="0" />
				</span>
				<span><a href="#" class="prepage">上一页</a></span>
				<span><a href="#" class="nextpage">下一页</a></span>
				<span>共188条记录</span>
				<input type="hidden" id="currentPageNo" name="currentPageNo" value="1" />
				<input type="hidden" id="totalPage" name="totalPage" value="1" />
				<input type="hidden" id="pageNo" name="pageNo" value="1" />
				<input type="hidden" id="tpId" name="tpId" value="${auctlVO.tpId}" />
			</div>
		</div>
	</div>

	<#include "/common/footer_both.ftl" />
	
	<script type="text/javascript" src="${js}/auction_list.js"></script>
	
</body>


</html>