function onSelect(note){
    var g = $("#user_maingrid").ligerGetGridManager();
    g.set({url:'wkrjsystem/wkrjUser/getUserList?deptId='+note.data.id});
    g.reload();
}

//根据条件检索
function searchUser() {
	var g = $("#user_maingrid").ligerGetGridManager();
	var searchUserRealname = $('#searchUserRealname').val();
	g.set({url:'wkrjsystem/wkrjUser/getUserList?userName='+searchUserRealname});
}


function showAllUser() {
	var g = $("#user_maingrid").ligerGetGridManager();
	g.set({url:'wkrjsystem/wkrjUser/getUserList'});
    g.reload();
}

var url="";
var lay;
var manager;
var perm;
var tree;
$(function(){
	
	tree = $("#user_dept_tree").ligerTree({
		checkbox: false, nodeWidth: 190,
		url : 'wkrjsystem/wkrjDept/getDeptTree',
		idFieldName : 'id',
		slide : false,
		onSelect: onSelect
	});
	lay=$("#user_layout").ligerLayout({leftWidth:220,isRightCollapse: true});

	
	
	manager = $("#user_maingrid").ligerGrid({
		url:'wkrjsystem/wkrjUser/getUserList',
        columns : [
        { display: '所属部门', name: 'dept_name', id:'dept_name',width: '30%', align: 'left' },
        { display: '账号', name: 'user_name', id:'user_name',width: '17%', align: 'left' },
        { display: '姓名', name: 'user_realname', id:'user_realname',width: '12%', align: 'left' },
        { display: '手机号', name: 'user_tel', id:'user_tel',width: '9%', align: 'left' },
        { display: '是否禁用', name: 'user_is_enable', id:'user_is_enable',width: '6%', align: 'left', render:user_is_enable },
        { display: '角色', name: 'user_role', id: 'user_role', width: '24%', align: 'left', render: showRoleName }
        ], height : '100%',
        width:'99.9%',
        usePager : true,
		rownumbers : true,
        alternatingRow : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : addUser,
				icon : 'add',
				id:'wkrjsystem/wkrjUser/addUser'
			}, {
				line : true
			}, {
				text : '修改',
				click : editUser,
				icon : 'modify',
				id:'wkrjsystem/wkrjUser/updateUser'
			}, {
				line : true
			}, {
				text : '删除',
				click : delUser,
				icon : 'delete',
				id:'wkrjsystem/wkrjUser/delUser'
			}, {
				text : '显示全部',
				click : showAllUser,
				icon : 'search',
				id:'wkrjsystem/wkrjUser/getUserList'
			}, {
				text : '禁用',
				click : disableUser,
				icon : 'candle',
				id:'wkrjsystem/wkrjUser/forbiddenUser'
			}, {
				text : '启用',
				click : enableUser,
				icon : 'ok',
				id:'wkrjsystem/wkrjUser/forbiddenUser'
			}, {
				text : '重置密码',
				click : resetPw,
				icon : 'lock',
				id:'wkrjsystem/wkrjUser/repeatPw'
			}]
		}
    });
	$("#pageloading").hide();
});


// 删除用户
function delUser(row){
	var g = $("#user_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录进行删除!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystem/wkrjUser/delUser',
		        data: { id: r.user_id },
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

//禁用用户
function disableUser(row){
	var g = $("#user_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择要禁用的用户!');
		return;
	}
	$.ligerDialog.confirm('确定要禁用此用户吗', function (param) {
		if (param) {
			$.ajax({
		        url: 'wkrjsystem/wkrjUser/forbiddenUser',
		        data: { id: r.user_id, flag: '1' },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('禁用成功!');
		        	  g.loadData();
		          } else {
		        	  $.ligerDialog.alert('禁用失败!');
		          }
		        }
		      });
		}
	})
}

//启用用户
function enableUser(row){
	var g = $("#user_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择要启用的用户!');
		return;
	}
	$.ligerDialog.confirm('确定要启用此用户吗', function (param) {
		if (param) {
			$.ajax({
		        url: 'wkrjsystem/wkrjUser/forbiddenUser',
		        data: { id: r.user_id, flag: '0' },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('启用成功!');
		        	  g.loadData();
		          } else {
		        	  $.ligerDialog.alert('启用失败!');
		          }
		        }
		      });
		}
	})
}

//重置密码成123
function resetPw(){
	
	var g = $("#user_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择要重置的用户!');
		return;
	}
	$.ligerDialog.confirm('你确定要重置密码吗,重置后密码变为123', function (param) {
		if (param) {
			$.ajax({
		        url: 'wkrjsystem/wkrjUser/repeatPw',
		        data: { id: r.user_id, flag: '0' },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  $.ligerDialog.alert('重置成功!');
		        	  g.loadData();
		          } else {
		        	  $.ligerDialog.alert('重置失败!');
		          }
		        }
		      });
		}
	});
}

/**
 * 添加用户
 */
function addUser(){
	var g = $("#user_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		url : "system/user/user_add.jsp",
		width : 500,
		height : 560,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("user_addWindow_form").getData();
				if (data.user_name == "" || data.user_name == null) {
					top.$.ligerDialog.alert("请输入账号");
					return;
				}
				if (data.user_password == "" || data.user_password == null) {
					top.$.ligerDialog.alert("请输入账号密码");
					return;
				}
				if (data.user_realname == "" || data.user_realname == null) {
					top.$.ligerDialog.alert("请输入姓名");
					return;
				}
				if (data.user_tel == "" || data.user_tel == null) {
					top.$.ligerDialog.alert("请输入手机号");
					return;
				}
				if(dialog.frame.liger.get("user_department_id")){
					var dept_id = dialog.frame.liger.get("user_department_id").getValue();
					data.dept_id = dept_id;
					if (data.dept_id == "" || data.dept_id == null) {
						top.$.ligerDialog.alert("请选择所属部门");
						return;
					}
				}
				if(dialog.frame.liger.get("userrolercombobox")){
					var user_role = dialog.frame.liger.get("userrolercombobox").getValue();
				}
				
				var role = "";
				var roles = user_role.split(";");
				for (var i = 0; i < roles.length; i++) {
					role += roles[i];
		            if (i < roles.length-1) {
		                role +=",";
		            }
				}
				data['user_role[0].role_id'] = role;
				$.ajax({
		            url: "wkrjsystem/wkrjUser/addUser",
		            data: data,
		            type: 'post',
		            dataType: 'json',
		            success: function(result){
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
		}, {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]
	});
}

/**
 * 模块修改
 */
function editUser(){
	var g = $("#user_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录进行修改!');
		return;
	}

	var s = parent.$.ligerDialog.open({
		url : "system/user/user_update.jsp",
		width : 500,
		height : 520,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("user_updateWindow_form").getData();
				if (data.user_name == "" || data.user_name == null) {
					top.$.ligerDialog.alert("请输入账号名称");
					return;
				}
				if (data.user_realname == "" || data.user_realname == null) {
					top.$.ligerDialog.alert("请输入真实姓名");
					return;
				}
				if (data.user_tel == "" || data.user_tel == null) {
					top.$.ligerDialog.alert("请输入手机号");
					return;
				}
				if(dialog.frame.liger.get("user_department_id")){
					var dept_id = dialog.frame.liger.get("user_department_id").getValue();
					data.dept_id = dept_id;
					if (data.dept_id == "" || data.dept_id == null) {
						top.$.ligerDialog.alert("请选择所属部门");
						return;
					}
				}
				data['user_role[0].role_id'] = dialog.frame.liger.get("userrolercombobox").getValue();
				$.ajax({
		            url: "wkrjsystem/wkrjUser/updateUser",
		            data: data,
		            type: 'post',
		            dataType: 'json',
		            success: function(result){
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
		}, {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]

	});
}

function checkSfz() {
	
	var sfz = $('#sjc_userview_sfz').val();
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	if (sfz!="") {
	    if (reg.test(sfz) === false) {  
	    	$.ligerDialog.alert('身份证输入不合法');
	        $('#sjc_userview_sfz').val('');
	        return  false;  
	    }  
    }
}

function checkTel(){
	
	var tel = $('#sjc_userview_tel').val();
	if(tel!=""){
		if(!(/^0?1[3|4|5|8][0-9]\d{8}$/.test(tel))){
			$.ligerDialog.alert('请输入正确的手机号');
			$('#sjc_userview_tel').val('');
			return;
		}
		
	}
	
}


function user_is_enable(rowdata, rowindex, value) {
	if (rowdata.user_is_enable == 0) {
		return '否';
	} else if(rowdata.user_is_enable == 1){
		return '是';
	}else{
		return '有问题';
	}
}

function showRoleName(rowdata, rowindex, value){
	var roleName="";
	if(rowdata.user_role.length>0){
		
		for(var i=0;i<rowdata.user_role.length;i++){
			roleName += rowdata.user_role[i].role_name;
			
			if(i<rowdata.user_role.length-1){
				roleName +=",";
			}
		}
	}
	return roleName;
}