<#-- 静态资源访问路径 -->
<#assign domain=requestContextPath.contextPath>

<#-- 静态资源访问路径 -->
<#assign static=domain + "/statics">

<#assign js=static + "/js">

<#assign css=static + "/css">

<#assign plugins=static + "/plugins">
<#assign images=static + "/images">

<#-- 图片服务器访问路径 -->
<#assign imgbase=requestContextPath.contextPath>
<#-- 版本号 -->
<#--<#assign version="version=" + .now?string("yyyyMMddHHmmss")>-->
<#assign version="version=201801081818">

<#assign favicon=images + "/favicon.ico">
<#assign bannerTitle="用极少的钱买到喜欢的车">
<#assign webtitle="车西西-" + bannerTitle>

<#--
<#include "/domain/domain.ftl">
-->