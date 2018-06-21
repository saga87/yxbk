<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
 <!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
<script type="text/javascript"
   src="system/js/user/user.js"></script>
<script type="text/javascript">
       $(function (){
           $("#user_department_id").ligerComboBox({
              width:300,
              height:30,
              selectBoxWidth:300,
              selectBoxHeight:200, valueField: 'id', treeLeafOnly: false, isMultiSelect: false,
              tree: { checkbox: false, url: 'wkrjsystem/wkrjDept/getDeptTree', ajaxType: 'post', idField: 'id', idFieldName: 'text', nodeWidth: 150 },
              onSelected: function (newvalue){
            	
              }
           });
           $("#userrolercombobox").ligerComboBox({
              width : 300, 
              height:30,
              selectBoxWidth: 300,
              selectBoxHeight: 300,  treeLeafOnly: false, isMultiSelect: true, isShowCheckBox: true,
              tree: { checkbox: true, url: 'wkrjsystem/wkrjUser/getRoleTree', ajaxType: 'post', idField: 'id', idFieldName: 'role_id', nodeWidth: 180 }
           	});
           
           $("#userIsEnabled").ligerComboBox({
        	  width:300,
        	  height:30
           });
       });
</script>
 </head>
 <body>
<!--添加用户开始-->
<form id="user_addWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		    <div class="formdiv" style="display: none;">
                <label style="text-align:right;">用户id:</label>
                <input name="user_id" id="user_ids" class="ui-textbox">
            </div>
            <div class="formdiv">
                <label style="text-align:right;">账号:</label>
                <input id="sjc_user_userName"
                    name="user_name" data-options="required:true">
            </div>
            <div class="formdiv" id="sjc_user_password" >
                <label style="text-align:right;">账号密码:</label>
                <input name="user_password" type="password"
                	data-options="required:true" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">用户角色:</label>
                <div class="r_div">
                	<input id="userrolercombobox"  type="text"
                    	name="user_role[0].role_id">
                </div>
            </div>
            <div class="formdiv">
                <label style="text-align:right;">姓名:</label>
                <input data-options="required:true" name="user_realname" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">所属部门:</label>
                <div class="r_div">
               		<input id="user_department_id" name="dept_id" />
               	</div>
            </div>
            <div class="formdiv">
                <label style="text-align:right;">手机号:</label>
                <input onblur="checkTel()" id="sjc_userview_tel"
                    name="user_tel" />
            </div>
            <div class="formdiv" >
                <label style="text-align:right;">是否禁用:</label>
                <div class="r_div">
	                <select id="userIsEnabled" name="user_is_enable" data-options="panelHeight:true">  
                        <option value="0" selected="selected">否</option>  
                        <option value="1">是</option>  
	                </select>
                </div>
            </div>
</form>
 </body>
 </html>