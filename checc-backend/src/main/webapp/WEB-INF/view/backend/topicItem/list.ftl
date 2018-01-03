<#include "/common/common.ftl" />
<@backend title="专题列表"
js=[
'/statics/backend/promotion/topicItem.js'
]
css=[]
>

<div class="box">
<form class="jqtransform" method="post" id="topicItemForm" action="${domain}/topicItem/list.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">专题商品列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<input type="hidden" id="topicId" name="topicId" value="${topicItemDO.topicId}">
					<tr>
						<td>专题商品名称：</td>
						<td>
							<input type="text" id="itemTitle" name="itemTitle" value="${topicItemDO.itemTitle}" class="input-text lh25" size="20">
						</td>
						<td>专题商品状态：</td>
						<td>
							<select id="status" name="status" class="select">
								<option value='' selected='selected'>--全部--</option>
								<option value='1' <#if topicItemDO.status == '1'>selected='selected'</#if> >有效</option>
								<option value='0' <#if topicItemDO.status == '0'>selected='selected'</#if> >无效</option>
							</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="padding-left:5px;border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#topicItemForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_add" type ="button" value="新增" id="addTopicItem" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th width="3%">ID</th>
			    		<th>专题类型</th>
			    		<th>专题状态</th>
			    		<th width="200px">开始时间</th>
			    		<th width="200px">结束时间</th>
			    		<th>商品名称</th>
			    		<#--
			    		<th>商品状态</th>
						-->			    		
			    		<#if '01' == topicType>
			    			<th>竞拍底价</th>
			    			<th>竞拍单次出价</th>
			    			<th>竞拍最大限次</th>
			    		<#else>
			    			<th>兑换数量</th>
			    			<th>兑换价格</th>
			    			<th>兑换剩余数量</th>
			    			<th>兑换限次</th>
			    		</#if>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">
			    				<#list topicTypes as type>
			    					<#if type.code == topicType>
			    						${type.desc}
			    					</#if>
								</#list>
			    			</td>
			    			<td class="td_center">
								<#list topicStatus as status>
			    					<#if status.code == obj.status>
			    						${status.desc}
			    					</#if>
								</#list>
							</td>
							<td class="td_center">${obj.startTime?datetime}</td>
							<td class="td_center">${obj.endTime?datetime}</td>
							<td class="td_center">${obj.itemTitle}</td>
							<#--
							<td class="td_center">
								<#if obj.itemStatus == 'true'>有效<#else>无效</#if>
							</td>
							-->
							<#if '01' == topicType>
								<td class="td_center">${obj.floorPrices!0?string('##.00')}</td>
								<td class="td_center">${obj.auctionCurrency}</td>
								<td class="td_center">${obj.auctionMaxTimes}</td>
							<#else>
								<td class="td_center">${obj.inventory}</td>
								<td class="td_center">${obj.exchangeAmount!0?string('##.00')}</td>
								<td class="td_center">${obj.residue}</td>
								<td class="td_center">${obj.exchangeLimitNum}</td>
							</#if>
			    			<td class="td_center">
			    				<#if obj.status == '01'>
			    					<a href="javascript:void(0);" class="editcatabtn editTopicItemBtn" param="${obj.id}">[编辑]</a>
			    				<#else>
			    					<a href="javascript:void(0);" class="editcatabtn editTopicItemBtn" param="${obj.id}" style="cursor:not-allowed;color:gray;" disabled='true'>[编辑]</a>
			    				</#if>
			    			</td>
			    		</tr>
			    	</#list>
			    	</#if>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if norecoders!=null>${noRecoders}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="topicItemForm" />
 
</form>
</div>

</@backend>