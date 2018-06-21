<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link href="system/js/uploadfy/uploadify.css"
     rel="stylesheet" type="text/css" />
<script type="text/javascript"
   src="system/js/uploadfy/jquery.uploadify.min.js"></script>
<script type="text/javascript"
   src="system/js/uploadfy/jquery.uploadify.js"></script>
<script type="text/javascript" src="page/menu/js/menu.js"></script>
<script type="text/javascript">
	$(function(){
		
		
		
	});
</script>
 <style>
   	/* .l-text{width:90% !important;}
   	.l-text input.ui-textbox{width:90% !important;} */
 </style>
 </head>
 <body>
<div class="l-loading" style="display:block" id="pageloading"></div>
<div id="user_layout" style="width:100%;">	
	
	<div position="left" leftWidth="150" >
        <ul id="usermenu"></ul> 
    </div> 
    
    <div position="center">
		
		<form id="menuForm" method="post" class="liger-form">
			<fieldset style="border:1px solid #ccc">
				<legend>输入区（左边托管菜单项的内容）:</legend>
				<table style="width:90%;line-height:30px;">
					<tr style="display:none">
						<td width="13%">菜单ID:</td>
						<td width="77%"><input type="text" 
							id="menu_id" name="menu[0].menu_id" style="width:100%;" /></td>
					</tr>
					<tr>
						<td width="13%">上级菜单:</td>
						<td width="77%"><input 
							name="menu_parent_id" id="menu_parent_id"
							 /></td>
					</tr>
					<tr>
						<td width="13%">菜单名称:</td>
						<td width="77%"><input  type="text"
							id="menu_name" name="menu[0].menu_name"
							style="width:300px;"/></td>
					</tr>
					<tr>
						<td width="13%">菜单顺序:</td>
						<td width="77%"><input type="text"
							id="menu_order" name="menu[0].menu_order" ltype='spinner' ligerui="{type:'int'}"
							 /></td>
					</tr>
					<tr>
						<td width="13%">是否隐藏:</td>
						<td width="77%">
							<div id="menu_radio"></div>
							<!-- <input type="radio"  name="menu[0].menu_is_display" value="0" />否
							<input type="radio"  name="menu[0].menu_is_display" value="1" />是</td> -->
					</tr>
					<tr>
						<td width="13%">菜单备注:</td>
						<td width="77%"><input type="text"
							id="menu_other" name="menu[0].menu_other" 
							style="width:300px;" /></td>
					</tr>
					<tr>
						<td width="13%">菜单图标:</td>
						<td width="77%">
						<img style="display:none;"  width="16px" height="16px" src="" id="imgmenu_icon">
						<input  type="text"  id="menu_icon" name="menu[0].menu_icon" style="width:100%;display:none"/>
						
						</td>
					</tr>
					<tr>
						<td width="13%"></td>
						<td width="77%">
						<input type="file" name="module_icon_file" id="menu_uploadify" />	
						</td>
					</tr>
					
				</table>
			</fieldset>
			<fieldset style="border:1px solid #ccc">
				<legend>显示区（通过右边模块列表树选择）:</legend>
				<table style="width:100%;line-height:30px;">
					<tr style="display:none">
						<td width="13%" style="padding:0px;margin:0px;">模块ID:</td>
						<td width="77%" style="padding:0px;margin:0px;"><input readonly="readonly"
							type="text" id="module_id" 
							name="module_id" style="width:80%;" /></td>
					</tr>
					<tr>
						<td width="13%" style="padding:0px;margin:0px;">模块名称:</td>
						<td width="77%" style="padding:0px;margin:0px;"><input readonly="readonly"
							 type="text" id="module_name"
							name="module_name" style="width:80%;" /></td>
					</tr>
					<tr>
						<td width="13%" style="padding:0px;margin:0px;">模块顺序:</td>
						<td width="77%" style="padding:0px;margin:0px;"><input readonly="readonly"
							 type="text" id="module_order"
							name="module_order" style="width:80%;" /></td>
					</tr>
					<tr>
						<td width="13%" style="padding:0px;margin:0px;">模块视图:</td>
						<td width="77%" style="padding:0px;margin:0px;"><input readonly="readonly"
							type="text" id="module_views"
							name="module_view" style="width:80%;"></input></td>
					</tr>
					<tr>
						<td width="13%" style="padding:0px;margin:0px;">模块图标:</td>
						<td width="77%" style="padding:0px;margin:0px;">
							<img id="module_icon" name="module_icon"/>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
		
		<div id="menu-buttons" style="text-align:center;">
			<div style="display:inline-block" ekper="wkrjsystem/wkrjMenu/updateMenu" id="addbtn">添加</div>
			<div style="display:inline-block" ekper="wkrjsystem/wkrjMenu/updateMenu" id="updatebtn">修改</div>
			<div style="display:inline-block" ekper="wkrjsystem/wkrjMenu/updateMenu" id="delbtn">删除</div>
			<!-- <a
				href="javascript:void(0)" class="easyui-linkbutton"
				ekper="wkrjsystem/wkrjMenu/updateMenu"
				iconCls="icon-edit" onclick="editMenu()">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				ekper="wkrjsystem/wkrjMenu/delMenu"
				iconCls="icon-remove" onclick="delMenu()">删除</a> -->
		</div>
		
		
	</div>
	
	<div position="right" >
        <ul id="systemmenu"></ul> 
    </div> 
		
	</div>
</div>
</body>
</html>
