<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/18
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--将操作菜单抽取出来，其他页面使用的时候用include指令包含即可--%>
<%--用户未登录显示这个菜单--%>
<c:if test="${empty user}">
    <div>
        <a href="pages/user/login.jsp">登录</a>
        <a href="pages/user/regist.jsp">注册</a>
    </div>
</c:if>

<%--用户登录成功显示的菜单--%>
<c:if test="${!empty user}">
    <div>
        <span>欢迎&nbsp;<span class="um_span" style="color: crimson; font-size: larger">${user.username}</span>&nbsp;光临书城!</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="client/OrderClientServlet?method=list">我的订单</a>
        <a href="UserServlet?method=logout">注销</a>&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</c:if>
