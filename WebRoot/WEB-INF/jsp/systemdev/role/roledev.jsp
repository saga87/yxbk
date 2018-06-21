<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>

<script type="text/javascript" src="systemdev/js/role/role.js"></script>
<script type="text/javascript">
 
</script>
<div class="l-loading" style="display:block" id="pageloading"></div>
<div id="role_layout" style="width:100%;">	
	<div position="center">
		<div id="role_maingrid"></div>
	</div>
	<div position="right" >
		<!-- <div id="permissiongrid"></div> -->
		
		 <div style="width:100%; height:100%; margin-bottom:10px; overflow:auto;  ">
            <div class="l-panel-topbar" style="height: 28px;">
            	<div class="l-panel-topbarinner  l-panel-topbarinner-left" ligeruiid="ToolBar1007">
	            	<!-- <div class="l-toolbar-item l-panel-btn l-toolbar-item-hasicon" toolbarid="item-1">
	            	<span onclick="selecAll()">全选</span><div class="l-panel-btn-l"></div>
	            	<div class="l-panel-btn-r"></div><div class="l-icon l-icon-add">
	            	</div></div> --><div class="l-bar-separator"></div>
	            	<div class="l-toolbar-item l-panel-btn l-toolbar-item-hasicon" toolbarid="item-2">
	            	<span onclick="setMenuPermission()">保存设置</span><div class="l-panel-btn-l"></div>
	            	<div class="l-panel-btn-r"></div><div class="l-icon l-icon-modify"></div></div>
         		</div>
        </div>
            <ul id="permissiongrid"></ul> 
    </div> 
		
	</div>
</div>


