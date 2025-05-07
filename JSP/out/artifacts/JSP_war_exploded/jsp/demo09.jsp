<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/15
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //从application中获取Count
    Integer count= (Integer) application.getAttribute("Count");
    if(count==null){
        count=1;
    }else{
        count++;
    }
    //将新的记录存入application中
    application.setAttribute("Count", count);
%>
<p>当前页面被访问了：<%=count%>次！</p>
</body>
</html>

