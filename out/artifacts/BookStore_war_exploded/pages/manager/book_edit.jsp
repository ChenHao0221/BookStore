<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/24
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/include/base.jsp"%>
    <style type="text/css">
        td{
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<h1>图书修改页面</h1>
<div id="main">
    <form action="manager/BookManagerServlet" method="post">
        <input name="method" value="update" type="hidden"/>
        <input name="id" value="${book.id}" type="hidden"/>
        <input name="pn" value="${param.pn}" type="hidden">
        <table border="1px" cellspacing="0" style="border-color: lavender">
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2" align="center" valign="middle">操作</td>
            </tr>
            <tr>
                <td><input name="title" type="text" value="${book.title}"></td>
                <td><input name="price" type="text" value="${book.price}"></td>
                <td><input name="author" type="text" value="${book.author}"></td>
                <td><input name="sales" type="text" value="${book.sales}"></td>
                <td><input name="stock" type="text" value="${book.stock}"></td>
                <td><input name="book_submit" type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
