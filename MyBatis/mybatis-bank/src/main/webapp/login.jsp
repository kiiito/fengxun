<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎页面</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    if(username == null || username.isEmpty()) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<h1>欢迎<%= username %>用户！</h1>
<p>您已成功登录系统。</p>
</body>
</html>
