<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/5/19
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>音乐页面</title>
</head>
<body>
<h1>登入成功</h1>
<form action="${pageContext.request.contextPath}/selectAll" method="get">
    <input type="submit" value="提交">
</form>
<c:forEach var="song" items="${songs}">
    歌名：${song.songName} 歌手名：${song.singerName}<br>
</c:forEach>
</body>
</html>
