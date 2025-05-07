<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/1
  Time: 19:40
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
    if(username == null || password == null
            ||username.isEmpty()||password.isEmpty() ){
        out.println("请输入用户名和密码！谢谢！");
    }
    else{

        out.println("您登陆成功！<br/> 帐号："+username
                +"<br/>密码："+password);
    }
%>
</body>
</html>

