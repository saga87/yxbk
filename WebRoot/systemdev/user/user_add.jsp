<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!-- <link href="systemdev/js/uploadfy/uploadify.css"
     rel="stylesheet" type="text/css" />
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.min.js"></script>
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.js"></script> -->
    <script type="text/javascript"
   src="systemdev/js/user/user.js"></script>
<script type="text/javascript">
       $(function (){
           $("#user_department_id").ligerComboBox({
               width : 300,
               height:30,
               selectBoxWidth: 200,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: false,
               tree: { checkbox: false, url: 'wkrjsystem/wkrjDept/getDeptTree', ajaxType: 'post', idField: 'id', idFieldName: 'text' }
           });
           $("#station_id").ligerComboBox({
           //url: 'wkrjsystem/wkrjStation/getAllStationList',
               width : 300,
               height:30,
               selectBoxWidth: 200,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: false,
               tree: { checkbox: false, url: 'wkrjsystemdev/wkrjUserDev/getStationTree', ajaxType: 'post', idField: 'id', idFieldName: 'text' }
           });
           $("#userrolercombobox").ligerComboBox({
        	   width : 300,
               height:30,
               selectBoxWidth: 200,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: true, isShowCheckBox: true,
               tree: { checkbox: true, url: 'wkrjsystemdev/wkrjUserDev/getRoleTree', ajaxType: 'post', idField: 'id', idFieldName: 'text' }
           });
           $("#userIsEnabled").ligerComboBox({
        	   width:300,
        	   height:30
           });
       });
</script>


<!--添加用户开始-->
<form id="user_addWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		    <div class="formdiv" style="display: none;">
                <label style="text-align:right;">用户id:</label>
                <input name="user_id"
                    id="user_ids" class="ui-textbox"/>
            </div>
            <div class="formdiv">
                <label style="text-align:right;">账号名称:</label>
                <input id="sjc_user_userName"
                    name="user_name" class="ui-textbox" data-options="required:true"/>
            </div>
            <!-- <div class="formdiv">
                <label style="text-align:right;">　　工号:</label> <input name="user_no" style="width: 200px;" 
                class="ui-textbox" data-options="required:true">
            </div> -->
            <div class="formdiv" id="sjc_user_password">
                <label style="text-align:right;">账户密码:</label>
                <input name="user_password"
                    type="password" class="ui-textbox" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">用户角色:</label>
                <input id="userrolercombobox"
                    name="user_role[0].role_id"/>
            </div>
            <div class="formdiv">
                <label style="text-align:right;">真实姓名:</label>
                <input data-options="required:true"
                    name="user_realname" class="ui-textbox"/>
            </div>
            <div class="formdiv">
                <label style="text-align:right;">用户部门:</label>
                <input id="user_department_id" 
                    name="dept_id"  />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">手机号:</label>
                <input onblur="checkTel()" id="sjc_userview_tel"
                    name="user_tel" class="ui-textbox">
            </div>
            <div class="formdiv">
                <label style="text-align:right;">身份证:</label>
                <input data-options="required:true" onblur="checkSfz()" id="sjc_userview_sfz"
                    name="user_card" class="ui-textbox">
            </div>
            <div class="formdiv">
                <label style="text-align:right;">用户岗位:</label>
                <input id="station_id" name="station_id" />
            </div> 
            <div class="formdiv">
                <label style="text-align:right;">是否禁用:</label> 
                <select id="userIsEnabled" name="user_is_enable" data-options="panelHeight:50">  
                        <option value="0" selected="selected">否</option>  
                        <option value="1">是</option>  
                </select> 
            </div>
</form>