<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%@include file="/systemdev/myContext.jsp" %>
 <head>
 <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=10"/>
<script type="text/javascript" src="page/syg/clinic/js/clinic.js"></script>

<script type="text/javascript">
 $(function (){
 	
 });
</script>

</head>
<body style="overflow:hidden;">
<div id="clinic_layout" style="width:100%;">	
	<div position="center">
	
	<div class="search_div">
            <div class="fl_s"><span class="search_name">指南名称：</span>
               <input type="text" id="clinic_name" style="border: 1px solid #d9d9d9;"/>
            </div>
            <input type="button" class="button_c" onclick="queryClinic()" value="查询">
            <input type="button" class="button_c" onclick="allClinic()" value="显示全部">
         </div>
	
	
	     <div id="clinic_maingrid"></div>
	</div>
</div>
</body>
</html>
