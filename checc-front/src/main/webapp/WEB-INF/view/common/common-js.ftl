<#-- 通用javascript -->
<#--
<script type="text/javascript" src="${static_domain}/js/lib.js"></script>
<script type="text/javascript" charset="utf-8" src="${static_domain}/js/dialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${static_domain}/js/widgets.js"></script>
<script type="text/javascript" charset="utf-8" src="${static_domain}/detail/fly.js"></script>
<script type="text/javascript" src="${static_domain}/meitun/domain/domain.js" ></script>
<script type="text/javascript" src="${static_domain}/user/js/jquery.user.login.js"></script>    
<script type="text/javascript" src="${static_domain}/meitun/meitun/js/head.js" ></script>
<script type="text/javascript" src="${static_domain}/user/js/user-static/remote/jquery.collection.js"></script>
<script type="text/javascript" src="${static_domain}/user/js/user-static/remote/jquery.user.bindMobile.js"></script>
<link type="text/css" rel="stylesheet" href="${static_domain}/css/base.css">
<link type="text/css" rel="stylesheet" href="${static_domain}/detail/css.css">
<link rel="shortcut icon" href="${static_domain}/favicon.ico">
-->

<script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>

<#-- 防止命名冲突 -->
<script type="text/javascript">
	var $ = jQuery.noConflict(), 
		domain = "${domain}", 
		PCACHE = {}, 
		layer;
	layui.use('layer', function(){
  		layer = layui.layer;
	}); 
	
	function needLoginAjax(){
		lgn_pg_ii = layer.open({
			type: 2,
			title: '请先登录',
			resize: false,
			scrollbar: false,
			//fixed: false,
			move:false,
			shade: 0.1,
			zIndex: 0,
			area: ['600px', '400px'],
			content: domain + '/user/loginAjax'
		});
	}
	
</script>



