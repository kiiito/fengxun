<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/15
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单信息</title>
    <style>
        table {
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
        th {
            background-color: gainsboro;
        }
    </style>
</head>
<body>
<h2>您的订单信息如下</h2>
<%
    // 定义书本信息
    String[] bookNames = {"老人与海", "牺牲"};
    double[] bookPrices = {45.2, 25.5};

    // 获取用户提交的数据
    String[] checks = request.getParameterValues("check"); // 获取选中的图书索引（"0"或"1"）
    String[] quantities = request.getParameterValues("quantity"); // 获取所有数量

    // 存储选中的图书到 Session
    if (checks != null) {
        java.util.List<java.util.Map<String, Object>> orderItems = new java.util.ArrayList<>();

        for (String check : checks) {
            int bookIndex = Integer.parseInt(check); // 转成整数索引
            int qty = Integer.parseInt(quantities[bookIndex]); // 获取对应数量

            java.util.Map<String, Object> item = new java.util.HashMap<>();
            item.put("name", bookNames[bookIndex]);
            item.put("price", bookPrices[bookIndex]);
            item.put("quantity", qty);

            orderItems.add(item);
        }
        session.setAttribute("orderItems", orderItems);
    } else {
        session.removeAttribute("orderItems");
    }

    // 4. 从 Session 读取并显示订单
    java.util.List<java.util.Map<String, Object>> orderItems =
            (java.util.List<java.util.Map<String, Object>>) session.getAttribute("orderItems");

    if (orderItems != null && !orderItems.isEmpty()) {
        double addNumber = 0.0;
%>
<table>
    <tr>
        <th>书籍名称</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
    </tr>
    <%
        for (java.util.Map<String, Object> item : orderItems) {
            String name = (String) item.get("name");
            double price = (Double) item.get("price");
            int qty = (Integer) item.get("quantity");
            double subtotal = price * qty;
            addNumber += subtotal;
    %>
    <tr>
        <td><%= name %></td>
        <td>¥<%= price %></td>
        <td><%= qty %></td>
        <td>¥<%= String.format("%.2f", subtotal) %></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="3" style="text-align: right;"><strong>总计：</strong></td>
        <td><strong>¥<%= String.format("%.2f", addNumber) %></strong></td>
    </tr>
</table>
<%
} else {
%>
<p>您没有选择任何图书！</p>
<%
    }
%>
</body>
</html>


