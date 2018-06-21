<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
<script type="text/javascript"
   src="page/dept/js/dept.js"></script>
<script type="text/javascript">
        $(function (){
            var dialog = frameElement.dialog;
            var dialogData = dialog.get('data');//获取data参数
            var dept_id = dialogData.content.attributes.dept_id;
            temp_manager = $("#safePerson_maingrid").ligerGrid({
		        url:'wkrjsystem/wkrjDept/getSafePersonList?dept_id='+dept_id,
		        columns: [
		        { display: '部门', name: 'dept_name', id: 'dept_name', width: '20%', align: 'center' },
		        { display: '安全人员', name: 'safe_person_name', id: 'safe_person_name', width: '20%', align: 'center' },
		        { display: '办公电话', name: 'safe_person_tel', id: 'safe_person_tel', width: '20%', align: 'center' },
		        { display: '手机', name: 'safe_person_phone', id: 'safe_person_phone', width: '20%', align: 'center' },
		        { display: '邮箱', name: 'safe_person_email', id: 'safe_person_email', width: '20%', align: 'center' }
		        ], height: '100%',
		        width : 985,
		        usePager :true,
		        rownumbers : true,
		        alternatingRow: true,
		        toolbar : {
		            items : [ {
		                text : '增加',
		                value : dept_id,
		                click : function(item) {
		                    addSafePerson(item.value);
		                },
		                icon : 'add',
		                id:'wkrjsystem/wkrjDept/addSafePerson'
		            }, {
		                line : true
		            }, {
		                text : '修改',
		                click : editSafePerson,
		                icon : 'modify',
		                id:'wkrjsystem/wkrjDept/updateSafePerson'
		            }, {
		                line : true
		            }, {
		                text : '删除',
		                click : safePerson_delRow,
		                icon : 'delete',
		                id:'wkrjsystem/wkrjDept/delSafePerson'
		            }]
		        }
		    });
       });
</script>
</head>
<body >
<div id="safePerson_layout" style="width:560px">	
	<div position="center">
		<div id="safePerson_maingrid"></div>
	</div>
</div>
</body>
