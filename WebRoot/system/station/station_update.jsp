<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <script type="text/javascript">
       $(function (){
    	   var dialog = frameElement.dialog;
    	   var dialogData = dialog.get('data');//获取data参数
    	   $("#station_updateWindow_form").ligerForm().setData(dialogData.content);
    	 
       });
</script>
 </head>
 <body>

<!--添加菜单开始-->
<form id="station_updateWindow_form" method="post" >
	            <div class="co" style="display: none;">
                    <label style="text-align:right;">岗位id:</label> <input
                        name="station_id" id="station_ids"  />
                </div>
                <div class="co">
                    <label style="text-align:right;">岗位名称:</label>
                    <input name="station_name" />
                </div>
</form>


</body>
</html>