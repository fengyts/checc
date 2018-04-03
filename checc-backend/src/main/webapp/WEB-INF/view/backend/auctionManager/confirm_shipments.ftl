<#include "/common/common.ftl" />
<@backend title="竞拍管理列表"
js=[
'/statics/backend/auctionManager/auctionManager.js'
]
css=[
'/statics/plugins/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css'
]
>

<style type="text/css">
	td {
		height: 35px;
	}
	tbody {
		background: #f2f2f2;
	}
</style>
<div class="panel-body box_border">
<input type="hidden" id="listIframeName" value="${listIframeName}">
<form method="post" class="form-horizontal dr-form-bordered" id="saveShipmentsStatusForm" action="${domain}/auctionManager/saveShipmentsInfo.htm">
	<input type="hidden" id="exchangeOrderId" name="exchangeOrderId" value="${vo.exchangeOrderId}" />
	<div class="form-group">
		<div class="col-md-6">
			<table class="list_table">
			  	<caption>确认基本信息</caption>
				<tbody>
					<tr>
						<td class="col-md-2">收货人：</td>
						<td class="td_left">${vo.mobile}</td>
					</tr>
					<tr>
						<td class="col-md-2">商品名称：</td>
						<td class="td_left">${vo.itemTitle}</td>
					</tr>
					<tr>
						<td class="col-md-2">兑换时间：</td>
						<td class="td_left">${vo.auctTime?string("yyyy-MM-dd HH:mm:ss")}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</br></br>
	<div class="form-group">
		<label class="col-md-1 control-label">快递公司<span class="dr-asterisk requiredField">*</span>:</label>
		<div class="col-md-4">
			<div class="input-group">
				<input type="hidden" id="expressId" name="expressId" value="" />
				<input type="text" class="form-control" readonly="readonly" id="name" name="name" placeholder="请选择快递公司">
				<span class="btn btn-default btn-sm input-group-addon" id="selectExpress">
					<span class="glyphicon glyphicon-plus"></span>选择快递公司
				</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-1 control-label">快递单号<span class="dr-asterisk requiredField">*</span>:</label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="waybillNo" name="waybillNo" placeholder="请输入快递单号"/>
			<font color="#f00">(请务必确保运单号输入正确无误,一旦保存将无法修改)</font>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-1 control-label">备注:</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2" id="remark" name="remark"></textarea>
		</div>
	</div>
	
	<div>
		<div class="col-sm-6 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<div class="col-md-4"></div>
			<div>
				<a href="javascript:void(0);" class="btn btn-info" id="btnCancel" param="${vo.recordId}">取消</a>
				<a href="javascript:void(0);" class="btn btn-primary" id="saveShipmentsStatus">保存</a>
			</div>
		</div>
	</div>
</form>
</div>

</@backend>