
$(function(){
	
	$('#leftMenuTree_dev').tree(
	{		
			url:'wkrjsystemdev/wkrjModule/getLeftMenu.wk',
			onClick : function(node) {
				if (null!=node.attributes.url && "null"!=node.attributes.url && node.attributes.url != "") {
					
					var url_ = permissionsCheck(node.attributes.url);
					if(""==url_){
						$.messager.alert('操作提示', '没有此权限');
						return false;
					}else{
						openTab(url_, node.text, node.id,
								node.iconCls);
					}
				}
			},
			onLoadSuccess:function(node, data){
				
				if(""==data){
					alert("没有任何权限");
					window.location.href="systemdev/developer.jsp";
				}
				
				if(false==data.success){
					alert(data.msg);
					window.location.href="systemdev/developer.jsp";
				}
			}
	});
	
	
});

// 创建tab
function openTab(plugin, title, id, iconCls) {
	if ($('#mainTabs_dev').tabs('exists', plugin)) {
		$('#mainTabs_dev').tabs('select', plugin);
	} else {
		if ($('#mainTabs_dev').tabs('exists', title)) {
			$('#mainTabs_dev').tabs('select', title);
		} else {
			
			//按理说这个地方应该统一起来，但是由于时间原因，先不修改了，增加了判断  by zxh
			if(plugin.indexOf(".wk")>0){
				
				var content = '<iframe id="'+id+'" scrolling="no" frameborder="0" src="'+plugin+'" style="width:100%;height:100%;"></iframe>';  
        
		        $('#mainTabs_dev').tabs('add',{  
		            title:title,  
		            closable:true,  
		            content:content,  
		            iconCls:iconCls||'icon-default'  
		        });  
			}else{
			
				$('#mainTabs_dev').tabs('add', {
					title : title,
					border : false,
					iconCls : iconCls,
					href : "page/"+plugin+".jsp",
					closable : true,
					extractor : function(data) {
						
						data = $.fn.panel.defaults.extractor(data);
						var tmp = $('<div></div>').html(data);
						return data;
					}
				});
		   }
		}
	}
}
	
	
// 退出登录
function loginout() {
	//window.location.href = "wkrjsystem/wkrjlogin/loginout";
}

// 修改密码窗口
function password() {
	$('#passwordWindow_dev').dialog('open').dialog('setTitle', '修改密码');
}

// 修改密码
function updatePassword() {
	url = 'login/updatepassword';
	$('#passwordForm_dev').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.alert('操作提示', '修改成功');
				$('#passwordWindow').dialog('close');
			} else {
				$.messager.alert('操作提示', result.msg);
			}
		}
	});
}

		
function permissionsCheck(url) {
			
	var resultStr = '';
	
	$.ajax({
		url : 'wkrjsystemdev/wkrjLoginDev/permissionsCheck.wk',
		type : "POST",
		data:{
			"url":url
		},
		async : false,
		success : function(objJson) {
			if(objJson.success == true) {
				resultStr = url;
			}
		},
		dataType : "json"
	});
	
	return resultStr;
}

