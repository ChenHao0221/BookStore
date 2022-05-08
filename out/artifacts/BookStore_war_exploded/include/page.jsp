<%--
  Created by IntelliJ IDEA.
  User: 陈昊
  Date: 2020/12/28
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function (){
        $("#gotopage").click(function (){
            //用户输入了要去第几页
            //获取到用户输入的值
            var pn = $("#pn_input").val();
            //发送新的分页请求
            window.location.href = "${page.url}&pn="+pn;
        });
    });
</script>

<div id="page_nav">
    <a href="${page.url}&pn=1">首页</a>
    <c:if test="${page.hasPrev}">
        <a href="${page.url}&pn=${page.pageNo-1}">上一页</a>
    </c:if>
    <c:if test="${page.hasPrev}">
        <a href="${page.url}&pn=${page.pageNo-1}">${page.pageNo-1}</a>
    </c:if>
    【${page.pageNo}】
    <c:if test="${page.hasNext}">
        <a href="${page.url}&pn=${page.pageNo+1}">${page.pageNo+1}</a>
    </c:if>
    <c:if test="${page.hasNext}">
        <a href="${page.url}&pn=${page.pageNo+1}">下一页</a>
    </c:if>
    <a href="${page.url}&pn=${page.totalPage}">末页</a>
    共${page.totalPage}页，${page.totalCount}条记录 到第<input value="${page.pageNo}" name="pn" id="pn_input" style="width: 30px"/>页
    <input type="button" value="确定" id="gotopage">
</div>