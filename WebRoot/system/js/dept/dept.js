var tree = null;
var url="";
var lay;
var manager;
var perm;
$(function(){
	$("#dept_toptoolbar").ligerToolBar({ items: [
                 { text: '增加', click: dept_addDept , icon:'add' },
                 { line:true },
                 { text: '修改', click: dept_editDept , icon:'modify' },
                 { line:true },
                 { text: '删除', click: dept_delRow , icon:'delete' }
             ]
             });
	
//	var data = [];
//    
//    data.push({ id: 1, pid: 0, text: '1' });
//  data.push({ id: 2, pid: 1, text: '1.1' });
//  data.push({ id: 4, pid: 2, text: '1.1.2' });
//   data.push({ id: 5, pid: 2, text: '1.1.2' });      
//
//  data.push({ id: 10, pid: 8, text: 'wefwfwfe' });
//   data.push({ id: 11, pid: 8, text: 'wgegwgwg' });
//  data.push({ id: 12, pid: 8, text: 'gwegwg' });
//
//   data.push({ id: 6, pid: 2, text: '1.1.3', ischecked: true });
//  data.push({ id: 7, pid: 2, text: '1.1.4' });
//  data.push({ id: 8, pid: 7, text: '1.1.5' });
//  data.push({ id: 9, pid: 7, text: '1.1.6' });
	//lay=$("#dept_layout").ligerLayout({rightWidth:400,isRightCollapse: true});
	tree = $("#dept_tree").ligerTree({
			 //data : data,
			 url : 'wkrjsystem/wkrjDept/getDeptTree',
			 idFieldName : 'id',
			 slide : false,
			 parentIDFieldName : 'pid',
	
		 });
//		 treeManager = $("#dept_tree").ligerGetTreeManager();
//		 treeManager.collapseAll();
});

function itemclick(){
	
}
function dept_delRow(row){
//	var g = $("#dept_maingrid").ligerGetGridManager();
//	var r = g.getSelectedRow();
	var r = tree.getSelected();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一个节点!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystem/wkrjDept/delDept',
		        data: { id: r.data.attributes.dept_id },
		        cache: false,
		        async: false,
		        success: function (result) {
					try{
					    result = eval('('+result+')');
					}catch(e){}
					if (result.success) {
						$.ligerDialog.alert('删除成功!');
						tree.reload();
					} else {
						if (result.msg != null) {
							$.ligerDialog.alert('请先删除子节点!');
						} else {
							$.ligerDialog.alert('删除失败!');
						}
					}
				}
		    });
		}
	});
}

/**
 * 添加部门
 */
function dept_addDept(){
	
	var g = $("#dept_tree").ligerGetTreeManager();
	parent.$.ligerDialog.show({
		
		url : "system/dept/dept_add.jsp",
		width : 400,
		height : 350,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				console.dir(dialog.frame.liger.get("dept_addWindow_form"))
				var data = dialog.frame.liger.get("dept_addWindow_form").getData();
				
				if(dialog.frame.liger.get("dept_parent_id")){
					var dept_parent_id = dialog.frame.liger.get("dept_parent_id").getValue();
					data.dept_parent_id = dept_parent_id;
				}
				$.ajax({
		            url: "wkrjsystem/wkrjDept/addDept",
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	tree.reload();
		                        dialog.close();
		                    } else {
		                    	$.ligerDialog.alert(result.msg);
		                    }
		                 }
		           });
				return;
				
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
 * 部门修改
 */
function dept_editDept(){
//	var g = $("#dept_maingrid").ligerGetGridManager();
//	var r = g.getSelectedRow();
	var g = $("#dept_tree").ligerGetTreeManager();
	var r = tree.getSelected();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一个节点!');
		return;
	}
	var s = parent.$.ligerDialog.open({
		//target: $("#dept_updateWindow_form"),
		url : "system/dept/dept_update.jsp",
		width : 400,
		height : 350,
		data: {
            content:r.data.attributes
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {

				var data = dialog.frame.liger.get("dept_updateWindow_form").getData();
				if(dialog.frame.liger.get("dept_parent_id")){
					var dept_parent_id = dialog.frame.liger.get("dept_parent_id").getValue();
					data.dept_parent_id = dept_parent_id;
				}
				$.ajax({
		            url: "wkrjsystem/wkrjDept/updateDept?yId="+r.data.attributes.dept_id,
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	tree.reload();
		                    	//tree.loadData(-1, 'wkrjsystem/wkrjDept/getDeptTree', '-1');
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
