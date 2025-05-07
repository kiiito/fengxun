<%--
  Created by IntelliJ IDEA.
  User: 风寻
  Date: 2025/3/18
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>title</title>
    <style>
        table {
            width: 50%;

            margin: 20px auto;
        }
        th, td {
            border: 1px solid #000;
            padding: 10px;
            text-align: center;
        }

    </style>
</head>
<body>
<h2 style="text-align: center;">身份证生日提取</h2>

<%!
    public String extractBirthday(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return "无效的身份证号码";
        }
        String birthday = idCard.substring(6, 14);
        return birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6, 8);
    }
%>
<%
    String arr[] = new String[2];
    arr[0] = "123456789123456789";
    arr[1] = "123456789123456789";

%>
<%
    for (int i = 0; i < arr.length; i++) {
        String idCard = arr[i];
        String birthday = null;
        if (idCard != null && !idCard.isEmpty()) {
            birthday = extractBirthday(idCard);
        }
%>
<table>
    <tr>
        <th>身份证</th>
        <th>生日</th>
    </tr>
    <tr>
        <td><%=idCard%></td>
        <td><%=birthday%></td>
    </tr>
</table>
<%
    }
%>
</body>
</html>
