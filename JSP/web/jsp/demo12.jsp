<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/15
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 检查是否有错误信息
    String error = (String) request.getAttribute("error");

    if(error != null) {
        // 显示错误信息
        out.print("登陆失败，" + error);
    }else{
        // 显示成功信息
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        out.print("登陆成功！用户名：" + username+"，密码："+password);
    }
%>
</body>
</html>

