<#include "/common/common.ftl" />
<@backend title="竞拍管理列表"
js=[
'/statics/backend/auctionManager/auctionManager.js'
]
css=[]
>

<div class="box">
<form class="jqtransform" method="post" id="auctionListForm" action="${domain}/auctionManager/auctionList.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">竞拍商品列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>商品名称:</td>
						<td>
				  			<input type="text" id="itemTitle" name="itemTitle" value="${paramDto.itemTitle}" class="input-text lh25" size="20">
						</td>
						<td>竞拍者手机号:</td>
						<td>
							<input type="text" id="mobile" name="mobile" value="${paramDto.mobile}" class="input-text lh25" size="20">
						</td>
						<td>购车状态</td>
						<td>
							<select class="select" id="purchaseApplyStatus" name="purchaseApplyStatus">
								<option value="">--全部--</option>
								<#list purchaseStatus as pst>
									<option value="${pst.code}" <#if paramDto.purchaseApplyStatus == pst.code>selected</#if>>${pst.desc}</option>
								</#list>
							</select>
						</td>
					</tr>
					</table>
				</div>
				<div class="box_bottom pb5 pt5 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
				    <a href="javascript:void(0);">
				    	<input class="ml10 btn btn82 btn_search" onclick="$('#auctionListForm').submit();" type="button" value="查询" name="button" />
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
			    		<th style="width:160px">竞拍周期</th>
			    		<th>竞拍状态</th>
			    		<th>参与人数</th>
			    		<th>拍得者</th>
			    		<th>当前/最终竞拍价</th>
			    		<th>购车状态</th>
			    		<th>备注</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.tiId}</td>
			    			<td class="td_center">${obj.itemTitle}</td>
			    			<td class="td_center">${obj.startTime?string('yyyy-MM-dd HH:mm:ss')}~</br>${obj.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			    			<td class="td_center">
			    				<#list topicProgress as tps>
			    					<#if obj.status == tps.code>${tps.desc}</#if>
			    				</#list>
							</td>
							<td>${((obj.countAuctionNum!0)==0)?string('--',obj.countAuctionNum)}</td>
							<td>
								<#if obj.status == '04'>${obj.mobile}<#else>--</#if>
							</td>
			    			<td class="td_center">${((obj.finalAuctionPrice!0)==0)?string('--',(obj.finalAuctionPrice!0)?string('#0.00'))}</td>
			    			<td>
			    				<#if obj.status == '04'>
			    					<#if obj.purchaseStatus??>
										<#list purchaseStatus as pst>
											<#if obj.purchaseStatus == pst.code>${pst.desc}</#if>
										</#list>
									<#else>
										${"待申请"}
			    					</#if>
								<#else>
									--
			    				</#if>
							</td>
			    			<td title="${obj.remark}" style="text-overflow:ellipsis;-moz-text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
								${obj.remark}
							</td>
			    			<td class="td_center">
			    				<#if obj.status == '04'>
									<#if obj.purchaseStatus??>
				    					<#if obj.purchaseStatus == '02'>
				    						<a href="javascript:void(0);" style="color:blue;" class="viewPurchaseStatus" param="${obj.purchaseId}" pstatus="03">[设置为已购车]</a></br>
										</#if>
									<#else>
										<a href="javascript:void(0);" style="color:blue;" class="viewPurchaseStatus" param="${obj.purchaseId}" pstatus="04">[设置为放弃购车]</a></br>
									</#if>
			    				</#if>
			    				<a href="javascript:void(0);" style="color:blue;" class="viewremark" param="${obj.purchaseId}" type="01">[修改备注]</a>
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
		

    <@pager  pagination=page  formId="auctionListForm" />
 
</form>
</div>

<div id="editRemarkBox" style="display:none;text-align:center;" class="pt15">
	<input type="hidden" id="purchaseId" value="">
	<textarea id="remark" name="remark" cols="50" rows="8" style="width:95%"></textarea>
	<a id="saveRemarkBtn" class="layui-btn layui-btn-normal mt15" type="01">保存</a>
</div>
</@backend>