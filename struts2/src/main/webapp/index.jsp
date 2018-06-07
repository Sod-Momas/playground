<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<h2>Hello World!</h2>

<form action="login">

    <div>
        <s:property value="result"/>
    </div>
    <div>
        <p>
            <label for="name">姓名</label> <input id="name" type='text' name="name">
        </p>
        <p>
            <label for="age">年龄</label>
            <input type="text" name="age" id="age">
        </p>
        <p>
            <input type="submit" value="提交">
        </p>
    </div>
</form>
</body>
</html>