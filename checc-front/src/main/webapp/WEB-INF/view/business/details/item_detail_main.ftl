<div class="main_box">
	<div class="checc_pos">
		<span>首页</span>
		<span>>></span>
		<span>商品详情</span>
	</div>
	
	<#-- 兑换或者竞拍  01:竞拍;02:兑换  -->
	<div class="detail_meta_box">
		<#if detailVO.topicType == '01'>
			<#include "/business/details/auction.ftl" />
		<#else>
			<#include "/business/details/exchange.ftl" />
		</#if>
	</div>
	
	<#-- 详情描述 -->
	<div class="detail_desc">
		${detailVO.description}
	</div>
</div>