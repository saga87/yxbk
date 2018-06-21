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
           $("#student_addWindow_form").ligerForm();
           
           var form = liger.get("student_addWindow_form");
	        var data = form.getData();
	        //alert(JSON.stringify(data));
       });
 
 
   </script>
   </head>
<body>
<!--添加岗位开始-->
<form id="student_addWindow_form" method="post">
    <!--             <div class="co" style="display: none;">
                    <label style="text-align:right;">岗位id:</label>
                    <input class="ui-textbox"
                        name="student_id" id="student_ids">
                </div> -->
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
            
                    <select name="sex"> 
                    <option value="">-请选择-</option>
                    <option value="1">-女-</option>
                    <option value="0">-男-</option>
                    </select>
                    
                </div>
                <div class="co">
                    <label style="text-align:right;">出生日期:</label>
                    <input type="text" name="cs_date" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
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