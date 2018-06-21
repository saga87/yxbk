var tree = null;
var url="";
var lay;
var manager;
var perm;
$(function(){
	manager = $("#data_tree").ligerGrid({
        columns: [
            { display: '名称', name: 'text', id: 'text',  align: 'left' },
            { display: '显示序号', name: 'attributes.data_order', id: 'attributes.dept_order',  align: 'left' },
            { display: '是否首页显示', name: 'attributes.data_show', id: 'attributes.data_show',  align: 'left', render: function (rowdata, rowindex, value){
            	if (value=='1') {
            		return "<font color='green'>是</font>";
				}else if (value=='2') {
            		return "<font color='red'>否</font>";
				}else{
					return "<font color='red'>无</font>";
				}
            }},
            { display: '操作', id: 'id', width: '20%', align: 'center', render: function (rowdata, rowindex, value){
            	return "<a onclick='data_editData(\""+rowindex+"\")' href='javascript:void(0)'>[修改]</a>"+ 
            		"&nbsp;<a onclick='data_delRow(\""+rowindex+"\")' href='javascript:void(0)'>[删除]</a>";
            }}
        ], width: '99.9%', height: '100%',
     
        usePager :false,
        autoCheckboxEven:false,
		rownumbers : true,
		single  : true,
        alternatingRow: true,
        checkbox : true,
        url : 'data/WkrjDataController/getDataList',
        enabledEdit: true,
        tree:{columnId:'text',idField:'id',parentIDField:'pid'},
        toolbar : {
			items : [ {
				text : '增加',
				click : data_addData,
				icon : 'add',
				id:'data/WkrjDataController/addData'
			}, {
				text : '修改',
				click : data_editData,
				icon : 'modify',
				id:'data/WkrjDataController/updateData'
			}, {
				text : '删除',
				click : data_delRow,
				icon : 'delete',
				id:'data/WkrjDataController/delData'
			}, {
				text : '设置首页显示',
				click : settingShow,
				icon : 'ok',
				id:'data/WkrjDataController/updateDataShow'
			}, {
				text : '取消首页显示',
				click : settingHide,
				icon : 'candle',
				id:'data/WkrjDataController/updateDataShow'
			}  ]
		}
    }
    );
	
});
/**
 * 设置首页显示
 */
function settingShow(){
	var g = $("#data_tree").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行操作!');
		return;
	}
	var ides = new Array();
	for (var i = 0; i < r.length; i++) {
		ides.push(r[i].id);
	}
	console.log(ides)
	$.ligerDialog.confirm('确定要设置首页显示吗？', function (param) {
		if (param) {
				$.ajax({
			        url: "data/WkrjDataController/updateDataShow",
			        data: {data_id:ides.join(","),data_show:1},
			        type:"POST",
			        dataType:"json",
			        success: function(result){
			                if (result.success) {
			                	$.ligerDialog.alert(result.msg);
			                	g.reload();
			                } else {
			                	$.ligerDialog.alert(result.msg);
			                }
			             }
			       });
	    }
	});
}
/**
 * 取消首页显示
 */
function settingHide(){
	var g = $("#data_tree").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行操作!');
		return;
	}
	var ides = new Array();
	for (var i = 0; i < r.length; i++) {
		ides.push(r[i].id);
	}
	$.ligerDialog.confirm('确定要取消首页显示吗？', function (param) {
		if (param) {
				$.ajax({
			        url: "data/WkrjDataController/updateDataShow",
			        data: {data_id:ides.join(","),data_show:2},
			        type:"POST",
			        dataType:"json",
			        success: function(result){
			                if (result.success) {
			                	$.ligerDialog.alert(result.msg);
			                	g.reload();
			                } else {
			                	$.ligerDialog.alert(result.msg);
			                }
			             }
			       });
	    }
	});
}
function data_delRow(rowindex){

	var g = $("#data_tree").ligerGetGridManager();
	var r = g.getSelectedRow();
	if(typeof(rowindex)=='string'){
		r = g.getRow(rowindex);
	}
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一个节点!');
		return;
	}
	 
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        url: 'data/WkrjDataController/delData',
		        data: { id:r.id },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('删除成功!');
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
 * 添加数据
 */
function data_addData(){
	
	var g = $("#data_tree").ligerGetGridManager();
	var r = g.getSelectedRow();
	var content;
	if(r!=null){
		var content = r.attributes;
	}
	parent.$.ligerDialog.open ({
		url : "page/syg/data/data_add.jsp",
		width : 600,
		height : 500,
		data: {
			content:content
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				
				var data = dialog.frame.liger.get("data_addWindow_form").getData();
				if(dialog.frame.liger.get("data_parent_id")){
					var data_parent_id = dialog.frame.liger.get("data_parent_id").getValue();
					data.data_parent_id = data_parent_id;
					
				}
				
				if (data.data_name == null || data.data_name == "") {
					top.$.ligerDialog.alert("名称");
					return;
				}
				
				if (data.data_order == null || data.data_order == "") {
					top.$.ligerDialog.alert("请填写显示序号");
					return;
				}
				
				if (data.data_show == null || data.data_show == "") {
					top.$.ligerDialog.alert("请选择是否首页显示");
					return;
				}
				$.ajax({
		            url: "data/WkrjDataController/addData",
		            data: data,
		            type:"POST",
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
		                    	dialog.close();
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
 * 数据修改
 */
function data_editData(rindex){
	var g = $("#data_tree").ligerGetGridManager();
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
		url : "page/syg/data/data_update.jsp",
		width : 500,
		height : 400,
		data: {
			content:r.attributes
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {

				var data = dialog.frame.liger.get("data_updateWindow_form").getData();
				if(dialog.frame.liger.get("data_parent_id")){
					var data_parent_id = dialog.frame.liger.get("data_parent_id").getValue();
					data.data_parent_id = data_parent_id;
				}
				if (data.data_parent_id == null || data.data_parent_id == "") {
					data.data_parent_id = -1;
				}
				if (data.data_name == null || data.data_name == "") {
					top.$.ligerDialog.alert("名称");
					return;
				}
				if (data.data_order == null || data.data_order == "") {
					top.$.ligerDialog.alert("请填写显示序号");
					return;
				}
				
				if (data.data_show == null || data.data_show == "") {
					top.$.ligerDialog.alert("请选择是否首页显示");
					return;
				}
				$.ajax({
		            url: "data/WkrjDataController/updateData?yId="+r.id,
		            data: data,
		            type:"POST",
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
		                    	dialog.close();
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

