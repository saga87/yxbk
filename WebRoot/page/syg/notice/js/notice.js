var url="";
var manager;
$(function(){
	lay=$("#notice_layout").ligerLayout({});
	manager = $("#notice_maingrid").ligerGrid({
		url:'notice/WkrjNoticeController/listNotice',
        columns: [
        { display: '标题', name: 'notice_title',  align: 'center' },
        { display: '内容', name: 'notice_content',   align: 'center' },
        { display: '发布时间', name: 'addtime',   align: 'center'}
        ], height: '100%',
        width:'99.9%',
        usePager :true,
        alternatingRow: true,
        checkbox : true,
		rownumbers : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : noticeManage_add,
				icon : 'add',
				id:'notice/WkrjNoticeController/addNotice'
			}, {
				line : true
			}, {
				text : '编辑',
				click : noticeManage_edit,
				icon : 'modify',
				id:'notice/WkrjNoticeController/updateNotice'
			}, {
				line : true
			}, {
				text : '删除',
				click : noticeManage_delRow,
				icon : 'delete',
				id:'notice/WkrjNoticeController/deleNotice'
			},{
				text : '查看',
				click : check,
				icon : 'search',
				id:'notice/WkrjNoticeController/listNotice'
			}]
		},
		onDblClickRow : function (data, rowindex, rowobj) {
				check(data);
		} 
    });
    	
});
/*
 * 查看信息详情
 */
function check(rindex){
	var r,g = $("#notice_maingrid").ligerGetGridManager();
	if(rindex.length<21){
		r = g.getRow(rindex);
	}else{
		r=g.getSelectedRow();
	}
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条查看信息!');
		return;
	}
	parent.$.ligerDialog.open({
		url : "page/syg/notice/notice_dealer.jsp",
		width : 750,
		height : 450,
		isResize :true,
		data: {
            content:r
		},
		buttons : [ {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]
	});
}


function noticeManage_delRow(row){
	var g = $("#notice_maingrid").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行删除!');
		return;
	}
	var ides = new Array();
	for (var int = 0; int < r.length; int++) {
		ides.push(r[int].notice_id);
	}
	$.ligerDialog.confirm('确定要删除信息吗', function (param) {
		if (param) {
			$.ajax({
		        type: "post",
		        url: 'notice/WkrjNoticeController/deleNotice',
		        data: { notice_id: ides.join(",") },
		        cache: false,
		        async: false,
		        dataType : "json",  
		        type : "POST",
		        success: function (result) {
		          if (result.success) {
		        	  $.ligerDialog.alert(result.msg);
		        	  g.loadData();
		          } else {
		        	  $.ligerDialog.alert(result.msg);
		          }
		        }
		      });
		}
	});
}

/**
 * 添加信息
 */
function noticeManage_add(){
	var g = $("#notice_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		url : "page/syg/notice/notice_add.jsp",
		width : 750,
		height : 450,
		isResize :true,
		id:'zzz',
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				//推荐方式
				var data = dialog.frame.liger.get("noticeManage_Window_addform").getData();
				if (data.notice_title == null || data.notice_title == "") {
					top.$.ligerDialog.alert("请填写标题");
					return;
				}else{
					var title = data.notice_title;
					if(title.length>100){
						top.$.ligerDialog.alert("字数不能超过100字");
						return;
					}
				}
				if (data.notice_content == null || data.notice_content == "") {
					top.$.ligerDialog.alert("请填内容");
					return;
				}else{
					var content = data.notice_content;
					if(content.length>1000){
						top.$.ligerDialog.alert("字数不能超过1000字");
						return;
					}
				}
				//保存
				$.ajax({
			        url: "notice/WkrjNoticeController/addNotice",
			        dataType : "json",  
			        type : "POST",
			        data: data,
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
 * 信息编辑
 */
function noticeManage_edit(index){
	var g = $("#notice_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条信息进行编辑!');
		return;
	}
	var s = parent.$.ligerDialog.open({
		url : "page/syg/notice/notice_update.jsp",
		width : 750,
		height : 450,
		isResize :true,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("noticeManage_Window_updateform").getData();
				if (data.notice_title == null || data.notice_title == "") {
					top.$.ligerDialog.alert("请填写标题");
					return;
				}else{
					var title = data.notice_title;
					if(title.length>100){
						top.$.ligerDialog.alert("字数不能超过100字");
						return;
					}
				}
				if (data.notice_content == null || data.notice_content == "") {
					top.$.ligerDialog.alert("请填内容");
					return;
				}else{
					var content = data.notice_content;
					if(content.length>1000){
						top.$.ligerDialog.alert("字数不能超过1000字");
						return;
					}
				}
				$.ajax({
		            url: "notice/WkrjNoticeController/updateNotice",
		            data: data,
		            dataType : "json",  
		            type : "POST",
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


