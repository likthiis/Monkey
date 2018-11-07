<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2018/10/30
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>main</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</head>
<body>

<div class="table-responsive">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>
                编号</th>
            <th>
                机器名</th>
            <th>
                IP</th>
            <th>
                状态</th>
            <th>
                详情</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${machines}" var="machine">
        <tr>
            <td>${machine.id}</td>
            <td>${machine.machineName}</td>
            <td>${machine.machineIp}</td>
            <td>
                <c:if test="${machine.machineState==1}">
                    <a href="/disconnect?id=${machine.id}"><c:out value="已连接"/></a>
                </c:if>
                <c:if test="${machine.machineState==0}">
                    <a href="/connect?id=${machine.id}"><c:out value="未连接"/></a>
                </c:if>
            <td><a href="/findMachinebyId?id=${machine.id}"><button type="button" class="btn btn-default">点击查询</button></a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
