<#include "/common/common.ftl"/>

<!doctype html>
<html lang="zh-CN">
<head>

<link rel="stylesheet" href="${domain}/statics/common/common-css/common.css">
<link rel="stylesheet" href="${domain}/statics/plugins/layer/layui-v2.2.1/layui/css/layui.css">
<script src="${domain}/statics/plugins/jquery/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${domain}/statics/plugins/layer/layui-v2.2.1/layui/layui.js"></script>
<script type="text/javascript" src="${domain}/statics/plugins/layer/layui-v2.2.1/layui/lay/modules/form.js"></script>
 
	<script type="text/javascript">
		var domain = ${domain};
	</script>
 
  <style type="text/css">
  	.input-width {
  		width : 300px;
  	}
  	.fo{
		color:red;
	}
  </style>

</head>
<body>
<form class="layui-form" id="modifyPwdForm" action="">
  <div class="layui-form-item mt15">
    <label class="layui-form-label">原密码<span class="fo">*</span></label>
    <div class="layui-input-inline ">
      <input type="hidden" id="id" name="id" value="<@shiro.principal property='id' />" />
      <input type="password" id="password" name="password" placeholder="请输入原密码" autocomplete="off" class="layui-input input-width">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">新密码<span class="fo">*</span></label>
    <div class="layui-input-inline">
      <input type="password" id="password1" name="password1" placeholder="请输入密码" autocomplete="off" class="layui-input input-width">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">确认密码<span class="fo">*</span></label>
    <div class="layui-input-inline">
      <input type="password" id="password2" name="password2" placeholder="请输入密码" autocomplete="off" class="layui-input input-width">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <a class="layui-btn" onclick="modify();">立即提交</a>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</body>
	<script>
		function modify(){
			$.ajax({
				url:'updatePassword',
				dataType : 'text',
				data : {"password":$("#password").val(),"password1":$("#password1").val(),"password2":$("#password2").val()},
				type : "post",
				cache : false,
				async : false,
				error : function(request) {
					alert("Server Connection Failure...");
				},
				success : function(res) {
					console.log(res);
					var data = JSON.parse(res);
					console.log(data);
					if (1 == data.result) {// 成功
						layer.alert(data.message, {icon : 1}, function() {
							parent.window.location.reload();
							var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
							parent.layer.close(index); // 再执行关闭
						});
					} else {// 失败
						layer.alert(data.message, {icon : 8});
					}
				}
			});
		}				
	</script>
</html>