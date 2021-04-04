<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借书信息</title>
</head>
<body>
<p>
    您选择的书本为：<%=request.getParameter("name") %>
</p>
<a href="books.jsp">返回订书页<a/>
<%
    String book = request.getParameter("name") ;
    //把书的信息以Cookie的方式传回给浏览器,删除一个Cookie

    //1. 确定要删除的Cookie
    // 前提:以 BOOK_NAME开关的Cookie数量大于5
    Cookie [] cookies = request.getCookies();

    //保存所有 以 BOOK_NAME开头的Cookie
    List<Cookie> bookCookie = new ArrayList<Cookie>();
    //用来保存笔books.jsp传入的book匹配的那个Cookie
    Cookie tmpCookie = null;

    if(cookies != null && cookies.length > 0){
        for(Cookie c : cookies){
            if(c.getName().startsWith("BOOK_NAME")){
                bookCookie.add(c);//保存起来到数组

                //匹配并保存本次传入的书名所匹配的cookie
                if(c.getValue().equals(book)){
                    tmpCookie = c;
                }
            }
        }
    }
    //-1.若从books.jsp页面传入的book不在BOOK_NAME的Cookie中,则删除较早那个Cookie
    // BOOK_NAME数组的第一个cookie
    if(bookCookie.size() >= 5 && tmpCookie ==null){
        tmpCookie = bookCookie.get(0);
    }

    //-2.若在其中,则删除该Cookie
    if(tmpCookie != null){
        tmpCookie.setMaxAge(0);//0表示浏览器不保存本cookie
        response.addCookie(tmpCookie);
    }

    //2.把从books.jsp传入的book作为一个Cookie返回
    Cookie cookie = new Cookie("BOOK_NAME" + book ,book);
    response.addCookie(cookie);
%>
</body>
</html>