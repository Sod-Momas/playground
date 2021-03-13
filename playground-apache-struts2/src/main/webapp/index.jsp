<%--
  Created by IntelliJ IDEA.
  User: Sod-Momas
  Date: 2018/10/21
  Time: 14:30
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
<h1> 欢迎使用 Struts 2! </h1>
<ul>
  <li><a href="<s:url action='hello'/>">Hello World</a></li>
  <li><a href="<s:url action='form'/>">提交表单</a></li>
</ul>
</body>
</html>