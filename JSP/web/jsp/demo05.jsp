<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/1
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    h2{
        color:blue;
    }
</style>
<body>
<h3>我的计算器</h3>
<hr>
<form action="" method="post">
    请输入第一个数：<input type="number" name="one"><br/>
    请选择运算方式：
    <select name="ope" id="ope">
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
    </select><br/>
    请输入第二个数：<input type="number" name="two"><br/>
    <input type="submit" name="cal" value="计算">
    <input type="reset" name="reset">
</form>
<%
    if("POST".equals(request.getMethod())){
        int num1=Integer.parseInt(request.getParameter("one"));
        int num2=Integer.parseInt(request.getParameter("two"));
        double result=0;
        String ope=request.getParameter("ope");

        switch(ope){
            case "+" :
                result=num1+num2;
                out.println("<h2>计算结果："+num1+ope+num2+"="+result+"</h2>");
                break;
            case "-" :
                result=num1-num2;
                out.println("<h2>计算结果："+num1+ope+num2+"="+result+"</h2>");
                break;
            case "*" :
                result=num1*num2;
                out.println("<h2>计算结果："+num1+ope+num2+"="+result+"</h2>");
                break;
            case "/" :
                if (num2 != 0) {
                    result = num1 / num2;
                    out.println("<h2>计算结果："+num1+ope+num2+"="+result+"</h2>");
                } else {
                    out.println("<p style='color:red'>错误：除数不能为零！</p>");
                    return;
                }
        }

    }

%>
</body>
</html>
