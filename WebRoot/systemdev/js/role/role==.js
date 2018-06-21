var url="";
var checkCs=0;

function role_type(value, row, index) {
	if (value == 2) {
		return '普通角色';
	} else {
		return '开发角色';
	}
}

$(function(){
	
	var CanCheck = true;//用来标志是否可以勾选
	
	$("#menuTree").tree({
		url:'wkrjsystemdev/wkrjRoleDev/getMenuPermission.wk',
		checkbox : true,
		onCheck : function(node, checked) {
			
			if (checked) {
				var parentNode = $("#menuTree").tree('getParent', node.target);
				
				if (parentNode != null) {
					$("#menuTree").tree('check', parentNode.target);
				}
			} else {
				var childNode = $("#menuTree").tree('getChildren', node.target);
				
				if (childNode.length > 0) {
					for ( var i = 0; i < childNode.length; i++) {
						$("#menuTree").tree('uncheck', childNode[i].target);
					}
				}
			}
		},
		onLoadSuccess:function(node, data){
			
			var childNode = $("#menuTree").tree('getChildren');
				
			if (childNode.length > 0) {
				
				for ( var i = 0; i < childNode.length; i++) {
					
					if(true==childNode[i].attributes.menu_is_display){
        		
		        	    var tt = $(childNode[i].target).find("span.tree-checkbox");
		        		$(tt[0]).removeClass("tree-checkbox");
		        		
		        	}
					
				}
			}
		}
	});
	
	$("#deptTree").tree({
	
		onSelect:function(node){
			$("#roledatagrid").datagrid({url:'wkrjsystemdev/wkrjRoleDev/getRoleList.wk?dept_id='+node.id})
		}
		
	});
	
});

// 设置菜单权限
function setMenuPermission() {
	
	var rows =  $('#roledatagrid').datagrid('getSelections');
	if(rows.length>1){
		$.messager.alert('操作提示', "保存时只能选择一个角色");
		return;
	}
	
	var row = $('#roledatagrid').datagrid('getSelected');
	if (row) {
		var menu = "";
		var permission = "";
		var menu_order ="";//菜单顺序
		
		var nodes = $('#menuTree').tree('getChecked');
		
		for ( var i = 0; i < nodes.length; i++) {
			
			if (nodes[i].attributes.menu_id != "" && typeof(nodes[i].attributes.menu_id)!="undefined" && true!=nodes[i].attributes.menu_is_display) {
				menu += nodes[i].attributes.menu_id + ",";
			}
			
			if (nodes[i].attributes.permid != "" && typeof(nodes[i].attributes.permid)!="undefined") {
				permission += nodes[i].attributes.permid + ",";
			}
			
			if (nodes[i].attributes.menu_order != "" && typeof(nodes[i].attributes.menu_order)!="undefined") {
				menu_order += nodes[i].attributes.menu_order + ",";
			}
		}
		
		menu = menu.substring(0, menu.length - 1);
		permission = permission.substring(0, permission.length - 1);
		menu_order = menu_order.substring(0, menu_order.length - 1);
		
		$.post('wkrjsystemdev/wkrjRoleDev/setMenuPermission.wk', {
			rolerId : row.role_id,
			menulist : menu,
			permissionlist : permission,
			menu_order:menu_order
		}, function(result) {
			
			try{
            	result = eval('('+result+')');
            }catch(e){
            		
            }
			
			if (result.success) {
				$.messager.alert('操作提示', result.msg);
			} else {
				$.messager.alert('操作提示', result.msg+'！！！');
			}
		});
	} else {
		$.messager.alert('操作提示', '请选择一个角色');
	}
}



// 添加角色
function addRole() {
	
	$('#roleForm').form('clear');
	$('#roleWindow').dialog('open').dialog('setTitle', '添加角色');
	url = 'wkrjsystemdev/wkrjRoleDev/addRole.wk';
}

//修改角色
function editRole(){
	
	var row = $('#roledatagrid').datagrid('getSelections');
	if (row.length==1) {
		$('#roleWindow').dialog('open').dialog('setTitle', '修改角色');
		$('#roleForm').form('load', row[0]);
	} else {
		$.messager.alert('操作提示', '请选择一条数据进行修改');
	}
	
	url = 'wkrjsystemdev/wkrjRoleDev/updateRole.wk';
}

function saveRole() {

	$('#roleForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.alert('操作提示', result.msg);
				$('#roleForm').form('clear');
				$('#roleWindow').dialog('close');
				$('#roledatagrid').datagrid('load', {});
			} else {
				$.messager.alert('操作提示', result.msg+'！！！');
			}
		}
	});
}

// 删除角色
function delRole() {
	var row = $('#roledatagrid').datagrid('getSelections');
	
	if (row.length>0) {
		
		var ids = new Array();
		
		for(var i=0;i<row.length;i++){
			ids.push(row[i].role_id);
		}
		
		$.messager.confirm('删除提示', '你确定要删除这些数据吗,删除后不能回复请谨慎操作!!!', function(r) {
			if (r) {
				$.post('wkrjsystemdev/wkrjRoleDev/delRole.wk', {
					id : ids.join(",")
				}, function(result) {
					
					try{
	            		result = eval('('+result+')');
	            	}catch(e){
	            		
	            	}
					
					if (result.success) {
						$.messager.alert('操作提示', '删除成功');
						$('#roledatagrid').datagrid('load', {});
						$("#menuTree").tree({url:'wkrjsystemdev/wkrjRoleDev/getMenuPermission.wk'});
					} else {
						$.messager.alert('操作提示', result.msg);
					}
				});
			}
		});
	}else{
		$.messager.alert('操作提示', '请选择您要删除的记录');
	}
}

function op(value,row,index){
	
	var btn = '<a style="text-decoration:none;" ekper="" onclick="role_showOp(\''+row.role_id+'\',\''+index+'\')" href="javascript:void(0)">[授权]</a>';
	return btn;
}


function role_showOp(id,index){
	
	checkCs=0;//重置成0
	if($("#role_layout").layout('panel', 'east').panel('options').collapsed){
		$("#role_layout").layout('expand','east');
	}
	
//	$("#roledatagrid").datagrid("unselectAll");
//	$("#roledatagrid").datagrid("checkRow",index);
//	$("#roledatagrid").datagrid("selectRow",index);
//	$("#roledatagrid").datagrid("selectRecord",index);
	
	$("#menuTree").tree({url:'wkrjsystemdev/wkrjRoleDev/getMenuPermission.wk?role_id='+id});
}
//拷贝角色
function copyRole(){
	
	var row = $('#roledatagrid').datagrid('getSelected');
	if (row) {
		$.post('wkrjsystemdev/wkrjRoleDev/copyRole.wk', {
			role_id : row.role_id
		}, function(result) {
			
			try{
        		result = eval('('+result+')');
        	}catch(e){
        		
        	}
			
			if (result.success) {
				$.messager.alert('操作提示', result.msg);
				$('#roledatagrid').datagrid('load', {});
			} else {
				$.messager.alert('操作提示', result.msg);
			}
		});
	} else {
		$.messager.alert('操作提示', '请选择一个角色');
	}

}
//展示所有的角色
function showAllRole(){
	$("#roledatagrid").datagrid({url:'wkrjsystemdev/wkrjRoleDev/getRoleList.wk'})
}

function treeChecked(treeMenu) {
	
	var roots = $('#' + treeMenu).tree('getRoots');//返回tree的所有根节点数组
	
	for ( var i = 0; i < roots.length; i++) {
		var node = $('#' + treeMenu).tree('find', roots[i].id);
		
		if(0==checkCs){
			
			$('#' + treeMenu).tree('check', node.target);
		
			var childNode = $('#' + treeMenu).tree('getChildren', node.target);
			if (childNode.length > 0) {
				for ( var j = 0; j < childNode.length; j++) {
					$('#' + treeMenu).tree('check', childNode[j].target);
				}
			}
			
		}else{
			
			$('#' + treeMenu).tree('uncheck', node.target);
		
			var childNode = $('#' + treeMenu).tree('getChildren', node.target);
			if (childNode.length > 0) {
				for ( var j = 0; j < childNode.length; j++) {
					$('#' + treeMenu).tree('uncheck', childNode[j].target);
				}
			}
			
		}
	}
	
	if(checkCs==0){
		checkCs=1;
	}else{
		checkCs=0;
	}
		
	
}

$("#roledatagrid").datagrid({
	
	onDblClickRow:function(rowIndex, rowData){
		
		$('#roleWindow').dialog('open').dialog('setTitle', '修改角色');
		$('#roleForm').form('load', rowData);
		
		url = 'wkrjsystemdev/wkrjRoleDev/updateRole.wk';
	}
});
