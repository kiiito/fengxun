<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/15
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if(username == null||username.trim().isEmpty()||password == null||password.trim().isEmpty()) {
        // 不规范
        String errorMsg = "用户名和密码均不能为空!";
        request.setAttribute("error", errorMsg);
        request.getRequestDispatcher("demo12.jsp").forward(request, response);
    } else {
        // 规范
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        response.sendRedirect("demo12.jsp");
    }
%>
</body>
</html>

