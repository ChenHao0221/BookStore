<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/8
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
    <%@include file="/include/base.jsp"%>
  </head>
  <body>
  <jsp:forward page="client/BookClientServlet?method=page"></jsp:forward>
  </body>
</html>
