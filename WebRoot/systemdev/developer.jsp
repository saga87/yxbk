<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<script type="text/javascript">	var base = '<%=basePath%>';</script>
<style>
.chao_yzm{float: left;position: relative;top: -38px;left: 169px;width: 70px;height: 37px;padding-left:5px}
</style>

<link rel="stylesheet" type="text/css"
	href="systemdev/js/easyui14/themes/default/easyui.css">
<script type="text/javascript"
	src="systemdev/js/easyui14/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="systemdev/js/easyui14/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="systemdev/js/easyui14/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="systemdev/js/login/logindev.js"></script>

</head>

<body >

      <form method="POST">
          <div class="name"><div class="yhm">用户名：</div><input type="text" name="username" value="" /></div>
          <div class="name mm"><div class="yhm">密&nbsp;&nbsp;码：</div><input name="password" type="password" value="" /></div>
          <!--  <div class="name mm yzm"><div class="yhm">验证码：</div><input name="yzm" type="text" value="" class="name_t yzm_t"/><img class="chao_yzm" src="validateCodeServlet" width="45" height="24" onclick="reloadImg(this);" title="点击更换一张验证码图片"/></div> -->
          <input type="button" value="登录" id="longinbutton" onClick="login()" />
      </form>
              
   
</body>

</html>

