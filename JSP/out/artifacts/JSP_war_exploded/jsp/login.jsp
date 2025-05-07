<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/29
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="box">
    <form action="check.jsp" method="post">
        <ul>
            <li>请输入用户名：<input type="text" name="userName">只能由字母，数字或下划线组成</li>
            <li><input type="submit" value="验证"></li>
        </ul>
    </form>
</div>
</body>
</html>
