
var url="";
var lay;
var manager;
var perm;
var icon_begin="system/icons/";
var icon_end=".png";

$(function(){

	lay=$("#user_layout").ligerLayout({leftWidth:300,rightWidth:300,isRightCollapse: false});
	
	$("#pageloading").hide();
	
	var dataGrid = [
               { id: 0, name: '显示' },
               { id: 1, name: '隐藏' }
    ]; 
    $("#menu_radio").ligerRadioList({
         data: dataGrid,
         textField: 'name'
     });
	
	$("#menu_uploadify").uploadify({
	 'buttonText' : '请选择',  
     'height' : 30,  
     'swf' : 'system/js/uploadfy/uploadify.swf',  
     'uploader' : '../../wkrjsystem/wkrjMenu/uploadPic',  
     'width' : 120,  
     'auto':false,  
     'multi':false,//单选
     'fileTypeExts':'*.png',
     'fileObjName'   : 'menu_icons',  
     'onUploadSuccess' : function(file, data, response) {  
     	 
     	  var d = eval('('+data+')');
     	  var d = eval('('+d+')');
     	  var filename = d.filename;
     	  
     	  $("#imgmenu_icon").attr("src",icon_begin+filename+icon_end);
     	  
     	  realAddMenu(filename);
     }  
	});
	
	$("#menu_parent_id").ligerComboBox({
	           width : 180,
	           selectBoxWidth: 200,
	           selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: true, isShowCheckBox: true,
	           tree: { checkbox: false, url: 'wkrjsystem/wkrjMenu/getTreeInfo', ajaxType: 'post', idField: 'id', idFieldName: 'id' }
	});
	
	//右侧列表
	$('#systemmenu').ligerTree({		
			url:'wkrjsystem/wkrjMenu/getGridInfo',
			checkbox: false,
			nodeWidth : 220,
			onSelect : function (data,target)
             { 
             	 node = data.data;
              
                $("#module_id").val(node.id);
				$("#module_name").val(node.text);
				$("#module_icon").attr("src",icon_begin+node.iconCls+icon_end);
				$("#module_views").val(node.attributes.module_url);
				$("#module_order").val(node.attributes.module_order);
				
				
				$("#menu_order").ligerSpinner('setValue',node.attributes.module_order);
				$("#menu_name").val(node.text);
				$("#menu_other").val(node.attributes.module_other);
				$("#menu_icon").val(node.iconCls);
				
				var is_hidden=0;
			    if(node.attributes.module_is_display){
			    	is_hidden=1
			    }
			    $("#menu_radio").ligerRadio('setValue',is_hidden);
				/*$("input[name='menu[0].menu_is_display']").each(function() {
					if ($(this).val() == node.attributes.module_is_display) { 
						$(this).attr("checked", "checked"); 
					}
				});*/
				
             }

	});
	
	//左侧列表
	$('#usermenu').ligerTree({		
			url:'wkrjsystem/wkrjMenu/getTreeInfo',
			checkbox: false,
			nodeWidth : 220,
			onSelect : function (data,target)
             { 
             	 node = data.data;
            
                var wkrjModule = node.attributes.wkrjModule;
				var parentId = node.attributes.menu_parent_id;
				
				if("-1"!=parentId && ""!=parentId){
					$("#menu_parent_id").ligerComboBox('setValue',parentId);
				}else{
					$("#menu_parent_id").ligerComboBox('setValue',"-1");
					//$("#menu_parent_id").ligerComboBox('setText',"");
				}
				
				$("#module_id").val(wkrjModule.module_id);
				$("#module_name").val(wkrjModule.module_name);
				$("#module_icon").attr("src",icon_begin+wkrjModule.module_icon+icon_end);
				$("#module_views").val(wkrjModule.module_url);
				$("#module_order").val(wkrjModule.module_order);
				
			
				$("#menu_order").ligerSpinner('setValue',node.attributes.menu_order);
				$("#menu_id").val(node.id);
				$("#menu_name").val(node.text);
				$("#menu_other").val(node.attributes.menu_other);
			    $("#menu_icon").val(node.iconCls);
		        /*$("input[name='menu[0].menu_is_display']").each(function() { 
					if ($(this).val() == node.attributes.menu_is_display) { 
					$(this).attr("checked", "checked"); 
				}});*/
			    var is_hidden=0;
			    if(node.attributes.menu_is_display){
			    	is_hidden=1
			    }
			    $("#menu_radio").ligerRadio('setValue',is_hidden);
		        
			    
			    if(null!=node.iconCls && "null"!=node.iconCls && ""!=node.iconCls){
			    	$("#imgmenu_icon").show();
		        	$("#imgmenu_icon").attr('src','system/icons/'+node.iconCls+'.png');
		        }else{
		        	$("#imgmenu_icon").hide();
		        }
	        
				
             }
	});
	
	//添加按钮
	$("#addbtn").ligerButton({
        	width : 50,
            icon: 'plug-in/ligerui/ligerUI/skins/icons/add.gif',
            click: function ()
            {
               
            	url="wkrjsystem/wkrjMenu/addMenu";
	
				if(""==$("#module_id").val()){
					$.ligerDialog.alert('请先选择右侧的功能模块');
					return false;
				}
				
				var len = $("#menu_uploadify").data('uploadify').queueData.queueLength;
				if(len>=2){
					$.ligerDialog.alert('只允许上传一个');
					return false;	
				}
				
				if($("#menu_uploadify").data('uploadify').queueData.queueLength>0){
					$('#menu_uploadify').uploadify('upload', '*');
				}else{
					realAddMenu("");
				}
            	
            }
     });
	//修改按钮
	$("#updatebtn").ligerButton({
        	width : 50,
            icon: 'plug-in/ligerui/ligerUI/skins/icons/modify.gif',
            click: function ()
            {
	
            	url="wkrjsystem/wkrjMenu/updateMenu";
				var nodes = $('#usermenu').ligerTree('getSelected');
				
				if(!nodes){
					$.ligerDialog.alert('请先选择左侧的用户菜单');
					return false;
				}
				
				var len = $("#menu_uploadify").data('uploadify').queueData.queueLength;
				if(len>=2){
					$.ligerDialog.alert('只允许上传一个');
					return false;	
				}
				
				if($("#menu_uploadify").data('uploadify').queueData.queueLength>0){
					$('#menu_uploadify').uploadify('upload', '*');
				}else{
					realAddMenu("");
				}
            }
     });
	//删除按钮
	$("#delbtn").ligerButton({
        	width : 50,
            icon: 'plug-in/ligerui/ligerUI/skins/icons/delete.gif',
            click: function ()
            {
            	
            	
                url="wkrjsystem/wkrjMenu/delMenu";
	
				var nodes = $('#usermenu').ligerTree('getSelected');
				if(!nodes){
					$.ligerDialog.alert('请先选择左侧的用户菜单');
					return false;
				}
				
			    $.ligerDialog.confirm('您确定要删除此记录吗?',function(r){
			    	if (r){
				        $.post('wkrjsystem/wkrjMenu/delMenu',
				        	{id:nodes.data.id,icon:nodes.data.iconCls},
				        	function(result){
				        		
				            	result = eval('('+result+')');
				            
					            if (result.success){
					            	$.ligerDialog.alert(result.msg);
									$("#menuForm").ligerForm().setData('');
									$("#module_icon").removeAttr("src");
									$("#imgmenu_icon").hide();
									$('#usermenu').ligerTree({url:'wkrjsystem/wkrjMenu/getTreeInfo',checkbox: false});
									$("#menu_parent_id").ligerComboBox({
							           width : 180,
							           selectBoxWidth: 200,
							           selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false, isMultiSelect: true, isShowCheckBox: true,
							           tree: { checkbox: false, url: 'wkrjsystem/wkrjMenu/getTreeInfo', ajaxType: 'post', idField: 'id', idFieldName: 'id' }
									});
									
					            } else {
					              $.ligerDialog.alert(result.msg);
					            }
				        });
			    	}
				});
            }
     });
	
});

/**
 * 真正保存
 * @param {} filename
 * @param {} isupdate
 */
function realAddMenu(filename){
	
	 if(""!=filename){
	 	var yFileName = $("#menu_icon").val();
	 	$("#menu_icon").val(filename);
	 }
	
	 var data = liger.get("menuForm").getData();
	 if(liger.get("menu_parent_id")){
		var module_parent_id = liger.get("menu_parent_id").getValue();
		data['menu[0].menu_parent_id'] = module_parent_id;
	 }
	 
	 if(liger.get("menu_radio")){
		var module_is_display =liger.get("menu_radio").getValue();
		data['menu[0].menu_is_display'] = module_is_display;
	}
	
	if(""==data['menu[0].menu_parent_id']){
		data['menu[0].menu_parent_id']="-1";
	}
	 
	 $.ajax({
        url: url,
        data: data,
        type:'post',
        success: function(result){
                
                result = eval('('+result+')');
                
                if (result.success) {
                	$.ligerDialog.alert(result.msg);
                	$('#usermenu').ligerTree({url:'wkrjsystem/wkrjMenu/getTreeInfo',checkbox: false});
                } else {
                	$.ligerDialog.alert(result.msg);
                }
             }
       });
}