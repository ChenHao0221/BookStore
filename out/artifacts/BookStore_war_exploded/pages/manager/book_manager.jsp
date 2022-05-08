<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/24
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书管理</title>
    <%@include file="/include/base.jsp"%>
    <style type="text/css">
        td{
            text-align: center;
            vertical-align: middle;
        }
        #main{
            position: absolute;
            height: 200px;
            overflow: auto;
            border-width: 1px;
            border-color: black;
            border-style: solid;
        }
    </style>
    <script type="text/javascript">
        $(function (){
            $(".delBtn").click(function (){
                //this代表当前被点击的a
                var td = $(this).parent().parent().children(":first");
                if (!confirm("确认删除【"+td.text()+"】吗？")){
                    //用户点击取消
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<div id="header">
<%--    <img class="logo_img" alt="" src="static/img/logo.jpg">--%>
    <span class="wel_word"><h1>图书管理系统</h1></span>
    <%@include file="/include/book-manager.jsp"%><br/>
</div>
<%--<a href="pages/manager/book_edit.jsp">修改</a>--%>
<%--${requestScope.list}所有图书的集合--%>
<div id="main">
    <table border="1px" cellspacing="0" style="border-color: lavender">
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td >作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2" align="center" valign="middle">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.pageData}" var="book">
            <%--这里是每一本图书的详细信息--%>
            <tr>
                <td>${book.title}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/BookManagerServlet?method=getBook&id=${book.id}&pn=${page.pageNo}">修改</a></td>
                <td><a class="delBtn" href="manager/BookManagerServlet?method=delete&id=${book.id}&pn=${page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"><a href="pages/manager/book_edit.jsp?">添加图书</a></td>
        </tr>
    </table>

    <%@include file="/include/page.jsp"%>
</div>
</body>
</html>
