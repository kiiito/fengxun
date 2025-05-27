<%--
  Created by IntelliJ IDEA.
  User: ��Ѱ
  Date: 2025/5/12
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
<%--    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">--%>
    <title>bank</title>
  </head>
  <body>
 <form action="${pageContext.request.contextPath}/transfer"method="post">
   转出账户:<input type="text" name="fromAction"><br>
   转入账户:<input type="text" name="toAction"><br>
   转账金额:<input type="text" name="money"><br>
   <input type="submit" value="提交">
 </form>
  </body>
</html>
