<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=10"/>
 <title>医学百科</title>
<script type="text/javascript">	var base = '<%=basePath%>';</script>
<script type="text/javascript" src="system/js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="system/js/login.js" ></script>
    <link rel="stylesheet" type="text/css" href="system/css/Login.css" />
</head>
<body>
	<div class="Dltop">
        <div class="TopLogo">
            <a href="#"><img src="system/imgs/DLogo.png" alt="网站Logo" /></a>
        </div>
    </div>
    <div class="DlBanne"><img src="system/imgs/Dback.jpg" /></div>
    <div class="Dlinput">
        <div class="inputCenter">
            <form autocorrect="off" autocapitalize="off" spellcheck="false">
                <span>用户名：</span><input type="text" class="name"
                        		id="strName" name="username"  autocomplete="off" />
                <span>密码：</span><input class="pass1" type="text" id="strPass1"  autocomplete="off"/>
                <input class="pass2" type="password" id="strPass2" name="password" value="" autocomplete="off" />
                <input class="submit" type="button" value="登&nbsp;陆" id="longinbutton"  onclick="login()"/>
                <input class="reset" type="reset" value="重&nbsp;置" />
            </form>
        </div>
    </div>
    <div class="Zlfooter">技术支持：山东潍科软件科技有限公司    服务热线：400-668-3609</div>
    <script>
    $(function(){
    	$("#strPass1").focus(function(){
    		$(this).hide();
    		$("#strPass2").show().focus();
    	});
    	$("#strPass2").blur(function(){
    		if($(this).val()==""){
    			$(this).hide();
    			$("#strPass1").show().val("密码");
    		}
    	});
    });
    
    </script>
</body>
</html>

