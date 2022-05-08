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
    <title>图书商城</title>
    <%@include file="/include/base.jsp" %>
    <script type="text/javascript">
        $(function (){
            //加入购物车的Ajax
            $(".addCartBtn").click(function (){
                //获取当前btn自定义的updateId属性
                var bookid = $(this).attr("updateId");
                //getJSON将响应的字符串直接转换为JSON对象
                $.getJSON("client/CartServlet?method=addAjax&id="+bookid, function (data) {
                    //将数据的内容放在相应区域
                });
            });
        });
    </script>
</head>
<body>
<%--  <!-- jsp脚本片段 java代码片段 -->--%>
<%--  <%--%>
<%--    out.write("Hello");--%>
<%--  %>--%>
<%--  <!-- jsp表达式 在页面输出内容 -->--%>
<%--  <%="Hello" %>--%>
<%--  <%=new Date() %>--%>
<%--  <% %>--%>
<div id=header>
    <%--  在这里加入操作菜单  --%>
    <%@include file="/include/user-info.jsp" %>

    <a href="pages/cart/cart.jsp">购物车</a>
    <a href="pages/manager/manager.jsp">后台管理</a>
</div>

<div>
    <form action="client/BookClientServlet" method="get">
        <input type="hidden" name="method" value="pageByPrice"/>
        价格：
        <input type="text" style="width: 50px" name="min" value="${param.min}">
        -
        <input type="text" style="width: 50px" name="max" value="${param.max}">
        元
        <button>查询</button>
    </form>
</div>

<div id="b_list">
    <table border="1px" cellspacing="0" style="border-color: lavender">
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
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
                <td colspan="2" class="book_add">
                    <button class="addCartBtn" updateId="${book.id}">加入购物车</button>
<%--                    <a href="client/CartServlet?method=add&id=${book.id}">加入购物车</a>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@include file="/include/page.jsp" %>

</div>
</body>
</html>
