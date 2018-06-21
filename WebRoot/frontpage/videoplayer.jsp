<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>视频播放页</title>
  <link href="frontpage/css/video_1.css" rel="stylesheet">

  <!-- If you'd like to support IE8 
  <script src="frontpage/js/video_8.js"></script>-->
<style>
.layui-header .layui_nav .layui-nav-more{ border-top-color: rgba(0, 0, 0, 0.7);top: 20px;right: 10px;}
.layui-header .li_item:hover .layui-nav-more{ border-top-color:rgba(255, 255, 255, 0.7);border-bottom-color: #3877c2;top: 12px;}
.layui-nav .li_item a:hover{
  color: #3877c2!important
}

.layui_nav .li_item{line-height: 45px}
</style>
<script type="text/javascript">
<% 
 String video_id = (String)request.getParameter("video_id");
%>
var cid = '<%=video_id%>';
$.ajax({
        url: "front/web/video/addPlayTime",
        data: {video_id:cid},
        dataType : "json",  
        type : "POST",
        success: function(res){
        
        }
         });

var video_path = "";
var videopath;
				$.ajax({
                    url: "front/web/video/getVideoById",
                    data: {video_id:cid},
                    dataType : "json",  
                    type : "POST",
                    success: function(data){
                    		var res =   data[0];
                    		$("#vtitle").text(res.video_name);      
                    		$("#videoname").text(res.video_name);//类别加名字
                    		$("#playtime").text(res.playtime+"次播放");
							video_path = res.video_path;
							
							if(video_path == "" || video_path == undefined || video_path == null){
								$.ajax({
									url: "front/web/video/getVideoFileById",
									data: {video_id:cid},
									dataType : "json",  
									type : "POST",
									success: function(res){        
										  if (res.length>0) {
													//window.open(res[0].file_path);
													videopath=res[0].file_path;
												 $("#my-video").append('<source src="'+videopath+'" type="video/mp4">');	
												//	$("#my-video").prop("src",videopath);
													$("#my-video").load();
													
												}
											}
                          });
							}else{
								if(video_path.substring(0,1)=="E"){
									video_path = video_path.substring(video_path.indexOf("\\"));
									var nvideo_path = video_path.replace('\\','/');
									video_path = "http://"+localIp+":8078/"+nvideo_path;
									 $("#my-video").append('<source src="'+video_path+'" type="video/mp4">');	
									 $("#my-video").load();
								}else if(video_path.substring(0,1)=="D"){
									video_path = video_path.substring(video_path.indexOf("/"));
									video_path = "http://"+localIp+":8088/examples"+video_path;
									 $("#my-video").append('<source src="'+video_path+'" type="video/mp4">');	
									 $("#my-video").load();
								}else{
									alert("该路径文件没找到，请检查路径文件");
									}
							}
							
							
                     }
               });  
			   
			   
	
			   
               
               
                          
                 $.ajax({
               url: "front/web/video/getVideoData",
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
                    for (var i = 0; i < 5; i++) {
                     	$("#videotype").append('<li class="layui-nav-item li_item"><a href="frontpage/video.jsp?data_id='+ctt[i].data_id +'"  class="fl">'+ctt[i].data_name+'</a><span class="span_right"></span></li>');
                     } 
					 $("#videotype").append('<li class="layui-nav-item li_item"><a href="javascript:;"   class="fl" id="gengduo">更多</a><dl class="dl_child layui_child layui-nav-child" style="left:-350px;" id="gengduo1"></dl></li>');
					 
					 if(ctt.length<5){
						 return;
					 }
					 
					for (var i = 5; i < ctt.length; i++) {
                     	$("#gengduo1").append('<dd class="child_left"><a href="frontpage/video.jsp?data_id='+ctt[i].data_id +'" class="">'+ctt[i].data_name+'</a></dd>');
                     } 
					 
					
					layui.use('element', function(){
					  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
					  
					  //监听导航点击
					  element.on('nav(demo)', function(elem){
						//console.log(elem)
						layer.msg(elem.text());
					  });
					});
                }
               });          
                          
                         

</script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin bor_b">
  <div class="layui-header">
    <div class="layui-logo"><img src="frontpage/imgs/logo.PNG"  class="ma_r" >医学百科</div>
         <div  class="layui-nav layui-layout-left co_33 margin_top">
		 <div style="display: inline-block;"><img src="frontpage/imgs/bf.png" class="" style="margin-top:-6px"></div>
           <div class="div_nav nav_ul222" >
                    <ul class="layui_nav layui_nav2 nav" id="videotype">
                    </ul>
                </div>
            <!-- </div> -->
          </div>
          <ul class="layui-nav layui-nav1 layui-layout-right">
            <li class="layui-nav-item">
              <a href="frontpage/index.jsp" class="co_33">
                <img src="frontpage/imgs/logo1.png" class="">
                平台首页
              </a>
              
            </li>
            <li class="layui-nav layui-nav1 layui-nav-item ">
              <a href="front/login/loginout" class="co_33">
                <img src="frontpage/imgs/logo2.png" class="ma_r">退出
              </a>
            </li>
          </ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <div class="side_div side_width">
          <img src="frontpage/imgs/tou.png" class="ma_r">
          <div class="d_inb line_h">
            <p class="line_h "><%=session_user_name%></p>
          </div>
      </div>
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a href="javascript:;" class="ba_2b">
            <img src="frontpage/imgs/li1.png" class="li_img">
            医学资料
          </a>
          <dl class="layui-nav-child">
            <dd><a href="frontpage/video_all.jsp"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">医学影像</span></a></dd>
            <dd><a href="frontpage/books_all.jsp"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">医学电子书</span></a></dd>
            <dd><a href="frontpage/clinic_all.jsp"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">临床诊疗指南</span></a></dd>
          </dl>
        </li>
      
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;" class="bac_ff">
      <div class="wid_100 mar_top mar_bot">
          <div class=" bac_38 font_s16 co_ff clearfix padd_20 hei ">
              <span id="vtitle"></span>
          </div>
      </div>
      <div class="bac_ff padd_20 clearfix font_s18 ">
      <div class="bac_e6 clearfix mar_top3">
          
          
          
  <video id="my-video" class="video-js video_ys fl" controls preload="auto" width="960" height="480"
   data-setup="{}">
    
    
  </video>
          
          
          <div class="fl bofa" style="width: 35%;">
              <ul id="bofa" class="bofa">
                  <li id="videoname" ></li>
              </ul>
          </div>
          <div class="font_s20 co_33 wid_100 fl padd_20 padd_bottom" id="playtime" style="padding:20px">       
            <span class="co_387 mar_left1">下载</span>
          </div>
      </div>
          
      </div>
     
    </div>

  </div>
  
 
</div>


<script src="frontpage/js/video.js"></script>
</body>
</html>