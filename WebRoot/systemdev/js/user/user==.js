var url="";


$("#userrolercombobox").combobox({  
    multiple:true,
    url:'wkrjsystemdev/wkrjRoleDev/getAllRoleList.wk'
}); 

$("#station_id").combobox({  
    url:'wkrjsystemdev/wkrjUserDev/getAllStationList.wk'
}); 

$("#user_department_id").combotree({  
    url:'wkrjsystemdev/wkrjUserDev/getDeptTree.wk'
}); 

$("#userDeptTree").tree({
	url:'wkrjsystemdev/wkrjUserDev/getDeptTree.wk'
});

$("#userDeptTree").tree({
	onSelect:function(node){
		$('#userdatagrid').datagrid('load', {deptId:node.id});
	}
});

function showAllUser(){
	
	$('#userdatagrid').datagrid('load', {});
	$("#userDeptTree").tree({url:'wkrjsystemdev/wkrjUserDev/getDeptTree.wk'});//让选中的不选中
}

function user_is_enable(value, row, index) {
	if (value == 0) {
		return '启用';
	} else if(value==1){
		return '禁用';
	}else{
		return '有问题';
	}
}

// 添加用户
function addUser() {
	
	var partentId = $('#userDeptTree').tree('getSelected');
	
	if (partentId) {
		$('#userWindow').dialog('open').dialog('setTitle', '添加用户');
		$('#userForm').form('clear');
		$('#user_department_id').combotree('setValue', partentId.id);
		$('#userIsEnabled').combobox('setValue', 0);
		
		$('#zxh_user_userName').removeAttr('readonly');
		$("#zxh_user_password").show();
		
		url="wkrjsystemdev/wkrjUserDev/addUser.wk";
		
	} else {
		$.messager.alert('操作提示', '请先选择部门');
	}
}

///修改用户
function editUser() {
	var row = $('#userdatagrid').datagrid('getSelected');
	if (row) {
		$('#userWindow').dialog('open').dialog('setTitle', '修改用户');
		$('#userForm').form('load', row);
		
		$('#zxh_user_userName').attr('readonly','readonly');
		
		if(row.user_is_enabled){
			$('#userIsEnabled').combobox('setValue', 1);
		}else{
			$('#userIsEnabled').combobox('setValue', 0);
		}
		
		if(""!=showRoleName2(row.user_role))
		$("#userrolercombobox").combobox("setValues",showRoleName2(row.user_role).split(",")); 
		
		url="wkrjsystemdev/wkrjUserDev/updateUser.wk";
		
		$("#zxh_user_password").hide();
		
	} else {
		$.messager.alert('操作提示', '请选择一条数据进行修改');
	}
}

// 保存用户
function saveUser() {
	
	
	$('#userForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.alert('操作提示', result.msg);
				
				var selected = $('#userDeptTree').tree('getSelected');
				if(selected){
					$('#userdatagrid').datagrid('load', {deptId:selected.id});
				}else{
					$('#userdatagrid').datagrid('load', {});
				}
				
				$('#userWindow').dialog('close');
//				$('#userForm').form('clear');
			} else {
				$.messager.alert('操作提示', result.msg);
			}
		}
	});
}

// 删除用户
function delUser() {
	var row = $('#userdatagrid').datagrid('getSelections');
	if (row) {
		
		var ids = new Array();
		
		for(var i=0;i<row.length;i++){
			ids.push(row[i].user_id);
		}
		
		$.messager.confirm('删除提示', '你确定要删除这些数据吗,删除后不能回复请谨慎操作!!!', function(r) {
			if (r) {
				$.post('wkrjsystemdev/wkrjUserDev/delUser.wk', {
					id : ids.join(",")
				}, function(result) {
					
					try{
	            		result = eval('('+result+')');
	            	}catch(e){
	            		
	            	}
					
					if (result.success) {
						$.messager.alert('操作提示', '删除成功');
						
						var selected = $('#userDeptTree').tree('getSelected');
						if(selected){
							$('#userdatagrid').datagrid('load', {deptId:selected.id});
						}else{
							$('#userdatagrid').datagrid('load', {});
						}
						
					} else {
						$.messager.alert('操作提示', result.msg);
					}
				});
			}
		});
	}else{
		$.messager.alert('操作提示', '请选择要删除的用户');
	}
}

//禁用用户
function forbiddenUser() {
	var row = $('#userdatagrid').datagrid('getSelected');
	if (row) {
		$.messager.confirm('删除提示', '你确定要禁用这些用户吗', function(r) {
			if (r) {
				$.post('wkrjsystemdev/wkrjUserDev/forbiddenUser.wk', {
					id : row.user_id,
					flag:1
				}, function(result) {
					
					try{
	            		result = eval('('+result+')');
	            	}catch(e){
	            		
	            	}
					
					if (result.success) {
						$.messager.alert('操作提示', '禁用成功');
						$('#userdatagrid').datagrid('load', {});
					} else {
						$.messager.alert('操作提示', result.msg);
					}
				});
			}
		});
	}else{
		$.messager.alert('操作提示', '请选择要禁用的用户');
	}
}

//启用用户
function startuser() {
	var row = $('#userdatagrid').datagrid('getSelected');
	if (row) {
		$.messager.confirm('操作提示', '你确定要启用这些用户吗', function(r) {
			if (r) {
				$.post('wkrjsystemdev/wkrjUserDev/forbiddenUser.wk', {
					id : row.user_id,
					flag:0
				}, function(result) {
					try{
	            		result = eval('('+result+')');
	            	}catch(e){
	            		
	            	}
					if (result.success) {
						$.messager.alert('操作提示', '启用成功');
						$('#userdatagrid').datagrid('load', {});
					} else {
						$.messager.alert('操作提示', result.msg);
					}
				});
			}
		});
	}else{
		$.messager.alert('操作提示', '请选择要启用的用户');
	}
}

function checkSfz(){
	
	var sfz = $('#zxh_userview_sfz').val();
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	if(sfz!=""){
	    if(reg.test(sfz) === false)  
	    {  
	       $.messager.alert('操作提示', '身份证输入不合法');
	       $('#zxh_userview_sfz').val('');
	       return  false;  
	    }  
    }
}

function checkTel(){
	
	var tel = $('#zxh_userview_tel').val();
	if(tel!=""){
		if(!(/^0?1[3|4|5|8][0-9]\d{8}$/.test(tel))){
			$.messager.alert('操作提示', '请输入正确的手机号');
			$('#zxh_userview_tel').val('');
			return;
		}
		
	}
	
}

function showRoleName(role){
	
	var roleName="";
	if(role.length>0){
		
		for(var i=0;i<role.length;i++){
			roleName += role[i].role_name;
			
			if(i<role.length-1){
				roleName +=",";
			}
		}
	}
	
	return roleName;
}

function showRoleName2(role){
	var roleName="";
	if(role.length>0){
		
		for(var i=0;i<role.length;i++){
			roleName += role[i].role_id;
			
			if(i<role.length-1){
				roleName +=",";
			}
		}
	}
	return roleName;
}

function getBmName(deptId){
	
	var deptname="";
	
	$.ajax({  
        url : "wkrjsystemdev/wkrjUserDev/getDeptNameById.wk",  
        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        type : "POST",
        data:{deptId:deptId},
        dataType : "json",  
        success : function(res) {  
        	if(res.obj){
           		deptname = res.obj.dept_name;
           	}
        }  
    });
    
     return deptname;
}

function getStationName(stationId){
	
	var stationName="";
	
	$.ajax({  
        url : "wkrjsystemdev/wkrjUserDev/getStationById.wk",  
        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        type : "POST",
        data:{stationId:stationId},
        dataType : "json",  
        success : function(res) {
        	if(res.obj){
           		stationName = res.obj.station_name;
           	}
        }  
    });
	
	return stationName;
}

function searchUser(){
	
	var deptId = $("#searchUserDept").combotree('getValue');
	var userName = $("#searchUserRealname").val();
	
	$('#userdatagrid').datagrid('load', {deptId:deptId,userName:userName});
	
}

$("#userdatagrid").datagrid({
	
	onDblClickRow:function(rowIndex, row){
		
		$('#userWindow').dialog('open').dialog('setTitle', '修改用户');
		$('#userForm').form('load', row);
		
		$('#zxh_user_userName').attr('readonly','readonly');
		
		if(row.user_is_enabled){
			$('#userIsEnabled').combobox('setValue', 1);
		}else{
			$('#userIsEnabled').combobox('setValue', 0);
		}
		
		if(""!=showRoleName2(row.user_role))
		$("#userrolercombobox").combobox("setValues",showRoleName2(row.user_role).split(",")); 
		
		url="wkrjsystemdev/wkrjUserDev/updateUser.wk";
		
		$("#zxh_user_password").hide();
	}
});

//重置密码成123
function repeatPw(){
	
	var row = $('#userdatagrid').datagrid('getSelected');
	if (row) {
		$.messager.confirm('删除提示', '你确定要重置密码吗,重置后密码变为123', function(r) {
			if (r) {
				$.post('wkrjsystemdev/wkrjUserDev/repeatPw.wk', {
					id : row.user_id
				}, function(result) {
					
					try{
	            		result = eval('('+result+')');
	            	}catch(e){
	            		
	            	}
					if (result.success) {
						$.messager.alert('操作提示', result.msg);
						$('#userdatagrid').datagrid('load', {});
					} else {
						$.messager.alert('操作提示', result.msg);
					}
				});
			}
		});
	}else{
		$.messager.alert('操作提示', '请选择要重置密码的记录');
	}
}