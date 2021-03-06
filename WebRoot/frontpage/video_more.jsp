<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>视频-更多</title>
  
  <style>
.layui-header .layui_nav .layui-nav-more{ border-top-color: rgba(0, 0, 0, 0.7);top: 38px;right: 0px;}
.layui-header .li_item:hover .layui-nav-more{ border-top-color:rgba(255, 255, 255, 0.7);border-bottom-color: #3877c2;top: 30px;}
.layui-nav .li_item a:hover{
  color: #3877c2!important
}

.layui_nav .li_item{line-height: 80px}
</style>
  <script type="text/javascript">
  
  <% 
 String data_id = (String)request.getParameter("data_id");
	%>

var data_id = '<%=data_id%>';
  
  
  var totals=0;
  var pernum = 6;
  
  	$(function (){
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
  	
	
	 $.ajax({
               url: "front/web/clinic/getDataById",//查询分类名不分模块
               data: {data_id:data_id},
               dataType : "json",  
               type : "POST",
               success: function(ctt){  
               	$("#videotypename").text(ctt.data_name);
               	}
                     
               });  
	

		$.ajax({
               url: "front/web/video/listVideos",
               data: {page:1,rows:pernum,data_id:data_id},
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
               		var sum = ctt.length;
                    
                     for (var i = 0; i < sum; i++) {
                     
                     	var cid = ctt[i].video_id;
                     
                     	$("#con").append('<li><a href="frontpage/video_detail.jsp?video_id='+cid+'"><img src='+ctt[i].coverpath+' class="wid_274 lx_img"><div class="text_name co_66 fs_14 div_tj2"><p class="co_387 font_s18">'+ctt[i].video_name+'</p><p class="">主讲：'+ctt[i].speaker+'</p><p>单位：'+ctt[i].speaker_unit+'</p><p>日期：'+ctt[i].addtime+'</p></div></a></li>');
                     	} 
                     
                }
               }); 
               
               $.ajax({
               url: "front/web/video/countVideos",
               data: {data_id:data_id},
               dataType : "json",  
               type : "POST",
               success: function(ctt){  
               		totals = ctt;
					
					
					layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
  
  //总页数低于页码总数
  laypage.render({
    elem: 'demo0'
    ,count: totals //数据总数
    ,limit:pernum
    ,theme: '#3877c2'
    ,jump: function(obj, first){
    //首次不执行
    if(!first){
    
    $("#con").empty();
    $.ajax({
               url: "front/web/video/listVideos",
               data: {page:obj.curr,rows:obj.limit},
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
               		var sum = ctt.length;
                    
                     for (var i = 0; i < sum; i++) {
                     
                     	var cid = ctt[i].video_id;
                     
                     	$("#con").append('<li><a href="frontpage/video_detail.jsp?video_id='+cid+'"><img src='+ctt[i].coverpath+' class="wid_274 lx_img"><div class="text_name co_66 fs_14 div_tj2"><p class="co_387 font_s18">'+ctt[i].video_name+'</p><p class="">主讲：'+ctt[i].speaker+'</p><p>单位：'+ctt[i].speaker_unit+'</p><p>日期：'+ctt[i].addtime+'</p></div></a></li>');
                     	} 
                     	
                     
                }
               }); 
    }
  }
  });
});
					
               	}
                     
               }); 
               
               
      });
               
               
               
  </script>
 
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin bor_b">
  <div class="layui-header">
    <div class="layui-logo"><img src="frontpage/imgs/logo.PNG"  class="ma_r" >医学百科</div>
         <div  class="layui-nav layui-layout-left co_33 margin_top" style="position: relative;">
           <div style="display: inline-block;"><img src="frontpage/imgs/bf.png" class="" style="margin-top:-6px"></div>
                <div class="div_nav nav_ul222">
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
  
  <div class="layui-body top_header score_width">
    <!-- 内容主体区域 -->
    <div  class="main_padding">
      
      <div class="main_top clearfix">
        <div class="main_top_fl main_width fl">
        <div class="fl_top fs fcolor clearfix">
            <div class="fl_top_fl fl" id="videotypename">医学视频</div>
            </div>
          </div>
          <div main_center div_tj>
            <ul class="tj_ul lx_ul zh_ul clearfix" id="con">
                
               
               
            </ul>

          </div>


        
      </div>
    
    <div id="demo0" class="fr"></div>
    </div>
  </div>
  
 
</div>


<script>
  $('#con li').hover(function(){
    $(this).removeClass('xin').siblings().addClass('xin');
    $(this).children('a').addClass('co_387').parent('a').siblings().removeClass('co_387')
  },function(){
     $(this).addClass('xin').siblings().removeClass('xin')
     $(this).children('a').removeClass('co_387').parent('a').siblings().addClass('co_387')
  })



  




</script>
</body>
</html>