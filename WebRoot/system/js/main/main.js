
$(function(){
	
	$("#main_layout").ligerLayout({
			leftWidth: 240,
			topHeight: 85,
	        space: 4
	});
		
	tab = $("#mainTabs").ligerTab({height:'100%'});
		
	$("#leftMenuTree").ligerTree({
		url:'wkrjsystem/wkrjMenu/getLeftMenu',
		checkbox:false,
		nodeWidth: 160,
		onClick: function(node) {
			
			if (null!=node.data.attributes.menu_url && "null"!=node.data.attributes.menu_url && node.data.attributes.menu_url != "") {
				var url_ = permissionsCheck(node.data.attributes.menu_url);
				if(""==url_){
					$.ligerDialog.alert('没有此权限');
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
	
	/*$.ajax({
        url: "wkrjsystem/reportInfoCheck/getNotCheckedList",
        //data: data,
        dataType:'json',
        type:'POST',
        success: function(result){
            //result = eval('('+result+')');
        	if (result.success) {
        		$.ligerDialog.alert('您有'+result.msg+'条信息未查看！');
                //dialog.close();
            } else {
            	//$.ligerDialog.alert(result.msg);
            }
        }
      });*/

	
});


// 创建tab
function openTab(plugin, title, id, iconCls) {
	tab = $("#mainTabs").ligerTab({height:'100%'});
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

function permissionsCheck(url) {
			
	var resultStr = '';
	
	$.ajax({
		url : 'wkrjsystem/wkrjlogin/permissionsCheck',
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
	
// 退出登录
function loginout() {
	//window.location.href = "wkrjsystem/wkrjlogin/loginout";
}

// 修改密码窗口
function password() {
	$.ligerDialog.open({ 
		url: 'system/updatepassword.jsp',
		height: 200,
		width: null, 
		buttons: [{ 
			text: '确定', 
			onclick: function (item, dialog) {
				
				var data = dialog.frame.liger.get("passwordForm").getData();
				$.ajax({
		            url: "wkrjsystem/wkrjlogin/updatepassword",
		            data: data,
		            dataType:'json',
		            type:'POST',
		            success: function(result){
	                    //result = eval('('+result+')');
	                    if (result.success) {
	                    	$.ligerDialog.alert(result.msg);
	                        dialog.close();
	                    } else {
	                    	$.ligerDialog.alert(result.msg);
	                    }
		            }
		          });
				
				
			}}, 
			{ text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
			} 
		}] 
	});
	//$('#passwordWindow_dev').dialog('open').dialog('setTitle', '修改密码');
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


