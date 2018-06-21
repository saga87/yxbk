
$(function(){
	
	$("#leftMenuTree_dev").ligerTree({		
		url:'wkrjsystemdev/wkrjModule/getLeftMenu.wk',
		checkbox:false,
		nodeWidth : 160,
		onClick: function(node) {
			if (null!=node.data.attributes.url && "null"!=node.data.attributes.url && node.data.attributes.url != "") {
				var url_ = permissionsCheck(node.data.attributes.url);
				if(""==url_){
					$.messager.alert('操作提示', '没有此权限');
					return false;
				}else{
					openTab(url_, node.data.text, node.data.id, node.iconCls);
				}
			}
		},
		onSuccess:function(node){
			if(""==node||node==null){
				alert("没有任何权限");
				window.location.href="systemdev/developer.jsp";
			}
			/*if(false==node.data.success){
				window.location.href="systemdev/developer.jsp";
			}*/
		}
	});
	
	
});

// 创建tab
function openTab(plugin, title, id, iconCls) {
	tab = $("#mainTabs_dev").ligerTab({height:'100%'});
	if(tab.isTabItemExist(id)){
		tab.selectTabItem(id);
	}else{
		if(plugin.indexOf(".wk")>0){
			tab.addTabItem({
                tabid: id,
                text: title,
                url: plugin
            });
		}else{
			tab.addTabItem({
                tabid: id,
                text: title,
                url: "page/"+plugin+".jsp"
            });
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

