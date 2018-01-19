<#include "/common/common.ftl" />

<@backend title="商品编辑spu" 
js=[
'/statics/common/common-js/jquery-1.9.1.min.js',
'/statics/plugins/editor/kindeditor-all-min.js',
'/statics/common/common-js/editorUtil.js',
'/statics/plugins/baidu_webuploader/webuploader.min.js',
'/statics/common/imgupload/upload.js',
'/statics/common/common-js/ajaxfileupload.js',
'/statics/backend/item/item.js'
]
css=[
'/statics/plugins/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css',
'/statics/plugins/editor/themes/custom/style.css',
'/statics/plugins/baidu_webuploader/webuploader.css',
'/statics/plugins/baidu_webuploader/image-upload/style.css'
]>


<div class="panel-body box_border">
<input type="hidden" id="listIframeName" value="${listIframeName}">
<form id="itemInfoEditForm" action="" class="form-horizontal dr-form-bordered">
	<div style="display:none;">
		<input type="hidden" id="id" name="id" value="${itemDO.id}" />
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	<hr/>
	
	<div class="form-group">
		<label class="col-md-2 control-label">商品名称<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="itemTitle" name="itemTitle" value="${itemDO.itemTitle}"/>
		</div>
		<label class="col-md-2 control-label">市场价<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="marketPrice" name="marketPrice" value="${itemDO.marketPrice}"/>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">商品类型<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="itemType" name="itemType">
				<#list itemTypes as itemType>
					<option value="${itemType.code}" <#if itemDO.itemType == itemType.code>selected</#if> >${itemType.desc}</option>
				</#list>
			</select>
		</div>
		<label class="col-md-2 control-label">商品状态<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="status" name="status">
				<#list itemStatus as itemSt>
					<option value="${itemSt.code}" <#if itemDO.status == itemSt.code>selected</#if> >${itemSt.desc}</option>
				</#list>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-2">备注</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2" id="remark" name="remark">${itemDO.remark}</textarea>
		</div>
	</div>
	
	<#-- 商品上传图片 -->
	<div class="form-group">
		<#include "/backend/item/upload_picture.ftl"/>
		<div class="col-md-12">
		</div>
	</div>
	
	<div class="box_top" style="margin-bottom:5px;">
		<b class="pl15">商品描述信息</b>
	</div>
	<div class="form-group">
		<div class="col-md-12">
			<#include "/common/description.ftl"/>
		</div>
	</div>
	
	<hr/>
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<a href="javascript:void(0);" class="btn btn-primary" id="cancelEditTabBtn">取消</a>
			<a href="javascript:void(0);" class="btn btn-success" id="updateBtn">保存</a>
		</div>
	</div>
	
</form>
</div>

</@backend>
