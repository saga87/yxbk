
var url="";
var lay;
var manager;
var perm;
$(function(){

	lay=$("#station_layout").ligerLayout({rightWidth:400,isRightCollapse: true});
	
	manager = $("#station_maingrid").ligerGrid({
		url:'wkrjsystem/wkrjStation/getStationList',
        columns: [
        { display: '岗位名称', name: 'station_name', id: 'station_name', width: '100%', align: 'left' }
        ], height: '100%',
        width:'100%',
        usePager : true,
		rownumbers : true,
        alternatingRow: true, 
        //tree:{columnId:'station_name',idField:'station_id',parentIDField:'station_parent_id'},
		toolbar : {
			items : [ {
				text : '增加',
				click : station_addStation,
				epker:'zxxx',
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : station_editStation,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : station_delRow,
				icon : 'delete'
			} ]
		}
    });
	
});

function station_delRow(row){
	var g = $("#station_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}
	$.ligerDialog.confirm('确定要删除此记录吗', function (param) {
		if (param) {
			$.ajax({
		        //type: "get",
		        url: 'wkrjsystem/wkrjStation/delStation',
		        data: { id: r.station_id },
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
function station_addStation(){
	var g = $("#station_maingrid").ligerGetGridManager();
	parent.$.ligerDialog.open({
		//target: $("#station_addWindow"),
		url : "system/station/station_add.jsp",
		width : 500,
		height : 300,
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("station_addWindow_form").getData();
				
				if (data.station_name == "" || data.station_name == null) {
					top.$.ligerDialog.alert("请输入岗位名称");
					return;
				}
				$.ajax({
		            url: "wkrjsystem/wkrjStation/addStation",
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
function station_editStation(){
	var g = $("#station_maingrid").ligerGetGridManager();
	var r = g.getSelectedRow();
	if (r == undefined)	{
		$.ligerDialog.alert('请选择一条记录!');
		return;
	}

	var s = parent.$.ligerDialog.open({
		url : "system/station/station_update.jsp",
		width : 500,
		height : 300,
		data: {
            content:r
        },
		buttons : [ {
			text : '确定',
			onclick : function(item, dialog) {
				var data = dialog.frame.liger.get("station_updateWindow_form").getData();
				
				if(typeof(data.station_name) == "undefined"){
					data['station_name'] = dialog.frame.$("input[name='station_name']").val();
				}
				
				if (data.station_name == "" || data.station_name == null) {
					top.$.ligerDialog.alert("请输入岗位名称");
					return;
				}
				$.ajax({
		            url: "wkrjsystem/wkrjStation/updateStation",
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
