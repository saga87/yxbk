<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
 </head>
 <body>

<link href="systemdev/js/uploadfy/uploadify.css"
     rel="stylesheet" type="text/css" />
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.min.js"></script>
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.js"></script>
   <script type="text/javascript"
   src="page/dept/js/dept.js"></script>
<script type="text/javascript">
       var yfilename = "";
       var xfilename = "";
       $(function (){
           $dialog = top.$("iframe[src='page/dept/safePerson_add.jsp']")[0].contentWindow;//用于关闭dialog
           var dialog = frameElement.dialog;
           var dialogData = dialog.get('data');//获取data参数
           $("#safePerson_addWindow_form").ligerForm().setData(dialogData.content);
                var deptCombobox = $("#person_dept_id").ligerComboBox({
		              width:300,
		              height:30,
		              selectBoxWidth:300,
		              selectBoxHeight:200, valueField: 'id', treeLeafOnly: false, isMultiSelect: false, readonly: false,
		              tree: { checkbox: false, url: 'wkrjsystem/wkrjDept/getDeptTree', ajaxType: 'post', idField: 'id' },
		              onSelected: function (newvalue){
		              }
		           });
		           deptCombobox.selectValue(dialogData.content);
       });
</script>

<!--添加安全人员开始-->
<form id="safePerson_addWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		<div class="Co">
			<label><span class="text_red" >*</span>&nbsp;&nbsp;安全人员:</label><input type="text" name="safe_person_name"/>
		</div>
		<div class="Co">
			<label><span class="text_red" >*</span>&nbsp;&nbsp;所属部门:</label>
			     <div class="r_div">
                    <input id="person_dept_id" name="dept_id" />
                </div>
		</div>
		<div class="Co">
            <label><span class="text_red" >*</span>&nbsp;&nbsp;办公电话:</label><input type="text" name="safe_person_tel"/>
        </div>
        <div class="Co">
            <label><span class="text_red" >*</span>&nbsp;&nbsp;手机:</label><input type="text" name="safe_person_phone"/>
        </div>
        <div class="Co">
            <label><span class="text_red" >*</span>&nbsp;&nbsp;邮箱:</label><input type="text" name="safe_person_email"/>
        </div>
		<input type="hidden" style="display:none;" name="check_id"  />
	    <input type="hidden" style="display:none;" name="file_yname"  />
        <input type="hidden" style="display:none;" name="file_xname"  />
    </div>
</form>
<!--添加安全人员结束-->
 </body>
 </html>