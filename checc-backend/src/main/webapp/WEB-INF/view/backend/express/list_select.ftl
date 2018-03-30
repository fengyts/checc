<#include "/common/common.ftl"/>

<@backend title="快递公司列表"
js=[
'/statics/backend/express/express.js'
]
css=[
'/statics/plugins/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css'
]>

<div class="box">
<form class="jqtransform" method="post" id="expressInfoForm" action="${domain}/expressInfo/list.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">快递公司页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>物流公司名称</td>
						<td>
				  			<input type="text" id="name" name="name" value="${expressInfoDO.name}" class="input-text lh25" size="30">
						</td>
						<td>物流公司编码</td>
						<td>
				  			<input type="text" id="companyCode" name="companyCode" value="${expressInfoDO.companyCode}" class="input-text lh25" size="20">
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
			    	<input class="ml10 btn btn-primary" onclick="cancelButton();" type="button" value="取消" name="button" />
				    <a href="javascript:void(0);" class="ml10 btn btn-info" onclick="$('#expressInfoForm').submit();">查询</a>
				    <input class="ml10 btn btn-success" type="button" value="确认" name="button" id="checkedConfirmBtn" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<thead>
			    	<tr>
			    		<th style="width:4%"></th>
			    		<th style="width:5%">ID</th>
			    		<th style="width:150px;">物流公司名称</th>
			    		<th>物流公司编码</th>
			    	</tr>
			    	</thead>
			    	<tbody id="expressDataList">
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td><input type="radio" class="singleCheck" name="checkExpress"/></td>
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">${obj.name}</td>
			    			<td class="td_center">${obj.companyCode}</td>
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
		

    <@pager  pagination=page  formId="expressInfoForm" />
 
</form>
</div>

</@backend>