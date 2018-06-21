var url="";
var manager;
$(function(){
	lay=$("#clinic_layout").ligerLayout({});
	manager = $("#clinic_maingrid").ligerGrid({
		url:'clinic/WkrjClinicController/listClinics',
        columns: [
        { display: '指南序号', name: 'clinic_id',  align: 'center',hide:true },
        { display: '指南名称', name: 'clinic_name',  align: 'center' },
        { display: '关键词', name: 'keyword',   align: 'center' },
        { display: '类别', name: 'data_name',   align: 'center'},
        { display: '出版时间', name: 'publish_time',   align: 'center',render:function(rowdata, rowindex, value){
            if(value=="1900-01-01"){
                return "";
            }else{
            	return value;
            }
            
             }},
        { display: '中文标题', name: 'chinesetitle',   align: 'center'},
        { display: '制定者', name: 'constitutor',   align: 'center'},
        { display: '出处', name: 'provenance',   align: 'center'},
        { display: '相关指南', name: 'correlationguide',   align: 'center'},
        { display: '临床类型', name: 'clinic_type',   align: 'center'},
        { display: '介绍', name: 'content',   align: 'center'},
        { display: '阅读量', name: 'pageview',   align: 'center'},
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
				click : clinicManage_add,
				icon : 'add',
				id:'clinic/WkrjClinicController/addClinic'
			}, {
				line : true
			}, {
				text : '编辑',
				click : clinicManage_edit,
				icon : 'modify',
				id:'clinic/WkrjClinicController/updateClinic'
			}, {
				line : true
			}, {
				text : '删除',
				click : clinicManage_delRow,
				icon : 'delete',
				id:'clinic/WkrjClinicController/deleteClinic'
			},{
				text : '查看',
				click : check,
				icon : 'search',
				id:'clinic/WkrjClinicController/listClinics'
			}]
		},
		onDblClickRow : function (data, rowindex, rowobj) {
				check(data);
		}
		
    });
	
    	
});


//根据条件检索(查询)
function queryClinic() {
	var g = $("#clinic_maingrid").ligerGetGridManager();
	var clinic_name = $('#clinic_name').val();
	g.set({url:'clinic/WkrjClinicController/listClinics?clinic_name='+clinic_name});    
}
//显示全部
function allClinic() {
	var g = $("#clinic_maingrid").ligerGetGridManager();
	g.set({url:'clinic/WkrjClinicController/listClinics'});    
}


/*
 * 查看信息详情
 */
function check(rindex){
	var r,g = $("#clinic_maingrid").ligerGetGridManager();
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
		url : "page/syg/clinic/clinic_dealer.jsp",
		height: 750,
        width: 550,
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


function clinicManage_delRow(row){
	var g = $("#clinic_maingrid").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行删除!');
		return;
	}
	var ides = new Array();
	for (var int = 0; int < r.length; int++) {
		ides.push(r[int].clinic_id);
	}
	$.ligerDialog.confirm('确定要删除信息吗', function (param) {
		if (param) {
			$.ajax({
		        type: "post",
		        url: 'clinic/WkrjClinicController/deleteClinic',
		        data: { clinic_id: ides.join(",") },
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
function clinicManage_add(){
	var g = $("#clinic_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		url : "page/syg/clinic/clinic_add.jsp",
		width : 750,
		height : 550,
		isResize :true,
		id:'zzz',
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				//推荐方式
				var data = dialog.frame.liger.get("clinicManage_Window_addform").getData();
				
				var publish_time = dialog.frame.$("#publish_time").val();
			    data.publish_time=publish_time;
				
				if (data.publish_time == null || data.publish_time == "") {
					data.publish_time = "1900-01-01";
				}
				
			    
			    if(dialog.frame.liger.get("data_id")){
					var data_id = dialog.frame.liger.get("data_id").getValue();
					data.data_id = data_id;
				}
				
				
				
				if (data.data_id == null || data.data_id == "") {
					top.$.ligerDialog.alert("请选择指南分类");
					return;
				}
			    
				data.pageview = 0;
			    
			    //获取select下拉框的值
			    var clinic_type = dialog.frame.$("[name=clinic_type]").val();
			    data.clinic_type=clinic_type;
			    
				if (data.clinic_name == null || data.clinic_name == "") {
					top.$.ligerDialog.alert("请填写名称");
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
				
				if(data.file_path.length==0&&(data.clinic_path == null || data.clinic_path == "")){
			    	top.$.ligerDialog.alert("指南路径与附件不能同时为空！");
					return;
			    }
				
				//保存
				$.ajax({
			        url: "clinic/WkrjClinicController/addClinic",
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
function clinicManage_edit(index){
	var g = $("#clinic_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条信息进行编辑!');
		return;
	}
	var s = parent.$.ligerDialog.open({
		url : "page/syg/clinic/clinic_update.jsp",
		width : 750,
		height : 550,
		isResize :true,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("clinicManage_Window_updateform").getData();
				
				var publish_time = dialog.frame.$("#publish_time").val();
			    data.publish_time=publish_time;
			    
			    if(dialog.frame.liger.get("data_id")){
					var data_id = dialog.frame.liger.get("data_id").getValue();
					data.data_id = data_id;
				}
			    
			    
			    var clinic_type = dialog.frame.$("[name=clinic_type]").val();
			    data.clinic_type=clinic_type;
			    
				
				if (data.clinic_name == null || data.clinic_name == "") {
					top.$.ligerDialog.alert("请填写名称");
					return;
				}
				
				if (data.coverpath == null || data.coverpath == "") {
					data.coverpath = "frontpage/imgs/ncover.png";
				}
				
				if (data.content == null || data.content == "") {
					
				}else{
//					var content = data.content;
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
				
				if(data.file_path.length==0&&(data.clinic_path == null || data.clinic_path == "")){
			    	top.$.ligerDialog.alert("指南路径与附件不能同时为空！");
					return;
			    }
				
				$.ajax({
		            url: "clinic/WkrjClinicController/updateClinic",
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


