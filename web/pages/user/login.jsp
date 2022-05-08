<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <%@include file="/include/base.jsp"%>
    <style type="text/css">
        /*input{*/
        /*    width: 180px;*/
        /*}*/
        a:link,a:visited{
            text-decoration: none;
            color: black;
        }
    </style>

    <!-- //使用base标签指定页面上所有路径的基础路径，所有路径都是以我指定的基础路径开始 绝对路径 -->
</head>
<body>
<div class="tit" >
    <h1 style="display: inline-block">欢迎登录！</h1>
<%--    <a href="pages/user/regist.jsp" style="color: crimson; display: inline-block">立即注册</a>--%>
    <%-- 获取到错误提示消息 --%>
    <span style="color: crimson">
        <%--如果没有错误消息，显示“请输入用户名和密码”，否则显示提示消息--%>
<%--        <%--%>
<%--            String str = (String) request.getAttribute("msg");--%>
<%--//            if (str == null){--%>
<%--//                str = "请输入用户名和密码";--%>
<%--//            }--%>
<%--            //使用三元运算符--%>
<%--        %>--%>
        <%--out.print(str==null?“请输入用户名和密码”:str)--%>

        <%--     JSP方式       --%>
<%--        <%=request.getAttribute("msg") == null?"请输入用户名和密码":request.getAttribute("msg")%>--%>

        <%--    el方式    --%>
        ${msg == null?"请输入用户名和密码":msg}
    </span>
</div>

<!--    <h1>欢迎登录</h1>-->
    <!-- http://localhost:8080/BookStore/user/LoginServlet 绝对路径 -->
    <form action="UserServlet" method="post">
        <%--    隐藏的表单项，用来标记需要调用Servlet中的哪个方法    --%>
        <input type="hidden" name="method" value="login">
        <table>
            <tr>
                <td>用户名：</td>
<%--       jsp回显方式         --%>
<%--                <td><input type="text" name="username" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>"/></td>--%>
<%--       el回显方式         --%>
                <td><input type="text" name="username" value="${param.username}"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="登录"/></td>
                <td><button><a href="pages/user/regist.jsp" >立即注册</a></button></td>
            </tr>
        </table>
    </form>
</body>
</html>