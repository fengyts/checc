<#include "/common/common.ftl" />

<@backend title="虚拟账号编辑" 
js=[
'/statics/backend/strategy/strategy.js'
]
css=[
'/statics/plugins/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css'
]>


<div class="panel-body box_border">
<form id="vmAccountEditForm" action="" class="form-horizontal dr-form-bordered" method="post" >
	<div style="display:none;">
		<input type="hidden" id="userId" name="userId" value="${vmAccount.userId}" />
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	
	<div class="box_top" style="margin-bottom:20px;">
		<b class="pl15">虚拟账号信息</b>
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">手机号</label>
		<div class="col-md-4">
			<input type="text" class="form-control id="mobile" name="mobile" readonly="true" value="${vmAccount.mobile}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">西币余额<font color="#ff0000">(修改金额是在原有基础上增加,即充值效果,而非直接改动)</font></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="totalCurrency" name="totalCurrency" value="${vmAccount.totalCurrency}" />
		</div>
	</div>
	
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<div class="col-md-4"></div>
			<div>
				<a href="javascript:void(0);" class="btn btn-info" param="edit" id="cancelTabBtn" onclick='cancelButton();'>取消</a>
				<a href="javascript:void(0);" class="btn btn-primary" id="updateVmAccountBtn">保存</a>
			</div>
		</div>
	</div>
	
</form>
</div>

</@backend>
