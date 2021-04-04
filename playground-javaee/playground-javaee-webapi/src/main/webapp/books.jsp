<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订书页</title>
</head>
<body>

<p>
点击书名可以把书放进购物车。
    <a href="javascript:location.reload(true)">刷新页面</a>
</p>

<ul>
    <li><a href="book.jsp?name=Andriod">Andriod</a></li>
    <li><a href="book.jsp?name=redis">redis</a></li>
    <li><a href="book.jsp?name=dubbo">dubbo</a></li>
    <li><a href="book.jsp?name=git">git</a></li>
    <li><a href="book.jsp?name=java">Java</a></li>
    <li><a href="book.jsp?name=.NET">.NET</a></li>
    <li><a href="book.jsp?name=SQL">SQL</a></li>
    <li><a href="book.jsp?name=shiro">shiro</a></li>
    <li><a href="book.jsp?name=PHP">PHP</a></li>
</ul>
<p> <a href="cleanCookie">clean books(清空)</a> </p>
<table border>
    <tbody>
        <tr>
            <th>Name</th>
            <th>value</th>
            <th>Comment</th>
            <th>Domain</th>
            <th>MaxAge</th>
            <th>Secure</th>
            <th>Version</th>
        </tr>
<% 
Cookie[] cookies = request.getCookies();
if (cookies !=null && cookies.length> 0) {
for (Cookie c : cookies) {
    if (c.getName().startsWith("BOOK_NAME")) {
%>
        <tr>
            <td><%= c.getName() %></td>
            <td><%= c.getValue() %></td>
            <td><%= c.getComment() %></td>
            <td><%= c.getDomain() %></td>
            <td><%= c.getMaxAge() %></td>
            <td><%= c.getSecure() %></td>
            <td><%= c.getVersion() %></td>
        </tr>
<% }}} %>
    </tbody>
</table>
<br/>
</body>
</html>