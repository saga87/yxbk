var url="";
var manager;
var video_ueditor;
$(function(){
	lay=$("#video_layout").ligerLayout({});
	manager = $("#video_maingrid").ligerGrid({
		url:'video/WkrjVideoController/listVideo',
        columns: [
        { display: '视频名称', name: 'video_name',  align: 'center' },
        { display: '主讲人', name: 'speaker',   align: 'center' },
        { display: '主讲人单位', name: 'speaker_unit',   align: 'center'},
        { display: '视频分类', name: 'data_name',   align: 'center'},
        { display: '介绍', name: 'content',   align: 'center'},
        { display: '发布时间', name: 'addtime',   align: 'center'},
        { display: '是否推荐', name: 'recommend',   align: 'center',render:function(rowdata, rowindex, value){
        	if (value=="1") {
				return "<font color='green'>是</font>";
			}
        	if (value=="2") {
				return "<font color='red'>否</font>";
			}
        } },
        { display: '推荐时间', name: 'recommendtime',   align: 'center'},
        { display: '播放次数', name: 'playtime',   align: 'center'}
        ], height: '100%',
        width:'99.9%',
        usePager :true,
        alternatingRow: true,
        checkbox : true,
		rownumbers : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : videoManage_add,
				icon : 'add',
				id:'video/WkrjVideoController/addVideo'
			}, {
				line : true
			}, {
				text : '编辑',
				click : videoManage_edit,
				icon : 'modify',
				id:'video/WkrjVideoController/updateVideo'
			}, {
				line : true
			}, {
				text : '删除',
				click : videoManage_delRow,
				icon : 'delete',
				id:'video/WkrjVideoController/deleVideo'
			},{
				text : '查看',
				click : check,
				icon : 'search',
				id:'video/WkrjVideoController/listVideo'
			},{
				text : '设置推荐',
				click : isRecommend,
				icon : 'ok',
				id:'video/WkrjVideoController/updateRecommend'
			},{
				text : '取消推荐',
				click : candleRecommend,
				icon : 'candle',
				id:'video/WkrjVideoController/updateRecommend'
			}]
		},
		onDblClickRow : function (data, rowindex, rowobj) {
				check(data);
		} 
    });
    	
});
//根据条件检索(查询)
function queryVideo() {
	var g = $("#video_maingrid").ligerGetGridManager();
	var video_name = $('#video_name').val();
	g.set({url:'video/WkrjVideoController/listVideo?video_name='+video_name});    
}
//显示全部
function allVideo() {
	var g = $("#video_maingrid").ligerGetGridManager();
	g.set({url:'video/WkrjVideoController/listVideo'});    
}
/*
 * 查看信息详情
 */
function check(rindex){
	var r,g = $("#video_maingrid").ligerGetGridManager();
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
		url : "page/syg/video/video_dealer.jsp",
		width : 750,
		height : 550,
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
/**
 * 取消推荐
 * @param row
 */
function candleRecommend(row){
	var g = $("#video_maingrid").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行操作!');
		return;
	}
	var ides = new Array();
	for (var int = 0; int < r.length; int++) {
		ides.push(r[int].video_id);
	}
	$.ligerDialog.confirm('确定要取消推荐吗？', function (param) {
		if (param) {
			$.ajax({
		        type: "post",
		        url: 'video/WkrjVideoController/updateRecommend',
		        data: { video_id: ides.join(","),recommend:2 },
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
 * 设置推荐视频
 * @param row
 */
function isRecommend(row){
	var g = $("#video_maingrid").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行操作!');
		return;
	}
	var ides = new Array();
	for (var int = 0; int < r.length; int++) {
		ides.push(r[int].video_id);
	}
	$.ligerDialog.confirm('确定要设置推荐吗', function (param) {
		if (param) {
			$.ajax({
		        type: "post",
		        url: 'video/WkrjVideoController/updateRecommend',
		        data: { video_id: ides.join(","),recommend:1 },
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
function videoManage_delRow(row){
	var g = $("#video_maingrid").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行删除!');
		return;
	}
	var ides = new Array();
	for (var int = 0; int < r.length; int++) {
		ides.push(r[int].video_id);
	}
	$.ligerDialog.confirm('确定要删除信息吗', function (param) {
		if (param) {
			$.ajax({
		        type: "post",
		        url: 'video/WkrjVideoController/deleVideo',
		        data: { video_id: ides.join(",") },
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
function videoManage_add(){
	var g = $("#video_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		url : "page/syg/video/video_add.jsp",
		width : 750,
		height : 550,
		isResize :true,
		id:'zzz',
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				//推荐方式
				var data = dialog.frame.liger.get("videoManage_Window_addform").getData();
				if (data.video_name == null || data.video_name == "") {
					top.$.ligerDialog.alert("请填写视频名称");
					return;
				}
				if(dialog.frame.liger.get("data_id")){
					var data_id = dialog.frame.liger.get("data_id").getValue();
					data.data_id = data_id;
				}
				if (data.data_id == null || data.data_id == "") {
					top.$.ligerDialog.alert("请选择视频分类");
					return;
				}
				
				
			
				
				if (data.coverpath == null || data.coverpath == "") {
					data.coverpath = "frontpage/imgs/ncover.png";
				}
				if (data.content == null || data.content == "") {
					
				}else{					
					if (data.content.length>1000) {
						top.$.ligerDialog.alert("字数不能大于1000字");
						return;
					}
				}	
				var name = dialog.frame.$("input[name='file_name']");
				var path = dialog.frame.$("input[name='file_path']");
				var type = dialog.frame.$("input[name='file_type']");
				var file_name = new Array();
				var file_url = new Array();
				var file_type = new Array();
				for(var i=0;i<path.length;i++){
					var nm = $(name[i]).attr("value");    //获取value
				    var url = $(path[i]).attr("value");    //获取value
				    var lb = $(type[i]).attr("value");    //获取value
				    file_url.push(url);
				    file_type.push(lb);
				    file_name.push(nm);
				}
				data.file_name = file_name.join();
			    data.file_path = file_url.join();
			    data.file_type = file_type.join();
				
				if(data.file_path.length==0&&(data.video_path == null || data.video_path == "")){
			    	top.$.ligerDialog.alert("视频路径与附件不能同时为空！");
					return;
			    }
				
				//保存
				$.ajax({
			        url: "video/WkrjVideoController/addVideo",
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
function videoManage_edit(index){
	var g = $("#video_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条信息进行编辑!');
		return;
	}
	var s = parent.$.ligerDialog.open({
		url : "page/syg/video/video_update.jsp",
		width : 750,
		height : 550,
		isResize :true,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("videoManage_Window_updateform").getData();
				if (data.video_name == null || data.video_name == "") {
					top.$.ligerDialog.alert("请填写视频名称");
					return;
				}
				if(dialog.frame.liger.get("data_id")){
					var data_id = dialog.frame.liger.get("data_id").getValue();
					data.data_id = data_id;
				}
				if (data.data_id == null || data.data_id == "") {
					top.$.ligerDialog.alert("请选择视频分类");
					return;
				}
				
				if (data.coverpath == null || data.coverpath == "") {
					data.coverpath = "frontpage/imgs/ncover.png";
				}
				if (data.content == null || data.content == "") {
					
				}else{
					if (data.content.length>1000) {
						top.$.ligerDialog.alert("字数不能大于1000字");
						return;
					}
				}
				var name = dialog.frame.$("input[name='file_name']");
				var path = dialog.frame.$("input[name='file_path']");
				var type = dialog.frame.$("input[name='file_type']");
				var file_name = new Array();
				var file_url = new Array();
				var file_type = new Array();
				for(var i=0;i<path.length;i++){
					var nm = $(name[i]).attr("value");    //获取value
				    var url = $(path[i]).attr("value");    //获取value
				    var lb = $(type[i]).attr("value");    //获取value
				    file_url.push(url);
				    file_type.push(lb);
				    file_name.push(nm);
				}
				data.file_name = file_name.join();
			    data.file_path = file_url.join();
			    data.file_type = file_type.join();
				
				if(data.file_path.length==0&&(data.video_path == null || data.video_path == "")){
			    	top.$.ligerDialog.alert("视频路径与附件不能同时为空！");
					return;
			    }
				
				$.ajax({
		            url: "video/WkrjVideoController/updateVideo",
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


