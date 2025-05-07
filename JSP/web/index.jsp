<%--
  Created by IntelliJ IDEA.
  User: ��Ѱ
  Date: 2025/2/18
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <style>
        img{
          float: left;
        }
        .box{
          margin: 0 auto;
          height: 800px;
          width: 1000px;
        }
        .one{
          margin-top: 20px;
        }
        .one,.two{
          width: 400px;
          float: right;
          border-bottom: 1px  black solid;
        }
        span{
          font-size: 14px;
          color: gray;
        }
        .two,.three{
          margin-top: 50px;
        }
        .four{
          margin-top: 20px;
        }
        .three,.four{
          width: 400px;
          float: right;
          color:gray;
        }
        button{
          height: 50px;
          width: 400px;
          border: 0;
          background-color: #005CBF;
          color: white;
        }
        .four{
          text-align: center;
        }
        .five{
          width:500px;
          height:50px;
          color: gray;
          font-size: 14px;
          position: relative;
          top: 20px;
          left: 200px;
        }
      </style>
    </head>
  <body>
  <h1 align="center">Working Sign in</h1>
  <div class="box">
    <img src="jsp/1.png">
    <div class="one">
      <b>Name</b><br><br>
      <span>请输入用户名</span><br><br>
    </div>

    <div class="two">
      <b>Password</b><br><br>
      <span>请输入密码</span><br><br>
    </div>

    <div class="three">
      <input type="checkbox">Remeber Me
      <br><br>
      <button>Sign in</button>
      <br>
      <div class="four">没有密码？<span style="color: blue;">注册</span></div>
    </div>
    <div class="five">@2023 Working Sign in . All rights resered | Design By LiMengZhen</div>
  </div>
  </body>
</html>
