<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">


<script type="text/javascript">
       $(function (){
    	   var dialog = frameElement.dialog;
    	   var dialogData = dialog.get('data');//获取data参数

    	   $("#role_updateWindow_form").ligerForm().setData(dialogData.content);
    	   
    	   /* var role_level =
            [{ id: 1, text: '市级' }, { id: 2, text: '县级' }, { id: 3, text: '乡镇' }, { id: 4, text: '学校' }]; 
           var roleLevel = $("#role_level").ligerComboBox({ width : 300, height : 30, selectBoxWidth : 300, selectBoxHeight : 100, 
            isMultiSelect: false, valueField: 'id' });
           roleLevel.selectValue(dialogData.content.role_level);*/
       });
</script>
 <!-- <style>
   	.l-text{width:250px !important;}
   	.l-text input.ui-textbox{width:250px !important;}
 </style> -->
 </head>
 <body>
<!--添加菜单开始-->
<form id="role_updateWindow_form" method="post" class="liger-form">
	            <div class="co" style="display: none;">
                    <label style="text-align:right;">角色id:</label>
                    <input name="role_id" id="role_ids" type="text" class="ui-textbox">
                </div>
                <div class="co">
                    <label style="text-align:right;">角色名称:</label> 
                    <input type="text"  name="role_name">
                </div>
                <!-- <div class="Co">
		            <label style="text-align:right;">角色级别:<br/></label>
		                    <div class="r_div">
		                        <input type="text" id="role_level" name="role_level" />
		                    </div>
		        </div> -->
                <!-- <div class="co">
                    <label style="text-align:right;">角色部门:</label> 
                    <input name="role_dept" id="role_dept" type="text" class="ui-combobox"
                            style="width:200px;" />
                </div> -->
                <div class="co">
                    <label style="text-align:right;">角色备注:<br/></label>
                    <div class="r_div">
                    	<textarea rows="4" cols="70" name="role_other"></textarea>
                    </div>
                </div>
	
</form>
 </body>
 </html>