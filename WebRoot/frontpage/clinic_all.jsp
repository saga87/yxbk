<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>首页</title>
<style>
.layui-header .layui_nav .layui-nav-more{ border-top-color: rgba(0, 0, 0, 0.7);top: 20px;right: 10px;}
.layui-header .li_item:hover .layui-nav-more{ border-top-color:rgba(255, 255, 255, 0.7);border-bottom-color: #3877c2;top: 12px;}
.layui-nav .li_item a:hover{
  color: #3877c2!important
}
.top_main_ul li{
	width:20%;
}
.layui_nav .li_item{line-height: 45px}
</style>


<script type="text/javascript">
	function search() {
	var clinic_name = $('#vname').val();
	
	
	if (window.navigator.userAgent.indexOf('compatible') != -1) {
        //兼容模式
         window.open("../frontpage/clinic_search.jsp?clinic_name="+clinic_name);
    } else if(window.navigator.userAgent.indexOf('Edge') != -1){
        //判断是否IE的Edge浏览器 
        window.open("frontpage/clinic_search.jsp?clinic_name="+clinic_name);
    }else {
        window.location = "frontpage/clinic_search.jsp?clinic_name="+clinic_name;   
    } 
}
</script>

<script type="text/javascript">


				var func = function(ctt,id) {
    				var sum = ctt.length;
                    		
                          if (sum>0) {
                          for (var i = 0; i < sum; i++) {
                          
                          var path = ctt[i].coverpath;
                          	$("#"+id).append('<li> <a href="frontpage/clinic_detail.jsp?clinic_id='+ctt[i].clinic_id+'"> <img   src=' +path+ '  class="img_a " style="height: 25vh;"> <div class="text_name co_66 fs_14"><p class="co_387 font_s18 yincang">'+ctt[i].clinic_name+'</p><p>制定者：'+ctt[i].constitutor+'</p><p>出处：'+ctt[i].provenance+'</p></div></a></li>');
                          	
                          	} 
                          }
};



$(function (){
			var divid = "topdiv";
			$.ajax({
               url: "front/web/clinic/getData",
               dataType : "json",  
               type : "POST",
               success: function(ctt){

					if(ctt.length<6){
						 for (var i = 0; i < ctt.length; i++) {
                     	$("#clinictype").append('<li class="layui-nav-item li_item"><a href="frontpage/clinic.jsp?data_id='+ctt[i].data_id +'"  class="fl">'+ctt[i].data_name+'</a><span class="span_right"></span></li>');
                     	$("#"+divid).after('<div class="main_center" id='+('div'+i)+'> <div class="fr wid_100 mar_top"><div class="fl_top bac_38 font_s16 co_ff clearfix" >'
                     	+'<span>'+ctt[i].data_name+'</span><a href="frontpage/clinic_more.jsp?data_id='+ctt[i].data_id+'" class="co_ff fr">更多>></a>'
                     	+'</div></div><div><ul class="box_ull zh_ul clearfix"   id='+('ul'+i)+'></ul></div></div>');
                     	
                     $.ajax({
                    url: "front/web/clinic/listClinics",
                    data: {page:1,rows:6,data_id:ctt[i].data_id},
                    dataType : "json", 
                    async:false, 
                    type : "POST",
                    success: function(res){        
                    		func(res,"ul"+i);
                     	}
               		});   
                     	
                     	
                     	divid = "div"+i;
                     } 
					}else{
						for (var i = 0; i < 5; i++) {

					$("#clinictype").append('<li class="layui-nav-item li_item"><a href="frontpage/clinic.jsp?data_id='+ctt[i].data_id +'"  class="fl">'+ctt[i].data_name+'</a><span class="span_right"></span></li>');
                     	$("#"+divid).after('<div class="main_center" id='+('div'+i)+'> <div class="fr wid_100 mar_top"><div class="fl_top bac_38 font_s16 co_ff clearfix" >'
                     	+'<span>'+ctt[i].data_name+'</span><a href="frontpage/clinic_more.jsp?data_id='+ctt[i].data_id+'" class="co_ff fr">更多>></a>'
                     	+'</div></div><div><ul class="box_ull zh_ul clearfix"   id='+('ul'+i)+'></ul></div></div>');
                     	
                     $.ajax({
                    url: "front/web/clinic/listClinics",
                    data: {page:1,rows:6,data_id:ctt[i].data_id},
                    dataType : "json", 
                    async:false, 
                    type : "POST",
                    success: function(res){        
                    		func(res,"ul"+i);
                     	}
               		});   
                     	
                     	
                     	divid = "div"+i;
				}
				
				$("#clinictype").append('<li class="layui-nav-item li_item"><a href="javascript:;"   class="fl" id="gengduo">更多</a><dl class="dl_child layui_child layui-nav-child" style="left:-350px;" id="gengduo1"></dl></li>');
				
				
				
				for (var i = 5; i < ctt.length; i++) {

					$("#gengduo1").append('<dd class="child_left"><a href="frontpage/clinic.jsp?data_id='+ctt[i].data_id +'" class="">'+ctt[i].data_name+'</a></dd>');
					$("#"+divid).after('<div class="main_center" id='+('div'+i)+'> <div class="fr wid_100 mar_top"><div class="fl_top bac_38 font_s16 co_ff clearfix" >'
                     	+'<span>'+ctt[i].data_name+'</span><a href="frontpage/clinic_more.jsp?data_id='+ctt[i].data_id+'" class="co_ff fr">更多>></a>'
                     	+'</div></div><div><ul class="box_ull zh_ul clearfix"   id='+('ul'+i)+'></ul></div></div>');
                     	
                     $.ajax({
                    url: "front/web/clinic/listClinics",
                    data: {page:1,rows:6,data_id:ctt[i].data_id},
                    dataType : "json", 
                    async:false, 
                    type : "POST",
                    success: function(res){        
                    		func(res,"ul"+i);
                     	}
               		});   
                     	
                     	
                     	divid = "div"+i;
					
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
			   
                    
					 
					
                }
               }); 


		$.ajax({
                    url: "front/web/clinic/listClinics",
                    data: {page:1,rows:8},
                    dataType : "json",  
                    type : "POST",
                    success: function(ctt){        
                    		var sum = ctt.length;
                    		
                          if (sum>0) {
                          for (var i = 0; i < sum; i++) {
                          
                          var path = ctt[i].coverpath;
                          
                          	$("#clinic_show").append('<li > <a href="frontpage/clinic_detail.jsp?clinic_id='+ctt[i].clinic_id+'"> <img   src=' +path+ '  class="img_a" style="height: 31vh;"></a><span class="li_span_yy fs2 fcolor">'+ctt[i].clinic_name+'</span></li>');
                          	} 
                          }
                     }
               }); 
               
    
});


</script>


</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin bor_b">
  <div class="layui-header ">
    <div class="layui-logo"><img src="frontpage/imgs/logo.PNG"  class="ma_r" >医学百科</div>
         <div  class="layui-nav layui-layout-left co_33 margin_top">
               <div class="div_nav">
                    <ul class="layui_nav layui_nav2 nav" id="clinictype">
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
      <div class="side_div">
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
            <dd><a href="javascript:;" style="background-color: #f2f2f2;color: #2b5990;"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">临床诊疗指南</span></a></dd>
          </dl>
        </li>
       
      
      </ul>
    </div>
  </div>
  
  <div class="layui-body top_header score_width">
    <!-- 内容主体区域 -->
    <div class="main_padding">
	<div class="div_radio">
			<input type="text" class="inp2" id="vname" name="vname" placeholder="请输入搜索内容" /> 
			<input type="button" class="an" onclick="search();" value="搜索" /> 
		</div>
        <div class="main_top clearfix">
            <div class="main_top_fl main_width fl" id="topdiv">
                  <div class="fl_top fs fcolor clearfix">
                      <div class="fl_top_fl fl">临床诊疗指南</div>
                      <div class="fl_top_fr fr">
                          <a href="frontpage/clinic_more.jsp?data_id=02" class="fcolor">
                              更多>>
                          </a>
                      </div>
                  </div>
                  <div class="fl_top_main" >
                      <ul class="top_main_ul clearfix" id="clinic_show">
                       
                      </ul>
                  </div>
            </div>

    </div>

    </div>
  </div>
  
 
</div>

</body>
</html>