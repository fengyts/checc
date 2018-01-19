<#include "/common/common.ftl"/>

<@backend title="商品列表"
js=[
'/statics/backend/item/item.js'
]
css=[
]>

<div class="box">
<form class="jqtransform" method="post" id="itemInfoForm" action="${domain}/item/list.htm">
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
				  			<input type="text" id="itemTitle" name="itemTitle" value="${itemDO.itemTitle}" class="input-text lh25" size="20">
						</td>
						<td>商品状态</td>
						<td>
				  			<select class="select" id="status" name="status" style="width:80px;">
				  				<option value="">--全部--</option>
				  				<option value='0'>未上架</option>
				  				<option value='1'>已上架</option>
				  				<option value='2'>已作废</option>
				  			</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#itemInfoForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_add" type ="button" value="新增" id="addItemInfo" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th style="width:3%">ID</th>
			    		<th>商品名称</th>
			    		<th>商品类型</th>
			    		<th>商品状态</th>
			    		<th>市场价</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">${obj.itemTitle}</td>
			    			<td class="td_center">
			    				<#list itemTypes as itemType>
									<#if itemType.code == obj.itemType>
										<#if itemType.code == '02'>
											<font color='#1ab3ff'>${itemType.desc}</font>
										<#else>
											${itemType.desc}
										</#if>
									</#if>
			    				</#list>
							</td>
			    			<td class="td_center">
								<#list itemStatus as status>
									<#if status.code == obj.status>
										${status.desc}
									</#if>
								</#list>	
							</td>
			    			<td class="td_center">${(obj.marketPrice!0)?string('#0.00')}</td>
			    			<td class="td_center">
			    				<a href="javascript:void(0);" style="color:blue;" class="editcatabtn editBtn" param="${obj.id}">[编辑]</a>
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
		

    <@pager  pagination=page  formId="itemInfoForm" />
 
</form>
</div>

</@backend>