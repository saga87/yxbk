<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <script language="javascript" type="text/javascript"
    src="plug-in/DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">
       $(function (){
    	   var dialog = frameElement.dialog;
    	   var dialogData = dialog.get('data');//获取data参数
    	   $("#student_updateWindow_form").ligerForm().setData(dialogData.content);
    	 
       });
</script>
 </head>
 <body>

<!--添加菜单开始-->
<form id="student_updateWindow_form" method="post" >
	            <div class="co" style="display: none;">
                    <label style="text-align:right;">id:</label> <input
                        name="student_id" id="student_id"  />
                </div>
                <div class="co">
                    <label style="text-align:right;">学号:</label>
                    <input name="student_num" />
                </div>
                <div class="co">
                    <label style="text-align:right;">姓名:</label>
                    <input name="name" />
                </div>
                <div class="co">
                    <label style="text-align:right;">性别:</label>
                    <input name="sex" />
                </div>
                <div class="co">
                    <label style="text-align:right;">出生日期:</label>
                    <input type="text" name="birthdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                </div>
                <div class="co">
                    <label style="text-align:right;">身份证:</label>
                    <input name="idcard" />
                </div>
                <div class="co">
                    <label style="text-align:right;">年级:</label>
                    <input name="nj" />
                </div>
                <div class="co">
                    <label style="text-align:right;">级部:</label>
                    <input name="jb" />
                </div>
                <div class="co">
                    <label style="text-align:right;">班级:</label>
                    <input name="bj" />
                </div>
                <div class="co">
                    <label style="text-align:right;">身高:</label>
                    <input name="height" />
                </div>
                <div class="co">
                    <label style="text-align:right;">体重:</label>
                    <input name="weight" />
                </div>
                <div class="co">
                    <label style="text-align:right;">邮箱:</label>
                    <input name="email" />
                </div>
</form>


</body>
</html>