<#include "/common/common.ftl"/>

<@backend title="充值配置列表"
js=[
'/statics/backend/strategy/depositConfig.js'
]
css=[
]>

<div class="box">
<form class="jqtransform" method="post" id="depositConfigForm" action="${domain}/strategy/vmAccountList.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">充值配置列表</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					</table>
				</div>
				<div class="box_bottom pb5 pt5 ml10 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
			    	<input class="layui-btn" type="button" value="新增" id="addDepositConfig" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th style="width:3%">ID</th>
			    		<th>充值金额</th>
			    		<th>折扣</th>
			    		<th>状态</th>
			    		<th>修改时间</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if page.list?default([])?size!=0>
			    	<#list page.list as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.id}</td>
			    			<td class="td_center">${obj.depositAmount}</td>
			    			<td style="display:none">${obj.discount?string('#0.00')}</td>
			    			<td class="td_center">
								<#if obj.discount == 1>无
								<#else>
									<font color="#ff0000">
										${((obj.discount*10)?length==1)?string((obj.discount*10),(obj.discount*100))}折
									</font>
								</#if>
							</td>
			    			<td class="td_center">${obj.status?string('生效', '无效')}</td>
			    			<td class="td_center">${obj.modifyTime?string('yyyy-MM-dd HH:mm:ss')}</td>
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
		
    <@pager  pagination=page  formId="depositConfigForm" />
</form>
</div>

<div id="editDepositConfig" style="display: none; padding: 10px 30px 0 0;">
	<form id="editDepositConfigForm" class="layui-form">
		<input type="hidden" id="id" name="id" value="">
		<div class="layui-form-item">
			<label class="layui-form-label">充值金额</label>
			<div class="layui-input-block">
				<input type="text" required lay-verify="required" autocomplete="off" class="layui-input" id="depositAmount" name="depositAmount" value="">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">折扣</label>
			<div class="layui-input-block">
			    <select id="discount" name="discount">
				    <option value="1.00">无折扣</option>
				    <option value="0.99">99折</option>
				    <option value="0.98">98折</option>
				    <option value="0.95">95折</option>
				    <option value="0.90">9折</option>
				    <option value="2">其它折扣</option>
			    </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">其他折扣</label>
			<div class="layui-input-block">
				<input type="text" readonly=true lay-verify="discountCustom" autocomplete="off" class="layui-input" style="background:#e6e6e6;cursor:not-allowed;" id="discountCustom" name="discountCustom" value="">
			</div>
		</div>
		<div class="layui-form-item" style="text-align: center;">
			<a href="javascript:void(0);" class="layui-btn layui-btn-normal ml10" id="saveUpdate" lay-filter="editDepositConfigForm">保存</a>
			<a href="javascript:void(0);" class="layui-btn ml10" id="cancel">取消</a>
		</div>
	</form>
	<div style="color: #ff0000;width: 300px;text-align: left; margin-left: 40px;">
		<span>温馨提示：其他折扣值为自定义折扣值，该值范围：0<折扣值≤1.0，为1或1.0表示无折扣 。</br>例如：98折则折扣值为0.98；9折则为0.9或0.90。最多两位小数。</span>
	</div>
</div>

</@backend>