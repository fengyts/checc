<#include "/common/common.ftl" />
<@backend title="专题列表"
js=[
'/statics/backend/promotion/topic.js'
]
css=[]
>

<div class="box">
<form class="jqtransform" method="post" id="topicForm" action="${domain}/topic/list.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">专题活动列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>专题状态：</td>
						<td>
							<select name="status" class="select">
								<option value='' selected='selected'>--全部--</option>
								<#list topicStatus as status>
									<option value='0' <#if topicDO.status==status.code>selected='selected'</#if>>${status.desc}</option>
								</#list>
							</select>
						</td>
						<td>专题类型：</td>
						<td>
				  			<select name="topicType" class="select">
								<option value='' selected='selected'>--全部--</option>
								<#list topicTypes as type>
									<option value='${type.code}'>${type.desc}</option>
								</#list>
							</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="padding-left:5px;border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="btn btn82 btn_search" onclick="$('#topicForm').submit();" type="button" value="查询" name="button" />
				    </a>
				    <input class="btn btn82 btn_add" type ="button" value="新增" id="addTopic" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th width="5%">ID</th>
			    		<th>专题类型</th>
			    		<th>专题状态</th>
			    		<th>开始时间</th>
			    		<th>结束时间</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">
			    				<#list topicTypes as type>
			    					<#if type.code == obj.topicType>
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
			    			<td class="td_center">
		    					<a href="javascript:void(0);" class="editcatabtn editBtn" param="${obj.id}">[编辑]</a>
			    				<a href="javascript:void(0);" class="editcatabtn associateItemBtn" param="${obj.id}" param1="${obj.name}" param2="${obj.topicType}">[查看关联商品]</a>
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
		

    <@pager  pagination=page  formId="topicForm" />
 
</form>
</div>

</@backend>