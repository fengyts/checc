<#include "/common/common.ftl" />
<@backend title="物流跟踪列表"
js=[
'/statics/backend/auctionManager/auctionManager.js'
]
css=[
'/statics/backend/css/expressage.css'
]
>

<div>
	<div class="eo_info">
		物流公司：<span>${expressage.company}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		快递单号：<span>${expressage.waybillNo}</span>
	</div>
	<hr/>
    <div class="result-top"><span id="sortSpan" class="col1" title="切换排序" onclick="sortToggle();">时间</span><span class="col2">地点和跟踪进度</span></div>
    <table id="queryResult" class="result-info" cellspacing="0">
        <tbody>
    		<#assign datas=expressage.datas>
        	<#if datas?default([])?size!=0>
        		<#assign lsize=datas?size>
	        	<#list datas as info>
	        		<#if 0==info_index>
        				<tr class="last"><#--最新物流跟踪动态样式-->
        			<#else>
        				<tr>
        			</#if>
				            <td class="row1">
				                <span class="day">${info.time?string('yyyy-MM-dd')}</span>
				                <span class="time">${info.time?string('HH:mm:ss')}</span>
				            </td>
			            <#if 0==info_index>
				            <td class="status status-check">
			            <#else>
				            <td class="status">
			            </#if>
				            	&nbsp;&nbsp;
			                	<div class="col2">
			                        <span class="step">
			                            <span class="line1"></span>
			                            <span class="line2"></span>
			                        </span>
				                </div>
				            </td>
				            <td class="context">
		        				${info.context}
				            </td>
				        </tr>
	        	</#list>
        	</#if>
        	<tr>
	            <td class="row1">
	                <span class="day">${expressage.shipmentsTime?string('yyyy-MM-dd')}</span>
	                <span class="time">${expressage.shipmentsTime?string('HH:mm:ss')}</span>
	            </td>
	            <td class="status status-first">&nbsp;&nbsp;
	            </td>
	            <td class="context">
					正在等待揽收
	            </td>
	        </tr>
        </tbody>
    </table>
</div>

</@backend>