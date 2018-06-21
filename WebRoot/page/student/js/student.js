
var url="";
var lay;
var manager;
var perm;
$(function(){

	lay=$("#student_layout").ligerLayout({rightWidth:400,isRightCollapse: true});
	
	manager = $("#student_maingrid").ligerGrid({
		url:'wkrj/appstudent/getStudentList',
        columns: [
        { display: '学号', name: 'student_num', id: 'student_num', width: '9%', align: 'left' },
        { display: '姓名', name: 'name', id: 'name', width: '9%', align: 'left' },
        { display: '性别', name: 'sex', id: 'sex', width: '7%', align: 'left' },
        { display: '出生日期', name: 'birthdate', id: 'birthdate', width: '9%', align: 'left' },
        { display: '身份证', name: 'idcard', id: 'idcard', width: '11%', align: 'left' },
        { display: '年级', name: 'nj', id: 'nj', width: '8%', align: 'left' },
        { display: '级部', name: 'jb', id: 'jb', width: '8%', align: 'left' },
        { display: '班级', name: 'bj', id: 'bj', width: '8%', align: 'left' },
        { display: '身高', name: 'height', id: 'height', width: '7%', align: 'left' },
        { display: '体重', name: 'weight', id: 'weight', width: '7%', align: 'left' },
        { display: '邮箱', name: 'email', id: 'email', width: '9%', align: 'left' },
        { display: '注册时间', name: 'inputtime', id: 'inputtime', width: '8%', align: 'left' },
        ], 
        height: '100%',
        width:'100%',
        usePager : true,
		rownumbers : true,
        alternatingRow: true, 
        //tree:{columnId:'student_name',idField:'student_id',parentIDField:'student_parent_id'},
		toolbar : {
			items : [ {
				text : '增加',
				click : addStudent,
				icon : 'add',
				id:'wkrj/appstudent/addStudent'
			}, {
				line : true
			}, {
				text : '修改',
				click : editStudent,
				icon : 'modify',
				id:'wkrj/appstudent/updateStudent'
			}, {
				line : true
			}, {
				text : '删除',
				click : student_delRow,
				icon : 'delete',
				id:'wkrj/appstudent/delStudent'
			}, {
				line : true
			}, {
			text : '生成二维码',
			click : QRcode,
			icon : 'add',
			id:'wkrj/QRcode/generateQrcode'
		} ]
		}
    });
	
});

function QRcode(row){
	var g = $("#student_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	$.ligerDialog.confirm('确定要生成二维码吗', function (param) {
		if (param) {
			$.ajax({
				//type: "get",
				url: 'wkrj/QRcode/generateQrcode',
				data: { student_id: r.student_id },
				cache: false,
				async: false,
				success: function (result) {
					try{
						result = eval('('+result+')');
					}catch(e){
					}
					if (result.success) {
						top.$.ligerDialog.alert('生成成功!');
						g.loadData();
					} else {
						top.$.ligerDialog.alert('生成失败!');
					}
				}
			});
		}
	})
}
function student_delRow(row){
	var g = $("#student_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrj/appstudent/delStudent',
		        data: { id: r.student_id },
		        cache: false,
		        async: false,
		        success: function (result) {
		        	try{
		                result = eval('('+result+')');
		            }catch(e){
		            }
		          if (result.success) {
		        	  top.$.ligerDialog.alert('删除成功!');
		        	  g.loadData();
		          } else {
		        	  top.$.ligerDialog.alert('删除失败!');
		          }
		        }
		      });
		}
	})
}

/**
 * 添加菜单
 */
function addStudent(){
	var g = $("#student_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		//target: $("#student_addWindow"),
		url : "page/student/student_add.jsp",
		width : 500,
		height : 620,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("student_addWindow_form").getData();
				

				$.ajax({
		            url: "wkrj/appstudent/addStudent",
		            data: data,
		            type:'post',
		            success: function(result){
		            	
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	top.$.ligerDialog.alert(result.msg);
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
function editStudent(){
	var g = $("#student_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}

	var s = parent.$.ligerDialog.open({
		url : "page/student/student_update.jsp",
		width : 500,
		height : 600,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("student_updateWindow_form").getData();
				

				$.ajax({
		            url: "wkrj/appstudent/updateStudent",
		            data: data,
		            success: function(result){
		                    try{
		                        result = eval('('+result+')');
		                    }catch(e){
		                    }
		                    if (result.success) {
		                    	top.$.ligerDialog.alert(result.msg);
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
