<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/system/myContext.jsp" %>

<div style="width:100%;height:100%;">
	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'west',split:true" title="组织机构"
			style="width:200px;">
			
			<!--<div id="setMenuPermission"-->
			<div style="background:#eee;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-reload" plain="true" onclick="showAllUser()">显示全部用户</a>
			</div>
			
			<ul id="userDeptTree" class="easyui-tree"
				data-options="border:false"
				url="">
			</ul>
		</div>
		<div id="usermanagetoolbar"
			style="height:60px;width:100%;">
			<a style="float:left;" href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-add" plain="true"
				ekper="wkrjsystem/wkrjUser/addUser"
				onclick="addUser()">添加</a> <a style="float:left;"
				href="javascript:void(0)" class="easyui-linkbutton"
				ekper="wkrjsystem/wkrjUser/updateUser"
				iconCls="icon-edit" plain="true" onclick="editUser()">修改</a> <a
				style="float:left;" href="javascript:void(0)"
				ekper="wkrjsystem/wkrjUser/delUser"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="delUser()">删除</a> <a style="float:left;"
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="showAllUser()">显示全部</a> <a
				style="float:left;" href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-no" plain="true"
				ekper="wkrjsystem/wkrjUser/forbiddenUser"
				onclick="forbiddenUser()">禁用</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" style="float:left;"
				ekper="wkrjsystem/wkrjUser/forbiddenUser"
					iconCls="icon-ok" plain="true" onclick="startuser()">启用</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" style="float:left;"
					ekper="wkrjsystem/wkrjUser/repeatPw"
					iconCls="icon-ok" plain="true" onclick="repeatPw()">重置密码</a><br/><br/>
			
			<div style="">
				部门:<input style="width: 185px;" 
					class="easyui-combotree" name="parent_department_id"
					data-options="method:'get'" id="searchUserDept"
					url="wkrjsystem/wkrjDept/getDeptTree">
			
			        用户名:<input class="easyui-validatebox" type="text" id="searchUserRealname"
				style="width: 190px;margin-top:4px;margin-left:3px;" />
			
			<a  href="javascript:void(0)"
				ekper="wkrjsystem/wkrjUser/getUserList"
				class="easyui-linkbutton" iconCls="icon-search" plain="true"
				onclick="searchUser()">查询</a>
			</div>
		</div>
		<div data-options="region:'center'">
			<table class="easyui-datagrid" id="userdatagrid"
				data-options="url:'wkrjsystem/wkrjUser/getUserList',method:'get',title:'用户列表',toolbar:'#usermanagetoolbar',border:false,singleSelect:false,fit:true,fitColumns:false,pagination:true,pageSize:20">
				<thead>

					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'user_id',align:'center'" hidden="hidden" width="80">用户
							user_card</th>
						<!-- <th data-options="field:'station_id',align:'center'" width="100">岗位</th> -->
						<th data-options="field:'user_role',align:'center'" width="100" formatter="showRoleName">角色</th>
						<th data-options="field:'user_name',align:'center'" width="100">用户名</th>
						<th data-options="field:'dept_id',align:'center'" formatter="getBmName" width="100">部门</th>
						<th data-options="field:'station_id',align:'center'" formatter="getStationName" width="100">岗位</th>
						<th data-options="field:'user_realname',align:'center'" width="100">真实姓名</th>
						<th data-options="field:'user_card',align:'center'" width="170">身份证号码</th>
						<th data-options="field:'user_tel',align:'center'" width="100">电话</th>
						<th data-options="field:'user_is_enable',align:'center'"
							width="100" formatter="user_is_enable">是否禁用</th>
						<th data-options="field:'user_inputtime',align:'center'" width="170">注册时间</th>
						<th data-options="field:'user_last_time',align:'center'"
							width="110">上次登录时间</th> 
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- 添加用户窗口开始 -->
	<div id="userWindow" class="easyui-dialog" modal="true"
		style="width: 450px; height: 460px; padding: 10px 20px" closed="true"
		buttons="#user-buttons">
		<form id="userForm" method="post" novalidate>
		
			<div class="formdiv" style="display: none;">
				<label style="text-align:right;">用户id:</label> <input name="user_id"
					id="user_ids" class="easyui-validatebox">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">账号名称:</label> <input id="zxh_user_userName"
					name="user_name" style="width: 200px;" class="easyui-validatebox" data-options="required:true">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">　　工号:</label> <input name="user_no" style="width: 200px;" 
				class="easyui-validatebox" data-options="required:true">
			</div>
			<div class="formdiv" id="zxh_user_password">
				<label style="text-align:right;">账户密码:</label> <input name="user_password"
					type="password" style="width: 200px;" class="easyui-validatebox" data-options="required:true">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">用户角色:</label> <input
					style="width: 200px;" id="userrolercombobox"
					class="easyui-combobox" name="user_role[0].role_id"
					data-options="method:'get',valueField : 'role_id',textField:'role_name',panelHeight:200,editable:false"
					style="width: 200px;">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">真实姓名:</label> <input data-options="required:true"
					name="user_realname" style="width: 200px;" class="easyui-validatebox">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">用户部门:</label> <input 
					style="width: 200px;" id="user_department_id" class="easyui-combotree"
					name="dept_id" data-options="method:'get',panelHeight:'200',required:true"
					style="width: 200px;" >
			</div>
			<div class="formdiv">
				<label style="text-align:right;">　手机号:</label> <input onblur="checkTel()" id="zxh_userview_tel"
					name="user_tel" style="width: 200px;" class="easyui-validatebox">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">　身份证:</label> <input data-options="required:true" onblur="checkSfz()" id="zxh_userview_sfz"
					name="user_card" style="width: 200px;" class="easyui-validatebox">
			</div>
			<div class="formdiv">
				<label style="text-align:right;">用户岗位:</label> <input
					style="width: 200px;" id="station_id" class="easyui-combobox"
					name="station_id" data-options="panelHeight:'auto',method:'get',valueField : 'station_id',textField:'station_name'"
					style="width: 200px;" >
			</div> 
			<div class="formdiv">
				<label style="text-align:right;">是否禁用:</label> 
				<select  class="easyui-combobox" id="userIsEnabled" name="user_is_enable" style="width:200px;" data-options="panelHeight:50">  
					    <option value="0" selected="selected">否</option>  
					    <option value="1">是</option>  
					  
				</select> 
			</div>
			
		</form>
	</div>
	<div id="user-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#userWindow').dialog('close')">关闭</a>
	</div>
	<!-- 添加用户窗口结束 -->
</div>

<script type="text/javascript" src="system/js/user/user.js"></script>