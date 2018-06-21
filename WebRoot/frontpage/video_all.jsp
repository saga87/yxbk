<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <script src="frontpage/js/videoall.js" type="text/javascript"></script>
  <title>普通用户视频</title>
<style>
.layui-header .layui_nav .layui-nav-more{ border-top-color: rgba(0, 0, 0, 0.7);top: 38px;right: 0px;}
.layui-header .li_item:hover .layui-nav-more{ border-top-color:rgba(255, 255, 255, 0.7);border-bottom-color: #3877c2;top: 30px;}
.layui-nav .li_item a:hover{
  color: #3877c2!important
}

.layui_nav .li_item{line-height: 80px}
</style>
<script type="text/javascript">
	function search() {
	var video_name = $('#vname').val();
	
	if (window.navigator.userAgent.indexOf('compatible') != -1) {
        //兼容模式
         window.open("../frontpage/video_search.jsp?video_name="+video_name);
    } else if(window.navigator.userAgent.indexOf('Edge') != -1){
        //判断是否IE的Edge浏览器 
        window.open("frontpage/video_search.jsp?video_name="+video_name);
    }else {
        window.location = "frontpage/video_search.jsp?video_name="+video_name;   
    } 
	
}
</script>


</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin bor_b">
  <div class="layui-header ">
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
            <dd><a href="javascript:;" style="background-color: #f2f2f2;color: #2b5990;"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">医学影像</span></a></dd>
            <dd><a href="frontpage/books_all.jsp"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">医学电子书</span></a></dd>
            <dd><a href="frontpage/clinic_all.jsp"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">临床诊疗指南</span></a></dd>
          </dl>
        </li>
      
      </ul>
    </div>
  </div>
  
  <div class="layui-body top_header score_width">
    <!-- 内容主体区域 -->
    <div class="main_padding" >
		<div class="div_radio">
			<input type="text" class="inp2" id="vname" name="vname" placeholder="请输入搜索内容" /> 
			<input type="button" class="an" onclick="search();" value="搜索" /> 
		</div>
        <div class="main_top clearfix" id="topdiv">
            <div class="main_top_fl main_top_fl1 fl">
                  <div class="fl_top fs fcolor clearfix">
                      <div class="fl_top_fl fl">推荐视频</div>
                      <div class="fl_top_fr fr">
                          <a href="frontpage/recomvideo_more.jsp" class="fcolor">
                              更多>>
                          </a>
                      </div>
                  </div>
                  <div class="fl_top_main box1">
                      <ul class="top_main_ul clearfix" id="recommendVideo">
                       
                      </ul>
                  </div>
            </div>
            <div class="main_top_fr main_top_fl main_top_fl1 margin_left fr">
                  <div class="fl_top fs fcolor clearfix">
                      <div class="fl_top_fl fl">最新视频</div>
                      <div class="fl_top_fr fr">
                         
                      </div>
                  </div>
                  <div class="fr_top_ul box1">
                        <ul class="clearfix"  id="newvideo">
                        </ul>
                  </div>
            </div>
        </div>
      

    </div>
    </div>
  </div>
  
 
</div>

</body>
</html>