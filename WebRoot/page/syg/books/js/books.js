var url="";
var manager;
var books_ueditor;
$(function(){
	lay=$("#books_layout").ligerLayout({});
	manager = $("#books_maingrid").ligerGrid({
		url:'books/WkrjBooksController/listBooks',
        columns: [
        { display: '图书名称', name: 'books_name',  align: 'center' },
        { display: '作者', name: 'books_author',   align: 'center' },
        { display: '出版社', name: 'publishinghouse',   align: 'center'},
        { display: '出版日期', name: 'publishing_time',   align: 'center',render:function(rowdata, rowindex, value){
            if(value=="1900-01-01"){
                return "";
            }else{
            	return value;
            }
            
             }},
        { display: '页码', name: 'pagination',   align: 'center',render:function(rowdata, rowindex, value){
            if(value=="0"){
                return "";
            }else{
            	return value;
            }
            
             }},
        { display: '语言类型', name: 'languagetype',   align: 'center',render:function(rowdata, rowindex, value){
        	if (value=="1") {
				return "中文";
			}
        	if (value=="2") {
				return "外文";
			}
        } },
        { display: '介绍', name: 'content',   align: 'center'},
        { display: '发布时间', name: 'addtime',   align: 'center'},
        { display: '阅读量', name: 'pageview',   align: 'center'}
        ], height: '100%',
        width:'99.9%',
        usePager :true,
        alternatingRow: true,
        checkbox : true,
		rownumbers : true,
		toolbar : {
			items : [ {
				text : '增加',
				click : booksManage_add,
				icon : 'add',
				id:'books/WkrjBooksController/addBooks'
			}, {
				line : true
			}, {
				text : '编辑',
				click : booksManage_edit,
				icon : 'modify',
				id:'books/WkrjBooksController/updateBooks'
			}, {
				line : true
			}, {
				text : '删除',
				click : booksManage_delRow,
				icon : 'delete',
				id:'books/WkrjBooksController/deleBooks'
			},{
				text : '查看',
				click : check,
				icon : 'search',
				id:'books/WkrjBooksController/listBooks'
			}]
		},
		onDblClickRow : function (data, rowindex, rowobj) {
				check(data);
		} 
    });
    	
});
//根据条件检索(查询)
function queryBooks() {
	var g = $("#books_maingrid").ligerGetGridManager();
	var books_name = $('#books_name').val();
	g.set({url:'books/WkrjBooksController/listBooks?books_name='+books_name});    
}
//显示全部
function allBooks() {
	var g = $("#books_maingrid").ligerGetGridManager();
	g.set({url:'books/WkrjBooksController/listBooks'});    
}
/*
 * 查看信息详情
 */
function check(rindex){
	var r,g = $("#books_maingrid").ligerGetGridManager();
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
		url : "page/syg/books/books_dealer.jsp",
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


function booksManage_delRow(row){
	var g = $("#books_maingrid").ligerGetGridManager();
	var r = g.getSelectedRows();
	if (r.length==0)	{
		$.ligerDialog.alert('请选择信息进行删除!');
		return;
	}
	var ides = new Array();
	for (var int = 0; int < r.length; int++) {
		ides.push(r[int].books_id);
	}
	$.ligerDialog.confirm('确定要删除信息吗', function (param) {
		if (param) {
			$.ajax({
		        type: "post",
		        url: 'books/WkrjBooksController/deleBooks',
		        data: { books_id: ides.join(",") },
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
function booksManage_add(){
	var g = $("#books_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		url : "page/syg/books/books_add.jsp",
		width : 750,
		height : 550,
		isResize :true,
		id:'zzz',
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				//推荐方式
				var data = dialog.frame.liger.get("booksManage_Window_addform").getData();
				var publishing_time = dialog.frame.$("#publishing_time").val();
				data.publishing_time=publishing_time;
				if (data.books_name == null || data.books_name == "") {
					top.$.ligerDialog.alert("请填写图书名称");
					return;
				}
//				if (data.books_author == null || data.books_author == "") {
//					top.$.ligerDialog.alert("请填写作者");
//					return;
//				}
//				if (data.publishinghouse == null || data.publishinghouse == "") {
//					top.$.ligerDialog.alert("请填写出版社");
//					return;
//				}

				if(dialog.frame.liger.get("data_id")){
					var data_id = dialog.frame.liger.get("data_id").getValue();
					data.data_id = data_id;
				}
				if (data.data_id == null || data.data_id == "") {
					top.$.ligerDialog.alert("请选择图书分类");
					return;
				}

				data.pageview = 0;

				if (data.publishing_time == null || data.publishing_time == "") {
					data.publishing_time = "1900-01-01";
				}
				if (data.coverpath == null || data.coverpath == "") {
					data.coverpath = "frontpage/imgs/ncover.png";
				}
				if (data.pagination == null || data.pagination == "") {
					data.pagination = "0";
				}
				if (data.languagetype == null || data.languagetype == "") {
					top.$.ligerDialog.alert("请选择语言类型");
					return;
				}
//				if (data.content == null || data.content == "") {
//					top.$.ligerDialog.alert("请填写介绍");
//					return;
//				}
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
			    
			    if(data.file_path.length==0&&(data.books_path == null || data.books_path == "")){
			    	top.$.ligerDialog.alert("图书路径与附件不能同时为空！");
					return;
			    }
			    
				//保存
				$.ajax({
			        url: "books/WkrjBooksController/addBooks",
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
function booksManage_edit(index){
	var g = $("#books_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条信息进行编辑!');
		return;
	}
	var s = parent.$.ligerDialog.open({
		url : "page/syg/books/books_update.jsp",
		width : 750,
		height : 550,
		isResize :true,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("booksManage_Window_updateform").getData();
				var publishing_time = dialog.frame.$("#publishing_time").val();
				data.publishing_time=publishing_time;
				if (data.books_name == null || data.books_name == "") {
					top.$.ligerDialog.alert("请填写图书名称");
					return;
				}
//				if (data.books_author == null || data.books_author == "") {
//					top.$.ligerDialog.alert("请填写作者");
//					return;
//				}
//				if (data.publishinghouse == null || data.publishinghouse == "") {
//					top.$.ligerDialog.alert("请填写出版社");
//					return;
//				}

				if(dialog.frame.liger.get("data_id")){
					var data_id = dialog.frame.liger.get("data_id").getValue();
					data.data_id = data_id;
				}
				if (data.data_id == null || data.data_id == "") {
					top.$.ligerDialog.alert("请选择图书分类");
					return;
				}

				if (data.publishing_time == null || data.publishing_time == "") {
					data.publishing_time = "1900-01-01";
				}
				if (data.coverpath == null || data.coverpath == "") {
					data.coverpath = "frontpage/imgs/ncover.png";
				}
				if (data.pagination == null || data.pagination == "") {
					data.pagination = "0";
				}
				if (data.languagetype == null || data.languagetype == "") {
					top.$.ligerDialog.alert("请选择语言类型");
					return;
				}
//				if (data.content == null || data.content == "") {
//					top.$.ligerDialog.alert("请填写介绍");
//					return;
//				}
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
			    
			    if(data.file_path.length==0&&(data.books_path == null || data.books_path == "")){
			    	top.$.ligerDialog.alert("图书路径与附件不能同时为空！");
					return;
			    }
			    
				$.ajax({
		            url: "books/WkrjBooksController/updateBooks",
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


