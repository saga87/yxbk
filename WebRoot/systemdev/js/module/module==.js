
var url="";

$(function(){
	
	$("#module_level").combobox({
		
		onSelect:function(v){
			
			if(1==v.value){
				$("#module_parent_id_div").show();//模块父节点ID的div
				$("#module_parent_id").combotree({required: true,url:'wkrjsystemdev/wkrjModule/getGridInfo.wk'});
			}else{
				$("#module_parent_id_div").hide();
				$("#module_parent_id").combotree({required: false,url:''});
			}
		}
	});
	
});

function saveModule(){
	
	//先验证一下表单
	if(!$('#module_addWindow_form').form('validate')){
		return false;	
	}
	
	var m =top.$("iframe[src='wkrjsystemdev/wkrjModule/getPage.wk']")[0].contentWindow;
	
	if(m.$("#uploadify").data('uploadify').queueData.queueLength>0){
		m.$('#uploadify').uploadify('upload', '*');
	}else{
		realAddModule("",1);
	}
	
}
/**
 * 真正保存
 * @param {} filename
 * @param {} isupdate
 */
function realAddModule(filename,isupdate){
	
	 if(""!=filename){
	 	var yFileName = $("input[name='module_icon']").val();
	 	$("input[name='module_icon']").val(filename);
	 }
	
	 $('#module_addWindow_form').form('submit', {
			url : url,
			onSubmit : function(param) {
				param.yFileName = yFileName;
				return $(this).form('validate');
			},
			success : function(result) {
				
				try{
            		result = eval('('+result+')');
            	}catch(e){
            		
            	}
				
				if (result.success) {
					$.messager.alert('操作提示', result.msg);
					$('#module_addWindow_form').form('clear');
					$('#module_addWindow').dialog('close');
					$("#module_treegrid").treegrid('reload');
					
				} else {
					$.messager.alert('操作提示', result.msg);
				}
			}
	});
	
}
	

function module_displayPic(value,row,index){
	if(null!=row.iconCls && "null"!=row.iconCls && ""!=row.iconCls){
		return '<img src="system/icons/'+row.iconCls+'.png"/>';
	}else{
		return '无';
	}
}

function module_displayPic_perm(value,row,index){
	if(null!=row.perm_icon && "null"!=row.perm_icon && ""!=row.perm_icon){
		return '<img src="system/icons/'+row.perm_icon+'.png"/>';
	}else{
		return '无';
	}
}

function module_delButton(value,row,index){
	
	var vals = new Array();
	vals[0] = row.id;
	vals[1] = row.module_level;
	vals[2] = row.module_icon;
	vals[3] = row.module_parent_id;
	
	var btn = '<a style="text-decoration:none;" onclick="module_delRow(\''+vals+'\')" href="javascript:void(0)">[删除]</a>'+
			  '<a style="text-decoration:none;" onclick="module_addPermission(\''+row.id+'\')" href="javascript:void(0)">[权限设置]</a>';
	
	return btn;
}

function module_addPermission(id){
	
	if($("#module_layout").layout('panel', 'east').panel('options').collapsed){
		$("#module_layout").layout('expand','east');
	}
	
	$("#module_qxdatagrid").datagrid('load',{module_id:id});
	
}

function module_delRow(row){
 	
    $.messager.confirm('信息提示','您确定要删除此记录吗?',function(r){
        if (r){
            $.post('wkrjsystemdev/wkrjModule/delModule.wk',
            	{id:row.split(",")[0],icon:row.split(",")[2]},
            	function(result){
            		
            	try{
            		result = eval('('+result+')');
            	}catch(e){
            		
            	}
                if (result.success){
                	$.messager.alert('信息提示', result.msg);
                	$('#module_treegrid').treegrid('remove', row.split(",")[0]);
                	$('#module_qxdatagrid').datagrid('load',{module_id:row.split(",")[0]});

                } else {
                   $.messager.alert('信息提示', result.msg);
                }
            });
        }
    });
	
}

/**
 * 添加菜单
 */
function module_addModule(){
	
	$('#module_addWindow').dialog('open').dialog('setTitle', '添加菜单');
	$('#module_addWindow_form').form('clear');//清空form
	url='wkrjsystemdev/wkrjModule/addModule.wk';
	$("input[name='module_is_display'][value=0]").attr("checked",true);//赋默认值
	$("#module_level").combobox("setValue","0");//赋默认值
	$("#module_icon").hide();
	$("#module_parent_id_div").hide();
	
	var row = $('#module_treegrid').treegrid('getSelected');
	if(row){
		
		$("#module_parent_id_div").show();
		$("#module_parent_id").combotree({required: true,url:'wkrjsystemdev/wkrjModule/getGridInfo.wk'});
		$("#module_parent_id").combotree('setValue',row.id);
		$("#module_level").combobox('setValue',1);
	}
}

/**
 * 模块修改
 */
function module_editModule(){
	
	var row = $('#module_treegrid').treegrid('getSelected');
	
    if (row){
        $('#module_addWindow').dialog('open').dialog('setTitle','修改菜单');
        
        $("#module_icon").show();
        
		if(row.module_level){
			$("#module_parent_id_div").show();
			//$("#module_parent_id_div").attr('class','Co');
			$("#module_parent_id").combotree({required: true,url:'wkrjsystemdev/wkrjModule/getGridInfo.wk'});
		}else{
			$("#module_parent_id_div").hide();
		}  
		
        if(row.module_is_display=="显示"){
        	row['module_is_display']=0;
        }else{
        	row['module_is_display']=1;
        }
        
        $('#module_addWindow_form').form('load',row);
        
        if(null!=row.module_icon && "null"!=row.module_icon && ""!=row.module_icon){
        	$("#module_icon").attr('src','system/icons/'+row.module_icon+'.png');
        }else{
        	$("#module_icon").hide();
        }
        
        url='wkrjsystemdev/wkrjModule/updateModule.wk';
    }else{
    	$.messager.alert('操作提示', '请选择您想修改的记录');
    }
	
}

/**
 * 添加权限
 */
function module_addPerm(){
	
	var row = $('#module_treegrid').treegrid('getSelected');
	
	if(!row){
		$.messager.alert('操作提示', '请先选择左侧菜单');
		return false;
	}
	
	$('#module_addPermissionWindow').dialog('open').dialog('setTitle', '添加权限');
	$('#module_addPermissionWindow_form').form('clear');//清空form
	
    if (row){
    	$("input[name='module_id']").val(row.id);
    }
	
	url='wkrjsystemdev/wkrjPermission/addPermission.wk';
	$("#perm_icon").hide();

}

/**
 * 权限修改
 */
function module_editPerm(){
	
	var rows = $('#module_qxdatagrid').datagrid('getSelections');
	
    if (rows.length>0){
    	
    	var row = rows[0];
    	
        $('#module_addPermissionWindow').dialog('open').dialog('setTitle','修改权限');
        
        $("#perm_icon").show();
        
        $('#module_addPermissionWindow_form').form('load',row);
        
        if(null!=row.perm_icon && "null"!=row.perm_icon && ""!=row.perm_icon){
        	$("#perm_icon").attr('src','system/icons/'+row.perm_icon+'.png');
        }else{
        	$("#perm_icon").hide();
        }
        
        url='wkrjsystemdev/wkrjPermission/updatePermission.wk';
    }else{
    	$.messager.alert('操作提示', '请选择您想修改的记录');
    }
	
}

/**
 * 删除权限
 */
function module_delPerm(){
	
	var row = $('#module_qxdatagrid').datagrid('getSelections');
	
    if (row.length>0){
       
    	var ids = new Array();
		var icons = new Array();
	
		for(var i=0;i<row.length;i++){
			ids.push(row[i].perm_id);
			if(null==row[i].perm_icon || "null"==row[i].perm_icon || ""==row[i].perm_icon){
				icons.push("zxh");//存入zxh是为了批量删除为空时好判断
			}else{
				icons.push(row[i].perm_icon);
			}
			
		}
    	
    	$.messager.confirm('信息提示','您确定要删除此记录吗?',function(r){
        if (r){
	            $.post('wkrjsystemdev/wkrjPermission/delPermission.wk',
//	            	{id:row.perm_id,icon:row.perm_icon},
	            	{id:ids.join(","),icon:icons.join(",")},
	            	function(result){
	            	
	            		try{
		            		result = eval('('+result+')');
		            	}catch(e){
		            		
		            	}
	            		
		                if (result.success){
		                	$.messager.alert('信息提示', result.msg);
		                	
		                	row = $('#module_treegrid').treegrid('getSelected');
		                	var leftId = "";
		                	if(row){
		                		leftId = row.id;
		                	}
		                	
		                	$('#module_qxdatagrid').datagrid('load',{module_id:leftId});
		                } else {
		                   $.messager.alert('信息提示', '删除失败');
		                }
	            });
        	}
   		 });
       
    }else{
    	$.messager.alert('操作提示', '请选择您想删除的记录');
    }
	
}

/**
 * 保存权限
 * @return {Boolean}
 */
function savePerm(){
	
	//先验证一下表单
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
	}
	
}

/**
 * 真正保存权限
 * @param {} filename
 * @param {} isupdate
 */
function realAddPerm(filename,isupdate){
	
	 if(""!=filename){
	 	$("input[name='perm_icon']").val(filename);
	 }
	
	 $('#module_addPermissionWindow_form').form('submit', {
			url : url,
			onSubmit : function(param) {
				
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert('操作提示', result.msg);
					$('#module_addPermissionWindow_form').form('clear');
					$('#module_addPermissionWindow').dialog('close');
					
					row = $('#module_treegrid').treegrid('getSelected');
                	var leftId = "";
                	if(row){
                		leftId = row.id;
                	}
					
					$("#module_qxdatagrid").datagrid('load',{module_id:leftId});
					
				} else {
					$.messager.alert('操作提示', result.msg);
				}
			}
	});
	
}

$('#module_qxdatagrid').datagrid({
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
});
	