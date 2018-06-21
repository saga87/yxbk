<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/frontpage/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>图书详情</title>
  
<style>
.layui-header .layui_nav .layui-nav-more{ border-top-color: rgba(0, 0, 0, 0.7);top: 38px;right: 0px;}
.layui-header .li_item:hover .layui-nav-more{ border-top-color:rgba(255, 255, 255, 0.7);border-bottom-color: #3877c2;top: 30px;}
.layui-nav .li_item a:hover{color: #3877c2!important}
.layui_nav .li_item{line-height: 80px}
</style>


<script type="text/javascript">


<% 
 String books_id = (String)request.getParameter("books_id");
%>

var cid = '<%=books_id%>';
var books_path = "";

$(function (){

	

		$.ajax({
               url: "front/web/books/getData",
               dataType : "json",  
               type : "POST",
               success: function(ctt){        
                    for (var i = 0; i < 5; i++) {
                     	$("#bookstype").append('<li class="layui-nav-item li_item"><a href="frontpage/books.jsp?data_id='+ctt[i].data_id +'"  class="fl">'+ctt[i].data_name+'</a><span class="span_right"></span></li>');
                     } 
					 $("#bookstype").append('<li class="layui-nav-item li_item"><a href="javascript:;"   class="fl" id="gengduo">更多</a><dl class="dl_child layui_child layui-nav-child" style="left:-350px;" id="gengduo1"></dl></li>');
					 
					 
					for (var i = 5; i < ctt.length; i++) {
                     	$("#gengduo1").append('<dd class="child_left"><a href="frontpage/books.jsp?data_id='+ctt[i].data_id +'" class="">'+ctt[i].data_name+'</a></dd>');
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
                    url: "front/web/books/getBooksById",
                    data: {page:1,rows:10,books_id:cid},
                    dataType : "json",  
                    type : "POST",
                    success: function(data){
                    		var res =   data[0];      
                    		$("#cname").text(res.books_name);
                    		$("#constitutor").text("作者： " +res.books_author);
                    		$("#provenance").text("出版社： " +res.publishinghouse);
                    		$("#publish_time").text("出版时间： " +res.publishing_time);
                    		$("#pageviews").text("浏览：" +res.pageview+"次");
                    		$("#content").text(res.content);
                            $('#cover').attr('src', res.coverpath); 
                            books_path = res.books_path;
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
           <div class="div_nav nav_ul222">
                    <ul class="layui_nav layui_nav2 nav" id="bookstype">
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
            <dd><a href="frontpage/clinic_all.jsp"><span class="span_i"><i class="fa fa-circle fa-lg"></i></span><span class="span_txt">临床诊疗指南</span></a></dd>
          </dl>
        </li>
        
      
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
      <div class="wid_100 mar_top mar_bot">
          <div class=" bac_38 font_s16 co_ff clearfix padd_20 hei">
              <span>详情</span>
          </div>
      </div>
      <div class="bac_ff padd_20 clearfix font_s18 padd_top1 padd_bottom" style="position: relative;    padding: 20px;">
          <img id="cover"  src="frontpage/imgs/ncover.png" class="fl" width="273px" height="406px">
          <div class="fl padd_20 clearfix wid_70" id="people">
            <p class="font_s24 co_33 clearfix " id="cname">
             
            </p>
            <p id="constitutor">制定者：</p>
            <p id="provenance">出处：</p>
            <p id="publish_time">出版时间：</p>
            <p id="pageviews">浏览量：</p>
            <div class=" clearfix">
                <div class="fl">内容简介：</div>
              <div class="fl" style="width: 87%;line-height: 27px;" id="content"></div>
            </div>
            <div class="mar_top3" style="position: absolute;bottom: 25px;">
                <span class="bac_38 co_ff bo_f font_s20" style="cursor: pointer;" onclick="read();">开始阅读</span>
            </div>
            

          </div>
          
      </div>
    
           
    </div>

  </div>
  
 
</div>

<script>




function read(){

	 $.ajax({
        url: "front/web/books/addPageviews",
        data: {books_id:cid},
        dataType : "json",  
        type : "POST",
        success: function(res){}
         }); 
         
	
	if(books_path == "" || books_path == undefined || books_path == null){
		$.ajax({
        url: "front/web/books/getBooksFileById",
        data: {books_id:cid},
        dataType : "json",  
        type : "POST",
        success: function(res){        
              if (res.length>0) {
              			window.open(res[0].file_path);
              		}
              	}
         });
	}else{
		if(books_path.substring(0,1)=="E"){
			books_path = books_path.substring(books_path.indexOf("\\"));
			var nbooks_path = books_path.replace('\\','/');
			window.open("http://"+localIp+":8078/"+nbooks_path);
		}else if(books_path.substring(0,1)=="D"){
			books_path = books_path.substring(books_path.indexOf("/"));
			window.open("http://"+localIp+":8088/examples"+books_path);
		}else{
			alert("该路径文件没找到，请检查路径");
		}
		
	}
	
	   
	
	/* if(books_path!=""){
		window.open("F:/lp/阿里巴巴Android开发手册.pdf");
	}else{
		$.ajax({
        url: "front/web/books/getBooksFileById",
        data: {books_id:cid},
        dataType : "json",  
        type : "POST",
        success: function(res){        
              if (res.length>0) {
              			window.open(res[0].file_path);
              		}
              	}
         });
	} */
	
	
	

	
                        
}

</script>
</body>
</html>