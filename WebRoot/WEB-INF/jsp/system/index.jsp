<%@page import="wkrjsystem.user.bean.WkrjUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	WkrjUser user = (WkrjUser)request.getSession().getAttribute("user");
	String session_user_deptid = user.getDept_id();
	String session_user_name = user.getUser_realname();
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<meta http-equiv="X-UA-Compatible" content="IE=10"/>
<base href="<%=basePath%>"></base>
<title>医学百科</title>

<script type="text/javascript">	var basePath = "<%=basePath%>";</script>
<script type="text/javascript">	var session_user_deptid = "<%=session_user_deptid%>";</script>
<script type="text/javascript">	var session_user_roler = "<%=session.getAttribute("userRoleId")%>";</script>
<script type="text/javascript" src="plug-in/load_user_js_css.js"></script>
<script type="text/javascript" src="system/js/echarts/echarts.min.js"></script>
<script type="text/javascript">
	var tab;
	var books_name = new Array();
	var books_nums = new Array();
	var video_name = new Array();
	var video_nums = new Array();
	var clinic_name = new Array();
	var clinic_nums = new Array();
	
	
	
	$(function(){
		$.ajax({
               url: "front/web/video/getVideoData",
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
                     for (var i = 0; i < ctt.length; i++) {
						 
				     video_name.push(ctt[i].data_name);
						
                     	
                     $.ajax({
                    url: "front/web/video/countVideos",
                    data: {data_id:ctt[i].data_id},
                    dataType : "json", 
                    async:false, 
                    type : "POST",
                    success: function(res){        
                    		video_nums.push(res+'');
                     	}
               		});   
                     	
                     } 
					videoTypeZx();
					videoTypeZz();
                }
               }); 
		
		
		
		
		
		$.ajax({
               url: "front/web/books/getData",
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
                     for (var i = 0; i < ctt.length; i++) {
						 
				     books_name.push(ctt[i].data_name);
						
                     	
                     $.ajax({
                    url: "front/web/books/countBooks",
                    data: {data_id:ctt[i].data_id},
                    dataType : "json", 
                    async:false, 
                    type : "POST",
                    success: function(res){        
                    		books_nums.push(res+'');
                     	}
               		});   
                     	
                     } 
					
					booksTypeZx();
					booksTypeZz();
                }
               }); 
			   
			   
			   
			   $.ajax({
               url: "front/web/clinic/getData",
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
                     for (var i = 0; i < ctt.length; i++) {
						 
				     clinic_name.push(ctt[i].data_name);
						
                     	
                     $.ajax({
                    url: "front/web/clinic/countClinics",
                    data: {data_id:ctt[i].data_id},
                    dataType : "json", 
                    async:false, 
                    type : "POST",
                    success: function(res){        
                    		clinic_nums.push(res+'');
                     	}
               		});   
                     	
                     } 
					
					clinicTypeZx();
					clinicTypeZz();
                }
               }); 
			   
		
			
		
		
        
        
		
	});
	$(".tabs li").live('contextmenu',function(e){
		        /* 选中当前触发事件的选项卡 */
		        var subtitle =$(this).text();
		        $('#mainTabs').tabs('select',subtitle);
		        //显示快捷菜单
		        $('#menu').menu('show', {
		            left: e.pageX,
		            top: e.pageY
		        });
		        return false;
	});
	$(function(){
		var perms = "${sessionScope.userPermission}";
	});
	
</script>
<style type="text/css">
/*首页样式修改*/
.l-layout-top{border:0px;}
.clearfix:before, .clearfix:after {content: "";display: table;}
.clearfix:after{clear: both;}
.clearfix {*zoom:1;}
-.yxyx{width: 43%;display: inline-block;}
</style>
</head>
<body style="overflow:hidden;">
	 <div id="main_layout" style="width:99.9%;overflow:hidden;">
		<div position="top" style="overflow:hidden;background:url(system/imgs/banner_bg.png) repeat-x;height:87px;">
			<div style="float: left;line-height:60px;font-size:24px;padding-left:20px;color:#fff;">
                                            医学百科
            </div>
			<div style="width:473px;height:85px;position:absolute;right:0px;z-index:1;">
				 <img onClick="password()"
					style="position:relative;left:407px;top:25px;cursor:pointer"
					src="system/imgs/002.png" />
				<a href="wkrjsystem/wkrjlogin/loginout"> <img
					style="position:relative;border:0px;left:412px;top:25px;cursor:pointer"
					src="system/imgs/003.png" /></a>
			</div>
		</div>
		<div position="left" title="<%=session_user_name%>,欢迎您!">
			<div id="leftMenuTree"></div>
		</div>
		<div position="center" id="mainTabs">
		  <div tabid="home" title="首页" style="padding:15px 30px;background: #bed5f3;" >
		        <div class="clearfix yxyx" style="width: 43%;display: inline-block;">
	                <span style="color:#000;position: relative;float:left;">医学影像统计</span>
					<div id="videoTypeZx" style="width:80%; height:200px;float: left;"></div>
					
				</div>
				
				<div class="clearfix yxyx" style="width: 53%;display: inline-block;">
                    <span style="color:#000;position: relative;float:left;">医学影像柱状统计</span>
                    <div id="videoTypeZz" style="width:80%;height:200px;float: left;"></div>
                </div>
				
				
				 <div class="clearfix " style="width: 43%;display: inline-block;">
                    <span style="color:#000;position: relative;float:left;">临床诊疗统计</span>
                    <div id="clinicTypeZx" style="width:80%;height:200px;float: left;"></div>
                </div>
				
				<div class="clearfix" style="width: 53%;display: inline-block;">
                    <span style="color:#000;position: relative;float:left;">临床诊疗柱状统计</span>
                    <div id="clinicTypeZz" style="width:80%;height:200px;float: left;"></div>
                </div>
				
				
				
				<div class="clearfix" style="width: 43%;display: inline-block;">
					
                    <span style="color:#000;position: relative;float:left;">医学图书统计</span>
                    <div id="booksTypeZx" style="width:80%;height:200px;float: left;"></div>
                </div>
		
				
				<div class="clearfix" style="width: 53%;display: inline-block;">
                    <span style="color:#000;position: relative;float:left;">医学图书柱状统计</span>
                    <div id="booksTypeZz" style="width:80%;height:200px;float: left;"></div>
                </div>
				
				
				
	       </div>
		   
		   
		   
				
		  
		   
		</div>
	</div>
</body>
<script type="text/javascript">
function videoTypeZx() {
    var myChart = echarts.init(document.getElementById('videoTypeZx'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['']
        },
        grid:{left:40,right:48},
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: {
            name: '(类别)',
            type: 'category',
            boundaryGap: false, //取消左侧的间距
            data: [],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#000',
                    fontSize: '10'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            }
        },
        yAxis: {
            name: '(数量)',
            type: 'value',
            interval: 150,
            splitLine: {
                show: false
            }, //去除网格线
            axisLine: {
                
                lineStyle: {
                    color: '#000'
                }
            }
        },
        series: [{
            name: '医学影像分类',
            type: 'line',
            symbol: 'circle', //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            symbolSize:8,
            data: [],
            itemStyle: {
                normal: 
                {
                    lineStyle:{color:'#bb3021'}
                    }
                },
        }]
    });
    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
    var names = video_name; //类别数组（实际用来盛放X轴坐标值）    
    var series1 = video_nums;
            myChart.hideLoading(); //隐藏加载动画
            myChart.setOption({ //加载数据图表
               xAxis: {
		        type: 'category',
		        data: names
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: series1,
		        type: 'line'
    }]
            });
};
function clinicTypeZx() {
    var myChart = echarts.init(document.getElementById('clinicTypeZx'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['']
        },
        grid:{left:40,right:48},
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: {
            name: '(类别)',
            type: 'category',
            boundaryGap: false, //取消左侧的间距
            data: [],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#000',
                    fontSize: '10'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            }
        },
        yAxis: {
            name: '(数量)',
            type: 'value',
            interval: 150,
            splitLine: {
                show: false
            }, //去除网格线
            axisLine: {
                
                lineStyle: {
                    color: '#000'
                }
            }
        },
        series: [{
            name: '临床诊疗分类',
            type: 'line',
            symbol: 'circle', //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            symbolSize:8,
            data: [],
            itemStyle: {
                normal: 
                {
                    lineStyle:{color:'#bb3021'}
                    }
                },
        }]
    });
    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
    var names = clinic_name; //类别数组（实际用来盛放X轴坐标值）    
    var series1 = clinic_nums;
            myChart.hideLoading(); //隐藏加载动画
            myChart.setOption({ //加载数据图表
               xAxis: {
                type: 'category',
                data: names
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: series1,
                type: 'line'
    }]
            });
};


function booksTypeZx() {
    var myChart = echarts.init(document.getElementById('booksTypeZx'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['']
        },
        grid:{left:40,right:48},
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: {
            name: '(类别)',
            type: 'category',
            boundaryGap: false, //取消左侧的间距
            data: [],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#000',
                    fontSize: '10'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            }
        },
        yAxis: {
            name: '(数量)',
            type: 'value',
            interval: 150,
            splitLine: {
                show: false
            }, //去除网格线
            axisLine: {
                
                lineStyle: {
                    color: '#000'
                }
            }
        },
        series: [{
            name: '临床诊疗分类',
            type: 'line',
            symbol: 'circle', //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            symbolSize:8,
            data: [],
            itemStyle: {
                normal: 
                {
                    lineStyle:{color:'#bb3021'}
                    }
                },
        }]
    });
    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
    var names = books_name; //类别数组（实际用来盛放X轴坐标值）    
    var series1 = books_nums;
            myChart.hideLoading(); //隐藏加载动画
            myChart.setOption({ //加载数据图表
               xAxis: {
                type: 'category',
                data: names
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: series1,
                type: 'line'
    }]
            });
};





function videoTypeZz() {
    var myChart = echarts.init(document.getElementById('videoTypeZz'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['']
        },
        grid:{left:40,right:48},
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: {
            name: '(类别)',
            type: 'category',
            boundaryGap: true, //取消左侧的间距
            data: [],
            axisLabel: {
				interval:0, //全部显示
				rotate:-30, //倾斜
                show: true,
                textStyle: {
                    color: '#000',
                    fontSize: '10'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            },
			//隐藏坐标轴
			 axisTick: {
          show: false
     }      
        },
        yAxis: {
            name: '(数量)',
            type: 'value',
            interval: 150,
            splitLine: {
                show: false
            }, //去除网格线
            axisLine: {
                
                lineStyle: {
                    color: '#000'
                }
            }
        },
        series: [{
            name: '医学影像分类',
            type: 'bar',
           
            data: [],
           
        }]
    });
    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
    var names = video_name; //类别数组（实际用来盛放X轴坐标值）    
    var series1 = video_nums;
            myChart.hideLoading(); //隐藏加载动画
            myChart.setOption({ //加载数据图表
               xAxis: {
		        type: 'category',
		        data: names
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: series1,
		        type: 'bar'
    }]
            });
};
function clinicTypeZz() {
    var myChart = echarts.init(document.getElementById('clinicTypeZz'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['']
        },
        grid:{left:40,right:48},
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: {
            name: '(类别)',
            type: 'category',
            boundaryGap: true, //取消左侧的间距
            data: [],
            axisLabel: {
				interval:0,
				rotate:-30,
                show: true,
                textStyle: {
                    color: '#000',
                    fontSize: '10'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            },
			//隐藏坐标轴
			 axisTick: {
          show: false
     }      
        },
        yAxis: {
            name: '(数量)',
            type: 'value',
            interval: 150,
            splitLine: {
                show: false
            }, //去除网格线
            axisLine: {
                
                lineStyle: {
                    color: '#000'
                }
            }
        },
        series: [{
            name: '临床诊疗分类',
            type: 'bar',
            symbol: 'circle', //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            symbolSize:8,
            data: [],
            itemStyle: {
                normal: 
                {
                    lineStyle:{color:'#bb3021'}
                    }
                },
        }]
    });
    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
    var names = clinic_name; //类别数组（实际用来盛放X轴坐标值）    
    var series1 = clinic_nums;
            myChart.hideLoading(); //隐藏加载动画
            myChart.setOption({ //加载数据图表
               xAxis: {
                type: 'category',
                data: names
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: series1,
                type: 'bar'
    }]
            });
};


function booksTypeZz() {
    var myChart = echarts.init(document.getElementById('booksTypeZz'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['']
        },
        grid:{left:40,right:48},
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: {
            name: '(类别)',
            type: 'category',
            boundaryGap: true, //取消左侧的间距
            data: [],
            axisLabel: {
				interval:0,
				rotate:-30,
                show: true,
                textStyle: {
                    color: '#000',
                    fontSize: '10'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            },
			//隐藏坐标轴
			 axisTick: {
          show: false
     }      
        },
        yAxis: {
            name: '(数量)',
            type: 'value',
            interval: 150,
            splitLine: {
                show: false
            }, //去除网格线
            axisLine: {
                
                lineStyle: {
                    color: '#000'
                }
            }
        },
        series: [{
            name: '临床诊疗分类',
            type: 'bar',
            symbol: 'circle', //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            symbolSize:8,
            data: [],
            itemStyle: {
                normal: 
                {
                    lineStyle:{color:'#bb3021'}
                    }
                },
        }]
    });
    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
    var names = books_name; //类别数组（实际用来盛放X轴坐标值）    
    var series1 = books_nums;
            myChart.hideLoading(); //隐藏加载动画
            myChart.setOption({ //加载数据图表
               xAxis: {
                type: 'category',
                data: names
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: series1,
                type: 'bar'
    }]
            });
};



















</script>
</html>