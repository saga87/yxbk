<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'MyJsp.jsp' starting page</title>

<script type="text/javascript" src="plug-in/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="plug-in/login/login.js"></script>
</head>

<body>
	<h1>登录页面----<div id="messageDiv"></div></h1>

	<form action=""  method="post">  
        用户名：<input type="text" name="username" />
		<br />  
        密 　码：<input type="password" name="password" />
		<br />
		<input type="button" id="login" onclick="checkUsr()" value="登陆">
	</form>
</body>
</html>
