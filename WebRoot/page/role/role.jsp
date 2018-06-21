<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=10"/>
<script type="text/javascript" src="system/js/role/role.js"></script>
<script type="text/javascript"></script>
</head>

<body style="overflow:hidden;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<div id="role_layout" style="width:100%;">	
	<div position="center">
		<div id="role_maingrid"></div>
	</div>
	<div position="right" >
		
		 <div style="width:100%;">
            <div class="l-panel-topbar" style="height: 28px;">
            	<div class="l-panel-topbarinner  l-panel-topbarinner-left" ligeruiid="ToolBar1007" style="display: block;">
	            	<div class="l-bar-separator"></div>
	            	<div class="l-toolbar-item l-panel-btn l-toolbar-item-hasicon" toolbarid="wkrjsystem/wkrjRole/setMenuPermission"  >
	            	<span onclick="setMenuPermission()">保存设置</span><div class="l-panel-btn-l"></div>
	            	<div class="l-panel-btn-r"></div><div class="l-icon l-icon-modify"></div></div>
         		</div>
       		</div>
            <ul id="permissiongrid" style="height:85%; overflow:auto;"></ul> 
    	</div> 
		
	</div>
</div>
</body>
</html>