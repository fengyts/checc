<#include "/common/common.ftl" />
<#include "/common/common-js.ftl" />
<#include "/common/page.ftl" />

<link rel="stylesheet" type="text/css" href="${plugins}/layer/layui-v2.2.1/layui/css/layui.css" />

<style>
	th,td {
		text-align:center !important;
	}
	.auc_list_title {
		margin: 15px 0 0 0;
		font-size: 18px;
		color: #666666;
		text-align: center;
	}
</style>

<div class="currency_rec_list">
	<div class="auc_list_title">
		<span>我的西币交易记录</span>
	</div>
	<div class="auc_data_list">
		<table class="layui-table">
		  	<colgroup>
		    	<col width="165px">
			    <col width="100px">
			    <col>
			    <col>
		  	</colgroup>
		  	<thead>
			    <tr>
				    <th>交易时间</th>
				    <th>交易类型</th>
				    <th>交易描述</th>
				    <th>西币数量(个)</th>
				</tr> 
			</thead>
			<tbody>
			  	<#if page.list?default([])?size!=0>
			  		<#list page.list as arc>
			  			<tr>
			  				<#assign plusOrMinus='+'>
			  				<td>${arc.bidTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			  				<td>
			  					<#list arcTypes as arcTp>
			  						<#if arcTp.code == arc.recordType>
			  							${arcTp.desc}
			  						</#if>
			  					</#list>
			  				</td>
			  				<td>
			  					<#if arc.recordType == '01'>
			  						<#assign plusOrMinus='-'>
			  						${arc.itemTitle}(出价${arc.auctNum!1}次)
			  					<#elseif arc.recordType == '02'>
			  						<#assign plusOrMinus='-'>
			  						${arc.itemTitle}(兑换${arc.auctNum!1}次)
			  					<#elseif arc.recordType == '03'>
			  						<#assign plusOrMinus='+'>
			  						充值
			  					<#else>
			  						<#assign plusOrMinus='+'>
			  						${arc.itemTitle}(总计出价${arc.auctNum!1}次)
			  					</#if>
			  				</td>
			  				<td>
			  					<font style="color:#888;"><b>
									${plusOrMinus}
									<#if arc.recordType == '03'>
										${arc.depositCurrency}
									<#else>
										${arc.totalCurrency}
									</#if>
								</b></font>
							</td>
			  			</tr>
			  		</#list>
				<#else>
					<tr>
						<td colSpan=4><font style="color:#666;font-size:16px;">暂时没有记录</font></td>
					<tr>
			  	</#if>
			</tbody>
		</table>
	</div>
	<div class="pager_act">
		<form id="currencyRecList" method="GET" action="${domain}/user/bis/currencyRecList">
			<@pager  pagination=page  formId="currencyRecList" />
		</form>
	</div>
</div>