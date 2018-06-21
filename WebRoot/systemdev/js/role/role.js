function role_type(value, row, index) {
	if (value == 2) {
		return '普通角色';
	} else {
		return '开发角色';
	}
}
var url="";
var lay;
var manager;
var perm;
$(function(){

	lay=$("#role_layout").ligerLayout({rightWidth:400,isRightCollapse: true});
	
	manager = $("#role_maingrid").ligerGrid({
		url:'wkrjsystemdev/wkrjRoleDev/getRoleList.wk',
        columns : [
        { display: '角色名称', name: 'role_name', id: 'role_name', width: '50%', align: 'left' },
       // { display: '角色类型', name: 'role_type', id: 'role_type', width: 50, align: 'left', render: function role_type(rowdata, rowindex, value) {} },
        { display: '备注', name: 'role_other', id:'role_other',width: '30%', align: 'left' },
        { display: '操作', isSort: false, width: '20%', render: function (rowdata, rowindex, value){
        	var h = "&nbsp;<a href='javascript:role_addPermission(\"" + rowdata.role_id + "\")'>[授权]</a>";
        	return h;
        	}
        }], height : '100%',
        usePager : true,
		rownumbers : true,
        alternatingRow : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : role_addRole,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : role_editRole,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : role_delRow,
				icon : 'delete'
			},{
				text : '复制角色',
				click : copyRole,
				icon : 'modify'
			} ]
		}
    });
	$("#pageloading").hide();
});

function role_addPermission(rowid){
	//打开右侧
	lay.setRightCollapse(false);
	$("#permissiongrid").ligerTree({
         url: 'wkrjsystemdev/wkrjRoleDev/getMenuPermission.wk?role_id='+rowid,  
         nodeWidth : '100%',
         height:'100%',
         idFieldName: 'id',
         isExpand:false,
         enabledCompleteCheckbox :false
	});
}

function selecAll(treeMenu) {
	
	
	alert('ffff1');
	return;
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

function role_delRow(row){
	var g = $("#role_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystemdev/wkrjRoleDev/delRole.wk',
		        data: { id: r.role_id },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('删除成功!');
		        	  g.loadData();
		          } else {
		        	  $.ligerDialog.alert('删除失败!');
		          }
		        }
		      });
		}
	})
}

/**
 * 添加角色
 */
function role_addRole() {
	var g = $("#role_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		url : "systemdev/role/role_add.jsp",
		width : 500,
		height : 300,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
		        var data = dialog.frame.liger.get("role_addWindow_form").getData();
				$.ajax({
		            url: "wkrjsystemdev/wkrjRoleDev/addRole.wk",
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	g.loadData();
		                        dialog.close();
		                    } else {
		                    	$.ligerDialog.alert(result.msg);
		                    }
		                 }
		           });
			}
		}, {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]
	});
}

/**
 * 角色修改
 */
function role_editRole(){
	var g = $("#role_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}

	var s = parent.$.ligerDialog.open({
		//target: $("#module_updateWindow_form"),
		url : "systemdev/role/role_update.jsp",
		//data : JSON.stringify(r),
		width : 500,
		height : 300,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("role_updateWindow_form").getData();

				$.ajax({
		            url: "wkrjsystemdev/wkrjRoleDev/updateRole.wk",
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	g.loadData();
		                        dialog.close();
		                    } else {
		                    	$.ligerDialog.alert(result.msg);
		                    }
		                 }
		           });
			}
		}, {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]

	});
}

// 设置菜单权限
function setMenuPermission() {
	
	var g = $("#role_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	
	var nodes = $("#permissiongrid").ligerTree('getChecked');
	
	if(null!=nodes && ""!=nodes){
		
		var menu = "";
		var permission = "";
		var menu_order ="";//菜单顺序
		
		for ( var i = 0; i < nodes.length; i++) {
			
			if (nodes[i].data.attributes.menu_id != "" && typeof(nodes[i].data.attributes.menu_id)!="undefined" && true!=nodes[i].data.attributes.menu_is_display) {
				menu += nodes[i].data.attributes.menu_id + ",";
			}
			
			if (nodes[i].data.attributes.permid != "" && typeof(nodes[i].data.attributes.permid)!="undefined") {
				permission += nodes[i].data.attributes.permid + ",";
			}
			
			if (nodes[i].data.attributes.menu_order != "" && typeof(nodes[i].data.attributes.menu_order)!="undefined") {
				menu_order += nodes[i].data.attributes.menu_order + ",";
			}
		}
		
		menu = menu.substring(0, menu.length - 1);
		permission = permission.substring(0, permission.length - 1);
		menu_order = menu_order.substring(0, menu_order.length - 1);
		
		$.post('wkrjsystemdev/wkrjRoleDev/setMenuPermission.wk', {
			rolerId : r.role_id,
			menulist : menu,
			permissionlist : permission,
			menu_order:menu_order
		}, function(result) {
			
            result = eval('('+result+')');
            
			if (result.success) {
				$.ligerDialog.alert(result.msg);
			} else {
				$.ligerDialog.alert(result.msg);
			}
		});
		
	}else {
		$.ligerDialog.alert('请选择一条记录!');
	}
	
	
}

//拷贝角色
function copyRole(){
	
	var g = $("#role_maingrid").ligerGetGridManager();
	var row = g.getSelectedRow();
	if (row == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	
	if (row) {
		$.post('wkrjsystemdev/wkrjRoleDev/copyRole.wk', {
			role_id : row.role_id
		}, function(result) {
			
        	result = eval('('+result+')');
			if (result.success) {
				$.ligerDialog.alert(result.msg);
		        g.loadData();
			} else {
				$.ligerDialog.alert(result.msg);
		        g.loadData();
			}
		});
	} else {
		$.messager.alert('操作提示', '请选择一个角色');
	}

}
	