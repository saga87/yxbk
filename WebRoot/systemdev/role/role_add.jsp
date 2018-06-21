<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>

<script type="text/javascript">
       $(function (){
			
    	   
    	   
       });

</script>

<!--添加角色开始-->
<form id="role_addWindow_form" method="post" class="liger-form">
                <div class="co" style="display: none;">
                    <label style="text-align:right;">角色id:</label> <input
                        name="role_id" id="role_ids" type="text" class="ui-textbox">
                </div>
               
                <div class="co">
                    <label style="text-align:right;">角色名称:</label> 
                    <input type="text" class="ui-textbox" name="role_name">
                </div>
               <!--  <div class="co">
                    <label style="text-align:right;">角色部门:</label> 
                    <input name="role_dept" id="role_dept" type="text" class="ui-combobox"
                            style="width:200px;" />
                </div> -->
                <div class="co">
                    <label style="text-align:right;">角色备注:<br/></label>
                    <textarea style="width:300px;" name="role_other"></textarea>
                </div>
</form>