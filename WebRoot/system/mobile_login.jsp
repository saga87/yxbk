<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
<title>潍坊校园安全管理平台</title>
<!-- <link type="text/css" rel="stylesheet" href="system/css/all.css" /> -->
<script type="text/javascript">	var base = '<%=basePath%>';</script>
<script type="text/javascript" src="system/js/jquery-1.8.3.min.js" ></script>
<!-- <script type="text/javascript" src="system/js/mobileLogin.js" ></script> -->
<!-- <link rel="stylesheet" type="text/css" href="plug-in/login/css/index.css"/> -->
    <link rel="stylesheet" type="text/css" href="system/css/style.css" />
<!--     <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script> -->
<!--     <script type="text/javascript" src="js/Login.js"></script> -->
</head>
<body>
	<div class="LoginBar">
        <div class="W640 Login">
            <img class="Login_Img" src="plug-in/login/imgs/LoginBg.jpg" alt="">
            <p class="Login_Tit W640"><img src="plug-in/login/imgs/LoginTit1.png" alt=""></p>
            <form class="W640" action="?" method="POST">
                <p class="ComInp"><input type="text" class="username" placeholder="用户名"></p>
                <p class="ComInp"><input type="password" class="pass" placeholder="密码"></p>
                <p><input type="button" class="sub" id="loginbutton" value="登录" onclick="login()"></p>
            </form>
        </div>
    </div>
    <script type="text/javascript">
    $(document).keydown(function(event) {
	    if (event.keyCode == 13) {
	        $("#loginbutton").click();
	    }
	});
        /* $(function () {
            $('form').submit(function () {
                var username=$('.username');
                var password=$('.pass');
                $(this).find('input').removeClass('cur');
                if(username.val()==''){
                    username.focus().addClass('cur');
                    return false;
                }else if(password.val()==''){
                    password.focus().addClass('cur');
                    return false;
                }else{
                    return true;
                }
            });
        }); */
        // 用户登录
		function login() {
// 		    var username = $("input[name='username']").val();
// 		    var password =  $("input[name='password']").val();
// 		    var yzm = $("input[name='yzmin']").val();
		    var username=$('.username');
            var password=$('.pass');
            $(this).find('input').removeClass('cur');
            if(username.val()==''){
                username.focus().addClass('cur');
                return false;
            }else if(password.val()==''){
                username.focus().removeClass('cur');
                password.focus().addClass('cur');
                return false;
            }else{
                // 用户提交
	            $.post('wkrjsystem/wkrjlogin/checkLogin_mobile', {
	                username : username.val(),
	                password : password.val()
	                //yzm:yzm
	            }, function(result) {
	                try{
	                    var result = eval('(' + result + ')');
	                }catch(e){
	                }
	                if(true==result.success){
	                    window.location.href = base+"wkrjsystem/wkrjlogin/login_mobile";
	                }else{
	                    alert(result.msg);
	                    //$("input[name='yzm']").val('');
	                }
	            });
            }
// 		    if (yzm == "" || yzm == "验证码") {
// 		        alert('验证码不能为空');
// 		        return;
// 		    }
		}
    </script>
</body>
</html>

