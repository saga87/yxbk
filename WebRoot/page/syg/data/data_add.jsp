<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">

<script type="text/javascript">
   $(function (){
	   $("#data_parent_id").ligerComboBox({
            width :300,height: 30,selectBoxWidth:300,selectBoxHeight: 200, 
            valueField: 'id', treeLeafOnly: false, isMultiSelect: true,
            tree: { checkbox: false, url: 'data/WkrjDataController/getDataTree', ajaxType: 'post', idField: 'id' }
	   });
            
   });
</script>
</head>
<body>
<!--添加部门开始-->
<form id="data_addWindow_form" method="post" class="liger-form">

	<div class="formdiv">
	    <label style="text-align:right;"><font color="red">*</font>名称:</label>
	    <input data-options="required:true" name="data_name">
	</div>
	<div class="formdiv">
	    <label style="text-align:right;">上级名称:</label>
	    <div class="r_div">
	    	<input name="data_parent_id" id="data_parent_id"
	            data-options="method:'post'"/>
	    </div>
	</div>
	<div class="formdiv">
        <label style="text-align:right;"><font color="red">*</font>显示序号:</label>
        <input data-options="required:true" name="data_order"   placeholder="请输入数字">
    </div>
    <div class="formdiv">
        <label style="text-align:right;"><font color="red">*</font>是否首页显示:</label>
        <select name="data_show">
            <option value="">请选择</option>
            <option value="1">是</option>
            <option value="2">否</option>
        </select>
    </div>
</form>
</body>
</html>