<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/4/15
  Time: 22:25
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
        table{
            border-collapse: collapse;
        }
        th,td{
            border: 1px solid black;
            padding: 8px;
        }
        th {
            background-color: gainsboro;
        }
        .input{
            text-align: center;
        }
    </style>
</head>
<body>
<div id="box">
    <form action="demo14.jsp" method="post">
        <table>
            <tr>
                <th>书籍名称</th>
                <th>书籍价格</th>
                <th>书籍数量</th>
                <th>是否添加至购物单</th>
            </tr>
            <tr class="oneBook">
                <td>
                    <label for="html5">老人与海</label>
                </td>
                <td>¥45.2</td>
                <td>
                    <label for="html5-qty">数量</label>
                    <input type="number" value="1" min="1" name="quantity" id="HTML5-qty" class="quantity">
                </td>
                <td class="input">
                    <label for="html5-check">添加</label>
                    <input type="checkbox" name="check" id="HTML5-check" value="0">
                </td>
            </tr>
            <tr class="twoBook">
                <td>
                    <label for="css3">牺牲</label>
                </td>
                <td>¥25.5</td>
                <td>
                    <label for="css3-qty">数量</label>
                    <input type="number" value="1" min="1" name="quantity" id="CSS3-qty" class="quantity">
                </td>
                <td class="input">
                    <label for="css3-check">添加</label>
                    <input type="checkbox" name="check" id="CSS3-check" value="1">
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="提交订单">
    </form>
</div>
</body>
</html>

