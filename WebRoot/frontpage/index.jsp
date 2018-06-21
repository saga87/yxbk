<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=10"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  
 
  
  <script src="frontpage/js/index.js" type="text/javascript"></script>
  <title>医学百科</title>
  <style>
    .font_s16 {
    font-size: 14px;
}

	.wid_75{
		width:71%;
		display:inline-block;
		float:right;
	}
.layui_po{padding:0 0 0 6px;width:820px;}
.d_inb{position:static;left:28px!important;}
.span_more{margin-top:31px;float:right;}
  </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin bor_b">
  <div class="layui-header">
  
    <div class="layui-logo">
	<img src="frontpage/imgs/logo.PNG"  class="ma_r" >
	医学百科
	</div>
         <div  class="layui-nav layui-nav1 layui-layout-left co_33 layui_po">
            <img src="frontpage/imgs/shengyin.png" class="ma_t38">
            <div class="d_inb" id="marquee1" >
               
            </div>
			<span class="span_more"><a href="frontpage/notice_simple.jsp"><img src="frontpage/imgs/32.png"></a></span>
          </div>
          <ul class="layui-nav layui-nav1 layui-layout-right">
            <li class="layui-nav-item">
              <a href="javascript:;" class="co_33">
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
      <div class="side_div">
          <img src="frontpage/imgs/tou.png" class="ma_r">
          <div class="d_inb line_h">
            <p class="line_h "><%=session_user_name%></br></p>
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
      <div class="fl wid_49" >
          <div class="hei bac_38 font_s16 co_ff clearfix padd_20">
            <span>推荐视频</span>
            <a href="frontpage/recomvideo_more.jsp" class="co_ff fr">更多>></a>
          </div>
          <div>
              <ul class="box padd_ba" id="recommendVideo">
                           
              </ul>

          </div>
      </div>
      <div class="fr wid_49" >
         <div class="hei bac_38 font_s16 co_ff clearfix padd_20">
            <span>临床诊疗指南</span>
            <a href="frontpage/clinic_simple.jsp?data_id=02" class="co_ff fr">更多>></a>
          </div>
          <div>
            <ul class="box padd_ba box_right" id="box_right">
                
            </ul>

          </div>
      </div>
      <div class="fr wid_100 mar_top">
        <div class="hei bac_38 font_s16 co_ff clearfix padd_20">
            <span>热门图书</span>
            <a href="frontpage/books_simple.jsp" class="co_ff fr">更多>></a>
          </div>
      </div>
      <div>
          <ul class="box_ull clearfix" id="listHotBooks">
           
        </ul>

      </div>
    </div>
  </div>
  
 
</div>

</body>
</html>