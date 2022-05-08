<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/18
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 所有页面要引入资源 --%>
<%--base标签的链接需要动态获取，而不是指定的--%>
<%--协议://主机名:端口号/项目路径/--%>
<base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/js/jquery.js"></script>