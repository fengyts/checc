<#include "/common/common.ftl"/> 

<div class="page-error centered mt30">
	
	<div class="error-symbol">
		<i class="fa-warning"></i>
	</div>
	
	<h3>
		错误 404
		<small>页面未找到</small>
	</h3>
	<p>${exception.message!'很抱歉！您访问的页面不存在......'}</p>
</div>

<div class="page-error-search centered">
	<a href="javascript:void(0);" class="go-back">
		<i class="fa-angle-left"></i>
		关闭
	</a>
</div>
<script>
	var tabId = $("iframe",window.parent.document).last().attr("id") || "";
	$(function(){
		$(".go-back").click(function(){
			if("" != tabId) {
				tabId = tabId.replace("mainIframe_","");
				parent.window.closeTab(tabId);
			}
		});
	});
</script>
   