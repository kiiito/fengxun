<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>list</title>
<%--    设置整个网页的基础路径--%>
<%--    <base href="http://localhost:8080/oa/">--%>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript">
        function del(dno) {
            if (window.confirm("亲，删了不可恢复哦！")) {
                document.location.href = "${pageContext.request.contextPath}/dept/del?deptno=" + dno
            }
        }
    </script>
</head>
<body>
<h3>欢迎${username}</h3>
<a href="${pageContext.request.contextPath}/user/exit">退出系统</a>
<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
<%--    <%--%>
<%--        List<Dept> deptList =( List<Dept>) request.getAttribute("deptList");--%>
<%--        int i = 0;--%>
<%--       for (Dept dept:deptList) {--%>

<%--    %>--%>
    <c:forEach items="${deptList}" varStatus="d" var="dept">
    <tr>
        <td>${d.count}</td>
        <td>${dept.deptno}</td>
        <td>${dept.dname}</td>
        <td>
            <a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
            <a href="${pageContext.request.contextPath}/dept/edit?deptno=${dept.deptno}">修改</a>
            <a href="${pageContext.request.contextPath}/dept/detail?deptno=${dept.deptno}">详情</a>
        </td>
    </tr>
    </c:forEach>
<%--           <%--%>
<%--       }--%>
<%--    %>--%>

</table>
<hr>
<a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>
</body>
</html>