<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=10"/>
<script type="text/javascript" src="system/js/user/user.js"></script>
<script type="text/javascript">
$(function (){

   
});
</script>
</head>
<body style="overflow:hidden;">
<a href="page/user/school.xls" id="school_a_id" target="_blank" style="display: none;"></a>
<div class="l-loading" style="display:block" id="pageloading"></div>
    <div id="user_layout" style="width:100%;">
	    <div position="center">
	        
	         <div class="l-panel-topbar" style="height: 35px;">
            	<div class="l-panel-topbarinner  l-panel-topbarinner-left" ligeruiid="ToolBar1007" style="display: block;">
	            	<div class="l-bar-separator">
	            	    姓名:
		    		<input class="" type="text" id="searchUserRealname"
	               		   style="width:160px;margin-top:4px;margin-left:3px;" />
	            	</div>
	            	<input type="button" style="width:50px;margin-top:10px;margin-left:3px;" value="查询" onclick="searchUser()">
         		</div>
       		</div>
	        
	        <div id="user_maingrid"></div>
	    </div>
        <div position="left" left="150px" >
            <ul id="user_dept_tree"></ul> 
        </div> 
    </div>
    <style>.l-text-wrapper{left:300px;top:-22px;}
    	#sea_a{position:relative;left:610px;top:-43px;z-index:3;}
    </style>
</body>
</html>