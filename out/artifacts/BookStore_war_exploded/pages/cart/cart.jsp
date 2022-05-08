<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2021/1/5
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <%@include file="/include/base.jsp" %>
    <script type="text/javascript">
        $(function () {
            //当input框内容发生变化，执行代码
            $(".changeinput").change(function () {
                //获取到要修改的数量
                var count = $(this).val();
                //获取要修改的id(prop是用来获取原生属性)
                var id = $(this).attr("updateid");
                //发请求修改数量
                // location.href = "client/CartServlet?method=update&id="+id+"&count="+count;
                //发送Ajax请求
                $.getJSON("client/CartServlet?method=updateAjax&id="+id+"&count="+count, function (data) {
                    //使用data接收数据
                    $(".b_count").text(data.totalCount);
                    $(".b_price").text(data.totalMoney);
                    $("#price_"+id).text(data.totalPrice);
                });
            });
        });
    </script>
</head>
<body>
    <div class="main">
        <c:if test="${empty cart.allItems}">
            购物车为空，<a href="index.jsp">赶紧去购买吧！</a>
        </c:if>
        <c:if test="${!empty cart.allItems}">
            <table>
                <tr>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>金额</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${cart.allItems}" var="item">
                    <tr>
                        <td>${item.book.title}</td>
                        <td>
                            <input updateid="${item.book.id}" class="changeinput" type="text" style="width: 30px" name="count" value="${item.count}">
                        </td>
                        <td>${item.bool.price}</td>
                        <td id="price_${item.book.id}">${item.totalPrice}</td>
                        <td><a href="client/CartServlet?method=delete&id=${item.book.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="cart_info">
                <span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount}</span>件商品</span>
                <span class="cart_span">总金额<span class="b_price">${cart.totalMoney}</span>元</span>
                <span class="cart_span"><a href="client/CartServlet?method=clear">清空购物车</a></span>
                <span class="cart_span"><a href="client/OrderClientServlet?method=checkout">去结账</a></span>
            </div>
        </c:if>
    </div>
</body>
</html>
