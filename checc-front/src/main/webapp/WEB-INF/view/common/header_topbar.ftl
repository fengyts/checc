<div class="topbar">
    <div class="hd_bar content_pos">
        <ul class="login_bar">
            <li id="ECS_MEMBERZONE">
                <a href="${domain}/index" rel="nofollow">车西西首页</a><s>|</s>
                <div id="loginStatusOld" style="display:inline">
	                <#if checcUser??>
	                	<#assign mb=checcUser.mobile />
	                	${mb[0..2] + '****' + mb[7..]}
	                	<s>|</s>
	                	<a id="logout" href="#" rel="nofollow">退出</a>
	                <#else>
	                	<a href="${domain}/user/login" rel="nofollow">请登录</a><s>|</s>
	                	<a href="${domain}/user/register" rel="nofollow">免费注册</a>
	                </#if>
                </div>
                <div id="loginAjax" style="display:none;">
                	<span id="hasLogin">
                		<s>|</s>
                		<a id="logout" href="#" rel="nofollow">退出</a>
                	</span>
                </div>
            </li>
        </ul>
        <ul class="userinfo_bar">
            <li class="more-menu">
                <a href="${domain}/user/deposit/dplist"><i class="iconfont"></i>西币充值</a>
                <a href="${domain}/user/bis/membercenter"><i class="iconfont"></i>会员中心</a>
                <a href="#" onclick="javascript:window.open('${domain}/index/helper');"><i class="iconfont"></i>帮助中心</a>
                <#--
                <a href="${domain}/index/helper" target="_blank"><i class="iconfont"></i>帮助中心</a>
                -->
            </li>
            <li class="more-menu" style="display:none;">
                <a href="#">会员中心</a>
                <i class="iconfont arrow">&#8193;</i>
                <div class="more-bd">
                    <div class="list">
                        <a href="#">我的订单</a>
                        <a href="#">我的评价</a>
                        <a href="#">我的余额</a>
                        <a href="#">我的红包</a>
                        <a href="#">我的收藏</a>
                        <a href="#" class="last">修改收货地址</a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

<script type="text/javascript">
	var lgn_pg_ii;
	$(function(){
		$("#logout").on('click', function(){
			var _ahref = '${domain}/user/logout';
			$(this).attr('href', _ahref + '?returnUrl=' + window.location.pathname.substring(domain.length));
		});
	});
</script>