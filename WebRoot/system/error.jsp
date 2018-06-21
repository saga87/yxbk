<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=100%, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>失败</title>
    <link rel="stylesheet" type="text/css" href="system/css/style.css">
</head>
<%-- <body>
    <div class="headerBar">
        <div class="header W640">
            <%@include file="top.jsp"%>
        </div>
    </div>
    <div class="Heit41"></div>
    <div class="W640">
        <div class="successPic"><img src="images/mobile/SuccessPic.png" alt=""></div>
    </div>
</body> --%>
<body class="f7">
    <div class="W640 Tsuccess">
        <div class="PageTit W640">上报巡查情况</div>
        <span><img src="system/imgs/error.png" alt=""></span>
        <p class="ErrorBank"><a href="javascript:void(0)" onclick="history.go(-1)">返回</a></p>
    </div>
</body>
</html>
