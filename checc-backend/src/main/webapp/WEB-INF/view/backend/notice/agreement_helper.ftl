<#include "/common/common.ftl" />

<@backend title="商品新增spu" 
js=[
'/statics/common/common-js/jquery-1.9.1.min.js',
'/statics/plugins/editor/kindeditor-all-min.js',
'/statics/common/common-js/editorUtil.js'
]
css=[
]>

<style type="text/css">
</style>

<form id="agreementAndHelperForm" method="post">
	<#include "/common/description.ftl"/>
	
	<input type="hidden" id="id" name="id" value="${noticeDO.id}">
	<input type="hidden" id="type" name="type" value="${type}">
</form>
<div class="mt15 ml15">
	<input type="button" value="预览" id="preview" class="layui-btn">
	<input type="button" value="保存" id="save" class="layui-btn layui-btn-normal">
</div>

<script type="text/javascript">
	$(function(){
		$("#preview").on('click', function(){
			editor.sync();
			var name = "预览";
			var _url = '${domain}/notice/preview';
			var content = $("#kindEditor").val();
			if(undefined == content || null == content || '' == content){
				layer.msg("没有内容可预览", {offset: '180px', time: 1500});
				return;
			}
			$.post(_url, {content: content}, function(data){
				var newWindow = window.open('_blank', name);
				if(!newWindow){ return false;}
				newWindow.document.write(data);
				newWindow.document.close();
				return newWindow;
			}, 'html');
		});
		
		$("#save").on('click', function (){
			editor.sync();
			var name = "预览";
			var _url = '${domain}/notice/save';
			var content = $("#kindEditor").val();
			if(undefined == content || null == content || '' == content){
				layer.msg("内容不能为空", {offset: '180px', time: 1500});
				return;
			}
			$.post(_url, $("#agreementAndHelperForm").serialize(), function(data){
				if(data.result == 1){
					layer.msg("操作成功", {offset: '180px', time: 1500});
					window.location.reload();
				} else {
					layer.msg(data.message);
				}
			}, 'json');
			
		});
		
	});
</script>

</@backend>