<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>

<script type="text/javascript" src="systemdev/js/user/user.js"></script>
<script type="text/javascript">
 
</script>
<div class="l-loading" style="display:block" id="pageloading"></div>
<div id="userdev_layout" style="width:100%;">
    
	<div position="center" >
	    <div style="">
	                    真实姓名:<input class="" type="text" id="searchUserRealname"
	                style="width:190px;margin-top:4px;margin-left:3px;" />
	            
	            <a  href="javascript:void(0)"
	                ekper="wkrjsystem/wkrjUser/getUserList"
	                class="" iconCls="icon-search" plain="true"
	                onclick="searchUser()">查询</a>
	    </div>
		<div id="user_maingrid"></div>
	</div>
	<div position="left" >
            <ul id="user_dept_tree"></ul> 
    </div> 
		
</div>

