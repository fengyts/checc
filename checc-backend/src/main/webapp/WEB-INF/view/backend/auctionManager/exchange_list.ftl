<#include "/common/common.ftl" />
<@backend title="兑换管理列表"
js=[
'/statics/backend/auctionManager/auctionManager.js'
]
css=[]
>

<div class="box">
<form class="jqtransform" method="post" id="exchangeListForm" action="${domain}/auctionManager/exchangeList.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">兑换商品列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>商品名称：</td>
						<td>
				  			<input type="text" id="itemTitle" name="itemTitle" value="${paramDto.itemTitle}" class="input-text lh25" size="20">
						</td>
						<td>兑换人手机号:</td>
						<td>
							<input type="text" id="mobile" name="mobile" value="${paramDto.mobile}" class="input-text lh25" size="20">
						</td>
						<td>发货状态:</td>
						<td>
				  			<select class="select" id="shipmentsStatus" name="shipmentsStatus" style="width:80px;">
				  				<option value="">--全部--</option>
				  				<#list shipmentsOrderStatus as sost>
									<option value="${sost.code}" <#if paramDto.purchaseApplyStatus == sost.code>selected</#if>>${sost.desc}</option>
								</#list>
				  			</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="ml10 btn btn82 btn_search" onclick="$('#exchangeListForm').submit();" type="button" value="查询" name="button" />
				    </a>
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList" style="table-layout:fixed;">
			    	<tr>
			    		<th style="width:3%">ID</th>
			    		<th style="width:200px">商品名称</th>
			    		<th style="width:160px">兑换周期</th>
			    		<th>兑换人</th>
			    		<th>兑换时间</th>
			    		<th>发货状态</th>
			    		<th>备注</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.tiId}</td>
			    			<td class="td_center">${obj.itemTitle}</td>
			    			<td class="td_center">${obj.startTime?string('yyyy-MM-dd HH:mm:ss')}~</br>${obj.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			    			<td class="td_center">${obj.mobile}</td>
			    			<td class="td_center">${(obj.auctTime)?string('yyyy-MM-dd HH:mm:ss')}</td>
			    			<td>
			    				<#list shipmentsOrderStatus as sost>
			    					<#if sost.code == obj.shipmentsStatus>${sost.desc}</#if>
			    				</#list>
			    			</td>
			    			<td title="${obj.remark}" style="text-overflow:ellipsis;-moz-text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
								${obj.remark}
							</td>
			    			<td class="td_center">
			    				<#if obj.shipmentsStatus == '01'>
				    				<a href="javascript:void(0);" style="color:blue;" class="shipments" param="${obj.exchangeOrderId}" estatus='02'>[发货]</a>
				    			<#elseif obj.shipmentsStatus == '02'>
				    				<a href="javascript:void(0);" style="color:blue;" class="shipments" param="${obj.exchangeOrderId}" estatus='03'>[已签收]</a>
			    				</#if>
			    				<a href="javascript:void(0);" style="color:blue;" class="viewremark" param="${obj.exchangeOrderId}" type="02">[修改备注]</a>
			    			</td>
			    		</tr>
			    	</#list>
			    	</#if>
			    </table>
			</div>
			
			<div style="font-size:16px">
				<#if noRecoders??>${noRecoders}</#if>
			</div>
		</div>
		

    <@pager  pagination=page  formId="exchangeListForm" />
 
</form>
</div>

<div id="editRemarkBox" style="display:none;text-align:center;" class="pt15">
	<input type="hidden" id="exchangeOrderId" value="">
	<textarea id="remark" name="remark" cols="50" rows="8" style="width:95%"></textarea>
	<a id="saveRemarkBtn" class="layui-btn layui-btn-normal mt15" type="02">保存</a>
</div>

</@backend>