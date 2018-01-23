<#include "/common/common.ftl" />

<@backend title="商品新增spu" 
js=[
'/statics/common/common-js/jquery-1.9.1.min.js',
'/statics/plugins/editor/kindeditor-all-min.js',
'/statics/common/common-js/editorUtil.js'
]
css=[
]>

<form id="ptv" method="post" action="${domain}/strategy/preview">
<#include "/common/description.ftl"/>
</form>
<div>
	<input type="button" value="预览" id="preview">
</div>

<script type="text/javascript">
	$(function(){
		$("#preview").on('click', function(){
			var content = $("#kindEditor").val();
			window.open('${domain}/strategy/preview?content=' + content);
		});
		
	});
</script>

</@backend>