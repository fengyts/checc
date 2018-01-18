<#include "/common/common.ftl"/>

<@backend title="虚拟账号列表"
js=[
'/statics/backend/strategy/strategy.js'
]
css=[
]>

<div id="mfVmAccountBox" style="display: none; padding: 10px;">
	<form id="mfVmAccountPwdForm" action="">
		<div style="color: #888888;">此操作会修改<strong><font color="#ff0000">所有</font></strong>虚拟账号的密码,请慎重操作！</div>
		<div class="pt10">请输入密码：</div>
		<div><input type='text' class="input-text lh25" size='40' id="vmAccountPwd" name="vmAccountPwd" /></div>
		<div class="pt10">请输入确认密码：</div>
		<div><input type='text' class="input-text lh25" size='40' id="vmAccountPwd1" name="vmAccountPwd1" /></div>
		<div class="ml10 mt15">
			<input class="layui-btn layui-btn-danger" type ="button" value="提交" id="submitVmAccountPwd" />
			<span class="ml10" id="subVmAcError" style="display:none;"><font color="#ff0000">两次密码不一致</font></span>
		</div>
	</form>
	
	<script type="text/javascript">
		$("#submitVmAccountPwd").on('click', function(){
			var _pwd = $("#vmAccountPwd").val();
			var _pwd1 = $("#vmAccountPwd1").val();
			if(null == _pwd || "" == _pwd || null == _pwd1 || "" == _pwd1 || _pwd != _pwd1){
				$("#subVmAcError").css("display", "inline");
				return;
			} else {
				$("#subVmAcError").css("display", "none");
				//$("#mfVmAccountPwdForm").submit();
				$.ajax({
					url: domain + "/strategy/modifyVmAccountPwd",
					post: 'POST',
					dataType: "json",
					data: {"pwd": _pwd, "pwd1": _pwd1},
					success: function(data){
						if(1 == data.result){
							layer.msg("修改成功", {time: 1000}, function(){
								window.layer.close(liVmAccount);
							});
						} else {
							layer.alert(data.message);
						}
					}
				});
			}
		});
	</script>
</div>

<div class="box">
<form class="jqtransform" method="post" id="vmAccountForm" action="${domain}/strategy/vmAccountList.htm">
		<#-- 搜索表单模块 -->
		<div id="search_bar" class="box mt10">
			<div class="box_border">
				<div class="box_top">
					<b class="pl15">虚拟账号列表页面</b>
				</div>
				<div class="box_center pt10 pb10">
					<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					</table>
				</div>
				<div class="box_bottom pb5 pt5 ml10 pr10 search_bar_btn" style="border-top:1px solid #dadada;">
					手机号码：<input type="text" id="mobile" name="mobile" value="${mobile}" class="input-text lh25" size="20">
			    	<input class="layui-btn" onclick="$('#vmAccountForm').submit();" type="button" value="查询" name="button" />
				    <input class="layui-btn layui-btn-normal ml10" type ="button" value="修改虚拟账号密码" id="modifyVmAccountPwd" />
				</div>
			</div>
		</div>
		
		<#-- 数据显示块 -->
		<div id="table" class="mt10">
			<div class="box span10 oh">
			    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="dataList">
			    	<tr>
			    		<th style="width:3%">ID</th>
			    		<th>手机号</th>
			    		<th>西币余额</th>
			    		<th>正参与竞拍的西币</th>
			    		<th>修改时间</th>
			    		<th>操作</th>
			    	</tr>
			    	<#if vmAccountList?default([])?size!=0>
			    	<#list vmAccountList as obj>
			    		<tr class="tr">
			    			<td class="td_center">${obj.userId}</td>
			    			<td class="td_center">${obj.mobile}</td>
			    			<td class="td_center">${obj.totalCurrency!0}</td>
			    			<td class="td_center">${obj.freeze!0}</td>
			    			<td class="td_center">${obj.modifyTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			    			<td class="td_center">
			    				<a href="javascript:void(0);" style="color:blue;" class="editcatabtn editBtn" param="${obj.userId}">[编辑]</a>
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
		
	<#--
    <@pager  pagination=page  formId="vmAccountForm" />
  	-->
</form>
</div>

</@backend>