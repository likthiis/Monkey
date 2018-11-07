<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2018/10/30
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>登录</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <style type="text/css">

       .lebt{
            position: relative;
            left: 100px;
            bottom: 30px;
            border: 1px;
            width: 500px;

        }
       .s{
            position: relative;
            bottom: 0px;
            left:100px;
            width:550px;
        }
       .r{
            position: relative;
            left:550px;
            bottom: 0px;
            width:50px;
        }


    </style>
</head>
<body>
<div class="r">
    <a href="/register">
        <button type="button" class="btn btn-default">注册</button>
    </a>
</div>
<div class="lebt">
<form action="/login" method="post" >
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" name="username">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
    <p><c:out value="${result}" /></p>
</form>
</div>

</body>
</html>

