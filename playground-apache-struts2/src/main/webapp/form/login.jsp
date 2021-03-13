<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页</title>
</head>
<style>
 .alert{
   color:#f00;
 }
</style>
<body>
<h1>请输入用户名密码进行登录!</h1>
<div>
<c:if test="${message!=null}">
<p class="alert" >message=[<s:property value="message" />]</p>
</c:if>

   <form action="${pageContext.request.contextPath}/form.action" method="post">
       用户名：<input name="loginName" type="text" value="sod"/></br>
       密码：<input name="loginPwd" type="password" value="momas"/></br>
       <input type="submit" value="确定"/>
   </form>
</div>
<a href="/">返回上一级</a>
</body>
</html>