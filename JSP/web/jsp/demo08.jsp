<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/8
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    h2{
        color: blue;
    }
</style>
<body>
<form action="demo08.jsp" method="post">
    请输入一个数：<input type="number" name="number"><br>
    <input type="submit">
</form>

<%
    if("POST".equals(request.getMethod())){
        int num1=Integer.parseInt(request.getParameter("number"));
        out.print("<h2>"+num1+"的平方是："+(double)(num1*num1)+"</h2>");
    }

%>
</body>
</html>


