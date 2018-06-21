
var url="";
var lay;
var manager;
var perm;
$(function(){

	lay=$("#module_layout").ligerLayout({rightWidth:560,isRightCollapse: true});
	
	manager = $("#maingrid").ligerGrid({
		url:'wkrjsystemdev/wkrjModule/getGridInfo.wk',
        columns: [
		{ display: '操作', isSort: false, width: '18%', render: function (rowdata, rowindex, value){
			var h = "<a style='text-decoration:none;' onclick='module_delRow(\""+rowindex+"\")' href='javascript:void(0)'>[删除]</a>" 
				+"&nbsp;<a href='javascript:module_addPermission(" + rowindex + ")'>[设置权限]</a>";
			return h;
			}
		},
        { display: '模块名称', name: 'module_name', id: 'module_name', width: '20%', align: 'left' },
        { display: '状态', render:changeStaus,name: 'module_is_display', id: 'module_is_display', width: '5%', align: 'left' },
        { display: '图标', name: 'iconCls', id:'iconCls',width: '5%', align: 'left' }, 
        { display: '菜单地址', name: 'module_url', width: '30%', align: 'left' }, 
        { display: '菜单顺序', name: 'module_order', id:'module_order',width: '20%', type: 'int', align: 'left' }
        ], height: '100%',
        width:'100%',
        usePager :false,
		rownumbers : true,
        alternatingRow: false, 
        tree:{columnId:'module_name',idField:'module_id',parentIDField:'module_parent_id'},
		toolbar : {
			items : [ {
				text : '增加',
				click : module_addModule,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : module_editModule,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : module_delRow,
				icon : 'delete'
			} ]
		}
    });
	
    
});

function changeStaus(rowdata, rowindex, value){
	
	if(rowdata.module_is_display==0){
		return "显示";
	}else{
		return "隐藏";
	}
	
}

$(function(){
	//$("#module_parent_id").ligerComboBox({ data: null, isMultiSelect: false, isShowCheckBox: false });
	 
    $("#module_level").ligerComboBox({
    	url:'wkrjsystemdev/wkrjModule/getGridInfo.wk', isMultiSelect: false,
        onSelected: function (newValue)
        {
        	
        	if(1==newValue){
        		
        		$("#module_parent_id_div").show();
        		
				$("#module_parent_id").ligerComboBox({
	                width : 180, 
	                selectBoxWidth: 200,
	                selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false,isMultiSelect :true,
	                tree: { checkbox:false,url: 'wkrjsystemdev/wkrjModule/getGridInfoNew.wk', ajaxType: 'post',idField:'id' }
            	});         		
        		
        	}else{
        		
        		$("#module_parent_id_div").hide();
				//$("#module_parent_id").ligerComboBox('setDisabled');
				$("#module_parent_id").ligerComboBox('setValue',"");
        	}
        	
        }
    });
	/*$("#module_level").combobox({
		
		onSelect:function(v){
			
			if(1==v.value){
				$("#module_parent_id_div").show();//模块父节点ID的div
				$("#module_parent_id").combotree({required: true,url:'wkrjsystemdev/wkrjModule/getGridInfo.wk'});
			}else{
				$("#module_parent_id_div").hide();
				$("#module_parent_id").combotree({required: false,url:''});
			}
		}
	});*/
	
});

function module_delRow(rowid){
	var g = $("#maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if(typeof(rowid)=="string"){
		r = manager.getRow(rowid);
	}
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystemdev/wkrjModule/delModule.wk',
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
 * 添加菜单
 */
function module_addModule(){
	
	var g = $("#maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	
	parent.$.ligerDialog.open({
		
		url : "systemdev/module/module_add.jsp",
		width : 500,
		height : 550,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				
				var m =dialog.frame;
				var len = 0;
				len = m.$("#uploadify").data('uploadify').queueData.queueLength;
				if(len>=2){
					$.messager.alert('操作提示', '只允许上传一个');
					return false;	
				}
				
				var data = dialog.frame.liger.get("module_addWindow_form").getData();
				
				if(dialog.frame.liger.get("module_parent_id")){
					var module_parent_id = dialog.frame.liger.get("module_parent_id").getValue();
					data.module_parent_id = module_parent_id;
				}
				
				
				if(len>0){
					m.$('#uploadify').uploadify('upload', '*');
				}else{
					realAddModule("",1,data,g,dialog);
				}
				
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
 * 真正保存
 * @param {} filename
 * @param {} isupdate
 */
function realAddModule(filename,isupdate,data,g,dialog){
	
	 if(""!=filename){
	 	var yFileName = $("input[name='module_icon']").val();
	 	$("input[name='module_icon']").val(filename);
	 }
	
	 $.ajax({
        url: "wkrjsystemdev/wkrjModule/addModule",
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
                    //window.location = "wkrjsystemdev/wkrjModule/getGridInfo.wk";
                } else {
                	top.$.ligerDialog.alert(result.msg);
                }
             }
       });
	
}


/**
 * 真正修改保存
 * @param {} filename
 * @param {} isupdate
 */
function realUpdateModule(data,g,dialog){
	
	 $.ajax({
	 	
        url: "wkrjsystemdev/wkrjModule/updateModule",
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
                	top.$.ligerDialog.alert(result.msg);
                }
             }
       });

}

/**
 * 模块修改
 */
function module_editModule(){
	var g = $("#maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}

	var s = parent.$.ligerDialog.open({
		//target: $("#module_updateWindow_form"),
		url : "systemdev/module/module_update.jsp",
		width : 500,
		height : 550,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var m =dialog.frame;
				var len = 0;
				len = m.$("#uploadify").data('uploadify').queueData.queueLength;
				if(len>=2){
					$.messager.alert('操作提示', '只允许上传一个');
					return false;	
				}
				
				var data = dialog.frame.liger.get("module_updateWindow_form").getData();
				if(dialog.frame.liger.get("module_parent_id")){
					var module_parent_id = dialog.frame.liger.get("module_parent_id").getValue();
					data.module_parent_id = module_parent_id;
				}
				if(dialog.frame.liger.get("module_is_display")){
					var module_is_display = dialog.frame.liger.get("module_is_display").getValue();
					data.module_is_display = module_is_display;
				}
				
				if(len>0){
					m.$('#uploadify').uploadify('upload', '*');
				}else{
					realUpdateModule(data,g,dialog);
				}
				
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

function module_addPermission(rowid){
	//打开右侧
	lay.setRightCollapse(false);
	
	//加载权限
	var row = manager.getRow(rowid);
	perm=$('#permissiongrid').ligerGrid({
		url:'wkrjsystemdev/wkrjPermission/getPermissionList.wk',
		parms :[{name:"module_id",value:row.module_id}],
		columns :[
			{ display: '图标', name: 'perm_icon_show', id: 'perm_icon', width: '20%', align: 'left' },
			{ display: '名称', name: 'perm_name', id: 'perm_name', width: '30%', align: 'left' },
			{ display: '权限', name: 'perm_flag', id: 'perm_flag', width: '50%', align: 'left' }
		],
		//pageSize :20,
		//pageSizeOptions :[20,30,50,100],
		usePager : true,
		alternatingRow : true,
		rownumbers : true,
		height : '99%',
		width : '100%',
		toolbar : {
			items : [ {
				text : '增加',
				click : module_addPerm,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : module_editPerm,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : module_delPerm,
				icon : 'delete'
			} ]
		}
	});
}

/**
 * 添加权限
 */
function module_addPerm(module_id) {//alert(module_id)
	
	var g = $("#maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	
	parent.$.ligerDialog.open({
		//target: $("#module_addWindow"),
		url : "systemdev/module/perm_add.jsp",
		data: {
            content:r
		},
		width : 500,
		height : 400,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {//alert(r.module_id);return;
				
				var m =dialog.frame;
				var len = 0;
				len = m.$("#perm_uploadify").data('uploadify').queueData.queueLength;
				if(len>=2){
					$.messager.alert('操作提示', '只允许上传一个');
					return false;	
				}
				
				var data = dialog.frame.liger.get("perm_addWindow_form").getData();
				data.module_id = r.module_id;
				var per = $("#permissiongrid").ligerGetGridManager();
				
				if(len>0){
					m.$('#perm_uploadify').uploadify('upload', '*');
				}else{
					realAddPerm(data,per,dialog);
				}
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
 * 权限修改
 */
function module_editPerm(){
	var g = $("#permissiongrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}

	var s = parent.$.ligerDialog.open({
		//target: $("#module_updateWindow_form"),
		url : "systemdev/module/perm_update.jsp",
		//data : JSON.stringify(r),
		width : 500,
		height : 400,
		data: {
                content:r
         },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {

				var m =dialog.frame;
				var len = 0;
				len = m.$("#perm_uploadify").data('uploadify').queueData.queueLength;
				if(len>=2){
					$.messager.alert('操作提示', '只允许上传一个');
					return false;	
				}
				
				var data = dialog.frame.liger.get("perm_updateWindow_form").getData();
				data.module_id = r.module_id;
				
				if(len>0){
					m.$('#perm_uploadify').uploadify('upload', '*');
				}else{
					realUpdatePerm(data,g,dialog);
				}

				
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
 * 删除权限
 */
function module_delPerm(){
	var g = $("#permissiongrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	console.dir(r);
	var ids = new Array();
	var icons = new Array();
	ids.push(r.perm_id);
	if(null==r.perm_icon || "null"==r.perm_icon || ""==r.perm_icon){
		icons.push("zxh");//存入zxh是为了批量删除为空时好判断
	}
//	for(var i=0;i<r.length;i++){
//		ids.push(r[i].perm_id);
//		if(null==r[i].perm_icon || "null"==r[i].perm_icon || ""==r[i].perm_icon){
//			icons.push("zxh");//存入zxh是为了批量删除为空时好判断
//		}else{
//			icons.push(r[i].perm_icon);
//		}
//	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystemdev/wkrjPermission/delPermission.wk',
		        data: { id:ids.join(","),icon:icons.join(",") },
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
 * 保存权限
 * @return {Boolean}
 */
function savePerm(){
	
	/*//先验证一下表单
	if(!$('#module_addPermissionWindow_form').form('validate')){
		return false;	
	}
	
	var m =top.$("iframe[src='wkrjsystemdev/wkrjModule/getPage.wk']")[0].contentWindow;
	var len = m.$("#perm_uploadify").data('uploadify').queueData.queueLength;
	
	if(len>=2){
		$.messager.alert('操作提示', '只允许上传一个');
		return false;	
	}
	if(len>0){
		m.$('#perm_uploadify').uploadify('upload', '*');
	}else{
		realAddPerm("",1);
	}*/
	
}

/**
 * 真正保存权限
 * @param {} filename
 * @param {} isupdate
 */
function realAddPerm(data,g,dialog){
	
	 $.ajax({
	 	
        url: "wkrjsystemdev/wkrjPermission/addPermission.wk",
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
                	top.$.ligerDialog.alert(result.msg);
                }
             }
       });
	
}

/**
 * 真正修改保存权限
 * @param {} filename
 * @param {} isupdate
 */
function realUpdatePerm(data,g,dialog){
	
	 $.ajax({
        url: "wkrjsystemdev/wkrjPermission/updatePermission.wk",
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
            	top.$.ligerDialog.alert(result.msg);
            }
           }
       });
	
}

/*$('#module_qxdatagrid').datagrid({
	onDblClickRow: function(rowIndex, rowData){
//		module_editPerm();
		var row = rowData;
        $('#module_addPermissionWindow').dialog('open').dialog('setTitle','修改权限');
        
        $("#perm_icon").show();
        
        $('#module_addPermissionWindow_form').form('load',row);
        
        if(null!=row.perm_icon && "null"!=row.perm_icon && ""!=row.perm_icon){
        	$("#perm_icon").attr('src','system/icons/'+row.perm_icon+'.png');
        }else{
        	$("#perm_icon").hide();
        }
        
        url='wkrjsystemdev/wkrjPermission/updatePermission.wk';
	    
	}
});


$('#module_treegrid').treegrid({
	onDblClickRow: function(row){
		module_editModule();
	}
});*/
	