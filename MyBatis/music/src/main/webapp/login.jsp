<%--
  Created by IntelliJ IDEA.
  User: 29298
  Date: 2025/5/19
  Time: 上午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="loginCss.css">
</head>
<body>
<div id="box">
    <div class="container">
        <div class="tit">登录</div>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" placeholder="帐号" name="username" id="username">
            <div>
                <input type="password" name="password" placeholder="密码" id="password">
                <span class="toggle-password" onclick="togglePassword()">👁️</span>
            </div>
            <button>登录</button>
            <span>没有账号？<a href="register.jsp">去注册</a>
        </span>
        </form>

    </div>
    <div class="square">
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
    <div class="circle">
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>
<script>
    //显示密码
    function togglePassword() {
        const passwordField = document.getElementById('password');
        const toggleButton = document.querySelector('.toggle-password');
        // 切换输入框类型
        const isPassword = passwordField.type === 'password';
        passwordField.type = isPassword ? 'text' : 'password';
    }
</script>
</body>
</html>
