<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/29
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="model.check" scope="request"/>
<jsp:setProperty name="user" property="username" param="userName"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>验证结果</h2>
<ul>
    <li>输入的用户名为：${user.username}</li>
    <li>是否有效：${user.checkUsername()}</li>
    <li>提示信息：${user.message()}</li>
</ul>
</body>
</html>
