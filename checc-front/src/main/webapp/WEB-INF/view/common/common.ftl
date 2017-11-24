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
<#assign version="version=201411291540">

<#assign itemdetailjsVersion="20151128">
<#assign itemNoStartdetailjsVersion="20151128">
<#assign itemOvertimeDetailjsVersion="20151128">
<#assign itemOverDetailjsVersion="20151128">

<#--
<#include "/domain/domain.ftl">
-->