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
    <style>
        div{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div id="box">
    <form action="demo06.jsp" method="post">
        <div class="one">
            1.(单选) 国庆节的日期是：<br>
            <input type="radio" name="a1">10月1日
            <input type="radio" name="b1">10月2日
            <input type="radio" name="c1">10月3日
            <input type="radio" name="d1">10月4日
        </div>
        <div class="two">
            2.(多选) 下列属于JavaWeb技术栈的是：<br>
            <input type="checkbox" name="q2">HTML
            <input type="checkbox" name="q2">CSS
            <input type="checkbox" name="q2">JAVA
            <input type="checkbox" name="q2">JSP
        </div>
        <div class="three">
            3.(填空) 一年有四季分别是：春、<input type="text" name="q3">、秋、冬
        </div>
        <input type="submit" value="提交答案">
    </form>
</div>
</body>
</html>

