<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<p>
    <a href="javascript:location.reload(true)">refresh</a>
</p>

<p><a href="book.jsp?name=Andriod">Andriod</a><br>
    <a href="book.jsp?name=redis">redis</a><br>
    <a href="book.jsp?name=dubbo">dubbo</a><br>
    <a href="book.jsp?name=git">git</a><br>
    <a href="book.jsp?name=java">Java</a><br>
    <a href="book.jsp?name=.NET">.NET</a><br>
    <a href="book.jsp?name=SQL">SQL</a><br>
    <a href="book.jsp?name=shiro">shiro</a><br>
    <a href="book.jsp?name=PHP">PHP</a><br></p>
<p></p>
<p>
    <%
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().startsWith("BOOK_NAME")) {
                    out.println("cookie value : == " + c.getValue());
                    out.println("<br/>");
                    out.println("cookie Comment : == " + c.getComment());
                    out.println("<br/>");
                    out.println("cookie Domain : == " + c.getDomain());
                    out.println("<br/>");
                    out.println("cookie MaxAge : == " + c.getMaxAge());
                    out.println("<br/>");
                    out.println("cookie Name : == " + c.getName());
                    out.println("<br/>");
                    out.println("cookie Secure : == " + c.getSecure());
                    out.println("<br/>");
                    out.println("cookie Version : == " + c.getVersion());
                    out.println("<p>---------------------<p>");
                    out.println("<br/>");
                }
            }
        }


    %>
</p>
</body>
</html>