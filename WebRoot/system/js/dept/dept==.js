var url="";



$(function(){
	//$("#dept_parent_id").combotree({url:'wkrjsystem/wkrjDept/getDeptTree'});
	$("#deptTree").tree({url:'wkrjsystem/wkrjDept/getDeptTree'});
});

// 添加组织结构
function addDept() {
	
	$('#deptWindow').dialog('open').dialog('setTitle', '添加组织结构');
	$('#deptForm').form('clear');
	url = 'wkrjsystem/wkrjDept/addDept';
	
	$("#dept_parent_id").combotree({url:'wkrjsystem/wkrjDept/getDeptTree'});
	
	var row = $('#deptTree').tree('getSelected');
	if (row) {
		$("#dept_parent_id").combotree('setValue',row.attributes.dept_id);
	}
	
}

//修改组织结构
function editDept(){
	
	var row = $('#deptTree').tree('getSelected');
	if (row) {
		
		$("#dept_parent_id").combotree({url:'wkrjsystem/wkrjDept/getDeptTree'});
		$('#deptWindow').dialog('open').dialog('setTitle', '修改组织结构');
		$('#deptForm').form('load', row.attributes);
		
		if("-1"==row.attributes.dept_parent_id){
			$("#dept_parent_id").combotree('setValue','');
		}
		
	} else {
		$.messager.alert('操作提示', '请选择一条数据进行修改');
	}
	
	url = 'wkrjsystem/wkrjDept/updateDept?yId='+row.attributes.dept_id;
}

function saveDept() {

	$('#deptForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.alert('操作提示', result.msg);
				$('#deptForm').form('clear');
				$('#deptWindow').dialog('close');
				$('#deptTree').tree({url:'wkrjsystem/wkrjDept/getDeptTree'});
				$("#dept_parent_id").combotree({url:'wkrjsystem/wkrjDept/getDeptTree'});
			} else {
				$.messager.alert('操作提示', result.msg+'！！！');
			}
		}
	});
}

// 删除组织结构
function delDept() {
	
	var row = $('#deptTree').tree('getSelected');
	
	if (row) {
		
		$.messager.confirm('删除提示', '你确定要删除这些数据吗,删除后不能回复请谨慎操作!!!', function(r) {
			if (r) {
				$.post('wkrjsystem/wkrjDept/delDept', {
					id : row.attributes.dept_id
				}, function(result) {
					try{
	            		result = eval('('+result+')');
	            	}catch(e){
	            		
	            	}
					if (result.success) {
						$.messager.alert('操作提示', '删除成功');
						$('#deptTree').tree({url:'wkrjsystem/wkrjDept/getDeptTree'});
						$("#dept_parent_id").combotree({url:'wkrjsystem/wkrjDept/getDeptTree'});
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


