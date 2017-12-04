<#include "/common/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${css}/checc_main.css">
    <link type="text/css" rel="stylesheet" href="${css}/my.css">
    
    <script type="text/javascript" src="${plugins}/jquery/jquery-1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${plugins}/layer/layui-v2.2.1/layui/layui.js"></script>
    <script type="text/javascript">
    	var layer;
		layui.use('layer', function(){
	  		layer = layui.layer;
		}); 
    </script>
    
    <style type="text/css">
    	iframe {
    		padding-left:12%; 
    		width:1080px; 
    		height:450px;
    	}
    	.step_desc_last {
    		color: #999999;
		    font-size: 16px;
		    line-height: 30px;
    	}
    </style>
</head>
<body>
<div class="hd_m">
    <div id="checc_lg" class="checc_lg">
        <div class="checc_clg">
            <span class="checc_lgn_text"><a href="http://www.checc.cc">车西西</a></span>
            <span class="checc_lgn_link">
                <a href="http://www.checc.cc">www.checc.cc</a>
            </span>
        </div>
        <div class="checc_lg_t"><span class="checc_lg_ti">用极低的价格买到喜欢的车</span></div>
        <div class="checc_qrcode"></div>
    </div>
</div>
<div class="content">
    <div id="fg_step" class="fg_step">
        <ul>
            <li id="step1">
                <div class="round pass">
                    <span class="step_num pass">1</span>
                </div>
                <div class="step_line pass"></div>
                <div class="step_desc"><span class="pass">填写手机号</span></div>
            </li>
            <li id="step2">
                <div class="round">
                    <div class="step_num">2</div>
                </div>
                <div class="step_line"></div>
                <div class="step_desc"><span>验证手机号</span></div>
            </li>
            <li id="step3">
                <div class="round">
                    <div class="step_num">3</div>
                </div>
                <div class="step_line"></div>
                <div class="step_desc"><span>设置新密码</span></div>
            </li>
            <li id="step4">
                <div class="round">
                    <div class="step_num">4</div>
                </div>
                <div class="step_desc_last"><span>完成</span></div>
            </li>
        </ul>
    </div>
</div>

<div class="step_ifacafd" id="cnit">
</div>
<iframe id="fgpwd_ifa" frameborder=0 scrolling=no src="${domain}/user/fgform">
</iframe>


<#include "/common/footer_both.ftl" />

</body>
</html>