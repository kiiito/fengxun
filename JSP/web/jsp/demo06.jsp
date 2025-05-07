<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/8
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    int result=0;
    String q1 = request.getParameter("a1");

    String[] q2 = request.getParameterValues("q2");
    String q3 = request.getParameter("q3");

    if(q1!=null) result+=100;
    if(q2!=null&&q2.length==4) result+=100;
    if("夏".equals(q3)) result+=100;
    out.print("已交卷！您的分数为！"+result+"分（300分为满分）");
%>
</body>
</html>
