<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>"></base>
<link rel="stylesheet" type="text/css"
	href="system/css/public.css">
<script type="text/javascript" src="system/js/main/main.js"></script>

<script type="text/javascript">
	$(function(){
		var perms = "${sessionScope.userPermission}";
		var userTypeCount = "${sessionScope.userCountType}";
		var pp ="";
		if(1!=userTypeCount){
			if(top.$('#mainTabs').length>0){
				pp = top.$('#mainTabs').tabs('getSelected');
			try{
	   			var iframe = pp.find("iframe");
	   			var btns = iframe[0].contentWindow.$(".easyui-linkbutton");
				btns.each(function(){
					var perm = $(this).attr('ekper');
					if(typeof(perm)!="undefined"){
						if(perms.indexOf(perm)>=0){
							$(this).show();
						}else{
							$(this).hide();
						}
					}
					
				});
						
			}catch(e){}
		}
		}
		
	});
</script>

</head>
