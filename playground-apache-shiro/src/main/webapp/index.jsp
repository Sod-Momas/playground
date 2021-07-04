<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>welcom shiro application</title>
</head>
<body>
<p>
<shiro:guest>
访客时显示
    Hi there!  Please <a href="login.jsp">Login</a> or <a href="signup.jsp">Signup</a> today!
</shiro:guest>
</p>
<p>
<shiro:user>
登录后显示
    Welcome back <shiro:principal/>
</shiro:user>
</p>
<p>
<shiro:notAuthenticated>
    你没有登录（如果登录不会显示）
</shiro:notAuthenticated>
</p>
<p>
显示你的名字
Hello, <shiro:principal/>, how are you today?
</p>

<p><a href="/user/1">1 user info</a></p>
<p><a href="/account/logout"> 退出登录</a></p>
</body>
</html>