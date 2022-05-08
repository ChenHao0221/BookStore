<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <%@include file="/include/base.jsp"%>
    <style type="text/css">
        input{
            width: 180px;
        }
    </style>

    <!-- 在注册页面进行表单验证，也就是项目的第一阶段 -->
    <!-- 引入jQuery文件   -->
    <script type="text/javascript">
        $(function () {
            //点击图片更换验证码
            $("#codeImg").click(function (){
                //改变src，让他在请求一遍code.jpg，但是要让浏览器知道这是一个新请求
                var url = "code.jpg?t=" + Math.random();
                $(this).prop("src",url);
            });

            $(".itxt[name=username]").blur(function (){
                var username = $(".itxt[name=username]").val();
                //用户已经输完用户名了
                //向服务器发个请求看是否可用
                //验证用户名
                var regUserName = /^[a-zA-Z0-9]{5,18}$/;
                if (regUserName.test(username)){
                    //用户名验证成功发请求
                    $.get("UserServlet?method=checkuser&username="+username, function (data) {
                        $(".errorMsg").text(data);
                    });
                }
            });

            //1、验证用户名、密码、确认密码、email是否符合规定
            $("#sub_btn").click(function () {
                //2、验证成功提交表单
                //获取用户输入的所有值
                var username = $(".itxt[name=username]").val();
                var password = $(".itxt[name=password]").val();
                var repwd = $(".itxt[name=repwd]").val();
                var email = $(".itxt[name=email]").val();

                //用户名：6<长度<18，不能出现非法字符，合法字符为字母、数字、_、-
                //密码规则：6<长度<18，不能出现非法字符
                //确认密码规则：和密码相同
                //邮箱规则：xxxx@xx.com

                //正则表达式
                //1、创建一个正则表达式
                var regUserName = /^[a-zA-Z0-9]{5,18}$/;
                var regPwd = /^[a-zA-Z0-9]{5,18}$/;
                var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                //2、使用表达式的test方法验证传入的字符串是否符合规则
                if (!regUserName.test(username)){
                    //如果用户名验证失败
                    $(".errorMsg").text("用户名不合法！");
                    return false;
                }
                if (!regPwd.test(password)){
                    $(".errorMsg").text("密码不合法！");
                    return false;
                }
                if (repwd != password){
                    $(".errorMsg").text("两次密码不一致！");
                    return false;
                }
                if (!regEmail.test(email)){
                    $(".errorMsg").text("邮箱格式错误！");
                    return false;
                }
            });
        });
    </script> 

</head>
<body>
<div class="tit">
    <h1 style="display: inline-block">欢迎注册！</h1>
    <span class="errorMsg" style="color: crimson;display: inline-block" >
<%--   jsp方式     --%>
<%--        <%=request.getAttribute("msg")==null?"请输入用户名和密码！":request.getAttribute("msg")%>--%>
<%--    el方式    --%>
        ${msg==null?"请输入用户名和密码！":msg}
    </span>
</div>

<form action="UserServlet" method="post">
    <input type="hidden" name="method" value="regist">
    <table>
        <tr>
            <td>用户名：</td>
<%--    jsp方式表单回显        --%>
<%--            <td><input class="itxt" type="text" name="username" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>"/></td>--%>
<%--    el方式        --%>
            <td><input class="itxt" type="text" name="username" value="${param.username}"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input class="itxt" type="password" name="password"/></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input class="itxt" type="password" name="repwd"/></td>
        </tr>
        <tr>
            <td> 邮箱：</td>
<%--     jsp方式表单回显       --%>
<%--            <td><input class="itxt" type="text" name="email" value="<%=request.getParameter("email")==null?"":request.getParameter("email")%>"/></td>--%>
<%--     el方式       --%>
            <td><input class="itxt" type="text" name="email" value="${param.email}"/></td>
        </tr>
        <tr>
            <td>验证码：</td>
            <td><input class="itxt" type="text" name="code"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="注册" id="sub_btn"/></td>
        </tr>
    </table>
    <img alt="" src="code.jpg" id="codeImg">
</form>

</body>
</html>