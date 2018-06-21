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
		url:'wkrjsystem/wkrjRole/getRoleList',
        columns : [
	        { display: '操作', isSort: false, width: '10%', render: function (rowdata, rowindex, value){
	        	var h = "&nbsp;<a href='javascript:role_addPermission(\"" + rowdata.role_id + "\")'>[授权]</a>";
	        	return h;
	        	}
	        },
	        { display: '角色名称', name: 'role_name', id: 'role_name', width: '55%', align: 'left' },
	        //{ display: '角色级别', name: 'role_level', id: 'role_level', width: '30%', align: 'left', render:role_level },
	       // { display: '角色类型', name: 'role_type', id: 'role_type', width: 50, align: 'left', render: function role_type(rowdata, rowindex, value) {} },
	        { display: '备注', name: 'role_other', id:'role_other',width: '44%', align: 'left' }
        ], height : '100%',
        usePager : true,
		rownumbers : true,
        alternatingRow : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : role_addRole,
				icon : 'add',
				id:'wkrjsystem/wkrjRole/addRole'
			}, {
				line : true
			}, {
				text : '修改',
				click : role_editRole,
				icon : 'modify',
				id:'wkrjsystem/wkrjRole/updateRole'
			}, {
				line : true
			}, {
				text : '删除',
				click : role_delRow,
				icon : 'delete',
				id:'wkrjsystem/wkrjRole/delRole'
			}, {
				line : true
			}, {
				text : '复制角色',
				click : copyRole,
				icon : 'modify',
				id:'wkrjsystem/wkrjRole/copyRole'
			} ]
		}
    });
	$("#pageloading").hide();
});

function role_addPermission(rowid){
	//打开右侧
	lay.setRightCollapse(false);
	$("#permissiongrid").ligerTree({
         url: 'wkrjsystem/wkrjRole/getMenuPermission?role_id='+rowid,  
         nodeWidth : '100%',
         height:'100%',
         idFieldName: 'id',
         isExpand:2,
         enabledCompleteCheckbox :false,
         onSuccess:function(node){
         	
         	var childNode = $("#permissiongrid").ligerTree('getAllNodes')
         	
         	if (childNode.length > 0) {
				for ( var i = 0; i < childNode.length; i++) {
					if(true==childNode[i].data.attributes.menu_is_display){
						var obj=$("#"+childNode[i].data.id)[0];
		        	    
		        	    var tt = $(obj).find("div.l-checkbox");
		        	    $(tt[0]).removeClass("l-box");
		        	}
				}
			}
			
		}
	});
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
		        url: 'wkrjsystem/wkrjRole/delRole',
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
		url : "page/role/role_add.jsp",
		width : 500,
		height : 400,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
		        var data = dialog.frame.liger.get("role_addWindow_form").getData();
		        if (data.role_name == "" || data.role_name == null) {
					top.$.ligerDialog.alert("请输入角色名称");
					return;
				}
		        //data.role_level = dialog.frame.liger.get("role_level").getValue();
				$.ajax({
		            url: "wkrjsystem/wkrjRole/addRole",
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
		url : "page/role/role_update.jsp",
		//data : JSON.stringify(r),
		width : 500,
		height :400,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("role_updateWindow_form").getData();
				if(typeof(data.role_name) == "undefined"){
					data['role_name'] = dialog.frame.$("textarea[name='role_name']").val();
				}
				if (data.role_name == "" || data.role_name == null) {
					top.$.ligerDialog.alert("请输入角色名称");
					return;
				}
				//datel = dialog.frame.liger.get("role_level").getValue();
				$.ajax({
		            url: "wkrjsystem/wkrjRole/updateRole",
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
			//=="0"时，处理
			if ((nodes[i].data.attributes.menu_order != "" && typeof(nodes[i].data.attributes.menu_order)!="undefined")||(nodes[i].data.attributes.menu_order=="0")) {
				menu_order += nodes[i].data.attributes.menu_order + ",";
			}
		}
		
		menu = menu.substring(0, menu.length - 1);
		permission = permission.substring(0, permission.length - 1);
		menu_order = menu_order.substring(0, menu_order.length - 1);
		
		$.post('wkrjsystem/wkrjRole/setMenuPermission', {
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
		$.post('wkrjsystem/wkrjRole/copyRole', {
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
function role_level(rowdata, rowindex, value) {
	if (rowdata.role_level == 1) {
		return '市级';
	} else if (rowdata.role_level == 2) {
		return '县级';
	} else if (rowdata.role_level == 3) {
		return '乡镇';
	} else if (rowdata.role_level == 4) {
		return '学校';
	} else {
		return '';
	}
}