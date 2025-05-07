<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/5/6
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <style>
    body
    {
      font-family: Arial, sans-serif; max-width: 500px; margin: 50px auto;
    } label { display: inline-block; width: 100px; }
    input, select { padding: 5px; width: 200px; }
  </style>
</head>
<body>

<div id="box">
  <form action="<%=request.getContextPath()%>/login" method="post">
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">密码:</label>
    <input type="password" id="password" name="password" required><br><br>
    <label for="userType">用户类型:</label>
    <select id="userType" name="userType">
      <option value="student">学生</option>
      <option value="teacher">教师</option>
      <option value="staff">教学工作人员</option>
    </select><br><br>
    <input type="submit" value="提交">
  </form>
</div>
</body>
</html>

