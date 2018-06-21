var icon_begin="system/icons/";
var icon_end=".png";
var url="";

$(function(){
	
	$("#module_tree").tree({
		onClick: function(node){
			
			$("#module_id").val(node.id);
			$("#module_name").val(node.text);
			$("#module_icon").attr("src",icon_begin+node.iconCls+icon_end);
			$("#module_views").val(node.attributes.module_url);
			$("#module_order").val(node.attributes.module_order);
			
			if(""==$("#menu_order").val()){
				$("#menu_order").numberbox('setValue',node.attributes.module_order);
			}
			if(""==$("#menu_name").val()){
				$("#menu_name").val(node.text);
			}
			if(""==$("#menu_other").val()){
				$("#menu_other").val(node.attributes.module_other);
			}
			if(""==$("#menu_icon").val()){
				$("#menu_icon").val(node.iconCls);
			}
			
			$("input[name='menu[0].menu_is_display']").each(function() { 
				if ($(this).val() == node.attributes.module_is_display) { 
				$(this).attr("checked", "checked"); 
			}});
			
			$('#menuForm').form('validate');
			
		}
	});
	
	$("#menu_tree").tree({
		onClick: function(node){
			
			var wkrjModule = node.attributes.wkrjModule;
			var parentId = node.attributes.menu_parent_id;
			if("-1"!=parentId && ""!=parentId){
				$("#menu_parent_id").combotree('setValue',parentId);
			}else{
				$("#menu_parent_id").combotree('setValue',"-1");
			}
			
			$("#module_id").val(wkrjModule.module_id);
			$("#module_name").val(wkrjModule.module_name);
			$("#module_icon").attr("src",icon_begin+wkrjModule.module_icon+icon_end);
			$("#module_views").val(wkrjModule.module_url);
			$("#module_order").val(wkrjModule.module_order);
			
		
			$("#menu_order").numberbox('setValue',node.attributes.menu_order);
			$("#menu_id").val(node.id);
			$("#menu_name").val(node.text);
			$("#menu_other").val(node.attributes.menu_other);
		    $("#menu_icon").val(node.iconCls);
	        $("input[name='menu[0].menu_is_display']").each(function() { 
				if ($(this).val() == node.attributes.menu_is_display) { 
				$(this).attr("checked", "checked"); 
			}});
	        
		    
		    if(null!=node.iconCls && "null"!=node.iconCls && ""!=node.iconCls){
		    	$("#imgmenu_icon").show();
	        	$("#imgmenu_icon").attr('src','system/icons/'+node.iconCls+'.png');
	        }else{
	        	$("#imgmenu_icon").hide();
	        }
			
	        
			$('#menuForm').form('validate');
			
		}
	});
	
});

function editMenu(){

	url="wkrjsystem/wkrjMenu/updateMenu";
	
	var nodes = $('#menu_tree').tree('getSelected');
	
	if(!nodes){
		$.messager.alert('操作提示', '请先选择左侧的用户菜单');
		return false;
	}
	
	//先验证一下表单
	if(!$('#menuForm').form('validate')){
		return false;	
	}
	
	var m =top.$("iframe[src='wkrjsystem/wkrjMenu/getPage.wk']")[0].contentWindow;
	
	if(m.$("#menu_uploadify").data('uploadify').queueData.queueLength>0){
		m.$('#menu_uploadify').uploadify('upload', '*');
	}else{
		realAddMenu("",1);
	}
}

function saveMenu(){
	
	url="wkrjsystem/wkrjMenu/addMenu";
	
	if(""==$("#module_id").val()){
		$.messager.alert('操作提示', '请先选择右侧的功能模块');
		return false;
	}
	
	//先验证一下表单
	if(!$('#menuForm').form('validate')){
		return false;	
	}
	
	var m =top.$("iframe[src='wkrjsystem/wkrjMenu/getPage.wk']")[0].contentWindow;
	
	if(m.$("#menu_uploadify").data('uploadify').queueData.queueLength>0){
		m.$('#menu_uploadify').uploadify('upload', '*');
	}else{
		realAddMenu("",1);
	}

}

/**
 * 真正保存
 * @param {} filename
 * @param {} isupdate
 */
function realAddMenu(filename,isupdate){
	
	 if(""!=filename){
	 	var yFileName = $("#menu_icon").val();
	 	$("#menu_icon").val(filename);
	 }
	
	 $('#menuForm').form('submit', {
			url : url,
			onSubmit : function(param) {
				param.yFileName = yFileName;
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert('操作提示', result.msg);
					$('#menuForm').form('reset');
					$("#menu_tree").tree('reload');
					$("#module_icon").removeAttr("src");
					$("#imgmenu_icon").hide();
					$("#menu_parent_id").combotree({url:'wkrjsystem/wkrjMenu/getTreeInfo'});
				} else {
					$.messager.alert('操作提示', '添加失败');
				}
			}
	});
	
}

/**
 * 删除菜单
 */
function delMenu(){

	url="wkrjsystem/wkrjMenu/delMenu";
	
	var nodes = $('#menu_tree').tree('getSelected');
	
	if(!nodes){
		$.messager.alert('操作提示', '请先选择左侧的用户菜单');
		return false;
	}
	
    $.messager.confirm('信息提示','您确定要删除此记录吗?',function(r){
    	if (r){
	        $.post('wkrjsystem/wkrjMenu/delMenu',
	        	{id:nodes.id,icon:nodes.iconCls},
	        	function(result){
	        		
	        	try{
            		result = eval('('+result+')');
            	}catch(e){
            		
            	}
	            if (result.success){
	            	$.messager.alert('信息提示', result.msg);
	            	$('#menu_tree').tree('remove', nodes.target);
					$('#menuForm').form('reset');	
					$("#module_icon").removeAttr("src");
					$("#imgmenu_icon").hide();
					$("#menu_parent_id").combotree({url:'wkrjsystem/wkrjMenu/getTreeInfo'});
					
	            } else {
	               $.messager.alert('信息提示', result.msg);
	            }
	        });
    	}
	});
}