<%--
  Created by IntelliJ IDEA.
  User: sothe
  Date: 2018/6/7
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的vip等级</title>
</head>
<body>
<p>vip等级 : vip称号</p>
<ul>
    <s:iterator  var="vip" value="vips">
        <li>
            <s:property value="#vip.level"/>
            :
            <s:property value="#vip.nick"/>
        </li>
    </s:iterator>
    遍历的时候使用到了OGNL
</ul>
</body>
</html>
