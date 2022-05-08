<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/29
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/include/base.jsp"%>
</head>
<body>
<div>
    <iframe name="cookieFrame"></iframe>
    <ul>
        <li><a target="cookieFrame" href="CookieServlet?method=create">创建cookie</a></li>
        <li><a target="cookieFrame" href="CookieServlet?method=get">获取cookie</a></li>
        <li><a target="cookieFrame" href="CookieServlet?method=delete">删除cookie</a></li>
        <li><a target="cookieFrame" href="CookieServlet?method=persist">创建持久性cookie[生存时间5秒]</a></li>
        <li><a target="cookieFrame" href="CookieServlet?method=setPath">设置cookie路径</a></li>
        <li><a target="cookieFrame" href="CookieServlet?method=update">修改cookie的值</a></li>
    </ul>
</div>
</body>
</html>
