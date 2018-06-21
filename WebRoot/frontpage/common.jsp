<%@page import="wkrjsystem.user.bean.WkrjUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    WkrjUser user = (WkrjUser)request.getSession().getAttribute("user");
    String role_id = (String)request.getSession().getAttribute("userRoleId");
    String session_user_deptid = user.getDept_id();
    String session_user_name = user.getUser_realname();
    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <base href="<%=basePath%>">
<script type="text/javascript"> var basePath = "<%=basePath%>";</script>
<script type="text/javascript"> var session_user_name = "<%=session_user_name%>";</script>
<script type="text/javascript"> var session_user_roler = "<%=session.getAttribute("userRoleId")%>";</script>
<script src="plug-in/ligerui/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>  
<script src="plug-in/ligerui/jquery.cookie.js"></script>
<!-- 通知公告滚动使用的插件 -->
 <link rel="stylesheet" href="frontpage/css/liMarquee.css">
 <script src="frontpage/js/jquery.liMarquee.js"></script>
<!-- kxbdSuperMarquee不使用了 -->
<script src="frontpage/js/kxbdSuperMarquee.js"></script>
<!-- LayUI JS文件 -->
<script src="plug-in/layui/layui.js" type="text/javascript"></script>
<!-- LayUI CSS文件 -->
<link href="frontpage/layui/css/layui.css" rel="stylesheet" type="text/css" />  
  <link rel="stylesheet" href="frontpage/font-awesome-4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="frontpage/css/all.css">
  <link rel="stylesheet" href="frontpage/css/css.css">
  <link rel="stylesheet" href="frontpage/css/index.css">
<script type="text/javascript">
	var localIp = "172.19.35.41";
</script>  
  </head>
  

</html>
