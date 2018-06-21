<%@page import="wkrjsystem.user.bean.WkrjUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	WkrjUser user = (WkrjUser)request.getSession().getAttribute("user");
	String session_user_roler = "";
	String session_user_name = "";
	if(user!=null){
	 session_user_roler = user.getUser_role().get(0).getRole_name();
	 session_user_name = user.getUser_realname();
	}
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
   <base href="<%=basePath%>"></base>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>首页</title>
  <link rel="stylesheet" href="web/layui/css/layui.css">
  <link rel="stylesheet" href="web/font-awesome-4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="web/css/css.css">
  <link rel="stylesheet" href="web/css/all.css">
  <link rel="stylesheet" href="web/css/index.css"> 
  <style>
    .font_s16 {
    font-size: 14px;
}
  </style>
  
  <script src="web/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript">
  	$(function (){

		$.ajax({
               url: "clinic/WkrjClinicController/listClinics",
               data: {page:1,pagesize:10},
               dataType : "json",  
               type : "POST",
               success: function(res){        
               		var sum = JSON.stringify(res.Total);
               		var ctt = res.Rows;
               		
                     if (sum>0) {
                     for (var i = 0; i < sum; i++) {
                     
                     	$("#box_right").append('<li class="clearfix"> <a class="box_right_text d_inb wid_79" href="javascript:void(0)" onclick="getContent(\''+ctt[i].clinic_name+'\');">'+ctt[i].clinic_name+'</a><a class="fr" href="##">'+ctt[i].addtime+'</a></li>');
                     	} 
                     }
                }
               }); 
               
               
               
                $.ajax({
               url: "books/WkrjBooksController/listBooks",
               data: {page:1,pagesize:5},
               dataType : "json",  
               type : "POST",
               success: function(res){        
               		var sum = JSON.stringify(res.Total);
               		var ctt = res.Rows;
               		
                     if (sum>0) {
                     for (var i = 0; i < sum; i++) {
                     var path = ctt[i].coverpath;
         $("#booksul").append('<li> <img src='+path+' width="100%"><div><p class="co_387 font_s18">'+ctt[i].books_name+' </p><p class="co_66">'+ctt[i].content+'</p></div></li>');
                     	} 
                     }
                }
               }); 
               
                $.ajax({
               url: "notice/WkrjNoticeController/listNotice",
               data: {page:1,pagesize:9},
               dataType : "json",  
               type : "POST",
               success: function(res){        
               		var sum = JSON.stringify(res.Total);
               		var ctt = res.Rows;
                     if (sum>0) {
                     for (var i = 0; i < sum; i++) {
                     	$("#noticeul").append('<li><span>'+ctt[i].notice_title+'</span></li>');
                     	} 
                     }
                }
               }); 
               
               
               
               
        });
               
               
            
               
            
              
               
               
               
               
               
               
               
                function getContent(cname)
        {
        		
    		 window.location.href='web/clinic_detail.jsp?clinic_name='+cname;
        }
               
  </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin bor_b">
  <div class="layui-header">
    <div class="layui-logo">医学百科</div>
         <div  class="layui-nav layui-nav1 layui-layout-left co_33">
            <img src="web/imgs/shengyin.png" class="ma_t38">
            <div class="d_inb" id="marquee1">
                <!--  -->
                <ul id="noticeul">
                  
                </ul>
            </div>
          </div>
          <ul class="layui-nav layui-nav1 layui-layout-right">
            <li class="layui-nav-item">
              <a href="web/index.jsp" class="co_33">
                <img src="web/imgs/logo1.png" class="">
                平台首页
              </a>
              
            </li>
            <li class="layui-nav layui-nav1 layui-nav-item ">
              <a href="wkrjsystem/wkrjlogin/loginout" class="co_33">
                <img src="web/imgs/logo2.png" class="ma_r">退出
              </a>
            </li>
          </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <div class="side_div">
          <img src="web/imgs/tou.png" class="ma_r">
          <div class="d_inb line_h">
            <p class="line_h "><%=session_user_name%></br>
          <%=session_user_roler%></p>
          </div>
      </div>
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a href="javascript:;" class="ba_2b">
            <img src="web/imgs/li1.png" class="li_img">
            医学资料
          </a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">医学影像</span></a></dd>
            <dd><a href="javascript:window.location.href='web/books.jsp';"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">医学电子书</span></a></dd>
            <dd><a href="javascript:window.location.href='web/clinic_all.jsp';"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">临床诊疗指南</span></a></dd>
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
            <a href="##" class="co_ff fr">更多>></a>
          </div>
          <div>
              <ul class="box padd_ba">
                  <li class="clearfix">
                      <img src="web/imgs/img1.jpg" class ="fl d_inb padd_5 width_le  ">
                      <div class="font_s18 fl d_inb wid_64">
                          <a href="##" class="co_38 box_right_text wid_95 d_b">主治花生过敏，新药 2 期临床结果积极主治花生过敏</a>
                          <p class="padd_top co_80 font_s16">近日，总部位于美国加州圣地亚哥的 AnaptysBio 公司公布了一项正在进行的 2a 期
                          临床试验的积极中期结果，这项试验评估了在研新药 ANB020 ......</p>
                          <p class="texta_r co_80 font_s16">2018-04-10</p>
                      </div>
                  </li>
                   <li class="clearfix">
                      <img src="web/imgs/img1.jpg" class ="fl d_inb padd_5 width_le  ">
                      <div class="font_s18 fl d_inb wid_64">
                          <a href="##" class="co_38 box_right_text box_right_text wid_95 d_b">主治花生过敏，新药 2 期临床结果积极主治花生过敏</a>
                          <p class="padd_top co_80 font_s16">近日，总部位于美国加州圣地亚哥的 AnaptysBio 公司公布了一项正在进行的 2a 期
                          临床试验的积极中期结果，这项试验评估了在研新药 ANB020 ......</p>
                          <p class="texta_r co_80 font_s16">2018-04-10</p>
                      </div>
                  </li>
                   <li class="clearfix">
                      <img src="web/imgs/img1.jpg" class ="fl d_inb padd_5 width_le  ">
                      <div class="font_s18 fl d_inb wid_64">
                          <a href="##" class="co_38 box_right_text box_right_text wid_95 d_b">主治花生过敏，新药 2 期临床结果积极主治花生过敏</a>
                          <p class="padd_top co_80 font_s16">近日，总部位于美国加州圣地亚哥的 AnaptysBio 公司公布了一项正在进行的 2a 期
                          临床试验的积极中期结果，这项试验评估了在研新药 ANB020 ......</p>
                          <p class="texta_r co_80 font_s16">2018-04-10</p>
                      </div>
                  </li>
              </ul>

          </div>
      </div>
      <div class="fr wid_49" >
         <div class="hei bac_38 font_s16 co_ff clearfix padd_20">
            <span>临床诊疗指南</span>
            <a href="web/clinic_simple.jsp" class="co_ff fr">更多>></a>
          </div>
          <div>
            <ul class="box padd_ba box_right" id="box_right">
               
                
            </ul>

          </div>
      </div>
      <div class="fr wid_100 mar_top">
        <div class="hei bac_38 font_s16 co_ff clearfix padd_20">
            <span>热门图书</span>
            <a href="##" class="co_ff fr">更多>></a>
          </div>
      </div>
      <div>
          <ul class="box_ull clearfix"  id="booksul">
           

        </ul>

      </div>
    </div>
  </div>
  
 
</div>
<script src="web/layui/layui.js"></script>
<script src="web/js/jquery-1.3.1.min.js"></script>
<script src="web/js/kxbdSuperMarquee.js"></script>

<script>
$(function(){
  $('#marquee1').kxbdSuperMarquee({
        distance:186,
        time:3,
        direction:'left'
      });

});
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(demo)', function(elem){
    //console.log(elem)
    layer.msg(elem.text());
  });
});

layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(demo)', function(elem){
    //console.log(elem)
    layer.msg(elem.text());
  });
});
</script>
</body>
</html>