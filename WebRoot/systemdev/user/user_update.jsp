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
           var dialog = frameElement.dialog;
           var dialogData = dialog.get('data');//获取data参数

           $("#user_updateWindow_form").ligerForm().setData(dialogData.content);

           $dialog = top.$("iframe[src='systemdev/user/user_update.jsp']")[0].contentWindow;//用于关闭dialog    
           var dept_combox = $("#user_department_id").ligerComboBox({
               width : 300,
               height:30,
               selectBoxWidth: 300,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: false,
               tree: { checkbox: false, url: 'wkrjsystem/wkrjDept/getDeptTree', ajaxType: 'post', idField: 'id' }
           });
           if (dialogData.content.dept_id == null || dialogData.content.dept_id == "") {
               //role_combox.selectValue('');
           } else {
               dept_combox.selectValue(dialogData.content.dept_id);
           }
           //dept_combox.selectValue(dialogData.content.dept_id);
           var station_combox = $("#station_id").ligerComboBox({
        	   width : 300,
               height:30,
               selectBoxWidth: 300,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: false,
               tree: { checkbox: false, url: 'wkrjsystemdev/wkrjUserDev/getStationTree', ajaxType: 'post', idField: 'id' }
           });
           if (dialogData.content.station_id == null || dialogData.content.station_id == "") {
               //role_combox.selectValue('');
           } else {
               station_combox.selectValue(dialogData.content.station_id);
           }
           //station_combox.selectValue(dialogData.content.station_id);
           var role_combox = $("#userrolercombobox").ligerComboBox({
        	   width : 300,
               height:30,
               selectBoxWidth: 300,
               selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: true,
               tree: { checkbox: true, url: 'wkrjsystemdev/wkrjUserDev/getRoleTree', ajaxType: 'post', idField: 'id' }
           });
           //role_combox.setText(showRoleName(dialogData.content.user_role).split(","));
           var role = showRoleName(dialogData.content.user_role).split(",");
           if (role == null || role == "") {
               //role_combox.selectValue('');
           } else {
               role_combox.selectValue(role);
           }
           //role_combox.selectValue(role);
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

 
<!--添加菜单开始-->
<form id="user_updateWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		    <div class="formdiv" style="display: none;">
                <label style="text-align:right;">用户id:</label>
                <input name="user_id"
                    id="user_ids" class="ui-textbox">
            </div>
            <div class="formdiv">
                <label style="text-align:right;">账号名称:</label>
                <input id="sjc_user_userName"
                    name="user_name" class="ui-textbox" data-options="required:true">
            </div>
            <div class="formdiv" id="sjc_user_password">
                <label style="text-align:right;">账户密码:</label>
                <input name="user_password"
                    type="password" class="ui-textbox" data-options="required:true">
            </div>
            <div class="formdiv">
                <label style="text-align:right;">用户角色:</label>
                <input id="userrolercombobox"
                    name="user_role[0].role_id" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">真实姓名:</label>
                <input data-options="required:true"
                    name="user_realname" class="ui-textbox">
            </div>
            <div class="formdiv">
                <label style="text-align:right;">用户部门:</label>
                <input id="user_department_id" 
                    name="dept_id" data-options="method:'get',panelHeight:'200',required:true" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">手机号:</label>
                <input onblur="checkTel()" id="sjc_userview_tel"
                    name="user_tel" class="ui-textbox" />
            </div>
            <div class="formdiv">
                <label style="text-align:right;">身份证:</label>
                <input data-options="required:true" onblur="checkSfz()" id="sjc_userview_sfz"
                    name="user_card" class="ui-textbox" />
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
		
		<input type="hidden" style="display:none;" name="user_id"  />
	</div>
</form>