<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>推荐视频</title>
  <style>
   

	.wid_87{
		width:78%;
		display:inline-block;
	}

  </style>
  <script type="text/javascript">
  
  var totals=0;
  var pernum = 5;
  
  
  	$(function (){

		$.ajax({
		url:'front/web/listNotice',
		type:'post',
		data:{page:1,rows:8},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#marquee1").append('<a style="margin: 15px 15px;" href="frontpage/notice_detail.jsp?notice_id='+data[i].notice_id+'"><span>'+data[i].notice_title+'</span></a>');
				}
			}
			
			$('#marquee1').liMarquee({
				scrollamount:80
      });
		}
	});

		$.ajax({
               url: "front/web/listRecommendVideo",
               data: {page:1,rows:pernum},
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
               		var sum = ctt.length;
               		
                     for (var i = 0; i < sum; i++) {
                     
                     	var cid = ctt[i].video_id;
                     
             $("#con").append('<li class="clearfix"><img src ="'+ctt[i].coverpath +'" class ="fl d_inb padd_5 width_le width_le3"><div class="font_s18 fl d_inb wid_87"><a href="frontpage/video_detail.jsp?video_id='+cid+'"  class="co_38 " >'+ctt[i].video_name+'</a><p class="padd_top co_80">'+ctt[i].content+'</p><p class="texta_r co_80">'+ctt[i].recommendtime+'</p></div></li>');
                     } 
                     
                }
               }); 
               
               
               $.ajax({
               url: "front/web/video/countRecomVideo",
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
               url: "front/web/listRecommendVideo",
               data: {page:obj.curr,rows:obj.limit},
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
               		var sum = ctt.length;
               		
                     for (var i = 0; i < sum; i++) {
                     
                     	var cid = ctt[i].video_id;
                     
             $("#con").append('<li class="clearfix"><img src ="'+ctt[i].coverpath +'" class ="fl d_inb padd_5 width_le width_le3"><div class="font_s18 fl d_inb wid_87"><a href="frontpage/video_detail.jsp?video_id='+cid+'"  class="co_38 " >'+ctt[i].video_name+'</a><p class="padd_top co_80">'+ctt[i].content+'</p><p class="texta_r co_80">'+ctt[i].recommendtime+'</p></div></li>');
                     } 
                     
                }
               }); 
    }}
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
         <div  class="layui-nav layui-nav1 layui-layout-left co_33">
            <img src="frontpage/imgs/shengyin.png" class="ma_t38">
            <div class="d_inb" id="marquee1">
               
            </div>
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
    <div style="padding: 15px;" class="clearfix">
      <div class="fr wid_100">
        <div class="hei bac_38 font_s16 co_ff clearfix padd_20">
            <span>推荐视频</span>
          </div>
      </div>
          <div>
          <ul class="box padd_ba clearfix" id="con" style="margin-top: 68px;">
                
               
               
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