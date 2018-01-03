<#include "/common/common.ftl" />

<@backend title="专题新增" 
js=[
'/statics/plugins/My97DatePicker/WdatePicker.js',
'/statics/backend/promotion/topic.js'
]
css=[
'/statics/plugins/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css'
]>


<div class="panel-body box_border">
<input type="hidden" id="listIframeName" value="${listIframeName}">
<form id="topicAddForm" action="" class="form-horizontal dr-form-bordered" method="post" enctype="multipart/form-data">
	<div style="display:none;">
		<input type="hidden" id="id" name="id" value="${topicDO.id}" />
	</div>
	<div class="form-group">
		<div class="col-md-4" style="padding-left:50px;color:red;">注：标注*为必填项</div>
	</div>
	
	<div class="box_top" style="margin-bottom:20px;">
		<b class="pl15">专题信息</b>
	</div>
	
	<div class="form-group">
		<label class="col-md-2 control-label">专题类型<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="topicType" name="topicType">
				<#list topicTypes as type>
					<option value='${type.code}'>${type.desc}</option>
				</#list>
			</select>
		</div>
		<label class="col-md-2 control-label">专题状态<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<select class="form-control" id="status" name="status">
				<#list topicStatus as status>
					<option value='${status.code}'>${status.desc}</option>
				</#list>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题开始时间<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control Wdate" id="startTime" name="startTime" value="${topicDO.startTime}" 
        		onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'${.now?datetime}',maxDate:'#F{$dp.$D(\'endTime\')}',readOnly: true});$(this).css('background-color','#ffffff');"/>
		</div>
		<label class="col-md-2 control-label">专题结束时间<span class="dr-asterisk requiredField">*</span></label>
		<div class="col-md-4">
			<input type="text" class="form-control Wdate" id="endTime" name="endTime" value="${topicDO.endTime}" 
    			onFocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',readOnly: true});$(this).css('background-color','#ffffff');"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题排序值</label>
		<div class="col-md-4">
			<input type="text" class="form-control" id="sort" name="sort" value="${topicDO.sort!0}" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">专题描述</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2" id="description" name="description" value="${topicDO.description!'竞拍专题'}">${topicDO.description!'竞拍专题'}</textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">备注</label>
		<div class="col-md-4">
			<textarea class="form-control" rows="2" id="remark" name="remark" value="${topicDO.remark}"></textarea>
		</div>
	</div>
	
	<#-- 商品上传图片 -->
	<#--
	<div class="form-group">
		<label class="col-md-2 control-label">选择专题图片</label>
		<div class="col-md-4">
			<input type="file" value="浏览" id="image" name="image">
			<div id="preview"></div>
		</div>
	</div>
	-->
	
	<hr/>
	<div>
		<div class="col-sm-12 panel-toolbar text-left dr-slash-text" id="operateBtn">
			<div class="col-md-4"></div>
			<div>
				<a href="javascript:void(0);" class="btn btn-info" id="btnCancel" onclick='cancelButton();'>取消</a>
				<a href="javascript:void(0);" class="btn btn-primary" id="saveBtn">保存</a>
			</div>
		</div>
	</div>
	
</form>
</div>

</@backend>
