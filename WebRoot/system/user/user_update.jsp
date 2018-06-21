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
           var dialog = frameElement.dialog;
           var dialogData = dialog.get('data');//获取data参数

           $("#user_updateWindow_form").ligerForm().setData(dialogData.content);

           $dialog = top.$("iframe[src='system/user/user_update.jsp']")[0].contentWindow;//用于关闭dialog    
           var dept_combox = $("#user_department_id").ligerComboBox({
               width : 300,
               height:30,
               selectBoxWidth: 300,
               selectBoxHeight: 200, valueField: 'id', treeLeafOnly: false, isMultiSelect: false,
               tree: { checkbox: false, url: 'wkrjsystem/wkrjDept/getDeptTree', ajaxType: 'post', idField: 'id', nodeWidth: 150 },
               onSelected: function (newvalue)
                {
                   
                    
                }
           });
           if (dialogData.content.dept_id == null || dialogData.content.dept_id == "") {
           } else {
               dept_combox.selectValue(dialogData.content.dept_id);
           }

           if (dialogData.content.station_id == null || dialogData.content.station_id == "") {
           } else {
               station_combox.selectValue(dialogData.content.station_id);
           }
           
           var role_combox = $("#userrolercombobox").ligerComboBox({
        	   width : 300,
               height:30,
               selectBoxWidth: 300,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: true,
               tree: { checkbox: true, url: 'wkrjsystem/wkrjUser/getRoleTree', ajaxType: 'post', idField: 'id', nodeWidth: 180 }
           });
           var role = showRoleName(dialogData.content.user_role).split(",");
           if (role == null || role == "") {
           } else {
               role_combox.selectValue(role);
           }
           

           $("#userIsEnabled").ligerComboBox({
        	  width:300,
        	  height:30
           });
           
       });
        function showRoleName(role){
		    var roleName="";
		    if(role.length>0){
		        
		        for(var i=0;i<role.length;i++){
		            roleName += role[i].role_id;
		            
		            if(i<role.length-1){
		                roleName +=";";
		            }
		        }
		    }
		    return roleName;
		}
</script>
 </head>
 <body>
<!--添加菜单开始-->
<form id="user_updateWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		    <div class="formdiv" style="display: none;">
                <label style="text-align:right;">用户id:</label>
                <input name="user_id"
                    id="user_ids" class="ui-textbox" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">账号:</label>
                <input id="sjc_user_userName"
                    name="user_name" data-options="required:true" />
            </div>
            <div class="formdiv" id="sjc_user_password" style="display: none;">
                <label style="text-align:right;">账号密码:</label>
                <input name="user_password" 
                    type="password" class="ui-textbox" data-options="required:true" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">角色:</label>
                <input id="userrolercombobox"
                    name="user_role[0].role_id" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">姓名:</label>
                <input data-options="required:true"
                    name="user_realname" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">所属部门:</label>
                <input id="user_department_id" 
                    name="dept_id" data-options="method:'get',panelHeight:'200',required:true" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">手机号:</label>
                <input onblur="checkTel()" id="sjc_userview_tel" name="user_tel" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">是否禁用:</label> 
                <select id="userIsEnabled" name="user_is_enable" data-options="panelHeight:50">  
                        <option value="0" selected="selected">否</option>  
                        <option value="1">是</option>  
                </select> 
            </div>
		
		<input type="hidden" style="display:none;" name="user_id"  />
	</div>
</form>
 </body>
 </html>