<%--
  Created by IntelliJ IDEA.
  User: ��Ѱ
  Date: 2025/4/30
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%--在这个符号里面写的 都是写在service方法里面的 在这里面不能嵌套方法 不能写静态 不能写私有变量--%>
<%
    int a = 1;
    out.write(a);
%>
<%--  在这个符号里面 都是写在类中(service方法之外) 但不建议使用 因为会产生进程安全问题(单例模式)--%>
<%!
    private int b = 1;
%>

<%--  这个符号 本质是out.print(); 用于定义的变量输出 也是写在service方法里面的--%>
<%=
100 + 200
%>

<a href="<%=request.getContextPath()%>/cookie/generate">cookie</a>
</body>
</html>
