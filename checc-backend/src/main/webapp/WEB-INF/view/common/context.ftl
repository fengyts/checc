<#macro backend title="一元购后台管理系统" 
	js=[] 
	css=[] 
	>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" href="${domain}/statics/common/common-css/msgbox.css">
	<link rel="stylesheet" href="${domain}/statics/common/common-css/common.css">
	<link rel="stylesheet" href="${domain}/statics/common/common-css/main.css">
	<link rel="stylesheet" href="${domain}/statics/plugins/layer/layui-v2.2.1/layui/css/layui.css">
	<#--
	<link rel="stylesheet" type="text/css" href="${domain}/statics/plugins/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	-->
	
	<script src="${domain}/statics/plugins/jquery/jquery-3.2.1/jquery-3.2.1.min.js"></script>
	
	<script type="text/javascript" src="${domain}/statics/plugins/layer/layui-v2.2.1/layui/layui.js"></script>
	
	<script type="text/javascript" src="${domain}/statics/common/common-js/jquery.custom.extends.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/common.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/util.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/favrite.js"></script>
	<script type="text/javascript" src="${domain}/statics/common/common-js/tab.js"></script>
	
		
	<script>
		var domain = "${domain}";
		
		/** 使用新版layui,定义layer和form模块 */
		/**
			1.x 升 2.0 特别注意事项
			layDate日期模块、layPage分页模块、Upload上传模块等等，均已完全重写，请按照最新文档修改
			获取 Form 模块接口，由之前的 var form = layui.form() 改为：var form = layui.form
			获取 Element 模块接口，由之前的 var element = layui.element() 改为：var element = layui.element
			layui.all.js 的目录调整到跟 layui.js 的同级目录，如有使用到 layui.all.js，请注意修改路径
			由于改动较大，2.0其实并不兼容1.x，强烈不推荐覆盖升级。官网仍会保留 1.x 的存档，最好按需升级。
		*/
		//var layer = layui.layer, form = layui.form;
		var layer, form;
		layui.use([ 'layer', 'form' ], function() {
			layer = layui.layer;
			form = layui.form;
		});
	</script>
	
	<#list css as file>   
		<#if file?lower_case?starts_with('http://')>
			<link rel="stylesheet" href="${file}?v=${version}" />
		<#else>
			<link rel="stylesheet" href="${domain}${file}?v=${version}" />	
		</#if>		
	</#list>
	<#list js as file>
		<#if file?lower_case?starts_with('http://')>   		
			<script type="text/javascript" src="${file}?v=${version}"></script>
		<#else>
			<script type="text/javascript" src="${domain}${file}?v=${version}"></script>
		</#if>
	</#list>
</head>
<body>
	<div class="container_custom">
		<#nested/>
	</div>
</body>
</html>
</#macro>