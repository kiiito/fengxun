<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用OA系统</title>
</head>
<body>
<%--<a href="<%=request.getContextPath()%>/dept/list">查看部门详情</a>--%>
<h1>用户登录</h1>
<hr>
<%-- 前端页面发送请求的时候，请求路径以“/”开始，带项目名。--%>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    username: <input type="text" name="username" ><br>
    password: <input type="password" name="password"><br>
    <input type="submit" value="login">
</form>
</body>
</html>