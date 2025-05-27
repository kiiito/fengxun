<%--
  Created by IntelliJ IDEA.
  User: 29298
  Date: 2025/5/20
  Time: 上午8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="registerCss.css">
</head>
<body>
<div id="box">
    <div class="container">
        <div class="tit">注册</div>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <!-- 用户名 -->
            <div class="input-group">
                <input type="text" id="username" placeholder="请输入账号" name="username">
            </div>
            <!-- 密码 -->
            <div class="input-group password-container">
                <input type="password" id="password" placeholder="请输入密码" name="password">
                <span class="toggle-password" onclick="togglePassword()">👁️</span>
            </div>

            <!-- 手机号 -->
            <div class="input-group">
                <input type="tel" id="phone" placeholder="请输入手机号" name="phoneNumber">
            </div>
            <div class="radio-group">
                <!-- 性别 -->
                <span>性别</span>
                <div class="radio-option">
                    <input type="radio" id="male" name="gender" value="male">
                    <label for="male" style="font-size: 12px">男</label>
                </div>
                <div class="radio-option">
                    <input type="radio" id="female" name="gender" value="female">
                    <label for="female" style="font-size: 12px">女</label>
                </div>
            </div>
            <input type="submit" value="注册">
        </form>
    </div>
    <!-- 下面为装饰元素  -->
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
    function togglePassword() {
        const passwordField = document.getElementById('password');
        const isPassword = passwordField.type === 'password';
        passwordField.type = isPassword ? 'text' : 'password';
    }

    document.addEventListener('DOMContentLoaded', function () {
        document.querySelectorAll('.square li, .circle li').forEach(el => {
            setTimeout(function () {
                el.style.opacity = '0.8';
            }, 1000);
        });
    });
</script>
</body>
</html>
