function onSelect(note) {
    //alert('onSelect:' + note.data.id);
    var g = $("#user_maingrid").ligerGetGridManager();
    //g.reload("wkrjsystemdev/wkrjUserDev/getUserList.wk?deptId="+note.data.id);
    //g.loadData("wkrjsystemdev/wkrjUserDev/getUserList.wk", {deptId:note.data.id});
    g.set({url:'wkrjsystemdev/wkrjUserDev/getUserList.wk?deptId='+note.data.id});
    g.reload();
}

function searchUser() {
	var g = $("#user_maingrid").ligerGetGridManager();
	var searchUserRealname = $('#searchUserRealname').val();
	g.set({url:'wkrjsystemdev/wkrjUserDev/getUserList.wk?userName='+searchUserRealname});
    g.reload();
}

function showAllUser() {
	var g = $("#user_maingrid").ligerGetGridManager();
    g.reload();
}

var url="";
var lay;
var manager;
var perm;
var tree;
$(function(){
	tree = $("#user_dept_tree").ligerTree({
		//data : data,
		checkbox: false, nodeWidth: 190,
		url : 'wkrjsystem/wkrjDept/getDeptTree?',
		idFieldName : 'id',
		slide : false,
		//parentIDFieldName : 'pid',
		onSelect: onSelect
	 });
	lay=$("#userdev_layout").ligerLayout({leftWidth:220,isRightCollapse: true});
	
	manager = $("#user_maingrid").ligerGrid({
		url:'wkrjsystemdev/wkrjUserDev/getUserList.wk',
        columns : [
        /*{ display: '用户', name: 'user_id', id: 'user_id', width: '9%', align: 'left' },*/
        { display: '角色', name: 'user_role', id: 'user_role', width: '12%', align: 'left', render: showRoleName },
        { display: '用户名', name: 'user_name', id:'user_name',width: '9%', align: 'left' },
        { display: '部门', name: 'dept_id', id:'dept_id',width: '9%', align: 'left', render: getBmName },
        { display: '岗位', name: 'station_id', id:'station_id',width: '9%', align: 'left', render: getStationName },
        { display: '真实姓名', name: 'user_realname', id:'user_realname',width: '9%', align: 'left' },
        { display: '身份证号', name: 'user_card', id:'user_card',width: '16%', align: 'left' },
        { display: '电话', name: 'user_tel', id:'user_tel',width: '9%', align: 'left' },
        { display: '是否禁用', name: 'user_is_enable', id:'user_is_enable',width: '9%', align: 'left', render:user_is_enable },
        { display: '注册时间', name: 'user_inputtime', id:'user_inputtime',width: '9%', align: 'left' },
        { display: '上次登录时间', name: 'user_last_time', id:'user_last_time',width: '9%', align: 'left' }
        ], height : '100%',
        width:'100%',
        usePager : true,
		rownumbers : true,
        alternatingRow : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : addUser,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : editUser,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : delUser,
				icon : 'delete'
			}, {
				text : '显示全部',
				click : showAllUser,
				icon : 'search'
			}, {
				text : '禁用',
				click : disableUser,
				icon : 'candle'
			}, {
				text : '启用',
				click : enableUser,
				icon : 'ok'
			}, {
				text : '重置密码',
				click : resetPw,
				icon : 'lock'
			}/*, {
				text : '取消微信绑定',
				click : cancelWeixin,
				icon : 'delete'
			}*/]
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
		        url: 'wkrjsystemdev/wkrjUserDev/delUser',
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
		        //type: "get",
		        url: 'wkrjsystemdev/wkrjUserDev/forbiddenUser.wk',
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
		        //type: "get",
		        url: 'wkrjsystemdev/wkrjUserDev/forbiddenUser.wk',
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
		        //type: "get",
		        url: 'wkrjsystemdev/wkrjUserDev/repeatPw.wk',
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
	})
}

/**
 * 添加用户
 */
function addUser(){
	var g = $("#user_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		//target: $("#user_addWindow"),
		url : "systemdev/user/user_add.jsp",
		width : 500,
		height : 640,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("user_addWindow_form").getData();
				if (data.user_name == "" || data.user_name == null) {
					top.$.ligerDialog.alert("请输入账号名称");
					return;
				}
				if (data.user_realname == "" || data.user_realname == null) {
					top.$.ligerDialog.alert("请输入真实姓名");
					return;
				}
				if (data.user_card == "" || data.user_card == null) {
					top.$.ligerDialog.alert("请输入身份证号");
					return;
				}
				if(dialog.frame.liger.get("station_id")){
					var station_id = dialog.frame.liger.get("station_id").getValue();
					data.station_id = station_id;
				}
				if(dialog.frame.liger.get("user_department_id")){
					var dept_id = dialog.frame.liger.get("user_department_id").getValue();
					data.dept_id = dept_id;
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
		            url: "wkrjsystemdev/wkrjUserDev/addUser",
		            data: data,
		            type: 'post',
		            dataType: 'json',
		            success: function(result){
		                    /*try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }*/
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
		url : "systemdev/user/user_update.jsp",
		width : 500,
		height : 640,
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
				if (data.user_card == "" || data.user_card == null) {
					top.$.ligerDialog.alert("请输入身份证号");
					return;
				}
				if(dialog.frame.liger.get("station_id")){
					var station_id = dialog.frame.liger.get("station_id").getValue();
					data.station_id = station_id;
				}
				if(dialog.frame.liger.get("user_department_id")){
					var dept_id = dialog.frame.liger.get("user_department_id").getValue();
					data.dept_id = dept_id;
				}
				data['user_role[0].role_id'] = dialog.frame.liger.get("userrolercombobox").getValue();
				$.ajax({
		            url: "wkrjsystemdev/wkrjUserDev/updateUser",
		            data: data,
		            type: 'post',
		            dataType: 'json',
		            success: function(result){
		                    /*try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }*/
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

function getBmName(rowdata, rowindex, value){
	
	var deptname="";
	
	$.ajax({  
        url : "wkrjsystemdev/wkrjUserDev/getDeptNameById.wk",  
        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        type : "POST",
        data:{deptId:rowdata.dept_id},
        dataType : "json",  
        success : function(res) {  
        	if(res.obj){
           		deptname = res.obj.dept_name;
           	}
        }  
    });
    
     return deptname;
}

function getStationName(rowdata, rowindex, value){
	
	var stationName="";
	
	$.ajax({  
        url : "wkrjsystemdev/wkrjUserDev/getStationById.wk",  
        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        type : "POST",
        data:{stationId:rowdata.station_id},
        dataType : "json",  
        success : function(res) {
        	if(res.obj){
           		stationName = res.obj.station_name;
           	}
        }  
    });
	
	return stationName;
}

function user_is_enable(value, row, index) {
	if (row == 0) {
		return '否';
	} else if(row == 1){
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