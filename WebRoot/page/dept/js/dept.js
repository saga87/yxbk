var tree = null;
var url="";
var lay;
var manager;
var perm;
$(function(){
	
	manager = $("#dept_tree").ligerGrid({
        columns: [
            { display: '部门名称', name: 'text', id: 'text', width: '55%', align: 'left' },
            { display: '顺序', name: 'attributes.dept_order', id: 'dept_order', width: '10%', align: 'left' },
            { display: '备注', name: 'attributes.dept_other', id: 'dept_other', width: '10%', align: 'left' },
            { display: '操作', id: 'id', width: '20%', align: 'center', render: function (rowdata, rowindex, value){
            	return "<a onclick='dept_editDept(\""+rowindex+"\")' href='javascript:void(0)'>[修改]</a>"+ 
            		"&nbsp;<a onclick='dept_delRow(\""+rowindex+"\")' href='javascript:void(0)'>[删除]</a>";
            }}
        ], width: '99.9%', height: '100%',
        usePager :false,
		rownumbers : false,
        alternatingRow: false,
        url : 'wkrjsystem/wkrjDept/getDeptAndSchoolGridList',
        alternatingRow: false, enabledEdit: true,
        tree:{columnId:'text',idField:'id',parentIDField:'pid'},
        toolbar : {
			items : [ {
				text : '增加',
				click : dept_addDept,
				icon : 'add',
				id:'wkrjsystem/wkrjDept/addDept'
			}, {
				line : true
			}, {
				text : '修改',
				click : dept_editDept,
				icon : 'modify',
				id:'wkrjsystem/wkrjDept/updateDept'
			}, {
				line : true
			}, {
				text : '删除',
				click : dept_delRow,
				icon : 'delete',
				id:'wkrjsystem/wkrjDept/delDept'
			} ]
		}
    }
    );
	
});

function dept_delRow(rowindex){

	//var r = tree.getSelected();
	var g = $("#dept_tree").ligerGetGridManager();
	var r = g.getSelectedRow();
	if(typeof(rowindex)=='string'){
		r = g.getRow(rowindex);
	}
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一个节点!');
		return;
	}
	if(r.type==undefined||r.type!="dept"){
		$.ligerDialog.alert('您不能在此删除学校信息!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystem/wkrjDept/delDept',
		        //data: { id: r.data.attributes.dept_id },
		        data: { id: r.id },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('删除成功!');
		        	  //tree.reload();
		        	  g.reload();
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
	})
}

/**
 * 添加部门
 */
function dept_addDept(){
	
	//var g = $("#dept_tree").ligerGetTreeManager();
	var g = $("#dept_tree").ligerGetGridManager();
	var r = g.getSelectedRow();
	var content;
	if(r!=null){
		var content = r.attributes;
	}
	parent.$.ligerDialog.open ({
		url : "page/dept/dept_add.jsp",
		width : 500,
		height : 400,
		data: {
			content:content
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				
				var data = dialog.frame.liger.get("dept_addWindow_form").getData();
				
				if(dialog.frame.liger.get("dept_parent_id")){
					var dept_parent_id = dialog.frame.liger.get("dept_parent_id").getValue();
					data.dept_parent_id = dept_parent_id;
				}
				if (data.dept_name == null || data.dept_name == "") {
					top.$.ligerDialog.alert("请输入部门名称");
					return;
				}
//				if (data.dept_parent_id == null || data.dept_parent_id == "") {
//					top.$.ligerDialog.alert("请选择上级部门");
//					return;
//				}
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
		                    	manager.reload();
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
function dept_editDept(rindex){
	var g = $("#dept_tree").ligerGetGridManager();
	var r;
	if(typeof(rindex)=='string'){
		r = g.getRow(rindex);
	}else{
		r = g.getSelectedRow();
	}
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一个节点!');
		return;
	}
	var s = parent.$.ligerDialog.open({
		url : "page/dept/dept_update.jsp",
		width : 500,
		height : 400,
		data: {
			content:r.attributes
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {

				var data = dialog.frame.liger.get("dept_updateWindow_form").getData();
				if(dialog.frame.liger.get("dept_parent_id")){
					var dept_parent_id = dialog.frame.liger.get("dept_parent_id").getValue();
					data.dept_parent_id = dept_parent_id;
				}
				if (data.dept_parent_id == null || data.dept_parent_id == "") {
					data.dept_parent_id = -1;
				}
				if (data.city_or_county == 1) {
					$("#fenguanlingdao_div").remove();
				} else {
					$("#fenguanjuzhang_div").remove();
				}
				$.ajax({
		            url: "wkrjsystem/wkrjDept/updateDept?yId="+r.id,
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	g.reload();
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

function safePerson_delRow(row){

	var g = $("#safePerson_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystem/wkrjDept/delSafePerson',
		        //data: { id: r.data.attributes.dept_id },
		        data: { id: r.safe_person_id },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('删除成功!');
		        	  //tree.reload();
		        	  g.reload();
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
	})
}

/**
 * 添加安全人员
 */
function addSafePerson(dept_id){
	var s = $("#safePerson_maingrid").ligerGetGridManager();
//	var g = $("#dept_tree").ligerGetGridManager();
//	var r = g.getSelectedRow();
//	var content;
//	if(r!=null){
//		var content = r.attributes;
//	}
	parent.$.ligerDialog.open ({
		
		url : "page/dept/safePerson_add.jsp",
		width : 500,
		height : 380,
		data: {
			content:dept_id
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				
				var data = dialog.frame.liger.get("safePerson_addWindow_form").getData();
				
				if(dialog.frame.liger.get("person_dept_id")){
					var dept_id = dialog.frame.liger.get("person_dept_id").getValue();
					data.dept_id = dept_id;
				}
				if (data.dept_id == null || data.dept_id == "") {
					top.$.ligerDialog.alert("请选择部门");
					return;
				}
				$.ajax({
		            url: "wkrjsystem/wkrjDept/addSafePerson",
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	s.reload();
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
 * 安全人员修改
 */
function editSafePerson(){

	var g = $("#safePerson_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}console.info(r)
	var s = parent.$.ligerDialog.open({
		url : "page/dept/safePerson_update.jsp",
		width : 500,
		height : 380,
		data: {
            //content:r.data.attributes
			content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {

				var data = dialog.frame.liger.get("safePerson_updateWindow_form").getData();
				if(dialog.frame.liger.get("person_dept_id")){
					var dept_id = dialog.frame.liger.get("person_dept_id").getValue();
					data.dept_id = dept_id;
				}
				$.ajax({
		            url: "wkrjsystem/wkrjDept/updateSafePerson?safe_person_id="+r.safe_person_id,
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	$.ligerDialog.alert(result.msg);
		                    	g.reload();
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
