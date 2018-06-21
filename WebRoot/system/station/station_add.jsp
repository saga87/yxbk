<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
 
<script type="text/javascript">
       $(function (){
           $("#station_addWindow_form").ligerForm();
           
           var form = liger.get("station_addWindow_form");
	        var data = form.getData();
	        //alert(JSON.stringify(data));
       });
 
 
   </script>
   </head>
<body>
<!--添加岗位开始-->
<form id="station_addWindow_form" method="post">
                <div class="co" style="display: none;">
                    <label style="text-align:right;">岗位id:</label>
                    <input class="ui-textbox"
                        name="station_id" id="station_ids">
                </div>
                <div class="co">
                    <label style="text-align:right;">岗位名称:</label>
                    <input name="station_name" />
                </div>
</form>

 </body>
 </html>