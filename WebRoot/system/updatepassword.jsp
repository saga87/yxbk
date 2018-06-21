<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>

<form id="passwordForm" method="post" class="liger-form">
	<div class="fitem">
		<label style="text-align:right;">旧密码:</label> <input name="oldpwd"
			type="password" style="width: 212px;" >
	</div>
	<div class="fitem">
		<label style="text-align:right;">新密码:</label> <input name="newpwd"
			type="password" style="width: 212px;" >
	</div>
</form>