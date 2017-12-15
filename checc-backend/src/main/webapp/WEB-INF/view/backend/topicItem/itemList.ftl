<#include "/common/common.ftl"/>

<@backend title="商品列表"
js=[
'/statics/backend/promotion/topicItem.js'
]
css=[
]>

<div class="box">
<form class="jqtransform" method="post" id="itemInfoForm" action="${domain}/topicItem/itemList.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">商品列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>商品名称</td>
						<td>
				  			<input type="text" id="itemTitle" name="itemTitle" value="${itemDO.itemTitle}" class="input-text lh25" size="35">
						</td>
						<td>
						    <a href="javascript:void(0);">
						    	<input class="btn btn82 btn_search" onclick="$('#itemInfoForm').submit();" type="button" value="查询" />
						    </a>
						    <a href="javascript:void(0);">
						    	<input class="btn btn82 btn_search" id="checkItemConfirmBtn" type="button" value="确认" />
						    </a>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<thead>
				    	<tr>
				    		<th style="width:5%">
					    		<#--<input type="checkbox" id="selectAll">-->
				    		</th>
				    		<th>商品名称</th>
				    		<th style="width:20%">市场价</th>
				    	</tr>
			    	</thead>
			    	<tbody id="itemListData">
				    	<#if page.list?default([])?size!=0>
				    	<#list page.list as obj>
				    		<tr class="tr">
				    			<td class="td_center"><input type="radio" name="checkItem" value="${obj.id}"></td>
				    			<td class="td_center">${obj.itemTitle}</td>
				    			<td class="td_center">${obj.marketPrice}</td>
				    		</tr>
				    	</#list>
				    	</#if>
					</tbody>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if noRecoders??>${noRecoders}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="itemInfoForm" />
 
</form>
</div>

</@backend>