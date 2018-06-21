<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户列表</title>
<script type="text/javascript" src="plug-in/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	
	<form action="shiro/user/addUser" method="post">
	
		用户名：<input type="text" name="name" value=${name }><br/>
		密　码：<input type="text" name="pw" value=${pw }><br/>
		角　色：<select name="role" multiple="multiple">
				<option value="1" >admin</option>
				<option value="2" >manager</option>
				<option value="3" >normal</option>
			  </select>
			  
		<!-- 权　限： 
			 查询<input type="checkbox" name="permission" value="query">
			 添加<input type="checkbox" name="permission" value="add">
			修改<input type="checkbox" name="permission" value="update">
			 删除<input type="checkbox" name="permission" value="del"><br/> -->
			  
		<input type="hidden" name="id" value="${id }">
		<input type="submit" value="保存">
	</form>
	
	<script type="text/javascript">
		
	$(function(){
		
		var role="${role}";
		
		$("select[name='role'] option").each(function(){
			
				var op = $(this).text();
				if(role.indexOf(op)>=0){
					$(this).attr("selected","selected");
				}
			}
		);
		
	});
		
	
	</script>
	
</body>
</html>
