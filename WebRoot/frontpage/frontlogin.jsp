<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
            
		String username = "";  
        String password = ""; 
         
        Cookie[] c = request.getCookies();  
        if (c != null) {  
            for (int i = 0; i < c.length; i++) {  
                if ("username".equals(c[i].getName())) {  
                    username = c[i].getValue();  
                } else if ("password".equals(c[i].getName())) {  
                    password = c[i].getValue();  
                }  
            }  
        } else {  
            username = "";  
            password = "";
         
        }  
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=10"/>
<script type="text/javascript"> var base = '<%=basePath%>';</script>
<script type="text/javascript" src="system/js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="system/js/login.js" ></script>
	<title>医学百科</title>
	<link rel="stylesheet" href="frontpage/css/all.css" >
	<link rel="stylesheet" href="frontpage/css/index.css" >  
</head>
<body>

	<%  
        
    %>  

	<div class="box">
		<div class="box_bg">
			<div class="box_form">
				<form class="form">
					<p class="yxbk fs color">医学百科</p>
					<div class="div_form">
						<div class="div_zh">
							<input type="text" class="inp" id="username" name="username" placeholder="请输入登录账号" value="<%=username%>" /> 
							<img src="frontpage/imgs/mm.png" alt="" class="zh_img">
						</div>
						<div class="div_zh">
							<input type="password" class="inp" id="password" name="password"  placeholder="请输入登录密码" value="<%=password%>"/>
							<img src="frontpage/imgs/zh.png" alt="" class="zh_img mm_img">
						</div>
						<a href="javascript:;" class="logo" id="longinbutton"  onclick="login()">登录</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>